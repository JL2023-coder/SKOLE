import sqlite3
import pandas as pd

from shiny import reactive
from shiny.express import input, ui, render


def getTable(table_name):
    # Whitelist
    whitelist = {"user", "bike", "subscription"}
    if table_name not in whitelist:
        return "ERROR: Wrong table name"
    # Open connection to database
    con = sqlite3.connect('bysykkel.db')

    # Use parameterized query to prevent SQL injection
    query = f"SELECT * FROM {table_name}"
    
    # Execute query with the table name
    df = pd.read_sql_query(query, con)
    
    # Close the connection
    con.close()

    return df


with ui.sidebar(bg="#f8f8f8"):  
    "Sidebar" 
    ui.input_action_button("user", "USER")  
    ui.input_action_button("bike", "BIKE")
    ui.input_action_button("subscription", "SUBSRIPTION")

    @render.text()
    @reactive.event(input.action_button)
    def counter():
        return f"{input.action_button()}" 

"Main content"  


ui.h1("BYSYKKEL DATABSE:)")
@reactive.effect
@reactive.event(input.user)
@render.data_frame
def render_table1():

    table_df = getTable()
    return table_df

