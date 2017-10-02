package javagame2;

import javagame2.entities.combatunits.Player;
import lombok.Data;

import static oracle.jrockit.jfr.events.Bits.intValue;

@Data
public class Board {
    private Player player;
    private int boardSize;
    private int[][] boardArray;
    private int[][] pureBoard;

    public Board(int size, Player player){
        this.player = player;
        this.boardSize = size;
        this.boardArray = new int[size][size];
        this.pureBoard = new int [size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                boardArray[r][c] = -1;
            }
        }
        this.setUpBoard(boardArray);
        this.setUpBoard(pureBoard);
    }

    private void setUpBoard(int [][] board) {
        for (int r = 1; r < boardSize - 1; r++) {
            for (int c = 1; c < boardSize - 1; c++) {
                if ((c >= 8) || ((r == 3 || r == 4) && c >= 5) || (r >= 7 && c >= 6)){
                    board[r][c] = 4; //Harder Random Mob Spawn
                }else {
                    board[r][c] = 3;//Random Mob Spawn
                }
                if((r >= 4 && c == 2) || (r >= 9 && c == 1) || (r == 6 && (c >= 2 && c <= 6))){
                    board[r][c] = 0;//Paths
                }
                if(((r == 1 || r == 2) && (c == 5 || c == 7)) || (r == 9 && c == 10) || ( r == 10 && c == 8)){
                    board[r][c] = -1;//Barriers
                }
                if((r == 2 && c == 2) || (r == 4 && c == 9) || (r == 8 && c == 1) || (r == 10 && c == 7)){
                    board[r][c] = 1;//Chests
                }
                if(((r == 4 || r == 5) && c == 1) || (r == 7 && c == 6)){
                    board[r][c] = 2;//Shops
                }
                if((r == 1 && c == 6) || (r == 10 && c == 10)){
                    board[r][c] = 5;//Bosses
                }
                if((r == 1 && c == 4) || (r == 6 && c == 10)){
                    board[r][c] = 6;//Buffs
                }
                if((r == 2 && c == 6) || (r == 10 && c == 9)){
                    board[r][c] = 7;//Boss Warning
                }
                if(r == 1 && c == 10){
                    board[r][c] = 10;//Ultra Chest
                }
                if(r == 10 && c == 2 ){
                    board[r][c] = 8;
                }
            }
        }
        this.addPlayer();
    }

    public void updateBoard(){
        this.resetTile();
        this.addPlayer();
    }

    private void addPlayer( ){
        boardArray[intValue(player.getLocation().getX())]
                [intValue(player.getLocation().getY())] = 14;
    }

    private void resetTile(){
        boardArray[intValue(player.getPrevLocation().getX())]
                [intValue(player.getPrevLocation().getY())]
                = pureBoard[intValue(player.getPrevLocation().getX())]
                [intValue(player.getPrevLocation().getY())];
    }

    public void printBoard(){
        for (int  r = 0;  r < this.boardSize; r++){
            System.out.println();
            for (int c = 0; c < this.boardSize; c++) {
                if (boardArray[r][c] == -1) {
                    System.out.print(" " + "X");
                } else if (boardArray[r][c] == 0) {
                    System.out.print(" " + " ");
                } else if (boardArray[r][c] == 1) {
                    System.out.print(" " + "M");
                } else if (boardArray[r][c] == 2) {
                    System.out.print(" " + "S");
                } else if (boardArray[r][c] == 3) {
                    System.out.print(" " + "M");
                } else if (boardArray[r][c] == 4) {
                    System.out.print(" " + "M");
                } else if (boardArray[r][c] == 5) {
                    System.out.print(" " + "B");
                } else if (boardArray[r][c] == 6) {
                    System.out.print(" " + "M");
                } else if (boardArray[r][c] == 7) {
                    System.out.print(" " + " ");
                } else if (boardArray[r][c] == 8) {
                    System.out.print(" " + "N");
                } else if(boardArray[r][c] == 9){
                    System.out.print(" " + " ");
                } else if (boardArray[r][c] == 10) {
                    System.out.print(" " + "M");
                }
                if(boardArray[r][c] == 14){
                    System.out.print(" " + "U");
                }
            }
        }
        System.out.println();
    }

    public void printTrueBoard(){
        for (int  r = 0;  r < this.boardSize; r++){
            System.out.println();
            for (int c = 0; c < this.boardSize; c++) {
                if (boardArray[r][c] == -1) {
                    System.out.print(" " + "X");
                } else if (boardArray[r][c] == 0) {
                    System.out.print(" " + " ");
                } else if (boardArray[r][c] == 1) {
                    System.out.print(" " + "C");
                } else if (boardArray[r][c] == 2) {
                    System.out.print(" " + "S");
                } else if (boardArray[r][c] == 3) {
                    System.out.print(" " + "M");
                } else if (boardArray[r][c] == 4) {
                    System.out.print(" " + "W");
                } else if (boardArray[r][c] == 5) {
                    System.out.print(" " + "B");
                } else if (boardArray[r][c] == 6) {
                    System.out.print(" " + "+");
                } else if (boardArray[r][c] == 7) {
                    System.out.print(" " + " ");
                } else if (boardArray[r][c] == 8) {
                    System.out.print(" " + "N");
                } else if(boardArray[r][c] == 9){
                    System.out.print(" " + " ");
                } else if (boardArray[r][c] == 10) {
                    System.out.print(" " + "R");
                }
                if(boardArray[r][c] == 14){
                    System.out.print(" " + "U");
                }
            }
        }
        System.out.println();
    }
}
