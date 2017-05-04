/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mj.tic.tac.toe;

import com.mj.tic.tac.toe.model.TTTBoard;
import com.mj.tic.tac.toe.model.TTTCell;
import com.mj.tic.tac.toe.model.GameState;
import com.mj.tic.tac.toe.model.Player;

/**
 *
 * @author MJ
 */
public interface TicTacToeView {

    
    //For requesting board size
    
    public void printRequestForBoardSizeMessage();

    public void printInvalidInputForBoardSize();

    public int getTicTacToeBoardSize();
    
    
    //For requesting player name

    public void printRequestForPlayerNameMessage(int playerNo);

    public void printInvalidInputForPlayerName(int playerNo);

    public String getPlayerName();

    
    //For requesting player next move

    public void printRequestForPlayerNextMoveMessage(Player player);
    
    public void printInvalidMove(TTTCell move, int boardCols);

    public TTTCell getPlayerNextMove(int boardCols);
    
    
    //For updating board view and final result.

    public void refreshBoardView(TTTBoard board);
    
    public void printGameState(GameState state, Player player);

}
