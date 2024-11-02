package jungle;
import jungle.Player;
import jungle.Game;
import jungle.pieces.Piece;
import jungle.squares.Square;
import jungle.IllegalMoveException;
import java.util.List;

public class Game {

    public static int HEIGHT = 9;
    public static int WIDTH = 7;
    public static int[] WATER_ROWS = {3, 4, 5};
    public static int[] WATER_COLS = {1, 2, 4, 5};
    public static int DEN_COL = 3;
    private int row;
    private int col;
    public Player p0;
    public Player p1;

    // constructor
    public Game(Player p0, Player p1) {
        this.p0 = p0;
        this.p1 = p1;
    }

    public void addStartingPieces() {
        // add P0 starting pieces
        addPiece(0, 0, 7, 0); // P0 Lion
        addPiece(0, 6, 6, 0); // P0 Tiger
        addPiece(1, 1, 3, 0); // P0 Dog
        addPiece(1, 1, 2, 0); // P0 Cat
        addPiece(2, 0, 1, 0); // P0 Rat
        addPiece(2, 2, 5, 0); // P0 Leopard
        addPiece(2, 4, 4, 0); // P0 Wolf
        addPiece(2, 8, 8, 0); // P0 Elephant

        // add P1 starting pieces
        addPiece(8, 6, 7, 1); // P1 Lion
        addPiece(8, 0, 6, 1); // P1 Tiger
        addPiece(7, 5, 3, 1); // P1 Dog
        addPiece(7, 1, 2, 1); // P1 Cat
        addPiece(6, 6, 1, 1); // P1 Rat
        addPiece(6, 4, 5, 1); // P1 Leopard
        addPiece(6, 2, 4, 1); // P1 Wolf
        addPiece(6, 0, 8, 1); // P1 Elephant
    }

    public void addPiece(int row, int col, int rank, int playerNumber) {
    }

    public Piece getPiece(int row, int col) {
        this.row = row;
        this.col = col;
        return null;
    }

    public void move(int fromRow, int fromCol, int toRow, int toCol) {

    }

    public Player getPlayer(int playerNumber) {
        return playerNumber == 0 ? p0 : p1;
    }

    public Player getWinner(int playerNumber) {
        // if p0 enters p1's Den OR p1 owns 0 pieces
        // return p0
        // else
        // return playerNumber == 0 ? p0 : p1;
        if (p0.hasCapturedDen() || !p1.hasPieces()) {
            return p0;
        } else if (p1.hasCapturedDen() || !p0.hasPieces()) {
            return p1;
        }
        return null;
    }

    public boolean isGameOver() {
        // if p0 OR p1 owns 0 pieces OR p0 OR p1 enters a Den square
        // return true
        // else
        return false;
    }

    public Square getSquare(int row, int col) {
//        this.row = row;
//        this.col = col;
        return null;
    }

    public List<Coordinate> getLegalMoves(int row, int col) {
        // if (row <= 6 && row >= 0 && col <= 8 && col >= 0) {

        // if rank == 0 row + 1, row - 1 col + 1. col -1; isWater
        // if rank == 7 leapVertically
        // if rank == 6 leapHorizontally
            return List.of();
        }
}
