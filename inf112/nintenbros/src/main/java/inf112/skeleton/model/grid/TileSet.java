package inf112.skeleton.model.grid;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class TileSet {
    private static Texture tileSet;
    
    static {
        tileSet = getImage();
    }
    
    private static Texture getImage(){
        Texture tileSet = new Texture(Gdx.files.internal("tileset.png"));
        return tileSet;
    }

    // Returns TextureRegion, a subimage from tileSet.png, corresponding tile to tileNum
    public static TextureRegion getTile(int tileNum){
        ArrayList<Integer> tilePos = tileNumToTilePosConverter(tileNum);
            int x = tilePos.get(1) * 16;
            int y = tilePos.get(0) * 16;
    
            TextureRegion tile = new TextureRegion(tileSet, x, y, 16, 16);
        
        return tile;
    }
        
    private static ArrayList<Integer> tileNumToTilePosConverter(int tileNum){
        ArrayList<Integer> tilePostions = new ArrayList<Integer>(2);

        int columns = 16;
    
        int rowNum = (tileNum) / columns; 
        int colNum = (tileNum) % columns;

        // System.out.println("tileNum = " +tileNum);
        // System.out.println("col:" + colNum + "row:" + rowNum);
        // System.out.println("--------");

        tilePostions.add(rowNum);
        tilePostions.add(colNum);

        return tilePostions;
    }

}
