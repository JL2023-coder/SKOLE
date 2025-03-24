package inf112.skeleton.model;

import inf112.skeleton.controller.*;
import inf112.skeleton.model.grid.CellPosition;
import inf112.skeleton.view.*;

public class GameModel implements ControllableModel, ViewableModel {
    private GameCharacter player;
    private GameBoard board;

    public GameModel(){
        this.player = new GameCharacter(150f, 280f, 48f, 64f);
        board = new GameBoard(20, 30);
    }

    @Override
    public void update(float delta, int input) {
        player.update(delta, input, board);
    }

    @Override
    public GameCharacter getPlayer() {
        return player;
    }

    @Override
    public GameBoard getBoard() {
        return board;
    }
    
    @Override
    public void playerJump(){
        this.player.jump();
    }
}
