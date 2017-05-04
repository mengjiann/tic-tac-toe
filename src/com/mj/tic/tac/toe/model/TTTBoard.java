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
        //Condition 1  - Top left
        if(currentRow - 2 >= 0 && currentCol - 2 >= 0 
                && cells[currentRow-2][currentCol-2].seed == theSeed 
                && cells[currentRow-1][currentCol-1].seed == theSeed){
            return true;
        }
    
        //Condition 2 - Top
        if(currentRow - 2 >= 0
                && cells[currentRow-2][currentCol].seed == theSeed 
                && cells[currentRow-1][currentCol].seed == theSeed){
            return true;
        }
        
        //Condition 3 - Top Right
        if(currentRow - 2 >= 0 && currentCol + 2 <= this.cols-1
                && cells[currentRow-2][currentCol+2].seed == theSeed 
                && cells[currentRow-1][currentCol+1].seed == theSeed){
            return true;
            
        }
        
        //Condition 4 - Right
        if(currentCol + 2 <= this.cols-1
                && cells[currentRow][currentCol+1].seed == theSeed 
                && cells[currentRow][currentCol+2].seed == theSeed){
            return true;
            
        }
        
        // Condition 5 - Bottom Right
        if(currentRow + 2 <= this.rows-1 && currentCol + 2 <= this.cols-1
                && cells[currentRow+1][currentCol+1].seed == theSeed 
                && cells[currentRow+2][currentCol+2].seed == theSeed){
            return true;
        }
        
        // Condition 6 - Bottom
        if(currentRow + 2 <= this.rows-1 
                && cells[currentRow+1][currentCol].seed == theSeed 
                && cells[currentRow+2][currentCol].seed == theSeed){
            return true;
        }
        
        // Condition 7 - Bottom Left
        if(currentRow + 2 <= this.rows-1 && currentCol - 2 >= 0
                && cells[currentRow+1][currentCol-1].seed == theSeed 
                && cells[currentRow+2][currentCol-2].seed == theSeed){
            return true;
        }
        
        // Condition 8 - Right
        if(currentCol - 2 >= 0
                && cells[currentRow][currentCol-1].seed == theSeed 
                && cells[currentRow][currentCol-2].seed == theSeed){
            return true;
        }
        
        //Smaller box
        // Condition 9 - 3 in the row 
        if(currentRow - 1 >= 0 && currentRow + 1 <= this.rows-1 
                && cells[currentRow-1][currentCol].seed == theSeed 
                && cells[currentRow+1][currentCol].seed == theSeed){
            return true;
        }
        
        //Condition 10 - 3 in the collumn
        if(currentCol - 1 >= 0 && currentCol + 1 <= this.cols-1 
                && cells[currentRow][currentCol-1].seed == theSeed 
                && cells[currentRow][currentCol+1].seed == theSeed){
            return true;
        }
        
        // Condition 11 //Diagonal
        if(currentRow - 1 >= 0 && currentCol - 1 >= 0 &&
                currentRow + 1 <= this.rows-1 && currentCol + 1 <= this.cols-1){
            return (
                cells[currentRow-1][currentCol-1].seed == theSeed 
                && cells[currentRow+1][currentCol+1].seed == theSeed
                || 
                cells[currentRow-1][currentCol+1].seed == theSeed 
                && cells[currentRow+1][currentCol-1].seed == theSeed);
            
        }
        return false;
        
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
