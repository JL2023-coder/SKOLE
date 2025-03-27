import sqlite3
import csv

path_db = "bysykkel_new.db"
path_csv = "bysykkel.csv"
con = sqlite3.connect(path_db)
cur = con.cursor()

def get_tables():
    result = []
    cur.execute("SELECT name FROM sqlite_master WHERE type='table';")
    tables = cur.fetchall()

    for t in tables:
        result.append(t[0])
    return result

def get_attributes_from_table(table):
    attributes = []
    cur.execute(f"SELECT * FROM {table} LIMIT 1;")
    for attribute in cur.description:
        attributes.append(attribute[0])
    return attributes

def get_column_types(table):
    types = {}
    cur.execute(f"PRAGMA table_info({table});")
    for col in cur.fetchall():
        types[col[1]] = col[2].upper()  # col[1] is name, col[2] is type
    return types

def convert_value(value, sqlite_type):
    if value == '':
        return None
    
    try:
        if sqlite_type == 'INTEGER':
            return int(value)
        elif sqlite_type == 'REAL':
            return float(value)
        elif sqlite_type == 'TEXT':
            return str(value)
        elif sqlite_type == 'DATETIME':
            return value
        else:
            return str(value)  # Default to string for unknown types
    except ValueError:
        return None

def formatter(attributes):
    streng = ""
    for attribute in attributes:
         streng += f"\"{attribute}\"" + ","
    return streng[:-1]

def read_CSV():
    with open(path_csv, "r") as f:
        reader = csv.reader(f)
        return list(reader)
    
# Returns a tuple
# [0] for attributes
# [1] for values
def get_all_values_in_row(index, attributes):
    values = []
    nyeAt = []
    head = read_CSV()
    
    # Get the table name from the first attribute (assuming it's in format table.column)
    table_name = attributes[0].split('.')[0]
    column_types = get_column_types(table_name)
    
    for i in range(0, len(head[0])):
        if head[0][i] in attributes:
            nyeAt.append(head[0][i])
            # Get the SQLite type for this column
            sqlite_type = column_types.get(head[0][i], 'TEXT')
            # Convert the value based on the SQLite type
            value = convert_value(head[index][i], sqlite_type)
            values.append(value)
    return tuple((nyeAt, values))

def get_rows():
    head = read_CSV()
    count = 0
    for line in head:
        count += 1
    return count




def insert_to_table(table):
    columns = get_attributes_from_table(table)
    columns = formatter(columns)

    size = get_rows()
    row_index = 1
    while row_index < size:
        try:
            # Get attributes and values for the current row
            attributes, values = get_all_values_in_row(row_index, get_attributes_from_table(table))
            
            # Skip if all values are None
            if all(value is None for value in values):
                row_index += 1
                continue
            
            # Create placeholders for the values
            placeholders = ','.join(['?' for _ in values])
            
            # Create the SQL query with placeholders using INSERT OR REPLACE
            query = f"INSERT OR REPLACE INTO {table} ({','.join(attributes)}) VALUES ({placeholders})"
            
            # Execute the query with values as parameters
            cur.execute(query, values)
            con.commit()

        except Exception as e:
            print(f"Error inserting row {row_index}: {str(e)}")
            print(f"Values: {values}")  # Print the values for debugging
        row_index += 1

def insert_to_all_tables():
    tables = get_tables()
    for t in tables:
        insert_to_table(f"\"{t}\"")

if __name__ == "__main__":
    insert_to_all_tables()
    input("\nPress Enter to exit...")

