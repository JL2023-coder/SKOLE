package no.uib.inf101.gameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import no.uib.inf101.res.Constants;

public class GameBall extends GameObject{{
    // Instance Variables
    this.posX = posX;
    this.posY = posY;
    this.width = 10;
    this.height = 10;
    this.hitbox = new Rectangle(this.posX, this.posY, this.width, this.height);
}
// Direction of gameBall
private Direction direction;


// Constructor
public GameBall(int posX, int posY, int width, int height){
    super(posX, posY, width, height);
    // When a new gameBall is created
    // Gets a new partly random direction
    initDirection();
}

// new Direction with random speeds between 2 and 7
private void initDirection(){
    Random random = new Random();
    int speedX = random.nextInt(5) + 2;
    int speedY = random.nextInt(5) + 2;
    direction = new Direction(speedX, speedY);
}

private void addSpeed(){
    // If speedX < BALL_SPEED increase speedX +1
    if (direction.speedX() < Constants.BALL_SPEED && direction.speedX() > 0){direction = new Direction(direction.speedX() +1, direction.speedY());}
    if (direction.speedX() > Constants.BALL_SPEED*(-1)-1 && direction.speedX() < 0){direction = new Direction(direction.speedX() -1, direction.speedY());}
    // If speedY < BALL_SPEED increase speedY +1
    if (direction.speedY() < Constants.BALL_SPEED && direction.speedY() >= 0){direction = new Direction(direction.speedX(), direction.speedY() +1);}
    if (direction.speedY() > Constants.BALL_SPEED*(-1)-1 && direction.speedY() < 0){direction = new Direction(direction.speedX(), direction.speedY() -1);}
}

// Changes deltaX and deltaY
// Moves player
// Updates hitbox
private void moveGameBall(){
    // Updates position
    this.posX += direction.speedX();
    this.posY += direction.speedY();
    updateHitbox();
}

// Checks if gameBall is colliding with a wall
// @return true if gameBall is coliding
private boolean isColidingWith(Wall playerWall){
    if(hitbox.intersects(playerWall.getHitbox())){
        return true;}
    return false;
}


// Checks if gameBall is colliding with any wall
// @return true if gameBall is coliding
// Updates direction
private boolean isColiding(Wall wallL, Wall wallR, Wall wallT, Wall wallB){
    // Chech collison on playerWallL or playerWallR
    // If true calls updateDirction with "X" direction
    if(isColidingWith(wallL) || isColidingWith(wallR)){
        updateDirection("X");
        return true;}
    // Chech collison on playerWallT or playerWallB
    // If true calls updateDirction with "Y" direction
    if(isColidingWith(wallT) || isColidingWith(wallB)){
        updateDirection("Y");
        return true;}
    return false;
}

// Changes direction
// X -> means collison with wall L or R
// Y -> means collison with wall T or B
public void updateDirection(String XorY){
    // Changes X direction
    if (XorY == "X"){this.direction = new Direction(direction.speedX()*-1, direction.speedY());}
    //Changes Y Direction
    if (XorY == "Y"){this.direction = new Direction(direction.speedX(), direction.speedY()*-1);}
    // Adds speed to gameBall
    addSpeed();
}

// Takes methods over in to one
// Called at game
public void playing(Wall wallL, Wall wallR, Wall wallT, Wall wallB){
    moveGameBall();
    isColiding(wallL, wallR, wallT, wallB);
}


// Checks if ball is out of screen
// @return true if ball is out of screen
public boolean outOfScreen(){
    if(posX < 0 || posX > Constants.SCREEN_WIDTH
    || posY < 0 || posY > Constants.SCREEN_HEIGHT){
        return true;
    }
    return false;
}


// Renders player
public void render(Graphics g){
    g.setColor(Color.WHITE);
    g.fillRect(this.posX, this.posY, this.width, this.height);
}
}