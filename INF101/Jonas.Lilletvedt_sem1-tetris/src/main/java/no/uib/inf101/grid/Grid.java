package no.uib.inf101.grid;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class Grid <E> implements IGrid<E> {
    
    // instance variables
    private int rows;
    private int cols;
    private E[][] grid;

    // public constructor
    public Grid(int rows, int cols, E defaultValue){
        this.rows = rows;
        this.cols = cols;

        // new grid
        this.grid = (E[][]) new Object[rows][cols];  //https://stackoverflow.com/questions/2927391/whats-the-reason-i-cant-create-generic-array-types-in-java

        // fills each cekll in grid with the default value
        for (E[] row : grid) {
            Arrays.fill(row, defaultValue);
        }
    }

    public Grid(int rows, int cols){
        this(rows, cols, null);
    }

    



    @Override
    public int rows() {
        return rows;
    }

    @Override
    public int cols() {
        return cols;
    }

    @Override // copied from lab4
    public Iterator<GridCell<E>> iterator() {
        List<GridCell<E>> cellColors = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                CellPosition pos = new CellPosition(row, col);
                E color = get(pos);
                cellColors.add(new GridCell<E>(pos, color));
            }
        }
        return cellColors.iterator();

    }

    @Override
    // @return value in cell
    public E get(CellPosition pos) {
        int row = pos.row();
        int col = pos.col();

        // if position does not exist
        // @return Out of bounds
        if (positionIsOnGrid(pos) == false){
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        else{
            return grid[row][col];
        }
    }

    @Override
    // sets new value to cell
    public void set(CellPosition pos, E value) {
        int row = pos.row();
        int col = pos.col();

        if (positionIsOnGrid(pos) == false){
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        else{
            grid[row][col] = value;
        }
        
    }

    @Override
    // @return True if positions exist on grid
    // false otherwise
    public boolean positionIsOnGrid(CellPosition pos) {
        int row = pos.row();
        int col = pos.col();

        return !(row < 0 || row >= rows || col <0 || col >= cols);
    }
    
}
