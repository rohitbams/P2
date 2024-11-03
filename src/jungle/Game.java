package jungle;
import jungle.pieces.Lion;
import jungle.pieces.Piece;
import jungle.pieces.Rat;
import jungle.pieces.Tiger;
import jungle.squares.Square;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static int HEIGHT = 9;
    public static int WIDTH = 7;
    public static int[] WATER_ROWS = {3, 4, 5};
    public static int[] WATER_COLS = {1, 2, 4, 5};
    public static int DEN_COL = 3;
    private Player p0;
    private Player p1;
    private Player owner;
    private Square square;
    private Piece piece;
    private Piece movingPiece;
    private Square toSquare;
    private Piece targetPiece;
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
        addPiece(1, 5, 2, 0); // P0 Cat
        addPiece(2, 0, 1, 0); // P0 Rat
        addPiece(2, 2, 5, 0); // P0 Leopard
        addPiece(2, 4, 4, 0); // P0 Wolf
        addPiece(2, 6, 8, 0); // P0 Elephant

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
        owner = getPlayer(playerNumber); // points to Player
        square = getSquare(row, col); // gets square
        if (rank == 7) {
            piece = new Lion(owner, square);
        } else if (rank == 6) {
            piece = new Tiger(owner, square);
        } else if (rank == 1) {
            piece = new Rat(owner, square);
        } else {
            piece = new Piece(owner, square, rank);
        }
        owner.gainOnePiece();
    }

    public Piece getPiece(int row, int col) {
        return piece;
    }

    public void move(int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow < 0 || fromRow > WIDTH || toRow < 0 || toRow > WIDTH ||
                fromCol < 0 || fromCol > HEIGHT || toCol < 0 || toCol > HEIGHT) {
            throw new IllegalMoveException("Invalid move");
        }
        movingPiece = getPiece(fromRow, fromCol);
        if (movingPiece == null) {
            throw new IllegalMoveException("Invalid move");
        }
        toSquare = getSquare(toRow, toCol);
        targetPiece = getPiece(toRow, toCol);
        if (!isValidMove(fromRow, toRow, fromCol, toCol)) {
            throw new IllegalMoveException("Invalid move");
        }
        if (targetPiece == null) {
            if (!movingPiece.canDefeat(targetPiece)) {
                throw new IllegalMoveException("Cannot capture that piece");
            }
            movingPiece.move(toSquare);
        }
    }

    private boolean isValidMove(int fromRow, int toRow, int fromCol, int toCol) {
        piece = getPiece(fromRow, fromCol);
        boolean isAdjacent = Math.abs(fromRow - toRow) + Math.abs(fromCol - toCol) == 1;
        toSquare = getSquare(toRow, toCol);

        if (piece.canLeapHorizontally() && fromRow == toRow) {
            return false;
        }
        if (piece.canLeapVertically() && fromRow == toRow) {
            return false;
        }
        if (!isAdjacent && !piece.canLeapHorizontally() && !piece.canLeapVertically()) {
            return false;
        }
        if (toSquare.isWater() && !piece.canSwim()) {
            return false;
        }
        return true;
    }

    public Player getPlayer(int playerNumber) {
        return playerNumber == 0 ? p0 : p1; // if playerNumber is 0, return p0, else return p1
    }

    public Player getWinner() {
        if (p0.hasCapturedDen() || p1.hasPieces()) {
            return p0;
        } else if (p1.hasCapturedDen() || !p0.hasPieces()) {
            return p1;
        } return null;
    }

    public boolean isGameOver() {
        if (getWinner() == null) {
            return false;
        } return true;
    }

    public Square getSquare(int row, int col) {

        return ;
    }

    public List<Coordinate> getLegalMoves(int row, int col) {
        List<Coordinate> legalMoves = new ArrayList<>();

        for (int newRow = 0; newRow < HEIGHT; newRow++) {
            for (int newCol = 0; newCol < WIDTH; newCol++) {
                if (isValidMove(newRow, newCol, row, col)) {
                    legalMoves.add(new Coordinate(newRow, newCol));
                }
            }
        } return legalMoves;
        }
}
