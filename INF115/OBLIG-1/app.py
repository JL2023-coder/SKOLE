import sqlite3
import pandas as pd

from shiny import App, ui, render


def getTable(table_name):
    # Open connection to database
    con = sqlite3.connect('bysykkel.db')

    # Use parameterized query to prevent SQL injection
    query = "SELECT * FROM ?"
    
    # Execute query with the table name
    df = pd.read_sql_query(query, con, params=(table_name))
    
    # Close the connection
    con.close()

    return df




# Sample DataFrame
user_df = getTable("user")

bike_df = pd.DataFrame({
    "Name": ["Alice", "Bob", "Charlie"],
    "Age": [25, 30, 35],
    "City": ["New York", "Los Angeles", "Chicago"]
})

subscription_df = pd.DataFrame({
    "Name": ["Alice", "Bob", "Charlie"],
    "Age": [25, 30, 35],
    "City": ["New York", "Los Angeles", "Chicago"]
})


app_ui = ui.page_fluid(
    # Add CSS for background color
    ui.tags.style("""
        body {
            background-color: #f0f0f0;  /* Ligh gray */
        }
    """),
    
    ui.h2("BYSYKKEL DATABSE"),

    ui.h3("USER TABLE"),
    ui.output_data_frame("user_table"),

    ui.h3("BIKE TABLE"),
    ui.output_data_frame("bike_table"),

    ui.h3("SUBSCRIPTION TABLE"),
    ui.output_data_frame("subscription_table")
)

def server(input, output, session):
    @output
    @render.data_frame
    def user_table():
        return user_df
    
    @output
    @render.data_frame
    def bike_table():
        return bike_df
    
    @output
    @render.data_frame
    def subscription_table():
        return subscription_df

app = App(app_ui, server)
