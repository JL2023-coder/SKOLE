package inf112.skeleton.model;

import inf112.skeleton.controller.*;
import inf112.skeleton.model.grid.CellPosition;
import inf112.skeleton.view.*;

public class GameCharacter {

    private static float speed = 300f;

    private static float g = 15f;
    private boolean isGrounded;
    
    private float xVel;
    private float yVel;

    private float xPos;
    private float yPos;
    private float width;
    private float height;

    private boolean test;

    public GameCharacter(float x, float y, float width, float height){
        this.xPos = x;
        this.yPos = y;
        this.width = width;
        this.height = height;

        this.yVel = 0;
        this.isGrounded = false;
        this.test = false;
    }

    public float getXPos(){
        return xPos;
    }
    public float getYPos(){
        return yPos;
    }
    public float getWidth(){
        return width;
    }
    public float getHeight(){
        return height;
    }
    private void isGrounded(boolean grounded){
        this.isGrounded = grounded;
    }

    public void jump(){
        if(this.isGrounded){
            this.isGrounded = false;
            this.yVel = 7;
        }
    }

    public void update(float delta, int input, GameBoard board){
        if(!this.isGrounded){
            yVel -= g * delta;
        }
        xVel = speed * delta * input;

        yPos += yVel;
        collition(board, false);
        xPos += xVel;
        collition(board, true);

    }

    private void collition(GameBoard board, Boolean checkSides){
        int leftPos = (int) Math.floor(this.xPos / board.getCellSize());
        int rightPos = (int) Math.floor((this.xPos + this.width) / board.getCellSize());
        int topPos = (int) Math.ceil((this.yPos + this.height) / board.getCellSize());
        int botPos = (int) Math.ceil((this.yPos) / board.getCellSize());

        // System.err.println(leftPos);
        // System.err.println(botPos);
        // System.err.println(board.get(new CellPosition(board.rows() - botPos, leftPos)));
        // System.err.println("----");
       
        if(checkSides){
            for (int cellY = botPos; cellY < topPos; cellY++) {
                if(this.xVel < 0){
                    //leftCheck:
                    CellPosition leftHit = new CellPosition(board.rows()-cellY-1, leftPos);
                    if(board.get(leftHit) == 1){
                        this.xPos = (leftPos*board.getCellSize()) + board.getCellSize();
                        break;
                    }
                }else if(this.xVel > 0){
                    //rightCheck:
                    CellPosition rightHit = new CellPosition(board.rows()-cellY-1, rightPos);
                    if(board.get(rightHit) == 1){
                        this.xPos = (rightPos*board.getCellSize()) - this.width;
                        break;
                    }
                }
            }
        }else{
            //---------------------
            leftPos = (int) Math.floor((this.xPos + 1) / board.getCellSize());
            rightPos = (int) Math.floor((this.xPos + this.width - 1) / board.getCellSize());
    
            this.isGrounded = false;
            for (int cellX = leftPos; cellX <= rightPos; cellX++) {
                if(this.yVel <= 0){
                    //botCheck:
                    CellPosition botHit = new CellPosition(board.cols()-botPos, cellX);
                    if(board.get(botHit) == 1){
                        this.isGrounded = true;
                        if(this.yVel < 0){
                            this.yVel = 0;
                        }
                        this.yPos = botPos*board.getCellSize();
                        break;
                    }
                }else if(this.yVel > 0){
                    //topCheck:
                    CellPosition topHit = new CellPosition(board.cols()-topPos, cellX);
                    if(board.get(topHit) == 1){
                        this.yVel = 0;
                        this.yPos = (topPos*board.getCellSize()) - this.height - board.getCellSize();
                        break;
                    }
                }
            }
        }


    }

}