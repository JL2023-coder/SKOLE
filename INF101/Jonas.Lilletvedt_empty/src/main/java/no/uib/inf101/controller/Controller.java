package no.uib.inf101.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import no.uib.inf101.main.Game;
import no.uib.inf101.main.GamePanel;

public class Controller implements KeyListener {
    //Instance Variables
    private GamePanel gamePanel;
    private Game game;
    // keyX is for wall L and R
    private int lastPressedKeyX;
    // keyY is for wall T and B
    private int lastPressedKeyY;

    // Constructor
    public Controller(GamePanel gamePanel, Game game){
        this.gamePanel = gamePanel;
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // If difficulty is null, you can change difficulty
        if (gamePanel.getDifficulty()==0){
            char typedChar = e.getKeyChar();
            if (typedChar == '1' && gamePanel.getGameState() == "CD"){
                // Sets game state to active
                // Sets difficulty to 1
                // Sets wall T and B to diff 1 walls
                gamePanel.setGameState("ACTIVE");
                gamePanel.setDifficulty(1);
                game.setWallB(game.getWallManager().getWallBDiff1());
                game.setWallT(game.getWallManager().getWallTDiff1());}
            else if (typedChar == '2' && gamePanel.getGameState() == "CD"){
                // Sets game state to active
                // Sets difficulty to 2
                // Sets wall T and B to diff 2 walls
                gamePanel.setGameState("ACTIVE");
                gamePanel.setDifficulty(2);
                game.setWallB(game.getWallManager().getWallBDiff2());
                game.setWallT(game.getWallManager().getWallTDiff2());
            }
        }

        // If gameState is "OVER"
        // Press R to restart game
        if (gamePanel.getGameState()=="OVER"){
            char typedChar = Character.toUpperCase(e.getKeyChar());
            // If typed "R"
            // Restart Game
            if(typedChar == 'R'){
                gamePanel.restartGame();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // Move top and bottom if difficulty is 2
            if(gamePanel.getDifficulty() == 2){
                // Left arrow was pressed
                // Set keyStatus to "L"
                game.getWallT().setKeyStatus("L");
                game.getWallB().setKeyStatus("L");
                lastPressedKeyY = e.getKeyCode();
            }
            }
        
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                // Move top and bottom if difficulty is 2
                if(gamePanel.getDifficulty() == 2){
                    // Right arrow was pressed
                    // Set keyStatus to "R"
                    game.getWallT().setKeyStatus("R");
                    game.getWallB().setKeyStatus("R");
                    lastPressedKeyY = e.getKeyCode();
                }
            }
            
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // Down arrow was pressed
            // Set keyStatus to "D"
            game.getWallR().setKeyStatus("D");
            game.getWallL().setKeyStatus("D");
            lastPressedKeyX = e.getKeyCode();
            
            }

            else if (e.getKeyCode() == KeyEvent.VK_UP) {
            // Up arrow was pressed
            // Set keyStatus to "U"
            game.getWallR().setKeyStatus("U");
            game.getWallL().setKeyStatus("U");
            lastPressedKeyX = e.getKeyCode();
    }
}

    @Override
    public void keyReleased(KeyEvent e) {
        // If key is released keyStatus set to Idle
        int releasedKey = e.getKeyCode();
        if(releasedKey == lastPressedKeyY){
            game.getWallT().setKeyStatus("I");
            game.getWallB().setKeyStatus("I");
        }
        if(releasedKey == lastPressedKeyX){
            game.getWallL().setKeyStatus("I");
            game.getWallR().setKeyStatus("I");
        }
    }
}