package inf112.skeleton.model;

import inf112.skeleton.controller.*;
import inf112.skeleton.model.grid.CellPosition;
import inf112.skeleton.view.*;

public class GameModel implements ControllableModel, ViewableModel {
    private GameCharacter player;
    private GameBoard board;
    // Set start spawn to 150
    private float spawnPointXPos = 150;

    public GameModel(){
        this.player = new GameCharacter(spawnPointXPos, 280f, 48f, 64f);
        board = new GameBoard(20, 30);
    }

    @Override
    public void update(float delta, int input) {
        player.update(delta, input, board);
    }

    @Override
    public float getPlayerXPos(){
        return player.getXPos();
    }

    @Override
    public float getPlayerYPos(){
        return player.getYPos();
    }

    @Override
    public float getPlayerWidth(){
        return player.getWidth();
    }

    @Override
    public float getPlayerHeight(){
        return player.getHeight();
    }

    @Override
    public GameBoard getBoard() {
        return board;
    }
    
    @Override
    public void playerJump(){
        this.player.jump();
    }

    public float getSpawnPointXPos(){
        return spawnPointXPos;
    }
}
