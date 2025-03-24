package no.uib.inf101.tetris.model.tetromino;

import java.util.Random;

public class RandomTetrominoFactory implements TetrominoFactory{

    @Override  
    // @return a random tetromino type
    // inspired by https://stackoverflow.com/questions/28512351/selecting-a-random-char-in-a-string-in-java-with-a-certain-method
    public Tetromino getNext() {
        // tetromino types, each char has its own type
        String tetrominoTypes = "LJSZTIO";
        
        // picks random index, based on the length of the string
        Random random = new Random();
        int index = random.nextInt(tetrominoTypes.length());
        
        // takes the char at position index out in the string
        char randomType = tetrominoTypes.charAt(index);
        
        // @return a random tetromino type
        return Tetromino.newTetromino(randomType);
    }
}
