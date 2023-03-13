package no.uib.inf101.tetris.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme{
    

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

    @Override
    public Color getFrameColor() {
        return (new Color(0, 0, 0, 0));
    }

    @Override
    public Color getBackgroundColor() {
        return null;
    }

    @Override
    public Color getGameOverColor() {
        return new Color(0, 0, 0, 128);
    }

    @Override
    public Color getGameOverText() {
        return Color.WHITE;
    }
    
    
}
