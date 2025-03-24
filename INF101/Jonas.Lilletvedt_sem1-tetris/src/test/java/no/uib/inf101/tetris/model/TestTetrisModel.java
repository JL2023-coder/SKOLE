package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.tetromino.PatternedTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;


public class TestTetrisModel {

    @Test
public void initialPositionOfO() {
  TetrisBoard board = new TetrisBoard(20, 10);
  TetrominoFactory factory = new PatternedTetrominoFactory("O");
  ViewableTetrisModel model = new TetrisModel(board, factory);

  List<GridCell<Character>> tetroCells = new ArrayList<>();
  for (GridCell<Character> gc : model.getTilesOnFallingTetromino()) {
    tetroCells.add(gc);
  }
  System.out.println(tetroCells);

  assertEquals(4, tetroCells.size());
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'O')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'O')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'O')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 5), 'O')));
}



@Test
public void initialPositionOfI() {
  TetrisBoard board = new TetrisBoard(20, 10);
  TetrominoFactory factory = new PatternedTetrominoFactory("I");
  ViewableTetrisModel model = new TetrisModel(board, factory);

  List<GridCell<Character>> tetroCells = new ArrayList<>();
  for (GridCell<Character> gc : model.getTilesOnFallingTetromino()) {
    tetroCells.add(gc);
  }

  assertEquals(4, tetroCells.size());
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 3), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 6), 'I')));
}




@Test
// made by me
// checks if method moveTetromino return true if moved, false otherwise
public void movedTetrominoTest() {
  TetrisBoard board = new TetrisBoard(20, 10);
  TetrominoFactory factory = new PatternedTetrominoFactory("T");
  TetrisModel model = new TetrisModel(board, factory);

  // start position at the top middel of the board (0,5)
  // all positions except between (0,0) and (19,9) should be legal and give true
  
  // checks if true
  // moves tetromino from (0,5) -> (5,8)
  assertTrue(model.moveTetromino(5, 3));

  List<GridCell<Character>> tetroCells = new ArrayList<>();
  for (GridCell<Character> gc : model.getTilesOnFallingTetromino()) {
    tetroCells.add(gc);
  }

  // checks if the tetromino was moved
  // position should be (5,7)
  assertEquals(4, tetroCells.size());
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(5, 6), 'T')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(5, 7), 'T')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(5, 8), 'T')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(6, 7), 'T')));
}


@Test
// made by me
// checks if method moveTetromino return false if move not possible
// checks if the tetromino was moved, it should not
public void movedTetrominoTestFalse() {
  TetrisBoard board = new TetrisBoard(20, 10);
  TetrominoFactory factory = new PatternedTetrominoFactory("T");
  TetrisModel model = new TetrisModel(board, factory);

  // start position at the top middel of the board (0,5)
  // all positions except between (0,0) and (19,9) should be legal and give true
  
  // checks if true
  // moves tetromino from (0,5) -> (5,18)
  assertFalse(model.moveTetromino(5, 12));

  List<GridCell<Character>> tetroCells = new ArrayList<>();
  for (GridCell<Character> gc : model.getTilesOnFallingTetromino()) {
    tetroCells.add(gc);
  }

  // checks if the tetromino was moved
  // position should be (0,5) since it should not move
  assertEquals(4, tetroCells.size());
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 3), 'T')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'T')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'T')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'T')));
}

@Test
// made by me
// checks if tetromino was dropped
// checks if new falling tetromino was made
public void dropTetrominoTest(){
  TetrisBoard board = new TetrisBoard(20, 10);
  TetrominoFactory factory = new PatternedTetrominoFactory("T");
  TetrisModel model = new TetrisModel(board, factory);

  // start position at the top middel of the board (0,5)

  // drops tetromino
  // sticks tetromino -> set new cell values
  // new falling tetromino
  model.dropTetromino();


  // checks board
  List<GridCell<Character>> BoardCells = new ArrayList<>();
  for (GridCell<Character> gc : model.getTilesOnBoard()) {
    BoardCells.add(gc);
  }
  // checks if the tetromino was dropped
  // position should be (18,5)
  assertEquals(200, BoardCells.size());
  assertTrue(BoardCells.contains(new GridCell<>(new CellPosition(18, 3), 'T')));
  assertTrue(BoardCells.contains(new GridCell<>(new CellPosition(18, 4), 'T')));
  assertTrue(BoardCells.contains(new GridCell<>(new CellPosition(18, 5), 'T')));
  assertTrue(BoardCells.contains(new GridCell<>(new CellPosition(19, 4), 'T')));
  
  
  // checks tetromino
  List<GridCell<Character>> tetroCells = new ArrayList<>();
  for (GridCell<Character> gc : model.getTilesOnFallingTetromino()) {
    tetroCells.add(gc);
  }
  // checks if new tetromino was created
  // position should be (0,5)
  assertEquals(4, tetroCells.size());
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 3), 'T')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'T')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'T')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'T')));

}

@Test
public void clockTickTest() {
  TetrisBoard board = new TetrisBoard(20, 10);
  TetrominoFactory factory = new PatternedTetrominoFactory("T");
  TetrisModel model = new TetrisModel(board, factory);

  // start position at the top middel of the board (0,5)

  
  // moves tetromino down (0,5) -> (1,5)
  model.clockTick();

  List<GridCell<Character>> tetroCells = new ArrayList<>();
  for (GridCell<Character> gc : model.getTilesOnFallingTetromino()) {
    tetroCells.add(gc);
  }

  // checks if the tetromino was moved
  // position should be (5,7)
  assertEquals(4, tetroCells.size());
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 3), 'T')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'T')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 5), 'T')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(2, 4), 'T')));
}



}



