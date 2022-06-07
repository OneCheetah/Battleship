package battleship.game;

import battleship.game.data.Ship;
import battleship.logic.GameManager;

public final class BattleField {

    private final Ship[] ships = {new Ship("Aircraft Carrier", 5),
        new Ship("Battleship", 4), new Ship("Submarine", 3),
        new Ship("Cruiser", 3), new Ship("Destroyer", 2)};
    private final Board2D player1 = new Board2D();
    private final Board2D player2 = new Board2D();
    private final GameManager game = new GameManager(player1, player2);

    /**
     * Starts the game
     */
    public void start() {
        System.out.println("Player 1, place your ships on the game field");
        prepare(player1);

        System.out.println("Player 2, place your ships to the game field");
        prepare(player2);

        game.shootingSystem();
    }

    /**
     * Starts the pre-game by asking users to place their ships
     */
    private void prepare(Board2D player) {
        for (Ship ship : ships) {
            Board2D.printBoard(player.getGameBoard());

            System.out.println("Enter the coordinates of the " + ship.getName() + " (" + ship.getLength() + " cells):");
            player.placeShips(ship, player.getGameBoard());
        }
        Board2D.printBoard(player.getGameBoard());

        GameManager.awaitInput();
    }
}
