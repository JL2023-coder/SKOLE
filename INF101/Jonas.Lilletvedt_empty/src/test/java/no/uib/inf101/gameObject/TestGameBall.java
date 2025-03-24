package no.uib.inf101.gameObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.gameObject.Direction;
import no.uib.inf101.gameObject.GameBall;
import no.uib.inf101.gameObject.Wall;
import no.uib.inf101.res.Constants;

public class TestGameBall{

    
@Test
// Test if outOfScreen works
// Checks if gameBall is out of screen
// If true returns true
// Checks inside and border of screen, aswell as outside of screen
public void testOutOfScreen(){
    // new gameBall in screen
    GameBall gameBall = new GameBall(10, 10, 10, 10);
    // Should be false -> ball is in screen
    assertFalse(gameBall.outOfScreen());

    // Check on border of screen

    // new gameBall inside of screen
    gameBall = new GameBall(Constants.SCREEN_WIDTH, 0, 10, 10);
    // Should be true -> ball is outside of screen
    assertFalse(gameBall.outOfScreen());
    // new gameBall inside of screen
    gameBall = new GameBall(0, Constants.SCREEN_HEIGHT, 10, 10);
    // Should be true -> ball is outside of screen
    assertFalse(gameBall.outOfScreen());

    // Check just outside of screen

    // new gameBall outside of screen
    gameBall = new GameBall(Constants.SCREEN_WIDTH +1, 0, 10, 10);
    // Should be true -> ball is outside of screen
    assertTrue(gameBall.outOfScreen());
     // new gameBall outside of screen
     gameBall = new GameBall(0, Constants.SCREEN_HEIGHT +1, 10, 10);
     // Should be true -> ball is outside of screen
     assertTrue(gameBall.outOfScreen());
}

@Test
// The nethod playing for gameBall, consists of many private methods not available for testing
// Further down I have made tests for these private methods, however they do need to be made public 
public void testPlaying(){
    // New gameBall
    GameBall gameBall = new GameBall(10, 600, 10, 10);

    // The method playing needs four walls, as it checks for collsion
    // New Wallmanager, to get new walls -> you could also create your own walls
    // using new Wall
    WallManager wallManager = new WallManager();
    Wall wallL = wallManager.getWallL();
    Wall wallR = wallManager.getWallR();
    Wall wallT = wallManager.getWallTDiff1();
    Wall wallB = wallManager.getWallBDiff1();

    // Call method
    gameBall.playing(wallL, wallR, wallT, wallB);
    // What method does:
    // 1. Moves gameBall using direction
    // 2. Checks collision using isColliding() -> isColliding changes direction, and can add speed 

    // 1. Check if gameBall has moved
    // should have moved between 2-7 in x and y position depending on the new random direction
    // We do not have a getter for position, need to use getHitbox
    System.out.println(gameBall.getHitbox());

    // 2. If we now call playing again, gameBall should have the same direcction
    // And moved the same distance as previous time
    gameBall.playing(wallL, wallR, wallT, wallB);
    System.out.println(gameBall.getHitbox());
}

@Test
public void testUpdateDirection(){
    // New gameBall
    GameBall gameBall = new GameBall(10, 600, 10, 10);

    // Need walls
    WallManager wallManager = new WallManager();
    Wall wallL = wallManager.getWallL();
    Wall wallR = wallManager.getWallR();
    Wall wallT = wallManager.getWallTDiff1();
    Wall wallB = wallManager.getWallBDiff1();

    // Moves ball once
    gameBall.playing(wallL, wallR, wallT, wallB);
    System.out.println(gameBall.getHitbox());

    // Call update direction
    // X for colliding with wall L or R
    // Y for colliding with wall T or B
    gameBall.updateDirection("X");
    // If X switches xSpeed
    // If x or y speed is less than 7 adds one

    // Call playing
    // Moves ball once depending on direction
    gameBall.playing(wallL, wallR, wallT, wallB);
    System.out.println(gameBall.getHitbox());
}


/* @Test
// Checks if gameBall moves
// Needs to be made public
public void testMoveGameBall(){
    // new gameBall
    // Gets a new direction
    GameBall gameBall = new GameBall(0, 0, 10, 10);

    // Call method moveGameBall -> Moves gameBall, depending on direction
    gameBall.moveGameBall();
    // Check if moved
    System.out.println(gameBall.getHitbox());
} */

/* @Test
// Checks if gameBall is colldiing with walls
// @return true if colliding
// updates the direction of gameBall
// Needs to be made public
public void testIsColliding(){
    // New gameBall
    GameBall gameBall = new GameBall(10, 600, 10, 10);

    // Need walls
    WallManager wallManager = new WallManager();
    Wall wallL = wallManager.getWallL();
    Wall wallR = wallManager.getWallR();
    Wall wallT = wallManager.getWallTDiff1();
    Wall wallB = wallManager.getWallBDiff1();

    // Check if they are colliding, should be false
    assertFalse(gameBall.isColiding(wallL, wallR, wallT, wallB));

    // new gameBall in same position as wallL
    gameBall = new GameBall(0, Constants.SCREEN_HEIGHT/2, 10, 10);
    
    // Check if they are colliding, should be true
    assertTrue(gameBall.isColiding(wallL, wallR, wallT, wallB));
} */
}




