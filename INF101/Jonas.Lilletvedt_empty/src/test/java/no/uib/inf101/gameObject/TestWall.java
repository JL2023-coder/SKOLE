package no.uib.inf101.gameObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Rectangle;

import org.junit.jupiter.api.Test;

import no.uib.inf101.gameObject.Wall;
import no.uib.inf101.res.Constants;

public class TestWall {

/* @Test
// Method needs to be made public -> Is currently private
// Move wall
// Should not mpve wall off screen
// Needs to be made public
public void testMoveWall(){

    // New wall at (100, 100)
    Wall wall = new Wall(100, 100, 10, 10);


    // Moves player 50 in x-direction
    // Moves player 100 in y-direction
    wall.moveWall(50, 100);

    // Need to use getHitbox, no getters or settes for position
    assertEquals(wall.getHitbox(), new Rectangle(150, 200, 10, 10));

    // Moves player 50 in x-direction
    // Moves player 2000 in y-direction
    // Wall shold not be able to be moved of screen
    wall.moveWall(50, 2000);

    // Need to use getHitbox, no getters or settes for position
    // Screen Height minus width
    assertEquals(wall.getHitbox(), new Rectangle(200, Constants.SCREEN_HEIGHT-10, 10, 10));
} */


@Test
// Method moves L and R wall depending on keyInput
public void testPlayingX(){
    // New wall at (100, 100)
    Wall wall = new Wall(100, 100, 10, 10);

    // Call method
    // Should not do anything, start keyInput is I -> Idle
    wall.playingX();

    // Need to use getHitbox, no getters or settes for position
    assertEquals(wall.getHitbox(), new Rectangle(100, 100, 10, 10));

    // SetKeyStatus to Up
    wall.setKeyStatus("U");
    // Call method
    wall.playingX();

    // Need to use getHitbox, no getters or settes for position
    // Subtract wall speed to y position
    assertEquals(wall.getHitbox(), new Rectangle(100, 100 - Constants.Wall_SPEED, 10, 10));

    // SetKeyStatus to Down
    wall.setKeyStatus("D");
    // Call method
    wall.playingX();

    // Need to use getHitbox, no getters or settes for position
    // New position should be equal to start position
    assertEquals(wall.getHitbox(), new Rectangle(100, 100, 10, 10));
}

@Test
// Method moves T and B wall depending on keyInput
public void testPlayingY(){
        // New wall at (100, 100)
        Wall wall = new Wall(100, 100, 10, 10);

        // Call method
        // Should not do anything, start keyInput is I -> Idle
        wall.playingY();
    
        // Need to use getHitbox, no getters or settes for position
        assertEquals(wall.getHitbox(), new Rectangle(100, 100, 10, 10));
    
        // SetKeyStatus to Left
        wall.setKeyStatus("L");
        // Call method
        wall.playingY();
    
        // Need to use getHitbox, no getters or settes for position
        // Subtract wall speed to x position
        assertEquals(wall.getHitbox(), new Rectangle(100- Constants.Wall_SPEED, 100, 10, 10));
    
        // SetKeyStatus to Right
        wall.setKeyStatus("R");
        // Call method
        wall.playingY();
    
        // Need to use getHitbox, no getters or settes for position
        // New position should be equal to start position
        assertEquals(wall.getHitbox(), new Rectangle(100, 100, 10, 10));
}  
}
  