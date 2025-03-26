package inf112.skeleton.view;

import inf112.skeleton.model.GameBoard;
import inf112.skeleton.model.GameCharacter;

public interface ViewableModel {
    public float getPlayerXPos();
    public float getPlayerYPos();
    public float getPlayerWidth();
    public float getPlayerHeight();
    public GameBoard getBoard();
    public float getSpawnPointXPos(); 
}
