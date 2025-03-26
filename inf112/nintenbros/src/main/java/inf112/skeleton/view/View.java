package inf112.skeleton.view;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import inf112.skeleton.model.GameBoard;
import inf112.skeleton.model.GameCharacter;
import inf112.skeleton.model.GameModel;
import inf112.skeleton.model.grid.CellPosition;

public class View {
    private ShapeRenderer shapeRenderer;
    private ViewableModel model;
    private SpriteBatch batch;

    public View(ViewableModel model){
        this.model = model;
        this.shapeRenderer = new ShapeRenderer();
    }

    public void render() {
        GameBoard gameBoard = model.getBoard();
        int cellSize = gameBoard.getCellSize();
        
        
        // Adding tiles
        batch = new SpriteBatch();
        batch.begin();
        for (int row = 0; row < gameBoard.rows(); row++) {
            for (int col = 0; col < gameBoard.cols(); col++) {
                CellPosition pos = new CellPosition(row, col);

                int x = col * cellSize;
                int y = (gameBoard.rows() - row - 1) * cellSize;
                
		        batch.draw(gameBoard.getTileBatch(gameBoard.get(pos)), x - model.getPlayerXPos() + model.getSpawnPointXPos(), y, cellSize, cellSize);
            }
        }
        batch.end();

        //outline
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        for (int row = 0; row < gameBoard.rows(); row++) {
            for (int col = 0; col < gameBoard.cols(); col++) {
                CellPosition pos = new CellPosition(row, col);

                int x = col * cellSize;
                int y = (gameBoard.rows() - row - 1) * cellSize;               
                shapeRenderer.rect(x, y, cellSize, cellSize);
            }
        }
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.ORANGE);  // Set rectangle color
        shapeRenderer.rect(model.getSpawnPointXPos(), model.getPlayerYPos(), model.getPlayerWidth(), model.getPlayerHeight()); // (x, y, width, height)
        shapeRenderer.end();

        //---------------------

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 0, 0, 1); // Red color
        shapeRenderer.circle(model.getSpawnPointXPos(), model.getPlayerYPos(), 5);
        shapeRenderer.end();
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}

