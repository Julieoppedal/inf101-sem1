package no.uib.inf101.grid;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**

The Grid class is an implementation of the IGrid interface that represents a grid of elements of type E.
The grid has a specified number of rows and columns and can be iterated over using the iterator method.
The class provides methods to set and get the value of a cell at a given position, as well as to check if a
position is on the grid.
@param <E> the type of elements in the grid
*/

public class Grid<E> implements IGrid<E> {

    protected int rows;
    private int cols;
    private List<List<E>> grid;

/**
 * Creates a new Grid object with the specified number of rows and columns, 
 * and initializes all cells with the given default value.
 * 
 * @param rows the number of rows in the grid
 * @param cols the number of columns in the grid
 * @param defV the default value for all cells in the grid
 */
    public Grid(int rows, int cols, E defV) {
        this.rows = rows;
        this.cols = cols;

        this.grid = new ArrayList<>();
        for (int i = 0; i< rows; i++){
            List<E> row = new ArrayList<>();
            for(int j = 0; j<cols; j++){
                row.add(defV);
            
        }
        this.grid.add(row); 
        } 
    }
/**
 * Constructs a grid with the specified number of rows and columns, where each cell is initialized 
 * to null.
 * 
 * @param rows the number of rows in the grid
 * @param cols the number of columns in the grid
 */
    public Grid(int rows, int cols) {
        this(rows, cols, null);

    }
/**
 * Returns the number of rows in the grid.
 * 
 * @return the number of rows in the grid
 */

    @Override
    public int rows() {
        return this.rows;
    }
/**
 * Returns the number of columns in the grid.
 * 
 * @return the number of columns in the grid
 */

    @Override
    public int cols() {
        return this.cols;
    }
/**
 * Returns an iterator over the grid, which iterates over the elements of type GridCell<E>.
 * 
 * @return an iterator over the grid
 */

    @Override
    public Iterator<GridCell<E>> iterator() {
        ArrayList<GridCell<E>> list = new ArrayList<GridCell<E>>();
        for (int x = 0; x< rows; x++){
            for(int y = 0; y<cols; y++){
                E val = grid.get(x).get(y);
                CellPosition cp = new CellPosition(x,y);
                GridCell<E> cell =  new GridCell<>(cp,val);
                list.add(cell);
            }
        }
        return list.iterator();
    } 

/**

Sets the value of the cell at the specified position on the grid to the given value.
@param pos the position of the cell to set
@param value the new value for the cell
@throws IndexOutOfBoundsException if the position is not on the grid
*/
    @Override
    public void set(CellPosition pos, E value) {
        int row = pos.row();
        int col = pos.col();
    
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
          throw new IndexOutOfBoundsException("Invalid position: " + pos);
        }
        
        grid.get(row).set(col, value);
    }
/**

Returns the value of the cell at the specified position on the grid.
@param pos the position of the cell to get
@return the value of the cell at the given position
@throws IndexOutOfBoundsException if the position is not on the grid
*/

    @Override
    public E get(CellPosition pos) {
        int row = pos.row();
        int col = pos.col();
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
          throw new IndexOutOfBoundsException("Invalid position: " + pos);
        }

        return this.grid.get(row).get(col);
    }
/**

Returns true if the given position is on the grid, false otherwise.
@param pos the position to check
@return true if the given position is on the grid, false otherwise
*/

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        int row = pos.row();
        int col = pos.col();

        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return false;
        }

        return true;

    }
    
}
