package no.uib.inf101.main;

import javax.swing.JFrame;

import no.uib.inf101.res.Constants;

public class GameWindow extends JFrame {
    // Instance Variables
    private static GameWindow gameWindow;

    // Constructor
    // Set parameters for gameWindow
    public GameWindow(GamePanel gamePanel){
        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setTitle(Constants.TITLE);
        this.add(gamePanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
