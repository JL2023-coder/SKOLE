package no.uib.inf101.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.lang.model.util.TypeKindVisitor14;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

public class Tetromino implements Iterable<GridCell<Character>> {
    
    // instance variables
    private char type;
    private boolean[][] shape;
    private CellPosition pos;
    
    // private constructor
    private Tetromino(char type, boolean[][] shape, CellPosition pos) {
        this.type = type;
        this.shape = shape;
        this.pos = pos;
    }


    // method moving the Tetromino to a new position decided by the previous position 
    // and two new variables 'deltarow' and 'deltacol', which are added to the previous position
    // @return a new Tetromino with equal shape and type to the previous with the new position
    public Tetromino shiftedBy(int deltaRow, int deltaCol) {
        CellPosition newPos = new CellPosition(pos.row() + deltaRow, pos.col() + deltaCol);
        return new Tetromino(type, shape, newPos);
    }

    // method moving the Tetromino so the center is at the top of the grid
    // @return a new Tetromino with equal shape and type but now centered at the top of the grid
    public Tetromino shiftedToTopCenterOf(GridDimension gridDimension) {
        // finds center column
        int deltaCol = (gridDimension.cols() - shape[0].length) / 2;

        // newposition
        CellPosition newCenterPos = new CellPosition(-1, deltaCol);
        return new Tetromino(type, shape, newCenterPos);
    }

    // rotates the tetromino clockwise, with equal center position
    // @return a new tetromino rotated once
    public Tetromino rotateTetromino(){
        // finds width and height of grid
        int oldHeight = shape.length;
        int oldWidth = shape[0].length;

        // new width and height is opposite
        // since all our shapes are square, this isnt necessary,
        // however it makes more sense, since we are rotating the grid
        boolean[][] rotatedShape = new boolean[oldWidth][oldHeight];

        // finds new position after rotation
        for (int oldRow = 0; oldRow < rotatedShape.length; oldRow++){
            for (int oldCol = 0; oldCol < rotatedShape[0].length; oldCol++){

                // rotated position
                int rotatedRow = oldCol;
                int rotatedCol = oldHeight - 1 - oldRow;

                // give the rotated position the previous value
                rotatedShape[rotatedRow][rotatedCol] = shape[oldRow][oldCol];
            }
        }
        // @return a new Tetromino, equal to the previous except rotated clockwise once
        return new Tetromino(type, rotatedShape, pos);
    }

    // package private, contains Tetromino shapes, each type has its own shape
    // first row only contains false, to easily turn the shapes later
    // @return a new Tetromino object with postion(0,0)
    static Tetromino newTetromino(char type) {
        boolean[][] shape;
        switch (type) {
            case 'I':
                shape = new boolean[][]{
                        {false, false, false, false},
                        {true, true, true, true},
                        {false, false, false, false},
                        {false, false, false, false}
                };
                break;
            case 'O':
                shape = new boolean[][]{
                    {false, false, false, false},
                    {false, true, true, false},
                    {false, true, true, false},
                    {false, false, false, false}
                };
                break;
            case 'T':
                shape = new boolean[][]{
                        {false, false, false},
                        {true, true, true},
                        {false, true, false}
                };
                break;
            case 'S':
                shape = new boolean[][]{
                        {false, false, false},
                        {false, true, true},
                        {true, true, false}
                };
                break;
            case 'Z':
                shape = new boolean[][]{
                    {false, false, false},
                    {true, true, false},
                    {false, true, true}
                };
                break;
            case 'J':
                shape = new boolean[][]{
                        {false, false, false},
                        {true, false, false},
                        {true, true, true}
                };
                break;
            case 'L':
                shape = new boolean[][]{
                        {false, false, false},
                        {true, true, true},
                        {true, false, false}
                };
                break;
            default:
                throw new IllegalArgumentException("Ukjent symbol: " + type);
        }
        return new Tetromino(type, shape, new CellPosition(0, 0));
    }



    @Override
    public Iterator<GridCell<Character>> iterator() {
        List<GridCell<Character>> cellList = new ArrayList<>();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col]) {
                    CellPosition position = new CellPosition(pos.row() + row, pos.col() + col);
                    cellList.add(new GridCell<Character>(position, type));
                }
            }
        }

        return cellList.iterator();    
}


@Override
    public int hashCode() {
        return Objects.hash(type, Arrays.deepHashCode(shape), pos);
    }

@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Tetromino other = (Tetromino) obj;
        return type == other.type && Arrays.deepEquals(shape, other.shape)
                && Objects.equals(pos, other.pos);
    }

}
