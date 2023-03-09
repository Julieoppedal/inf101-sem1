package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;

public class TetrisBoard extends Grid<Character> {
    public TetrisBoard(int rows, int cols) {
        super(rows, cols, '-');

    }

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
            if (value == gridCell.value() && rows == gridCell.pos().row()) {
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

