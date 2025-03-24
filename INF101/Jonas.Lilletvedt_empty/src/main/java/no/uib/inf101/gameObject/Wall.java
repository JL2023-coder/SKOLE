package no.uib.inf101.gameObject;

import no.uib.inf101.main.GameWindow;
import no.uib.inf101.res.Constants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Wall extends GameObject {{
        // Instance Variables
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(this.posX, this.posY, this.width, this.height);
}
    // KeyStatus for wall
    // I for Idle
    // Decides if wall should move
    private String keyStatus = "I";

    // Gets keyStatus
    public String getKeyStatus() {
        return this.keyStatus;
    }

    // Sets keyStatus for Wall
    public void setKeyStatus(String keyStatus){
        this.keyStatus = keyStatus;
    }

    // Constructor
    public Wall(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
    }
    
    // Renders player
    public void render(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(this.posX, this.posY, this.width, this.height);
    }
    
    // Changes deltaX and deltaY
    // Moves obstacle
    // Updates hitbox
    private void moveWall(int deltaX, int deltaY) {
        this.posX += deltaX;
        this.posY += deltaY;

        // Stops wall from going out of screen
        if(posY < 0){
            posY = 0;}
        if(posY > Constants.SCREEN_HEIGHT - this.height){
            posY = Constants.SCREEN_HEIGHT - this.height;}
        if(posX < 0){
            posX = 0;}
        if(posX > Constants.SCREEN_WIDTH - this.width){
            posX = Constants.SCREEN_WIDTH- this.width;}

        // Updates hitbox
        updateHitbox();
    }

    // Moves xWalls -> L and R
    // Up or down depending on key input
    public void playingX(){
        if(this.keyStatus == "D"){moveWall(0, Constants.Wall_SPEED);}
        if(this.keyStatus == "U"){moveWall(0, Constants.Wall_SPEED*(-1));}
    }

    // Moves yWalls -> T and B
    // Left or right depending on key input
    public void playingY(){
        if(this.keyStatus == "L"){moveWall(Constants.Wall_SPEED*(-1), 0);}
        if(this.keyStatus == "R"){moveWall(Constants.Wall_SPEED, 0);}

}
}