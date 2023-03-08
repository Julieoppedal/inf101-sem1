package no.uib.inf101.tetris.model.tetromino;

public class PatternedTetrominoFactory implements TetrominoFactory{

    private final String letters;
    private int index;

    public PatternedTetrominoFactory(String letters) {
        this.letters = letters;
        this.index = 0;
        
    }

    @Override
    public Tetromino getNext() {
        char letter = letters.charAt(index);
        index = (index + 1) % letters.length();
        return Tetromino.newTetromino(letter);

    }

    
}
