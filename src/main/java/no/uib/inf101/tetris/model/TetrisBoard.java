package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;

/**

The TetrisBoard class represents the game board for the Tetris game. It extends the Grid class and is

used to keep track of the current state of the game. The board is a grid of characters, where each

character represents a block in the game.
*/

public class TetrisBoard extends Grid<Character> {

/**

Constructs a new TetrisBoard object with the specified number of rows and columns. The initial value
for each cell in the board is '-'.
@param rows the number of rows in the board
@param cols the number of columns in the board
*/

    public TetrisBoard(int rows, int cols) {
        super(rows, cols, '-');

    }


/**

Returns a string representation of the board. Each character in the string represents a block in the
game, and the string is formatted to display the board in a readable way.
@return a string representation of the board
*/
    public String prettyString() {
        StringBuilder sb = new StringBuilder();
    
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                sb.append(get(new CellPosition(i, j)));
            }
            if (i < rows() - 1) { // Sjekker om vi er på den siste raden
                sb.append("\n");
            }
        }
    
        return sb.toString();
    }


    private boolean elementInRow(int rows, Character value) {
        for(GridCell<Character> gridCell: this) {
            if (rows == gridCell.pos().row() && gridCell.value() == '-') {
                return true;
            }
        }
        return false;
    }
    
    private void rowValue(int row, Character value) {
        for (int col = 0; col < cols(); col++) {
            set(new CellPosition(row, col), value);
        }
    }

    private void moveRow(int oldrow, int newrow) {
        for(int x = 0; x < this.cols(); x++) {
            set(new CellPosition(newrow, x), get(new CellPosition(oldrow, x)));
        }
    }


/**

Removes all full rows from the board, and returns the number of rows that were removed.
@return the number of full rows that were removed from the board
*/
    public int removeFullRows() {
        int count = 0;
        int a = this.rows() - 1; 
        int b = this.rows() - 1;
    
        while (a >= 0) {
            if (elementInRow(a, '-') == false) {  // sjekk om raden er full
                count++;
                b = a;
                while (b > 0) {  // flytt alle rader over denne ned en rad
                    moveRow(b-1, b);
                    b--;
                }
                rowValue(0, '-');  // legg til en ny rad på toppen
            } else {
                a--;
            }
        }
    
        return count;
    }

}

