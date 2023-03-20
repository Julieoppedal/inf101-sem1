package no.uib.inf101.tetris.view;

import java.awt.Color;

/**

The DefaultColorTheme class implements the ColorTheme interface and defines the default color
scheme used in the Tetris game.
*/

public class DefaultColorTheme implements ColorTheme{
/**
 * Returns the color associated with the given character according to the default color scheme.
 * 
 * @param c The character representing the Tetris shape.
 * @return The color associated with the given character.
 * @throws IllegalArgumentException if the given character does not have a corresponding color in the scheme.
 */
    

    @Override
    public Color getCellColor(Character c) {
        Color color = switch(c) {
            case 'r' -> Color.RED;
            case 'L' -> Color.GREEN;
            case 'O' -> Color.YELLOW;
            case 'J' -> Color.BLUE;
            case 'I' -> Color.CYAN;
            case 'T' -> Color.MAGENTA;
            case 'S' -> Color.ORANGE;
            case 'w' -> Color.WHITE;
            case 'Z' -> Color.PINK;
            case '-' -> Color.BLACK;
            default -> throw new IllegalArgumentException(
                "No available color for '" + c + "'");
        };
        return color;
    }

/**
 * Returns the frame color of the Tetris grid according to the default color scheme.
 * 
 * @return The frame color of the Tetris grid.
 */

    @Override
    public Color getFrameColor() {
        return (new Color(0, 0, 0, 0));
    }

/**
 * Returns the background color of the Tetris grid according to the default color scheme.
 * 
 * @return The background color of the Tetris grid.
 */

    @Override
    public Color getBackgroundColor() {
        return null;
    }
/**
 * Returns the game over screen color according to the default color scheme.
 * 
 * @return The game over screen color.
 */
    @Override
    public Color getGameOverColor() {
        return new Color(0, 0, 0, 128);
    }
/**
 * Returns the color of the game over text according to the default color scheme.
 * 
 * @return The color of the game over text.
 */
    @Override
    public Color getGameOverText() {
        return Color.WHITE;
    }
    
    
}
