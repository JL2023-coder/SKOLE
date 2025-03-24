package no.uib.inf101.tetris.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getCellColor(char cellColor) {
        Color color = switch(cellColor) {

            //color board 
            case 'r' -> Color.RED;
            case 'y' -> Color.YELLOW;
            case 'g' -> Color.GREEN;
            case 'b' -> Color.BLUE;
            case '-' -> Color.BLACK;

            // color tetromino types
            case 'S' -> Color.GREEN;
            case 'T' -> Color.PINK;
            case 'L' -> Color.ORANGE;
            case 'O' -> Color.YELLOW;
            case 'J' -> Color.BLUE;
            case 'Z' -> Color.RED;
            case 'I' -> Color.MAGENTA;

            default -> throw new IllegalArgumentException(
                "No available color for '" + cellColor + "'");
          };
          return color;
        
    }

    @Override
    public Color getFrameColor() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public Color getBackgroundColor() {
        return null;
    }
    
}
