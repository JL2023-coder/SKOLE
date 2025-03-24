package no.uib.inf101.gameObject;

import no.uib.inf101.main.GamePanel;
import no.uib.inf101.res.Constants;

public class WallManager {
    // Gets walls for different difficulties
    public WallManager(){
    }

    // Gets WallL
    // @return WallL
    public Wall getWallL(){
        return new Wall(0, Constants.SCREEN_HEIGHT/2, Constants.X_WALL_WIDTH, Constants.X_WALL_HEIGHT);
    }

    // Gets WallR
    // @return WallR
    public Wall getWallR(){
        return new Wall(Constants.SCREEN_WIDTH-Constants.X_WALL_WIDTH, Constants.SCREEN_HEIGHT/2, Constants.X_WALL_WIDTH, Constants.X_WALL_HEIGHT); 
    }

    // Gets WallT
    // @return WallT
    public Wall getWallTDiff1(){
        return new Wall(0, 0, Constants.SCREEN_WIDTH, Constants.Y_WALL_HEIGHT);
    }

    // Gets WallB
    // @return WallB
    public Wall getWallBDiff1(){
        return new Wall(0, Constants.SCREEN_HEIGHT-Constants.Y_WALL_HEIGHT, Constants.SCREEN_WIDTH, Constants.Y_WALL_HEIGHT);
    }
    
    // Gets WallT difficulty 2
    // @return WallT
    public Wall getWallTDiff2(){
        return new Wall(Constants.SCREEN_WIDTH/2, 0, Constants.Y_WALL_WIDTH, Constants.Y_WALL_HEIGHT);
    }

    // Gets WallLB difficulty 2
    // @return WallB
    public Wall getWallBDiff2(){
        return new Wall(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT-Constants.Y_WALL_HEIGHT, Constants.Y_WALL_WIDTH, Constants.Y_WALL_HEIGHT);
    }
}

