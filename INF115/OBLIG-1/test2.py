
import sqlite3
import csv

path_db = "OBLIG-1/bysykkel.db"
path_csv = "OBLIG-1/bysykkel.csv"
con = sqlite3.connect(path_db)
cur = con.cursor()

def get_tables():
    result = []
    cur.execute("SELECT name FROM sqlite_master WHERE type='table';")
    tables = cur.fetchall()

    for t in tables:
        result.append(t[0])
    return result

def get_attribute_from_table(table):
    attributes = []
    cur.execute(f"SELECT * FROM {table} LIMIT 1;")
    for attribute in cur.description:
        attributes.append(attribute[0])
    return attributes

def convert_to_string(value):
    return str(value)

def formatter_a(attributes):
    streng = ""
    for attribute in attributes:
        streng += attribute +","
    return streng[:-1]

def formatter_v(attributes):
    streng = ""
    for attribute in attributes:
        try: 
            a = int(attribute)
            streng += attribute +","
        except:
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
    nyeAt= []
    head = read_CSV()
    i = 1
    run = True
    for i in range(0,len(head[0])):
        if head[0][i] in attributes:
            nyeAt.append(head[0][i])
            values.append(head[index][i])
    return tuple((nyeAt,values))


def insert_to_table(table):
    columns = get_attribute_from_table(table)
    print(len(columns))
    columns = formatter_a(columns)

    size = len(get_all_values_in_row(0, columns))
    row_index = 1
    print(size)
    while row_index < size:
        try:
            # List of attributes to be inserted
            attributes_to_be_inserted = get_all_values_in_row(row_index, get_attribute_from_table("user"))[0]
            # Change to correct format for insertion
            attributes_to_be_inserted = formatter_a(attributes_to_be_inserted)
            # List of attributes to be inserted
            values_to_be_inserted = get_all_values_in_row(row_index, get_attribute_from_table("user"))[1]
            # Change to correct format for insertion
            values_to_be_inserted =formatter_v(values_to_be_inserted)

            print(f"INSERT INTO {table} ({attributes_to_be_inserted}) VALUES({values_to_be_inserted})")
            cur.execute(f"INSERT INTO {table} ({attributes_to_be_inserted}) VALUES({values_to_be_inserted})")

            con.commit()
        except:
            pass
        row_index += 1

insert_to_table("user")
