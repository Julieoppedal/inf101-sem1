/**

The ControllableTetrisModel interface defines the methods that a controllable implementation of the Tetris model
should have. These methods allow for moving, rotating, and dropping tetrominoes, as well as getting the current game
state and the time between ticks. Additionally, there is a method for advancing the game clock by one tick.
*/

package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.GameState;

public interface ControllableTetrisModel {


    boolean moveTetromino(int deltaRow, int deltaCol);

    boolean rotateTetromino();

    boolean dropTetromino();

    GameState getGameState();

    int getTimeBetweenTicks();

    void clockTick();

    
    
}
