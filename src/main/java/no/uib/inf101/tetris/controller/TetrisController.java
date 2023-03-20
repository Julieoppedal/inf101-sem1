package no.uib.inf101.tetris.controller;

import java.awt.event.KeyEvent;

import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.view.TetrisView;
import javax.swing.Timer;
import java.awt.event.ActionEvent; 

/**

The TetrisController class is responsible for handling user input and updating the model and view accordingly.

It implements the java.awt.event.KeyListener interface and listens for key events from the TetrisView.
*/



public class TetrisController implements java.awt.event.KeyListener {

    private final ControllableTetrisModel consol;
    private final TetrisView tetrisView;
    private final Timer timer;
    private final TetrisSong music;

    /**

Constructs a new TetrisController with the specified ControllableTetrisModel and TetrisView.
@param consol the ControllableTetrisModel to use.
@param tetrisView the TetrisView to use.
*/

    public TetrisController(ControllableTetrisModel consol, TetrisView tetrisView) {
        this.tetrisView = tetrisView;
        this.consol = consol;
        this.music = new TetrisSong();

        // Initialize timer
        this.timer = new Timer(consol.getTimeBetweenTicks(), this::clockTick);
        this.timer.setInitialDelay(0);
        this.timer.start();

        

        this.tetrisView.setFocusable(true); // make TetrisView focusable
        this.tetrisView.addKeyListener(this); // add the key listener to TetrisView

        this.music.run();
    }

/**

Responds to key pressed events from the TetrisView and updates the model and view accordingly.
@param e the KeyEvent that occurred.
*/

    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            consol.moveTetromino(0,-1);
            // Left arrow was pressed
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            consol.moveTetromino(0,1);

            // Right arrow was pressed
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            consol.moveTetromino(1,0);
            // Down arrow was pressed
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            consol.rotateTetromino();
            // Up arrow was pressed
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            consol.dropTetromino();

            // Spacebar was pressed
        }
        tetrisView.repaint();
    }

/**

This method is not used.
@param e the KeyEvent that occurred.
*/
    @Override
    public void keyTyped(KeyEvent e) {

    }
/**

This method is not used.
@param e the KeyEvent that occurred.
*/

    @Override
    public void keyReleased(KeyEvent e) {

    }
/**

Updates the model and view when the timer goes.
@param e the ActionEvent that occurred.
*/

    public void clockTick(ActionEvent e) {
        consol.clockTick();
        tetrisView.repaint();
    }
}


