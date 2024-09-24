package no.uib.inf101.tetris;

import javax.swing.JFrame;

import no.uib.inf101.tetris.controller.TetrisController;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.TetrisView;

/**

The TetrisMain class is the main class for running the INF101 Tetris game. It creates the necessary
objects and initializes the game window.
*/

public class TetrisMain {

  /**

The title of the main window.
*/
  public static final String WINDOW_TITLE = "INF101 Tetris";

/**

The main method starts the game by creating a TetrisBoard, a TetrominoFactory, a TetrisModel, a TetrisView,
and a TetrisController. It then creates a JFrame, sets its properties, and displays the TetrisView in it.
@param args command line arguments (not used)
*/
  
  public static void main(String[] args) {

    TetrisBoard board = new TetrisBoard(20,10);
    

    TetrominoFactory factory = new RandomTetrominoFactory();
  
    TetrisModel model = new TetrisModel(board,factory);


    TetrisView view = new TetrisView(model);

    new TetrisController(model, view);

    // The JFrame is the "root" application window.
    // We here set som properties of the main window, 
    // and tell it to display our tetrisView
    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Here we set which component to view in our window
    frame.setContentPane(view);
    
    // Call these methods to actually display the window
    frame.pack();
    frame.setVisible(true);
  }
  
}
