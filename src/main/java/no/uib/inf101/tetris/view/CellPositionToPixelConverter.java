package no.uib.inf101.tetris.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;




public class CellPositionToPixelConverter {
    Rectangle2D box;  
    GridDimension gd; 
    double margin;
    
    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin){
        this.box = box;
        this.gd = gd;
        this.margin = margin;

    }

  
    public Rectangle2D getBoundsForCell(CellPosition cp){
        double cellWidth = (box.getWidth() - (double)(gd.cols() + 1) * margin) / (double)(gd.cols());
        double cellHeight = (box.getHeight() - (double)(gd.rows() + 1) * margin) / (double)(gd.rows());
        double x = box.getX() + margin + (double)(cp.col())* (cellWidth + (double)(margin));
        double y = box.getY() + margin + (double)(cp.row())* (cellHeight + (double)(margin));
        return new Rectangle2D.Double(x, y, cellWidth, cellHeight);
  
    }
}