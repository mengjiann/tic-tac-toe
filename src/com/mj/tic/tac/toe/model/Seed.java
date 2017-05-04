/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mj.tic.tac.toe.model;

/**
 *
 * @author Chua Hock Chuan from https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html
 * MJ - Made some modification to suit my need.
 * 
 */
public enum Seed {
    EMPTY(""), 
    CROSS("x"), 
    NOUGHT("o");
    
    public String symbol;
    
    Seed(String symbol){
        this.symbol = symbol;
    }
}
