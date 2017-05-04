/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mj.tic.tac.toe.model;

import com.mj.board.game.model.Generic2DBoard;

/**
 *
 * @author Chua Hock Chuan from
 * https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html
 * MJ - Done some re-fractoring 
 */
public class TTTBoard extends Generic2DBoard {

    private TTTCell[][] cells;  // a board composes of rows-by-COLS TTTCell instances
  
    /**
     * Initialize (or re-initialize) the seeds of the game board
     */
    @Override
    public void initBoard() {
        cells = new TTTCell[rows][cols];  // allocate the array
        
        // allocate element of the array
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                cells[row][col] = new TTTCell(row + 1, col + 1); 
                cells[row][col].clear();
            }
        }
    }

    /**
     * Return true if it is a draw (i.e., no more EMPTY cell)
     */
    public boolean isDraw() {
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (cells[row][col].seed == Seed.EMPTY) {
                    return false; // an empty seed found, not a draw, exit
                }
            }
        }
        return true; // no empty cell, it's a draw
    }

    /**
     * Return true if the player with "theSeed" has won after placing at
     * (currentRow, currentCol)
     */
    public boolean hasWon(Seed theSeed) {
        return (cells[currentRow][0].seed == theSeed // 3-in-the-row
                && cells[currentRow][1].seed == theSeed
                && cells[currentRow][2].seed == theSeed
                || cells[0][currentCol].seed == theSeed // 3-in-the-column
                && cells[1][currentCol].seed == theSeed
                && cells[2][currentCol].seed == theSeed
                || currentRow == currentCol // 3-in-the-diagonal
                && cells[0][0].seed == theSeed
                && cells[1][1].seed == theSeed
                && cells[2][2].seed == theSeed
                || currentRow + currentCol == 2 // 3-in-the-opposite-diagonal
                && cells[0][2].seed == theSeed
                && cells[1][1].seed == theSeed
                && cells[2][0].seed == theSeed);
    }

    /**
     * Return true if the move is within the board size. (currentRow,
     * currentCol)
     */
    public boolean isValidMove(TTTCell move) {

        // Translate to array
        int row = move.getRow() - 1;
        int col = move.getCol() - 1;

        if (row >= 0 && row < this.rows && col >= 0 && col < this.cols
                && cells[row][col].getSeed() == Seed.EMPTY) {
            return true;
        }
        return false;
    }

    /**
     * Update next move 
     */
    public void updateNextMove(TTTCell move) {
        // Translate to array
        int row = move.getRow() - 1;
        int col = move.getCol() - 1;

        cells[row][col].setSeed(move.getSeed());
        currentRow = row;
        currentCol = col;
    }
    
    public TTTBoard(int rows, int cols) {
        super(rows, cols);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public TTTCell[][] getCells() {
        return cells;
    }

}
