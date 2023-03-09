package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {

    private final TetrisBoard board;
    private final TetrominoFactory factory;
    private Tetromino fallingTile;
    private GameState state;

    public TetrisModel(TetrisBoard board,TetrominoFactory factory) {
        this.board = board;
        this.factory = factory;
        this.fallingTile = factory.getNext().shiftedToTopCenterOf(board);
        this.state = GameState.ACTIVE_GAME;
    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return board;
    }

    @Override
    public Tetromino getFallingTile() {
        return fallingTile;
    }

    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol) {
        Tetromino shiftedTetromino = this.fallingTile.shiftedBy(deltaRow, deltaCol);
        if (isValidPosition(shiftedTetromino)) {
            this.fallingTile = shiftedTetromino;
            return true;
        }
        return false;
    }

    boolean isValidPosition(Tetromino shiftedTetromino) {
        for(GridCell<Character> tile : shiftedTetromino) {
            CellPosition tilePosition = tile.pos();
            if(!(board.positionIsOnGrid(tilePosition)) || board.get(tilePosition) != '-') {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean rotateTetromino() {
        Tetromino rotatedTetromino = this.fallingTile.rotate();
        if (isValidPosition(rotatedTetromino)) {
            this.fallingTile = rotatedTetromino;
            return true;
        }
        return false;
    }

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

    @Override
    public GameState getGameState() {
        return this.state;
    }

    @Override
    public int getTimeBetweenTicks() {
        return 1000;
    }   

}

