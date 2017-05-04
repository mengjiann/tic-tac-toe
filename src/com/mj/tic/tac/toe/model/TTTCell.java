/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mj.tic.tac.toe.model;

import com.mj.board.game.model.Generic2DCell;

/**
 *
 * @author Chua Hock Chuan from https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html
 * MJ - Done some re-fractoring 
 * 
 */

public class TTTCell extends Generic2DCell {
    
   Seed seed; // content of this cell of type Seed.
                 // take a value of Seed.EMPTY, Seed.CROSS, or Seed.NOUGHT
 
   /** Constructor to initialize this cell */
   public TTTCell(int row, int col) {
      super(row, col);
   }
 
   /** Clear the cell content to EMPTY */
   public void clear() {
      seed = Seed.EMPTY;
   }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }
   
}
