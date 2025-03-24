package no.uib.inf101.main;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import no.uib.inf101.controller.Controller;
import no.uib.inf101.gameObject.GameBall;
import no.uib.inf101.res.Constants;

public class GamePanel extends JPanel{
    // Instance variables
    private Color backgroundColor = Color.BLACK;
    private Game game; 
    private String gameState = "CD"; // CD -> choose difficulty
    private int difficulty; // Difficulty

    // Constructor
    public GamePanel(Game game){
        this.game = game;

        // Adds KeyListener to gamePanel
        // Adds Controller
        addKeyListener(new Controller(this, game));

        // Sets focus
        // For keyboard inputs
        this.setFocusable(true);
    }


    // Paint upon the window
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        // Sets background
        setBackground(backgroundColor);

        //Updates and chechks game state
        updateGameState();

        // Renders game
        // Choose difficulty screen
        if (gameState == "CD"){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            String gameOverMessage = "Choose Difficulty: 1 for easy, 2 for hard";
            int x = (getWidth() - g.getFontMetrics().stringWidth(gameOverMessage)) / 2;
            int y = getHeight() / 2;
            g.drawString(gameOverMessage, x, y);
        }
        // Game active screen
        if (gameState == "ACTIVE"){
            // Renders game
            game.render(g);
            // Writes score
            g.setFont(new Font("Arial", Font.BOLD, 16));
            String score = Integer.toString(game.getScore());
            int x = Constants.SCREEN_WIDTH - 180;
            int y = 50;
            g.drawString("SCORE: "+ score, x, y);
        }

        // Game over screen
        if (gameState == "OVER"){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            String gameOver = "Game Over, HIGHSCORE: " + game.getScore();
            String restartGame = "Press R to play again";
            int xGameOver = (getWidth() - g.getFontMetrics().stringWidth(gameOver)) / 2;
            int xRestartGame = (getWidth() - g.getFontMetrics().stringWidth(restartGame)) / 2;
            int y = getHeight() / 2;
            g.drawString(gameOver, xGameOver, y);
            g.drawString(restartGame, xRestartGame, y + 38);
        }
    }

    // Gets gameStata
    // @return gameState
    public String getGameState(){
        return gameState;
    }

    // Sets gameState
    public void setGameState(String gameState){
        this.gameState = gameState;
    }

    // Sets difficulty
    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }

    // Gets difficulty
    // @returb diificulty
    public int getDifficulty(){
        return difficulty;
    }

    // Checks if ball out of screen
    // If true gameState = "OVER"
    public void updateGameState(){
        if(game.getGameBall().outOfScreen() == true){
            gameState = "OVER";
        }
    }

    // Gets game
    // @return game
    public Game getGame(){
        return game;
    }

    // Restarts game
    public void restartGame(){
        // New gameBall
        game.setGameBall(new GameBall(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2, 10, 10));
        // New Walls
        game.setWallL(game.getWallManager().getWallL());
        game.setWallR(game.getWallManager().getWallR());
        game.setWallT(game.getWallManager().getWallTDiff1());
        game.setWallB(game.getWallManager().getWallBDiff1());
        
        // GameState "CD"
        gameState = "CD";
        // Difficulty set to null
        difficulty = 0;
        // Set score to 0
        game.resetScore();

        // Repaint screen, to match with gameState
        repaint();
    }
}