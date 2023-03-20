package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;


/**

The ViewableTetrisModel interface provides methods for accessing the current state of a Tetris game
that can be displayed in a graphical user interface.
*/
public interface ViewableTetrisModel {
/**
 * Returns the dimensions of the game board as a {@link GridDimension} object.
 * @return the dimensions of the game board
 */
    GridDimension getDimension();
/**
 * Returns an iterable collection of {@link GridCell} objects representing the tiles currently on the game board.
 * @return an iterable collection of GridCell objects representing the tiles on the game board
 */
    Iterable<GridCell<Character>> getTilesOnBoard ();
/**
 * Returns an iterable collection of {@link GridCell} objects representing the falling tile.
 * @return an iterable collection of GridCell objects representing the falling tile
 */
    Iterable<GridCell<Character>> getFallingTile ();
/**
 * Returns the current state of the game as a {@link GameState} enum value.
 * @return the current state of the game
 */
    GameState getGameState();
    
    
}
