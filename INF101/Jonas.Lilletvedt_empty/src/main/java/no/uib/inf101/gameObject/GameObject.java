package no.uib.inf101.gameObject;

import java.awt.Rectangle;

public abstract class GameObject {
    // Instance Variables
    protected int posX, posY;
    protected int width;
    protected int height;
    protected Rectangle hitbox;
    
    // Constructor
    public GameObject(int posX, int posY, int width, int height){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    // Get hitbox
    // @return hitbox
    public Rectangle getHitbox(){
        return hitbox;
    }

    // Updates hitbox for new x and y values
    public void updateHitbox(){
        hitbox.setLocation(posX, posY);
    }
}
