package no.uib.inf101.tetris.model.tetromino;

import java.util.Random;

public class RandomTetrominoFactory implements TetrominoFactory {

    private final String tetrominoShape = "LJSZTIO";
    private final Random random = new Random();

    @Override
    public Tetromino getNext() {
        char randomShape = tetrominoShape.charAt(random.nextInt(tetrominoShape.length()));
        return Tetromino.newTetromino(randomShape);
    }

    
    
}
