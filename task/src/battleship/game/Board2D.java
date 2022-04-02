package battleship.game;

import battleship.logic.BoardManager;

import java.util.Arrays;

public final class Board2D extends BoardManager{
    private final String[][] gameBoard = new String[10][10];
    private final String[][] hiddenBoard = new String[10][10];

    public Board2D() {
        // Fill the game board with ~
        for (String[] strings : gameBoard) {
            Arrays.fill(strings, "~");
        }
        // Fill the hidden board with ~
        for (String[] strings : hiddenBoard) {
            Arrays.fill(strings, "~");
        }
    }

    public String[][] getGameBoard() {
        return gameBoard;
    }

    public String[][] getHiddenBoard() {
        return hiddenBoard;
    }

    /**
     * Prints out the game board
     * @param gameBoard The game board you want printed
     */
    public static void printBoard(String[][] gameBoard) {
        char letter = 'A';
        for (int i = 0; i < gameBoard.length + 1; i++) {
            if (i == 0) {
                System.out.println("  1 2 3 4 5 6 7 8 9 10");
                continue;
            }
            for (int j = 0; j < gameBoard[i - 1].length; j++) {
                if (j == 0) {
                    System.out.print(letter + " " + gameBoard[i - 1][j] + " ");
                    letter++;
                } else {
                    System.out.print(gameBoard[i - 1][j] + " ");
                }
            }
            System.out.println();
        }
    }
}