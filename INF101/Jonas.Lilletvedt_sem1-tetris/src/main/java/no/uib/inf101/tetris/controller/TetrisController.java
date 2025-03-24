package no.uib.inf101.tetris.controller;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.view.TetrisView;
import javax.swing.Timer;

public class TetrisController implements java.awt.event.KeyListener{

    // instance variables
    private ControllableTetrisModel controllableTetrisModel;
    private TetrisView tetrisView;
    private Timer timer;
    private TetrisSong tetrisSong;


    // constructor
    public TetrisController(ControllableTetrisModel controllableTetrisModel, TetrisView tetrisView) {
        this.controllableTetrisModel = controllableTetrisModel;
        this.tetrisView = tetrisView;

        // installs TetrisController-object as a listener for key events, when window is in focus
        // sets the Listener to active/true
        tetrisView.addKeyListener(this);
        tetrisView.setFocusable(true);

        // timer
        // does action per intervall
        this.timer = new Timer(controllableTetrisModel.getMilisecondsPerTick(), this::clockTick);

        // starts timer
        timer.start();

        tetrisSong = new TetrisSong();
        // start song
        tetrisSong.run();
    }

    @Override // method from KeyListener
    public void keyTyped(KeyEvent e) {
    }

    @Override // method from KeyListener
    public void keyPressed(KeyEvent e) {

    // only able to move and get new falling tetrominos if gameState == GameState.ACTIVE_GAME
    if (controllableTetrisModel.getGameState() == GameState.ACTIVE_GAME){
                
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        // Left arrow was pressed
        this.controllableTetrisModel.moveTetromino(0, -1);
        }
    
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        // Right arrow was pressed
        this.controllableTetrisModel.moveTetromino(0, 1);
        }
        
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        // Down arrow was pressed
        if(this.controllableTetrisModel.moveTetromino(1, 0)){

            // restarts timer
            timer.restart();
        }
    
        }
        
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
        // Up arrow was pressed
        this.controllableTetrisModel.rotateTetromino();
        }
        
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        // Spacebar was pressed
        this.controllableTetrisModel.dropTetromino();

        // restarts timer
        timer.restart();
        }

        tetrisView.repaint();
    }
    }

    @Override // method from KeyListener
    public void keyReleased(KeyEvent e) {
    }

    // makes a call to clockTick if gamne state is active
    public void clockTick(ActionEvent e){

        // if gamne state active -> call to clockTick
        // moves tetromino down
        if (controllableTetrisModel.getGameState() == GameState.ACTIVE_GAME){
            controllableTetrisModel.clockTick();

            // updates timer -> not used yet
            updateTimerDelay();

            // new screen
            tetrisView.repaint();
        }
    }

    // @return delay
    public int getDelay(){
        return controllableTetrisModel.getMilisecondsPerTick();
    }


    // updates delay -> nor sure if it will be used
    private void updateTimerDelay() {
        int delay = getDelay();
        timer.setDelay(delay);
        timer.setInitialDelay(delay);
    }



}
