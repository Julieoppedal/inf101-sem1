package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;

public class TestTetrisBoard {

  private TetrisBoard getTetrisBoardWithContents(String[] strings) {
    TetrisBoard board = new TetrisBoard(strings.length, strings[0].length());
    for (int i = 0; i < strings.length; i++) {
      for (int j = 0; j < strings[i].length(); j++) {
        board.set(new CellPosition(i, j), strings[i].charAt(j));
      }
    }
    return board;
  }



    
  @Test
  public void testRemoveFullRows() {
    TetrisBoard board = getTetrisBoardWithContents(new String[] {
      "-T",
      "TT",
      "LT",
      "L-",
      "LL"
    });
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
    public void testRemoveFullRowsKeepBottomRow() {
    TetrisBoard board = getTetrisBoardWithContents(new String[] {
    "--",
    "--",
    "--",
    "-T",
    "L-"
    });
    assertEquals(0, board.removeFullRows());
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
    public void testRemoveFullRowsRemoveTopRow() {
    TetrisBoard board = getTetrisBoardWithContents(new String[] {
    "--",
    "--",
    "--",
    "--",
    "TT"
    });
    assertEquals(1, board.removeFullRows());
    String expected = String.join("\n", new String[] {
    "--",
    "--",
    "--",
    "--",
    "--"
    });
    assertEquals(expected, board.prettyString());
    }

    @Test
    public void testRemoveFullRowsDifferentWidth() {
    TetrisBoard board = getTetrisBoardWithContents(new String[] {
    "--T",
    "--T",
    "--T",
    "--T",
    "--T",
    "--T"
    });
    assertEquals(6, board.removeFullRows());
    String expected = String.join("\n", new String[] {
    "----",
    "----",
    "----",
    "----"
    });
    assertEquals(expected, board.prettyString());
    }
    
}
