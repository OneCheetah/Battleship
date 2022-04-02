package battleship.logic;

import battleship.game.data.Ship;

import java.util.Objects;
import java.util.Scanner;

public class BoardManager {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    /**
     * @param ship The current ship that we are checking
     * @return Weather or not the ship is the correct length
     */
    private boolean isProperLength(Ship ship) {
        if (x1 == x2) {
            return Math.abs(y1 - y2) == ship.getLength() - 1;
        } else if (y1 == y2) {
            return Math.abs(x1 - x2) == ship.getLength() - 1;
        }
        return true;
    }

    /**
     * @return Weather or not the location of the ship is valid
     */
    private boolean isValidLocation() {
        return x1 == x2 || y1 == y2;
    }

    /**
     * Checks weather are not the ship is too close/is colliding with another ship
     * @param gameBoard The current board we are using
     * @return boolean
     */
    private boolean isColliding(String[][] gameBoard) {
        for (int i = Math.min(y1, y2); i < Math.max(y1, y2) + 1; i++) {
            if (x1 > 0) {
                if (Objects.equals(gameBoard[x1 - 1][i], "O")) {
                    return true;
                }
            }
            if (x1 < 9) {
                if (Objects.equals(gameBoard[x1 + 1][i], "O")) {
                    return true;
                }
            }
            if (x1 >= 0 && x2 < 10) {
                if (Objects.equals(gameBoard[x1][i], "O")) {
                    return true;
                }
            }
        }
        for (int i = Math.min(x1, x2); i < Math.max(x1, x2) + 1; i++) {
            if (y1 > 0) {
                if (Objects.equals(gameBoard[i][y1 - 1], "O")) {
                    return true;
                }
            }
            if (y1 < 9) {
                if (Objects.equals(gameBoard[i][y1 + 1], "O")) {
                    return true;
                }
            }
            if (y1 >= 0 && i < 10) {
                if (Objects.equals(gameBoard[i][y1], "O")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *  Starts the ship placing process
     *
     * @param ship The ship that is being place
     * @param gameBoard The current game board that is being used
     */
    public void placeShips(Ship ship, String[][] gameBoard) {
        final Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] cord = input.split(" ");

        this.x1 = cord[0].charAt(0) - 65;
        this.y1 = Integer.parseInt(cord[0].substring(1)) - 1;
        this.x2 = cord[1].charAt(0) - 65;
        this.y2 = Integer.parseInt(cord[1].substring(1)) - 1;

        if (!isValidLocation()) {
            System.out.println("Error! Wrong ship location! Try again:");
            placeShips(ship, gameBoard);
        } else if (!isProperLength(ship)) {
            System.out.println("Error! Wrong length of the " + ship.getName() + "! Try again:");
            placeShips(ship, gameBoard);
        } else if (isColliding(gameBoard)) {
            System.out.println("Error! You placed it too close to another one. Try again:");
            placeShips(ship, gameBoard);
        }

        if (x1 == x2) {
            for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
                gameBoard[x1][i] = "O";
            }
        } else {
            for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                gameBoard[i][y1] = "O";
            }
        }
    }
}
