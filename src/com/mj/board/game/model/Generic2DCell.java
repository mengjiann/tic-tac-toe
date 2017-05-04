/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mj.board.game.model;

import com.mj.tic.tac.toe.model.Seed;

/**
 *
 * @author Chua Hock Chuan from
 * https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html
 * MJ - Done some re-fractoring 
 */
public abstract class Generic2DCell {

    int row, col; // row and column of this cell
    
    public Generic2DCell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    abstract public void clear();

}
