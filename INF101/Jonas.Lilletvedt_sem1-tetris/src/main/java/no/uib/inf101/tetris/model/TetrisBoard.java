package no.uib.inf101.tetris.model;

import java.util.Arrays;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;

public class TetrisBoard extends Grid<Character> {
    // instance variables
    private Character[][] grid;


    // constructor
    public TetrisBoard(int rows, int cols) {
        super(rows, cols);
        this.grid = new Character[rows][cols]; 
        
        for (int row = 0; row < grid.length; row ++){
            for (int col = 0; col < grid[row].length; col ++){
                set(new CellPosition(row, col), '-');
            }}
    }

    // @return grid in string
    public String prettyString() {
        // start string -> nothing
        String string = "";

        // finds value for each cell
        for (int row = 0; row < grid.length; row ++){
            for (int col = 0; col < grid[row].length; col ++){

                // adds the char to the string from each cell
                string += ((get(new CellPosition(row, col))));
            }

            // breaks when through all cells
            if (row == grid.length -1){break;}

            // adds a line when it have gone through a row
            string += "\n";
        }
        return string;
    }

    // removes full rows
    // copy the rows above full rows and moves them down
    public int removeFullRows(){
        // counter -> amount of full rows
        int count = 0;

        // goes through every row from the bottom
        for (int rows = rows()-1; rows >= 0; rows--){

            // checks if row is full
            if (!valueExistInRow(rows, '-')){
                // increase counter by one
                count++;
                
                // set full row to '-' -> my null value
                setRowToValue(rows, '-');

                // copy and moves rows down
                copyRow(rows);

                // add one to check the row after a row have benn moved down
                rows ++;
            }
        }

        return count;
    }

    // help method
    // @return true, if value exists in row
    public boolean valueExistInRow(int row, char value){


        // for loop thorugh hole row
        for (int col = 0; col < cols(); col++){


            // value does exist in row
            if (get(new CellPosition(row, col)) == value){
                return true;
            }
        }
        // value does not exist in row
        return false;
    }


    // help method
    // sets all cells to a value
    public void setRowToValue(int row, char value){
        // for loop thorugh hole row
        for (int col = 0; col < cols(); col++){

            // sets all cells in row to value
            set(new CellPosition(row, col), value);
        }
    }

    // help method
    // copies all values in a row 
    // moves every row above removed row deon
    public void copyRow(int removedRow){
        // start from the removedRow
        // goes through every row above, and moves them down one
        for (int row = removedRow; row >= 1; row--){
        
            // goes through row
            for (int col = 0; col < cols(); col++){

                // gives the row belove the value to the row above
                set(new CellPosition(row, col), get(new CellPosition(row-1, col)));
            }
        }
        setRowToValue(0, '-');
    }
}
