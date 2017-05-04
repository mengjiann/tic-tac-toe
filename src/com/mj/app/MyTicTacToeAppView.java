/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mj.app;

import com.mj.tic.tac.toe.model.TTTBoard;
import com.mj.tic.tac.toe.model.TTTCell;
import com.mj.tic.tac.toe.model.GameState;
import com.mj.tic.tac.toe.model.Player;
import java.util.Scanner;
import com.mj.tic.tac.toe.TicTacToeView;

/**
 *
 * @author MJ
 */
public class MyTicTacToeAppView implements TicTacToeView {
    private static final boolean _DEBUG = false;
    private static final Scanner in = new Scanner(System.in);  // input Scanner

    //For requesting board size
    @Override
    public void printRequestForBoardSizeMessage() {
        printNewLine();
        println("Enter board size:");
    }

    @Override
    public void printInvalidInputForBoardSize() {
        println("Board size should not be less than 3. Try again...");
    }
    
    @Override
    public int getTicTacToeBoardSize() {
        validateInputAsInt();
        int boardSize = in.nextInt();

        printDebug("Entered board size: " + boardSize);
        in.nextLine();
        return boardSize;
    }

    
    
    //For requesting player name
    @Override
    public void printInvalidInputForPlayerName(int playerNo) {
        printNewLine();
        println("Invalid name for Player " + playerNo + ". Try again...");
    }
    
    @Override
    public void printRequestForPlayerNameMessage(int playerNo) {
        printNewLine();
        println("Enter name for Player " + playerNo);
    }

    @Override
    public String getPlayerName() {
        String playerName = in.nextLine();
        printDebug("Entered player name: " + playerName);
        return playerName;
    }
    
    

    //For requesting player next move
    @Override
    public void printRequestForPlayerNextMoveMessage(Player player) {
        printNewLine();
        println(player.getName() + ", choose a box to place an '" + player.getSeed().symbol + "' into: ");
    }
    
    @Override
    public void printInvalidMove(TTTCell move, int boardCols) {
        printNewLine();
        println("This move at (" + convertToBoardCellView(move, boardCols) + ") is not valid. Try again...");
    }
    
    @Override
    public TTTCell getPlayerNextMove(int boardCols) {
        validateInputAsInt();
        int move = in.nextInt();
        TTTCell moveCell = translateUserMoveInIntegerToCell(move, boardCols);
        printDebug(moveCell.toString());
        return moveCell;
    }
    
    private TTTCell translateUserMoveInIntegerToCell(int move, int boardCols) {

        int divOpValue = move / boardCols;
        int modOpValue = move % boardCols;
        
        printDebug("Move: " + move + " Cols: " + boardCols);
        printDebug("Div: " + move / boardCols + " Mod: " + move % boardCols);
        
        return new TTTCell(modOpValue == 0 ? divOpValue : (divOpValue + 1),
                (modOpValue == 0 ? boardCols : modOpValue));
    }
    
    
    
    //For updating board view and final result.
    @Override
    public void refreshBoardView(TTTBoard board) {

        int rows = board.getRows();
        int cols = board.getCols();
        TTTCell[][] cells = board.getCells();
        
        int maxBoardCellValue = board.getRows()*board.getRows();
        int digitCountForMaxBoardValue = getNumberOfDigitForInteger(maxBoardCellValue);

        printNewLine();
        // Print each rows.
        for (int row = 0; row < rows; ++row) {
            // Print each columns
            for (int col = 0; col < cols; ++col) {
                printCell(cells[row][col], board.getCols(), digitCountForMaxBoardValue);
                if (col < cols - 1) {
                    print("|");
                }
            }
            
            //Print row separator
            println("");
            if (row < rows - 1) {
                for (int col = 0; col < cols; ++col) {
                    print("--"+repeatCharacterForCount("-", digitCountForMaxBoardValue)+"-");
                }
            }
            println("");

        }
    }
    
    public void printCell(TTTCell cell, int boardCols, int digitCountForMaxBoardValue) {
        switch (cell.getSeed()) {
            case CROSS:
                print(" "+repeatCharacterForCount(" ", digitCountForMaxBoardValue-1)+"X ");
                break;
            case NOUGHT:
                print(" "+repeatCharacterForCount(" ", digitCountForMaxBoardValue-1)+"O ");
                break;
            case EMPTY:
                int cellVal = convertToBoardCellView(cell, boardCols);
                // Add padding for symbol "0" for required cell value
                int digitCountForCellValue = getNumberOfDigitForInteger(cellVal);
                print(" " + repeatCharacterForCount("0", digitCountForMaxBoardValue - digitCountForCellValue) 
                        + cellVal + " ");
                break;
        }
    }
    
    private int getNumberOfDigitForInteger(int integer){
        if(integer == 0){
            return 1;
        }
        int count=0;
        while(integer>0){
            integer=integer/10;
            count++;
        }
        return count;
    }
    
    private String repeatCharacterForCount(String symbol, int count){
        String symbolStr = "";
        for(int i=0; i < count;i++){
            symbolStr = symbolStr + symbol;
        }
        return symbolStr;
    }
    
    private int convertToBoardCellView(TTTCell cell, int boardCols) {
        return (cell.getRow() - 1) * boardCols + cell.getCol();
    }


    @Override
    public void printGameState(GameState currentState, Player player) {
        printNewLine();
        if (currentState == GameState.CROSS_WON || currentState == GameState.NOUGHT_WON) {
            println("Congratulations " + player.getName() + "! You have won.");
        } else if (currentState == GameState.DRAW) {
            println("Too bad. It's Draw!");
        }
    }

    // Validdation
    private void validateInputAsInt(){
        while(!in.hasNextInt()){
            String input = in.next();
            println(input+" is not a valid number. Please enter again.");
        }
    }
    
    
    // Printing to screen
    private void printNewLine() {
        System.out.println("\n");
    }

    private void println(String str) {
        System.out.println(str);
    }

    private void print(String str) {
        System.out.print(str);
    }

    private void printDebug(String str) {
        if (_DEBUG) {
            System.out.println(str);
        }
    }

}
