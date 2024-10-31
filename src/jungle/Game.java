package jungle;
import jungle.Piece.Piece;
import jungle.Square.Square;
import java.util.List;

public class Game {

    public static int HEIGHT = 9;
    public static int WIDTH =7;
    public static int[] WATER_ROWS = {3, 4, 5};
    public static int[] WATER_COLS = {1, 2, 4, 5};
    public static int DEN_COL = 3;
    public static Square[][] board;
    public Player p0;
    public Player p1;
    // constructor
    public Game(Player p0, Player p1){
        board = new Square[HEIGHT][WIDTH];
        this.p0 = Player.p0;
        this.p1 = Player.p1;

    }

    public void addStartingPieces()  {
    }

    public void addPiece(int row, int col, int rank, int playerNumber) {
    }

    public Piece getPiece() {
        return null ;
    }

    public void move(int fromRow, int fromCol, int toRow, int toCol) {
    }

    public Player getPlayer(int playerNumber) {
        // playerNumber == 0 return player p0
        // else
        return Player.p1;
    }

    public Player getWinner(int playerNumber) {
        // if p0 enters p1's Den OR p1 owns 0 pieces
        // return p0
        // else
        return Player.p1;
    }

    public boolean isGameOver() {
        // if p0 OR p1 owns 0 pieces OR p0 OR p1 enters a Den square
        // return true
        // else
        return false;
    }

    public Square getSquare(int row, int col) {
        // int = board[][];
        // col = board[][]
        return board[HEIGHT][WIDTH];
    }

    List<Coordinate> getLegalMoves(int row, int col) {
        return null;
    }

}
