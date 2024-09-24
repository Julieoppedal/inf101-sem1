package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

/**
 * Represents the Tetris game model. This class implements both the ViewableTetrisModel
 * and ControllableTetrisModel interfaces.
 */

public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {

    private final TetrisBoard board;
    private final TetrominoFactory factory;
    private Tetromino fallingTile;
    private GameState state;

    /**
     * Constructs a new TetrisModel object with the given TetrisBoard and TetrominoFactory.
     *
     * @param board   The TetrisBoard object to use as the game board.
     * @param factory The TetrominoFactory object to use for creating new Tetrominos.
     */

    public TetrisModel(TetrisBoard board,TetrominoFactory factory) {
        this.board = board;
        this.factory = factory;
        this.fallingTile = factory.getNext().shiftedToTopCenterOf(board);
        this.state = GameState.ACTIVE_GAME;
    }
    /**
     * Returns the dimensions of the game board.
     *
     * @return A GridDimension object representing the dimensions of the game board.
     */

    @Override
    public GridDimension getDimension() {
        return board;
    }
    
    /**
     * Returns an Iterable of GridCells representing the tiles currently on the game board.
     *
     * @return An Iterable of GridCells representing the tiles currently on the game board.
     */

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return board;
    }
    /**
     * Returns the current falling Tetromino.
     *
     * @return The current falling Tetromino.
     */

    @Override
    public Tetromino getFallingTile() {
        return fallingTile;
    }
    /**
     * Attempts to move the falling Tetromino by the given deltaRow and deltaCol values. If the
     * new position is valid, the Tetromino is moved and true is returned. Otherwise, the Tetromino
     * is not moved and false is returned.
     *
     * @param deltaRow The number of rows to move the Tetromino down (positive) or up (negative).
     * @param deltaCol The number of columns to move the Tetromino right (positive) or left (negative).
     * @return true if the Tetromino was moved successfully, false otherwise.
     */

    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol) {
        Tetromino shiftedTetromino = this.fallingTile.shiftedBy(deltaRow, deltaCol);
        if (isValidPosition(shiftedTetromino)) {
            this.fallingTile = shiftedTetromino;
            return true;
        }
        return false;
    }

    private boolean isValidPosition(Tetromino shiftedTetromino) {
        for(GridCell<Character> tile : shiftedTetromino) {
            CellPosition tilePosition = tile.pos();
            if(!(board.positionIsOnGrid(tilePosition)) || board.get(tilePosition) != '-') {
                return false;
            }
        }
        return true;
    }
/**

Attempts to rotate the falling Tetromino. If the new rotation is valid, the Tetromino is
rotated and true is returned. Otherwise, the Tetromino is not rotated and false is returned.
@return true if the Tetromino was rotated successfully, false otherwise.
*/
    @Override
    public boolean rotateTetromino() {
        Tetromino rotatedTetromino = this.fallingTile.rotate();
        if (isValidPosition(rotatedTetromino)) {
            this.fallingTile = rotatedTetromino;
            return true;
        }
        return false;
    }
/**

Drops the current falling Tetromino down to the lowest valid position on the game board and
attaches it to the board. A new Tetromino is then spawned and set as the new falling Tetromino.
@return true if the Tetromino was dropped and attached successfully, false otherwise.
*/
    @Override
    public boolean dropTetromino() {
        Tetromino movedTetromino = this.fallingTile;
        while (isValidPosition(movedTetromino.shiftedBy(1, 0))) {
            movedTetromino = movedTetromino.shiftedBy(1, 0);
        }
        this.fallingTile = movedTetromino;
        attachFallingTileToBoard();
        getNextFallingTile();
        return true;
    }
    
    private void getNextFallingTile() {
        Tetromino nextFallingTile = this.factory.getNext().shiftedToTopCenterOf(board);
        if (!isValidPosition(nextFallingTile)) {
            this.state = GameState.GAME_OVER;
        } else {
            this.fallingTile = nextFallingTile;
        }
    }
    
    private void attachFallingTileToBoard() {
        for (GridCell<Character> cell : fallingTile) {
            board.set(cell.pos(), cell.value());
        }
        board.removeFullRows();
    }
/**

Returns the current state of the game.
@return The current state of the game.
*/
    @Override
    public GameState getGameState() {
        return this.state;
    }
/**

Returns the time in milliseconds between each clock tick.
@return The time in milliseconds between each clock tick.
*/
    @Override
    public int getTimeBetweenTicks() {
        return 1000;
    }
/**

Advances the game by one clock tick. If the falling Tetromino can be moved down one row, it is
moved. Otherwise, the Tetromino is attached to the board and a new Tetromino is spawned and set
as the new falling Tetromino.
*/
    @Override
    public void clockTick() {
        if (moveTetromino(1, 0)) {
            // Tetrominoen kan flyttes nedover
            return;
        }
        // Tetrominoen kan ikke flyttes nedover, limer den fast
        attachFallingTileToBoard();
        getNextFallingTile();
    } 

}

