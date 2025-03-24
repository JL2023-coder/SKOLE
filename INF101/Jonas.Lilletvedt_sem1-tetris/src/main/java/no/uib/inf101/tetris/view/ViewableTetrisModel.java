package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

public interface ViewableTetrisModel {
    // @return the GridDimenison
    GridDimension getDimension();
    
    // @return a Iterable<GridCell<Character>> used to iterate through the grid/cells
    Iterable<GridCell<Character>> getTilesOnBoard();

    // @return a Iterable<GridCell<Character>> used to iterate through the grid/cells
    Iterable<GridCell<Character>> getTilesOnFallingTetromino();

    // @return GameState
    GameState getGameState();


}
