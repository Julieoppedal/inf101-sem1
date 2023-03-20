package no.uib.inf101.tetris.model.tetromino;

import java.util.Random;

/**

This class represents a factory for generating random Tetromino objects.
It implements the TetrominoFactory interface and provides a method to generate a random Tetromino
from a set of possible shapes.
The possible shapes are stored in the private String field "tetrominoShape" as characters, which represent
the following shapes: L, J, S, Z, T, I, O.
The getNext() method returns a new Tetromino object with a random shape selected from the "tetrominoShape" field.
*/

public class RandomTetrominoFactory implements TetrominoFactory {

    private final String tetrominoShape = "LJSZTIO";
    private final Random random = new Random();

    @Override
    public Tetromino getNext() {
        char randomShape = tetrominoShape.charAt(random.nextInt(tetrominoShape.length()));
        return Tetromino.newTetromino(randomShape);
    }

    
    
}
