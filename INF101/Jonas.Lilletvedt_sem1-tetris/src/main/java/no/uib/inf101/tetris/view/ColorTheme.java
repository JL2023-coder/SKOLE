package no.uib.inf101.tetris.view;

import java.awt.Color;

public interface ColorTheme {

    // new color TRANSPARENT for GAME_OVER stat
    public static final Color TRANSPARENT = new Color(0, 0, 0, 128);


    Color getCellColor(char cellColor);
    // Gets the color of the cell
    // @param cellColor the value reprsenting each cell
    // @return the color given by the character cellColor
    // @llegalArgumentException, if the value cellColor does not have a color given to it


    Color getFrameColor();
    // Gets the frame color
    // @return the frame color


    Color getBackgroundColor();
    // Gets the background color
    // @return the background color
}
