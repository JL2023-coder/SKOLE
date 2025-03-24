package inf112.skeleton.view;

import inf112.skeleton.model.GameBoard;
import inf112.skeleton.model.GameCharacter;

public interface ViewableModel {
    public GameCharacter getPlayer();
    public GameBoard getBoard();
}
