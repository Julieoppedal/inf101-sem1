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

    public TetrisModel(TetrisBoard board,TetrominoFactory factory) {
        this.board = board;
        this.factory = factory;
        this.fallingTile = factory.getNext().shiftedToTopCenterOf(board);
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
    public Iterable<GridCell<Character>> getFallingTile() {
        return fallingTile;
    }

    @Override
    public Boolean moveTetromino(int deltaRow, int deltaCol) {
        Tetromino shiftedTetromino = this.fallingTile.shiftedBy(deltaRow, deltaCol);
        if (isValidPosition(shiftedTetromino)) {
            this.fallingTile = shiftedTetromino;
            return true;
        }
        return false;
    }

    private Boolean isValidPosition(Tetromino shiftedTetromino) {
        for(GridCell<Character> tile : shiftedTetromino) {
            CellPosition tilePosition = tile.pos();
            if(!(board.positionIsOnGrid(tilePosition)) || board.get(tilePosition) != '-') {
                return false;
            }
        }
        return true;
    }

    

}
