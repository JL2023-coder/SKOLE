### csv2db\n\nThis script imports CSV data into an SQLite database.

If it was not downloaded directly from git, it may not have preserved the executable 
permission. You can run the following command to give it the permission: 
"chmod +x csv_insert_to_db_new.py" in the terminal.

This script reads a CSV file and inserts the values into a SQLite database. It assumes that the CSV file has a header with with the colum names, if not you can add this manually. It also assumes that the SQLite database has tables with the same names as the CSV columns.
Lastly you will either need to change the path to the CSV file and the path of
the database to match your own file paths.
