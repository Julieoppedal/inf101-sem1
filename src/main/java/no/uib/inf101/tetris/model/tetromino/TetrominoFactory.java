package no.uib.inf101.tetris.model.tetromino;


/**

The TetrominoFactory interface defines the contract for creating Tetromino objects.

A TetrominoFactory implementation should provide a way to generate a new Tetromino object

based on some logic or rules.
*/
public interface TetrominoFactory {

    Tetromino getNext();
/**

Generates the next Tetromino object based on some logic or rules.
@return a new Tetromino object.
*/
    
}
