package no.uib.inf101.tetris.controller;

import java.awt.event.KeyEvent;

import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.view.TetrisView;
import javax.swing.Timer;
import java.awt.event.ActionEvent; 




public class TetrisController implements java.awt.event.KeyListener {
    private final ControllableTetrisModel consol;
    private final TetrisView tetrisView;
    private final Timer timer;
    private final TetrisSong music;

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

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }


    public void clockTick(ActionEvent e) {
        consol.clockTick();
        tetrisView.repaint();
    }
}


