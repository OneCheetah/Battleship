package battleship.logic;

import battleship.game.Board2D;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public final class GameManager {

    private final Board2D player1;
    private final Board2D player2;
    private static final Scanner in = new Scanner(System.in);
    private int player1HitShips = 0;
    private int player2HitShips = 0;

    public GameManager(Board2D player1, Board2D player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * This method is what starts the shooting/fighting process of the game
     */
    public void ShootingSystem() {

        Board2D currentPlayer;
        Board2D enemyPlayer;

        boolean isPlayer1Turn = true;

        while (true) {

            if (isPlayer1Turn) {
                currentPlayer = player1;
                enemyPlayer = player2;
            } else {
                currentPlayer = player2;
                enemyPlayer = player1;
            }

            currentPlayer.printBoard(currentPlayer.getHiddenBoard());
            System.out.println("---------------------");
            currentPlayer.printBoard(currentPlayer.getGameBoard());

            if (isPlayer1Turn) {
                System.out.println("Player 1, it's your turn:");
            } else {
                System.out.println("Player 2, it's your turn:");
            }

            try {
                String input = in.next();
                int x = input.charAt(0) - 65;
                int y = Integer.parseInt(input.substring(1)) - 1;

                if (enemyPlayer.getGameBoard()[x][y].equals("O") || enemyPlayer.getGameBoard()[x][y].equals("X")) {

                    if (enemyPlayer.getGameBoard()[x][y].equals("O")) {
                        if (isPlayer1Turn) {
                            player1HitShips++;
                        } else {
                            player2HitShips++;
                        }

                        int totalLengthOfShips = 17;
                        if (player1HitShips == totalLengthOfShips || player2HitShips == totalLengthOfShips) {
                            break;
                        }
                    }

                    enemyPlayer.getGameBoard()[x][y] = "X";
                    currentPlayer.getHiddenBoard()[x][y] = "X";

                    if (!isSunk(x, y, enemyPlayer)) {
                        System.out.println("You hit a ship!");
                        awaitInput();
                    } else {
                        System.out.println("You sank a ship! Specify a new target:");
                        awaitInput();
                    }

                } else {
                    enemyPlayer.getGameBoard()[x][y] = "M";
                    currentPlayer.getHiddenBoard()[x][y] = "M";

                    System.out.println("You missed!");
                    awaitInput();
                }
                isPlayer1Turn = !isPlayer1Turn;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            }
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    /**
     *
     * Checks weather or not a ship as been sunk
     *
     * @param x Coordinates
     * @param y Coordinates
     * @return boolean
     */
    private boolean isSunk(int x, int y, Board2D player) {
        int i = 1;
        int j = 1;
        int k = 1;
        int l = 1;

        // South Check
        while (true) {
            if (x + i < 10) {
                if (player.getGameBoard()[x + i][y].equals("~")) {
                    break;
                } else if ((player.getGameBoard()[x + i][y].equals("O"))) {
                    return false;
                }
            } else {
                break;
            }
            i++;
        }

        // North Check
        while (true) {
            if (x - j >= 0) {
                if (Objects.equals(player.getGameBoard()[x - j][y], "~")) {
                    break;
                } else if (Objects.equals(player.getGameBoard()[x - j][y], "O")) {
                    return false;
                }
            } else {
                break;
            }
            j++;
        }

        // East Check
        while (true) {
            if (y + k < 10) {
                if (Objects.equals(player.getGameBoard()[x][y + k], "~")) {
                    break;
                } else if (Objects.equals(player.getGameBoard()[x][y + k], "O")) {
                    return false;
                }
            } else {
                break;
            }
            k++;
        }

        // West Check
        while (true) {
            if (y - l >= 0) {
                if (Objects.equals(player.getGameBoard()[x][y - l], "~")) {
                    break;
                } else if (Objects.equals(player.getGameBoard()[x][y - l], "O")) {
                    return false;
                }
            } else {
                break;
            }
            l++;
        }

        return true;
    }

    public static void awaitInput() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
