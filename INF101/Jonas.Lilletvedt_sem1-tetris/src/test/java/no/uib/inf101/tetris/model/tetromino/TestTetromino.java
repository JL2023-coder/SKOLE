package no.uib.inf101.tetris.model.tetromino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.tetromino.Tetromino;

public class TestTetromino {
    @Test
public void testHashCodeAndEquals() {
  Tetromino t1 = Tetromino.newTetromino('T');
  Tetromino t2 = Tetromino.newTetromino('T');
  Tetromino t3 = Tetromino.newTetromino('T').shiftedBy(1, 0);
  Tetromino s1 = Tetromino.newTetromino('S');
  Tetromino s2 = Tetromino.newTetromino('S').shiftedBy(0, 0);

  assertEquals(t1, t2);
  assertEquals(s1, s2);
  assertEquals(t1.hashCode(), t2.hashCode());
  assertEquals(s1.hashCode(), s2.hashCode());
  assertNotEquals(t1, t3);
  assertNotEquals(t1, s1);
}

@Test
public void tetrominoIterationOfT() {
  // Create a standard 'T' tetromino placed at (10, 100) to test
  Tetromino tetro = Tetromino.newTetromino('T');
  tetro = tetro.shiftedBy(10, 100);

  // Collect which objects are iterated through
  List<GridCell<Character>> objs = new ArrayList<>();
  for (GridCell<Character> gc : tetro) {
    objs.add(gc);
  }

  // Check that we got the expected GridCell objects
  assertEquals(4, objs.size());
  assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 100), 'T')));
  assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'T')));
  assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'T')));
  assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'T')));
}


@Test  // test made by me
public void tetrominoIterationOfs() {
  // Create a standard 'S' tetromino placed at (10, 100) to test
  Tetromino tetro = Tetromino.newTetromino('S');
  tetro = tetro.shiftedBy(10, 100);

  // Collect which objects are iterated through
  List<GridCell<Character>> objs = new ArrayList<>();
  for (GridCell<Character> gc : tetro) {
    objs.add(gc);
  }

  // Check that we got the expected GridCell objects
  assertEquals(4, objs.size());
  assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'S')));
  assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'S')));
  assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 100), 'S')));
  assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'S')));
}

@Test  // test made by me
public void tetrominoShiftedByDouble() {
  // Create a standard 'S' tetromino placed at (10, 100) to test
  Tetromino tetro = Tetromino.newTetromino('S');
  tetro = tetro.shiftedBy(10, 100);
  
  // shifted by once
  tetro = tetro.shiftedBy(1, 2);

  // shifted by twice
  tetro = tetro.shiftedBy(1, 2);

  List<GridCell<Character>> objs = new ArrayList<>();
  for (GridCell<Character> gc : tetro) {
    objs.add(gc);
  }

    // Check that we got the expected GridCell objects
    assertEquals(4, objs.size());
    assertTrue(objs.contains(new GridCell<>(new CellPosition(13, 105), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(13, 106), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(14, 104), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(14, 105), 'S')));

 
}


@Test  // test made by me
public void tetrominoShiftedToTopOfCenter4() {
  // 4*4 test
  Tetromino tetro1 = Tetromino.newTetromino('I');

  GridDimension gd4 = new Grid<>(20, 10);

  // Shifted to top of center of GridDimenison
  tetro1 = tetro1.shiftedToTopCenterOf(gd4);

  List<GridCell<Character>> objs = new ArrayList<>();
  for (GridCell<Character> gc : tetro1) {
    objs.add(gc);
  }

  // Check that we got the expected GridCell objects
  assertEquals(4, objs.size());
  assertTrue(objs.contains(new GridCell<>(new CellPosition(0, 3), 'I')));
  assertTrue(objs.contains(new GridCell<>(new CellPosition(0, 4), 'I')));
  assertTrue(objs.contains(new GridCell<>(new CellPosition(0, 5), 'I')));
  assertTrue(objs.contains(new GridCell<>(new CellPosition(0, 6), 'I')));


}
@Test  // test made by me
public void tetrominoShiftedToTopOfCenter3() {
  // 3*3 test
  Tetromino tetro2 = Tetromino.newTetromino('T');

  GridDimension gd3 = new Grid<>(20,10);


  // Shifted to top of center of GridDimenison
  tetro2 = tetro2.shiftedToTopCenterOf(gd3);

  List<GridCell<Character>> objs = new ArrayList<>();
  for (GridCell<Character> gc : tetro2) {
    objs.add(gc);
  }

  // Check that we got the expected GridCell objects
  assertEquals(4, objs.size());
  assertTrue(objs.contains(new GridCell<>(new CellPosition(0, 3), 'T')));
  assertTrue(objs.contains(new GridCell<>(new CellPosition(0, 4), 'T')));
  assertTrue(objs.contains(new GridCell<>(new CellPosition(0, 5), 'T')));
  assertTrue(objs.contains(new GridCell<>(new CellPosition(1, 4), 'T')));
}


@Test  // test made by me
// checks if we return a new tetromino rotated clockwise compared to the prevoius one
public void tetrominoRotatedTestT() {
  // Create a standard 'T' tetromino placed at (10, 10) to test
  Tetromino tetro = Tetromino.newTetromino('T');
  tetro = tetro.shiftedBy(10, 10);

  // rotated once, clockwise
  tetro = tetro.rotateTetromino();

  List<GridCell<Character>> objs = new ArrayList<>();
  for (GridCell<Character> gc : tetro) {
    objs.add(gc);
  }

    // Check that we got the expected GridCell objects
    assertEquals(4, objs.size());
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 10), 'T')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(10, 11), 'T')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 11), 'T')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 11), 'T')));

 
}

@Test  // test made by me
// checks if we return a new tetromino rotated clockwise compared to the prevoius one
public void tetrominoRotatedTestI() {
  // Create a standard 'I' tetromino placed at (10, 10) to test
  Tetromino tetro = Tetromino.newTetromino('I');
  tetro = tetro.shiftedBy(10, 10);

  // rotated once, clockwise
  tetro = tetro.rotateTetromino();

  List<GridCell<Character>> objs = new ArrayList<>();
  for (GridCell<Character> gc : tetro) {
    objs.add(gc);
  }

    // Check that we got the expected GridCell objects
    assertEquals(4, objs.size());
    assertTrue(objs.contains(new GridCell<>(new CellPosition(10, 12), 'I')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 12), 'I')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 12), 'I')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(13, 12), 'I')));

 
}



}




    

