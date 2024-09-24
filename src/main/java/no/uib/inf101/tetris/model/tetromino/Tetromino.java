package no.uib.inf101.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

/**

The Tetromino class represents a tetromino shape used in Tetris game.

A tetromino consists of a symbol, a boolean matrix representing its shape,

and its position on the game grid.
*/

public class Tetromino implements Iterable<GridCell<Character>> {
    private final char symbol;
    private final boolean[][] shape;
    private final CellPosition pos;
/**

Constructs a new Tetromino with the given symbol, shape, and position.
@param symbol the symbol that represents the Tetromino
@param shape a boolean matrix representing the shape of the Tetromino
@param pos the position of the Tetromino on the game grid
*/

    Tetromino(char symbol, boolean[][] shape, CellPosition pos) {
        this.symbol = symbol;
        this.shape = shape;
        this.pos = pos;

    }
/**

Creates a new Tetromino object with the given symbol.
@param symbol the symbol that represents the Tetromino
@return a new Tetromino object with the given symbol
@throws IllegalArgumentException if there is no available shape for the given symbol
*/
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
/**

Returns a new Tetromino that is shifted by the given delta row and delta column.
@param deltaRow the number of rows to shift the Tetromino by
@param deltaCol the number of columns to shift the Tetromino by
@return a new Tetromino that is shifted by the given delta row and delta column
*/
    public Tetromino shiftedBy(int deltaRow, int deltaCol) {
        CellPosition newPosition = new CellPosition(pos.row() + deltaRow, pos.col() + deltaCol);
        return new Tetromino(symbol, shape, newPosition);
    }

/**

Returns a new Tetromino that is shifted to the top center of the given grid dimension.
@param dimension the dimension of the game grid
@return a new Tetromino that is shifted to the top center of the given grid dimension
*/
    
    public Tetromino shiftedToTopCenterOf(GridDimension dimension) {
        int middle = dimension.cols() / 2;
        int left = middle - (shape[0].length / 2);
        CellPosition top = new CellPosition(-1,left);
        return new Tetromino(symbol, shape, top);
    }

/**

Returns a new Tetromino that is rotated 90 degrees clockwise.
@return a new Tetromino that is rotated 90 degrees clockwise
*/

    public Tetromino rotate() {
        int oldRows = shape.length;
        int oldCols = shape[0].length;
        boolean[][] newShape = new boolean[oldCols][oldRows];
    
        // roter matrisen
        for (int r = 0; r < oldRows; r++) {
            for (int c = 0; c < oldCols; c++) {
                newShape[c][oldRows - 1 - r] = shape[r][c];
            }
        }
    
        return new Tetromino(symbol, newShape, pos);
    }

/**

Returns an iterator over the GridCells that the Tetromino occupies on the game grid.
@return an iterator over the GridCells that the Tetromino occupies on the game grid
*/

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

/**

Returns a hash code value for the Tetromino object.
@return a hash code value for the Tetromino object
*/

    @Override
    public int hashCode() {
        return Objects.hash(this.symbol, Arrays.deepHashCode(this.shape), this.pos);
    }
/**

Indicates whether some other object is "equal to" this one.
@param obj the reference object with which to compare
@return true if this object is the same as the obj argument; false otherwise
*/

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
