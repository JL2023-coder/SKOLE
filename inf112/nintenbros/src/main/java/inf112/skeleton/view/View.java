package inf112.skeleton.view;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

                // System.out.println("pos=" + pos);
                // System.out.println("-----");
                // System.out.println("value=" + gameBoard.get(pos));
                // System.out.println("-----");
                
		        batch.draw(gameBoard.getTileBatch(gameBoard.get(pos)), x, y, cellSize, cellSize);
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

        //-----------------------
        
        GameCharacter player = model.getPlayer();
        
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.ORANGE);  // Set rectangle color
        shapeRenderer.rect(player.getXPos(), player.getYPos(), player.getWidth(), player.getHeight()); // (x, y, width, height)
        shapeRenderer.end();

        //---------------------

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 0, 0, 1); // Red color
        shapeRenderer.circle(player.getXPos(), player.getYPos(), 5);
        shapeRenderer.end();
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
