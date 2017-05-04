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
import com.mj.tic.tac.toe.model.Seed;

/**
 * With reference to: https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html
 * @author MJ
 */
public class TicTacToe {

    // Game components
    private TTTBoard board;
    private Player[] players;
    private TicTacToeView boardView;

    // State
    private GameState currentState;
    private Player currentPlayer;

    // Named-constants 
    public static final int PLAYER_COUNT = 2;
    public static final int PLAYER_CROSS = 0;
    public static final int PLAYER_NOUGHT = 1;
    public static final int MIN_BOARD_SIZE = 3;

    public TicTacToe(TicTacToeView view) {

        this.boardView = view;

        int boardSize = getBoardSize();

        initGame(boardSize);

        for (int i = 1; i <= PLAYER_COUNT; i++) {
            String playerName = getPlayerNameForPlayerNumber(i);
            players[i - 1].setName(playerName);
        }

        // Start the game with Player 1 - CROSS
        currentPlayer = players[PLAYER_CROSS];
        do {
            boardView.refreshBoardView(board);
            
            TTTCell nextMove = getValidMoveForPlayer();
            board.updateNextMove(nextMove);

            updateGameState(currentPlayer);

            // Switch player 
            currentPlayer = (currentPlayer.getSeed() == Seed.CROSS)
                    ? players[PLAYER_NOUGHT] : players[PLAYER_CROSS];

        } while (currentState == GameState.PLAYING);  // repeat until game-over
    }

    private void initGame(int boardSize) {
        
        // Init the board and clear the content
        board = new TTTBoard(boardSize, boardSize);
        board.initBoard();

        // Init the players
        players = new Player[PLAYER_COUNT];

        players[PLAYER_CROSS] = new Player(Seed.CROSS);
        players[PLAYER_NOUGHT] = new Player(Seed.NOUGHT);

        currentState = GameState.PLAYING;

    }

    private int getBoardSize() {
        boolean validInput = false;  // For validation.
        int boardSize = 0;

        do {
            boardView.printRequestForBoardSizeMessage();
            boardSize = boardView.getTicTacToeBoardSize();

            validInput = boardSize >= MIN_BOARD_SIZE;
            if (!validInput) {
                boardView.printInvalidInputForBoardSize();
            }
        } while (!validInput);

        return boardSize;
    }

    private String getPlayerNameForPlayerNumber(int playerNo) {
        boolean validInput = false;  // For validation.
        String playerName = "";

        do {
            boardView.printRequestForPlayerNameMessage(playerNo);
            playerName = boardView.getPlayerName();

            validInput = !playerName.trim().isEmpty();
            if (!validInput) {
                boardView.printInvalidInputForPlayerName(playerNo);
            }
        } while (!validInput);   // repeat until input is valid

        return playerName;
    }

    private TTTCell getValidMoveForPlayer() {
        boolean validInput = false;  // For validation.
        TTTCell move;

        do {
            boardView.printRequestForPlayerNextMoveMessage(currentPlayer);
            move = boardView.getPlayerNextMove(board.getCols());
            move.setSeed(currentPlayer.getSeed());

            validInput = board.isValidMove(move);
            if (!validInput) {
                boardView.printInvalidMove(move, board.getCols());
            }
        } while (!validInput);

        return move;
    }

    private void updateGameState(Player player) {

        Seed theSeed = player.getSeed();

        if (board.hasWon(theSeed)) {  // check for win
            currentState = (theSeed == Seed.CROSS)
                    ? GameState.CROSS_WON : GameState.NOUGHT_WON;

        } else if (board.isDraw()) {  // check for draw
            currentState = GameState.DRAW;
        }

        if (currentState == GameState.CROSS_WON
                || currentState == GameState.NOUGHT_WON
                || currentState == GameState.DRAW) {

            Player winPlayer = currentState == GameState.CROSS_WON
                    ? players[PLAYER_CROSS] : players[PLAYER_NOUGHT];

            boardView.printGameState(currentState, winPlayer);
        }
    }

}
