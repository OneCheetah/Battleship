package battleship.game;

import battleship.game.data.Ship;
import battleship.logic.GameManager;

import java.util.ArrayList;
import java.util.List;

public final class BattleField {

    private final List<Ship> ships = new ArrayList<>();
    private final Board2D player1 = new Board2D();
    private final Board2D player2 = new Board2D();
    private final GameManager game = new GameManager(player1, player2);

    public BattleField() {
        ships.add(new Ship("Aircraft Carrier", 5));
        ships.add(new Ship("Battleship", 4));
        ships.add(new Ship("Submarine", 3));
        ships.add(new Ship("Cruiser", 3));
        ships.add(new Ship("Destroyer", 2));
    }

    /**
     * Starts the game
     */
    public void start() {
        System.out.println("Player 1, place your ships on the game field");
        prepare(player1);

        System.out.println("Player 2, place your ships to the game field");
        prepare(player2);

        game.ShootingSystem();
    }

    /**
     * Starts the prep phase of the game/placing of ships
     */
    private void prepare(Board2D player2) {
        for (Ship ship : ships) {
            Board2D.printBoard(player2.getGameBoard());

            System.out.println("Enter the coordinates of the " + ship.getName() + " (" + ship.getLength() + " cells):");
            player2.placeShips(ship, player2.getGameBoard());
        }
        Board2D.printBoard(player2.getGameBoard());

        GameManager.awaitInput();
    }
}
