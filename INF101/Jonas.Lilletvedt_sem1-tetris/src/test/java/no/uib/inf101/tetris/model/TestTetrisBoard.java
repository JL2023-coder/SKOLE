package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;

public class TestTetrisBoard {

    @Test
public void prettyStringTest() {
  TetrisBoard board = new TetrisBoard(3, 4);
  board.set(new CellPosition(0, 0), 'g');
  board.set(new CellPosition(0, 3), 'y');
  board.set(new CellPosition(2, 0), 'r');
  board.set(new CellPosition(2, 3), 'b');
  String expected = String.join("\n", new String[] {
      "g--y",
      "----",
      "r--b"
  });
  assertEquals(expected, board.prettyString());
}

@Test
// my own test
// check if return true 
// check if problems occur
public void valueExistInRowTest(){
  TetrisBoard board = new TetrisBoard(3, 4);
  board.set(new CellPosition(0, 0), 'g');
  board.set(new CellPosition(0, 1), 'y');
  board.set(new CellPosition(0, 2), 'r');
  board.set(new CellPosition(0, 3), 'b');
  String expected = String.join("\n", new String[] {
      "gyrb",
      "----",
      "----"
  });

  assertTrue(!board.valueExistInRow(0, '-'));
  assertTrue(board.valueExistInRow(1, '-'));
  assertTrue(board.valueExistInRow(2, '-'));

  assertEquals(expected, board.prettyString());

}


@Test
// my own test
//check if problmes occur
// checks if set value to rows
public void setRowToValueTest(){
  TetrisBoard board = new TetrisBoard(4, 4);
  board.set(new CellPosition(0, 0), 'g');
  board.set(new CellPosition(1, 1), 'y');
  board.set(new CellPosition(2, 2), 'r');
  board.set(new CellPosition(3, 3), 'b');
  String expectedBefore = String.join("\n", new String[] {
      "g---",
      "-y--",
      "--r-",
      "---b"
  });
  assertEquals(expectedBefore, board.prettyString());


  board.setRowToValue(0,'-');

  String expectedAfter = String.join("\n", new String[] {
    "----",
    "-y--",
    "--r-",
    "---b"
  });
    
  assertEquals(expectedAfter, board.prettyString());
}



@Test
// my own test
// check if problmes occur
// checks if it copy rows
// check if shift rows to new place
public void copyRowTest(){
  TetrisBoard board = new TetrisBoard(4, 4);
  board.set(new CellPosition(1, 0), 'g');
  board.set(new CellPosition(1, 3), 'y');

  board.set(new CellPosition(2, 2), 'g');
  board.set(new CellPosition(2, 3), 'g');

  board.set(new CellPosition(3, 0), 'g');
  board.set(new CellPosition(3, 1), 'y');
  board.set(new CellPosition(3, 2), 'g');
  board.set(new CellPosition(3, 3), 'y');
  
  String expectedBefore = String.join("\n", new String[] {
      "----",
      "g--y",
      "--gg",
      "gygy"
  });

  assertEquals(expectedBefore, board.prettyString());

  board.setRowToValue(3, '-');

  board.copyRow(3);
  String expectedAfter = String.join("\n", new String[] {
    "----",
    "----",
    "g--y",
    "--gg"
  });

  assertEquals(expectedAfter, board.prettyString());
}

@Test
// my own test
// check if problmes occur
// checks if remove rows
// chech if copy rows
// check if shift rowes to new position
public void removeFullRowsTest(){
  TetrisBoard board = new TetrisBoard(4, 4);
  board.set(new CellPosition(0, 0), 'g');
  board.set(new CellPosition(0, 3), 'y');

  board.set(new CellPosition(2, 2), 'g');
  board.set(new CellPosition(2, 3), 'g');

  board.set(new CellPosition(3, 0), 'g');
  board.set(new CellPosition(3, 1), 'y');
  board.set(new CellPosition(3, 2), 'g');
  board.set(new CellPosition(3, 3), 'y');

  String expectedBefore = String.join("\n", new String[] {
    "g--y",
    "----",
    "--gg",
    "gygy"
});

board.removeFullRows();
}


@Test
public void testRemoveFullRows() {
  // Tester at fulle rader fjernes i brettet:
  // -T
  // TT
  // LT
  // L-
  // LL

  TetrisBoard board = new TetrisBoard(5, 2);
  board.set(new CellPosition(0, 1), 'T');
  board.set(new CellPosition(1, 0), 'T');
  board.set(new CellPosition(1, 1), 'T');
  board.set(new CellPosition(2, 1), 'T');
  board.set(new CellPosition(4, 0), 'L');
  board.set(new CellPosition(4, 1), 'L');
  board.set(new CellPosition(3, 0), 'L');
  board.set(new CellPosition(2, 0), 'L');

  assertEquals(3, board.removeFullRows());

  String expected = String.join("\n", new String[] {
    "--",
    "--",
    "--",
    "-T",
    "L-"
  });
  assertEquals(expected, board.prettyString());
}


@Test
// my own test
public void testRemoveFullRows0() {
  // Tester at fulle rader fjernes i brettet:
  // TT
  // TT
  // LT
  // L-
  // LL


  TetrisBoard board = new TetrisBoard(5, 2);
  board.set(new CellPosition(0, 1), 'T');
  board.set(new CellPosition(0, 0), 'T');
  board.set(new CellPosition(1, 0), 'T');
  board.set(new CellPosition(1, 1), 'T');
  board.set(new CellPosition(2, 1), 'T');
  board.set(new CellPosition(4, 0), 'L');
  board.set(new CellPosition(4, 1), 'L');
  board.set(new CellPosition(3, 0), 'L');
  board.set(new CellPosition(2, 0), 'L');

  assertEquals(4, board.removeFullRows());

  String expected = String.join("\n", new String[] {
    "--",
    "--",
    "--",
    "--",
    "L-"
  });
  assertEquals(expected, board.prettyString());
}


    
}
