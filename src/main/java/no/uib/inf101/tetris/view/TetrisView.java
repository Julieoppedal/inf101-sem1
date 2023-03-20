package no.uib.inf101.tetris.view;

import javax.swing.JPanel;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.GameState;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.Font;

/**

The TetrisView class is responsible for rendering the current state of the game.

It extends the JPanel class and implements the paintComponent method to draw the game on screen.
*/

public class TetrisView extends JPanel {
    private final ViewableTetrisModel model;
    private final ColorTheme color;


/**

Constructs a new TetrisView object.
@param model the ViewableTetrisModel to render
*/
    public TetrisView(ViewableTetrisModel model) {
        this.model = model;
        this.color = new DefaultColorTheme();
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(300, 400));
        this.setBackground(color.getBackgroundColor());
    }
/**

Draws the game board, including the falling tile and the tiles already on the board.
@param g2d the Graphics2D object to use for drawing
*/
  
  // The paintComponent method is called by the Java Swing framework every time
  // either the window opens or resizes, or we call .repaint() on this object. 
  // Note: NEVER call paintComponent directly yourself

    private void drawGame(Graphics2D g2d) {
        double margin = 30;
        double x = margin;
        double y = margin;
        double width = this.getWidth() - 2 * margin;
        double height = this.getHeight() - 2 * margin;

        Rectangle2D r2d = new Rectangle2D.Double(x, y, width, height);

        g2d.setColor(color.getFrameColor());
        g2d.fill(r2d);

        CellPositionToPixelConverter cptp = new CellPositionToPixelConverter(r2d, this.model.getDimension() , 2);
        
        drawCells(g2d, this.model.getTilesOnBoard(), cptp, color);
        drawCells(g2d, this.model.getFallingTile(), cptp, color);

        if (model.getGameState() == GameState.GAME_OVER) {
            Color gameOverColor = color.getGameOverColor();
            Color gameOverText = color.getGameOverText();
            g2d.setColor(gameOverColor);
            g2d.fill(r2d);
            g2d.setColor(gameOverText);
            g2d.setFont(new Font("Calibri", Font.BOLD, 30)); // sets the font to Arial, bold, size 30
            Inf101Graphics.drawCenteredString(g2d, "GAME OVER", r2d);
        }

    }
/**

Draws a collection of cells onto the graphics object using the provided cell position to pixel converter
and color theme.
@param g2d the Graphics2D object to use for drawing
@param cells the cells to draw
@param cptpc the cell position to pixel converter to use for translating cell positions to pixels
@param ct the color theme to use for drawing the cells
*/
    private static void drawCells(Graphics2D g2d, Iterable<GridCell<Character>> cells, CellPositionToPixelConverter cptpc, ColorTheme ct) {
        for (GridCell<Character> cell : cells) {
            Rectangle2D cellRect = cptpc.getBoundsForCell(cell.pos());
            Color cellColor = ct.getCellColor(cell.value());
            g2d.setColor(cellColor);
            g2d.fill(cellRect);
        }
    }
/**

Overrides the paintComponent method of the JPanel class to draw the game on the screen using the
drawGame method.
@param g the Graphics object to use for drawing
*/

    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
    }

    
  
}