package inf112.skeleton.app;

import inf112.skeleton.controller.*;
import inf112.skeleton.model.*;
import inf112.skeleton.view.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class MyGame extends ApplicationAdapter {
    private GameModel model;
    private View view;
    private GameController controller;

    @Override
    public void create() {
        model = new GameModel();
        view = new View(model);
        controller = new GameController(model);
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update model using the controller
        controller.update(delta);

        // Render view
        view.render();
    }

    @Override
    public void dispose() {
        view.dispose();
    }
}
