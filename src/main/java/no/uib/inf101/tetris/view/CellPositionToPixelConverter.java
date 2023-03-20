package no.uib.inf101.tetris.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;


/**

This class converts a cell position in a grid to its corresponding pixel location on a specified rectangle box.

It takes in the dimensions of the grid and the size of the margin to calculate the exact position of each cell.
*/

public class CellPositionToPixelConverter {
    Rectangle2D box;  
    GridDimension gd; 
    double margin;
    

/**

Constructs a new CellPositionToPixelConverter object with the specified parameters.
@param box The rectangle box in which the grid is to be displayed.
@param gd The dimensions of the grid to be displayed.
@param margin The size of the margin between each cell in the grid.
*/
    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin){
        this.box = box;
        this.gd = gd;
        this.margin = margin;

    }

  /**

Calculates and returns the pixel location of the specified cell position on the specified rectangle box.
@param cp The cell position for which the pixel location is to be calculated.
@return The pixel location of the specified cell position on the specified rectangle box.
*/
    public Rectangle2D getBoundsForCell(CellPosition cp){
        double cellWidth = (box.getWidth() - (double)(gd.cols() + 1) * margin) / (double)(gd.cols());
        double cellHeight = (box.getHeight() - (double)(gd.rows() + 1) * margin) / (double)(gd.rows());
        double x = box.getX() + margin + (double)(cp.col())* (cellWidth + (double)(margin));
        double y = box.getY() + margin + (double)(cp.row())* (cellHeight + (double)(margin));
        return new Rectangle2D.Double(x, y, cellWidth, cellHeight);
  
    }
}