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
