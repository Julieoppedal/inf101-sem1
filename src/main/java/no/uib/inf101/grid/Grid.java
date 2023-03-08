package no.uib.inf101.grid;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Grid<E> implements IGrid<E> {
    private int rows;
    private int cols;
    private List<List<E>> grid;

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

    public Grid(int rows, int cols) {
        this(rows, cols, null);

    }




    @Override
    public int rows() {
        return this.rows;
    }

    @Override
    public int cols() {
        return this.cols;
    }

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

    @Override
    public void set(CellPosition pos, E value) {
        int row = pos.row();
        int col = pos.col();
    
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
          throw new IndexOutOfBoundsException("Invalid position: " + pos);
        }
        
        grid.get(row).set(col, value);
    }

    @Override
    public E get(CellPosition pos) {
        int row = pos.row();
        int col = pos.col();
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
          throw new IndexOutOfBoundsException("Invalid position: " + pos);
        }

        return this.grid.get(row).get(col);
    }

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
