/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mj.board.game.model;

/**
 *
 * @author Chua Hock Chuan from
 * https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html
 * MJ - Done some re-fractoring 
 */
public abstract class Generic2DBoard {
    
    protected int rows, cols;
    protected int currentRow, currentCol;  // the current position for cell

    public Generic2DBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }
    
    abstract public void initBoard();
    
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    

}
