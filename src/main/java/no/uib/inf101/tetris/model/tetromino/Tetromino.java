package no.uib.inf101.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

public class Tetromino implements Iterable<GridCell<Character>> {
    private final char symbol;
    private final boolean[][] shape;
    CellPosition pos;

    private Tetromino(char symbol, boolean[][] shape, CellPosition pos) {
        this.symbol = symbol;
        this.shape = shape;
        this.pos = pos;

    }
    
    static Tetromino newTetromino(char symbol) {
        boolean[][] shape;
        switch(symbol) {
            case 'I':
                shape = new boolean[][] {
                    { false, false, false, false },
                    { true, true, true, true },
                    { false, false, false, false },
                    { false, false, false, false }
                };
                break;
            case 'J':
                shape = new boolean[][] {
                    { false, false, false },
                    { true, true, true },
                    { false, false, true }
                };
                break;
            case 'L':
                shape = new boolean[][] {
                    { false, false, false },
                    { true, true, true },
                    { true, false, false }
                };
                break;
            case 'O':
                shape = new boolean[][] {
                    {false, false, false,false},
                    {false,true, true,false}, 
                    {false, true, true, false},
                    { false, false,false,false }
                };
                break;
            case 'S':
                shape = new boolean[][] {
                    { false, false, false },
                    { false, true, true },
                    { true, true, false }
                };
                break;
            case 'Z':
                shape = new boolean[][] {
                    { false, false, false },
                    { true, true, false },
                    { false, true, true }
                };
                break;
            case 'T':
                shape = new boolean[][] {
                    { false, false, false },
                    {true, true, true},
                    {false,true,false}
                };
                break;
            default: throw new IllegalArgumentException("No available shape for '" + symbol + "'");
        }
        return new Tetromino(symbol, shape, new CellPosition(0, 0));
    }

    public Tetromino shiftedBy(int deltaRow, int deltaCol) {
        CellPosition newPosition = new CellPosition(pos.row() + deltaRow, pos.col() + deltaCol);
        return new Tetromino(symbol, shape, newPosition);
    }

    public Tetromino shiftedToTopCenterOf(GridDimension dimension) {
        int middle = dimension.cols() / 2;
        int left = middle - (shape[0].length / 2);
        CellPosition top = new CellPosition(-1,left);
        return new Tetromino(symbol, shape, top);
    }

    

    @Override
    public Iterator<GridCell<Character>> iterator() {
        ArrayList<GridCell<Character>> list = new ArrayList<GridCell<Character>>();
        int rows = shape.length;
        int cols = shape[0].length;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (shape[x][y]) {
                    Character val = symbol;
                    CellPosition cp = new CellPosition(pos.row() + x, pos.col() + y);
                    GridCell<Character> cell = new GridCell<>(cp, val);
                    list.add(cell);
                }
            }
        }
        return list.iterator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.symbol, Arrays.deepHashCode(this.shape), this.pos);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Tetromino))
            return false;
        Tetromino tetromino = (Tetromino) obj;
        return tetromino.symbol == this.symbol && Arrays.deepEquals(tetromino.shape, this.shape) && tetromino.pos.equals(this.pos);
    }

}
