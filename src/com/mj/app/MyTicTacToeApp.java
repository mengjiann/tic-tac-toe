/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mj.app;

import com.mj.tic.tac.toe.TicTacToe;

/**
 *
 * @author MJ
 */
public class MyTicTacToeApp {
    public static void main(String[] args) {
        
        MyTicTacToeAppView view = new MyTicTacToeAppView();
        
        new TicTacToe(view);
    }
}
