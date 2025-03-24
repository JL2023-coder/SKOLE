package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.model.tetromino.Tetromino;

public interface ControllableTetrisModel {

    // method makes us able to move a tetromino around the board
    // @return a boolean, tells us if the tetromino was sucessfully moved
    boolean moveTetromino(int deltaRow, int deltaCol);

    // method makes us able to rotate a tetromino
    void rotateTetromino();


    // method makes us able to drop a tetromino
    void dropTetromino();

    // @return GameState
    GameState getGameState();

    // @return miliseconds per tick
    int getMilisecondsPerTick();

    // method called each time the clock ticks
    // makes the tetromino drop
    void clockTick();



}
