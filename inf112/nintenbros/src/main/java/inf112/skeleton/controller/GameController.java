package inf112.skeleton.controller;

import inf112.skeleton.view.*;
import inf112.skeleton.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class GameController implements InputProcessor{

    private ControllableModel model;
    private int leftInput;
    private int rightInput;

    public GameController (ControllableModel model){
        this.model = model;

    }

    public void update(float delta){
        model.update(delta, rightInput-leftInput);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            leftInput = 1;
            return true; // Event handled
        }
        if (keycode == Input.Keys.RIGHT) {
            rightInput = 1;
            return true; // Event handled
        }
        if (keycode == Input.Keys.UP) {
            this.model.playerJump();
        }
        return false; // Let other processors handle it
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            leftInput = 0;
            return true; // Event handled
        }
        if (keycode == Input.Keys.RIGHT) {
            rightInput = 0;
            return true; // Event handled
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}
