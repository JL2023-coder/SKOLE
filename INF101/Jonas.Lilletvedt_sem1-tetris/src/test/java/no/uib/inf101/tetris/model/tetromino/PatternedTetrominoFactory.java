package no.uib.inf101.tetris.model.tetromino;

public class PatternedTetrominoFactory implements TetrominoFactory{
    // instance variables
    private String string;
    private int index;
    
    
    // constructor
    public PatternedTetrominoFactory(String string) {
        this.string = string;
        this.index = 0;  // index is set at 0
    }

    @Override
    public Tetromino getNext() {
        char type = string.charAt(index);  // gets type of tetromino, char

        index++;   // index increases each time, to get the next char from the string
        if (index >= string.length()){ // if index is above string length set index to 0, start over
            index = 0; 
        }
        return Tetromino.newTetromino(type); // @return a new tetromino with random type
    }

    
}
