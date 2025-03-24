package no.uib.inf101.main;

import java.awt.Graphics;

import no.uib.inf101.gameObject.GameBall;
import no.uib.inf101.gameObject.Wall;
import no.uib.inf101.gameObject.WallManager;
import no.uib.inf101.res.Constants;

public class Game implements Runnable{
    // Instance Variables
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private GameBall gameBall;
    private WallManager wallManager;
    private Wall wallL;
    private Wall wallR;
    private Wall wallT;
    private Wall wallB;
    private int score = 0;

    // For runnable
    private Thread gameThread;
    private final int FPS = 60;

    // Constructor
    public Game(){
    // New wallManager
    // WallManager is used to get walls
    wallManager = new WallManager();
    // Adds entites
    initEntities();
    // New GamePanel
    gamePanel = new GamePanel(this);
    // New GameWindow
    gameWindow = new GameWindow(gamePanel);
    
    // Starts gamethread
    startGameLoop();
    }

    // Gets score
    public int getScore(){
        return score;
    }

    // Gets score
    public void resetScore(){
        this.score = 0;
    }
    
    // Gets gameBall
    public GameBall getGameBall(){
        return gameBall;
    }

    // Set gameBall
    public void setGameBall(GameBall gameBall){
        this.gameBall = gameBall;
    }

    // Gets playerWallL
    public Wall getWallL(){
        return wallL;
    }

    // Gets playerWallR
    public Wall getWallR(){
        return wallR;
    }

    // Gets playerWallT
    public Wall getWallT(){
        return wallT;
    }

    // Gets playerWallB
    public Wall getWallB(){
        return wallB;
    }

    // Sets playerWallT
    public void setWallL(Wall wall){
        wallL = wall;
    }
    
    // Sets playerWallB
    public void setWallR(Wall wall){
        wallR = wall;
    }

    // Sets playerWallT
    public void setWallT(Wall wall){
        wallT = wall;
    }
    
    // Sets playerWallB
    public void setWallB(Wall wall){
        wallB = wall;
    }

    // Gets WallManager
    public WallManager getWallManager(){
        return wallManager;
    }

    // Add entities
    private void initEntities() {
        gameBall = new GameBall(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2, 10, 10);
        wallL = wallManager.getWallL();
        wallR = wallManager.getWallR();
        // Start walls are diff1
        wallT = wallManager.getWallTDiff1();
        wallB = wallManager.getWallBDiff1();
    }

    // Renderes objects
    public void render(Graphics g){
        gameBall.render(g);
        wallL.render(g);
        wallR.render(g);
        wallT.render(g);
        wallB.render(g);
    }

    // Starts gameLooop
    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    // Runnable
    // Repaint per frameTime
    // Loop is inspired by 
    // Kaarin Gaming -> https://www.youtube.com/watch?v=aFS9Whsoecc 
    public void run() {
        // Timer for frame
        double frameTime = 1000000000 / FPS;
        long previousFrame = System.nanoTime();
        long currentFrame = System.nanoTime();

        while(true){
            // New frame
            currentFrame = System.nanoTime();
            if(currentFrame - previousFrame >= frameTime){
                // If gameState is active 
                // Update game and gameScreen
                if(gamePanel.getGameState()=="ACTIVE"){
                    // Increases score by one
                    score++;
                    // Checks and updates position of gameObjects
                    gameBall.playing(wallL, wallR, wallT, wallB);
                    // Keystatus is set in controller, shows which direction wall should move
                    wallL.playingX();
                    wallR.playingX();
                    wallT.playingY();
                    wallB.playingY();
                    // New frame
                    // In repaint checks gameStatus
                    gamePanel.repaint();
                    // Timer for frame
                    previousFrame = currentFrame;}
            }
        }
    }
}
