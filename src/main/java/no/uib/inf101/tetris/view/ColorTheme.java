package no.uib.inf101.tetris.view;

import java.awt.Color;

/**

The ColorTheme interface defines a set of methods to be implemented by classes that provide

color schemes for Tetris game components.
*/

public interface ColorTheme {


/**

Returns the color associated with a particular character.
@param c The character to get the color for.
@return The color associated with the character.
*/
    Color getCellColor(Character c);
/**

Returns the color of the frame around the Tetris game board.
@return The color of the frame.
*/
    Color getFrameColor();
/**

Returns the color of the background behind the Tetris game board.
@return The color of the background.
*/
    Color getBackgroundColor();

/**

Returns the color used to indicate that the game is over.
@return The color used to indicate game over.
*/


    Color getGameOverColor();
/**

Returns the color of the text displayed when the game is over.
@return The color of the game over text.
*/

    Color getGameOverText();

    
}
