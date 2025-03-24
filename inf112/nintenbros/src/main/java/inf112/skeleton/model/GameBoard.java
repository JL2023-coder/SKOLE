package inf112.skeleton.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.skeleton.model.grid.*;


public class GameBoard extends Grid<Integer> {
    private static int cellSize = 32;

    // Constructor
    public GameBoard(int rows, int cols) {
        super(rows, cols, 0);  // Use default value '0' for initialization
        initializeMap();
    }

    private List<List<Integer>> getGrid() {
        return CSVReader.readCSV("src/main/resources/maps/map1.csv");
    }

   // Add values to grid
   private void initializeMap() {
        List<List<Integer>> map = getGrid();
        // Update grid to match map size
        setRows(map.size());
        setCols(map.get(0).size());
        for(int r = 0; r < rows(); r++){
            for(int c = 0; c < cols(); c++){
                set(new CellPosition(r, c), map.get(r).get(c));
            }
        }
    }

    public TextureRegion getTileBatch(int tileNum){
        return TileSet.getTile(tileNum);
    }

    public int getCellSize(){
        return cellSize;
    }
}



