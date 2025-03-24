package no.uib.inf101.tetris.model;

import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.grid.*;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel{
    // instance variables
    private TetrisBoard board;
    private TetrominoFactory factory;
    private Tetromino fallingTetromino;
    private GameState gameState;

    // constructor
    public TetrisModel(TetrisBoard board, TetrominoFactory factory) {
        this.board = board;
        this.factory = factory;
        this.fallingTetromino = factory.getNext().shiftedToTopCenterOf(getDimension()); // cells for a new tetromino centered at the top with rnd shape
        this.gameState = GameState.ACTIVE_GAME; // initial state
    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return board;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnFallingTetromino() {
        return fallingTetromino;
    }

    @Override
    // moves the tetromino using the shiftedBy method
    // @return true if the move was possible, else false
    // if false, the tetromino does not move
    public boolean moveTetromino(int deltaRow, int deltaCol) { 
        Tetromino movedTetromino = fallingTetromino.shiftedBy(deltaRow, deltaCol);
        if (legalMove(movedTetromino)){
            fallingTetromino = movedTetromino;
        }
        return legalMove(movedTetromino);
    }


    @Override
    // rotates the tetromino using the rotateTetromino method
    public void rotateTetromino() {
        Tetromino rotatedTetromino = fallingTetromino.rotateTetromino();

        // if false, the tetromino does not move
        if(legalMove(rotatedTetromino)){

            // if true, fallingTetromino gets rotated shape
            fallingTetromino = rotatedTetromino;
        }
    }

    
    
    // help method to check if the move was accepted
    public boolean legalMove(Tetromino movedTetromino){
        int boardRows = getDimension().rows();
        int boardCols = getDimension().cols();

        // modified parts of TestTetromino
        for(GridCell<Character> cell : movedTetromino){
            CellPosition position = cell.pos();
            if (position.row() < 0 || position.col() < 0 || position.row() >= boardRows || position.col() >= boardCols || board.get(position) != '-'){
                return false;
            } 
    }
    return true;
        
}

    @Override
    // drops the tetromino
    // @return true if drop was possible
    // if faslse does not move
    public void dropTetromino() {
        while(moveTetromino(1, 0)){}
        stickTetromino();

        // remove all full rows before dropping new tetromino
        board.removeFullRows();

        // call to -> newFallingTetromino -> gives new fallingTetromino
        newFallingTetromino();
    }

    // help method
    // drops new falling tetromino
    // if position of new falling tetromino is illegal -> gameState = GameState.GAME_OVER
    public void newFallingTetromino(){
        this.fallingTetromino = factory.getNext().shiftedToTopCenterOf(getDimension());
        if (!legalMove(this.fallingTetromino)){this.gameState = GameState.GAME_OVER;
    }
    }
    
    // help method
    // iterates over the tetromino and gives the cells a new value
    // makes a call to -> newFallingTetromino -> gives new fallingTetromino
    public void stickTetromino(){
        
        // goes through the grid/cells to the fallingTetromino
        for (GridCell<Character> cell : this.fallingTetromino) {
            
            // if the cell has a different value then '-' -> standard
            // set the cell to a new value
            if (cell.value() != '-'){board.set(cell.pos(), cell.value());
            }
        }
    }

    @Override
    // return gameState value
    public GameState getGameState() {
        return this.gameState;
    }

    // @return miliseconds per tick
    @Override
    public int getMilisecondsPerTick() {
        return 1000;
    }

    @Override
    // method called each time the clock ticks
    // makes the tetromino drop
    public void clockTick() {
        if(!moveTetromino(1,0)){
            dropTetromino();
        }
    }
}


 
