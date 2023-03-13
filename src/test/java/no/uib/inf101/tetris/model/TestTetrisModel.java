package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.tetromino.PatternedTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TestTetrisModel {

    @Test
    public void initialPositionOfO() {
      TetrisBoard board = new TetrisBoard(20, 10);
      TetrominoFactory factory = new PatternedTetrominoFactory("O");
      ViewableTetrisModel model = new TetrisModel(board, factory);
    
      List<GridCell<Character>> tetroCells = new ArrayList<>();
      for (GridCell<Character> gc : model.getFallingTile()) {
        tetroCells.add(gc);
      }
    
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
      for (GridCell<Character> gc : model.getFallingTile()) {
        tetroCells.add(gc);
      }
    
      assertEquals(4, tetroCells.size());
      assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 3), 'I')));
      assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'I')));
      assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'I')));
      assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 6), 'I')));
    }

    @Test
    public void testMoveTetrominoSuccess() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("O");
        TetrisModel tetrisModel = new TetrisModel(board, factory);
        assertTrue(tetrisModel.moveTetromino(1, 0));
    }

    @Test
    public void testMoveTetrominoSuccessChangesCellPositions() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("O");
        TetrisModel tetrisModel = new TetrisModel(board, factory);
        List<CellPosition> originalPositions = new ArrayList<>();
        for (GridCell<Character> cell : tetrisModel.getFallingTile()) {
            originalPositions.add(cell.pos());
        }
        
        // beveg teromino nedover med en rad
        assertTrue(tetrisModel.moveTetromino(1, 0));
    
        // sjekk at posisjon til cellene i den fallende brikken er endret
        List<CellPosition> newPositions = new ArrayList<>();
        for (GridCell<Character> cell : tetrisModel.getFallingTile()) {
            newPositions.add(cell.pos());
        }
        assertNotEquals(originalPositions, newPositions);
    }
    
    @Test
    public void testMoveTetrominoOutOfBounds() {
      TetrisBoard board = new TetrisBoard(20, 10);
      TetrominoFactory factory = new PatternedTetrominoFactory("I");
      TetrisModel model = new TetrisModel(board, factory);
      int initialRow = model.getFallingTile().iterator().next().pos().row();
      int initialCol = model.getFallingTile().iterator().next().pos().col();
  
      // Try to move tetromino out of bounds
      boolean result = model.moveTetromino(0, -initialCol - 1);
  
      // Assert that no changes were made
      assertEquals(initialRow, model.getFallingTile().iterator().next().pos().row());
      assertEquals(initialCol, model.getFallingTile().iterator().next().pos().col());
      assertFalse(result);
  }


  @Test
  public void testMoveTetrominoOccupiedCell() {
    TetrisBoard board = new TetrisBoard(20, 10);
    TetrominoFactory factory = new PatternedTetrominoFactory("I");
    TetrisModel model = new TetrisModel(board, factory);

    board.set(new CellPosition(10, 5), '-');

    assertFalse(model.moveTetromino(0, 5));
    
  }
  //Gjør ferdig denne testen
  @Test
  public void testRotateTetrominoSuccess() {
      TetrisBoard board = new TetrisBoard(20, 10);
      TetrominoFactory factory = new PatternedTetrominoFactory("I");
      TetrisModel model = new TetrisModel(board, factory);

      model.moveTetromino(1, 0);
      assertTrue(model.rotateTetromino());

      List<GridCell<Character>> tetroCells = new ArrayList<>();
      for (GridCell<Character> gc : model.getFallingTile()) {
        tetroCells.add(gc);
      }



      assertEquals(4, tetroCells.size());
      assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'I')));
      assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 5), 'I')));
      assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(2, 5), 'I')));
      assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(3, 5), 'I')));
    }




  @Test
  public void testDropTetromino() {
    TetrisBoard board = new TetrisBoard(20, 10);
    TetrominoFactory factory = new PatternedTetrominoFactory("I");
    TetrisModel model = new TetrisModel(board, factory);
  
    // Drop the first tetromino
    assertTrue(model.dropTetromino());
    assertNotNull(model.getFallingTile());
    
    // Drop the second tetromino
    assertTrue(model.dropTetromino());
    assertNotNull(model.getFallingTile());
  }

  @Test
  void testClockTick() {
      TetrisBoard board = new TetrisBoard(20, 10);
      TetrominoFactory factory = new PatternedTetrominoFactory("I");
      TetrisModel model = new TetrisModel(board, factory);
      Tetromino initialTetromino = model.getFallingTile();
  
      // Test that the initial tetromino is in the expected position
      List<CellPosition> expectedPositions = List.of(
          new CellPosition(0, 3),
          new CellPosition(0, 4),
          new CellPosition(0, 5),
          new CellPosition(0, 6)
      );
      List<CellPosition> actualPositions = new ArrayList<>();
      for (GridCell<Character> cell : initialTetromino) {
          actualPositions.add(cell.pos());
      }
      assertEquals(expectedPositions, actualPositions);
  
      // Move the tetromino down one row and test that it has moved
      model.clockTick();
      Tetromino movedTetromino = model.getFallingTile();
      expectedPositions = List.of(
          new CellPosition(1, 3),
          new CellPosition(1, 4),
          new CellPosition(1, 5),
          new CellPosition(1, 6)
      );
      actualPositions = new ArrayList<>();
      for (GridCell<Character> cell : movedTetromino) {
          actualPositions.add(cell.pos());
      }
      assertEquals(expectedPositions, actualPositions);
  }

}