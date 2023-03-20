package no.uib.inf101.tetris.model;

/**

An enumeration of the possible states of the Tetris game.
The two states are {@code ACTIVE_GAME} and {@code GAME_OVER}.
The game is in the {@code ACTIVE_GAME} state when it is ongoing and the player
can still make moves. The {@code GAME_OVER} state is reached when the game is over
and the player cannot make any more moves.
*/

public enum GameState {
    ACTIVE_GAME,
    GAME_OVER

    
}
