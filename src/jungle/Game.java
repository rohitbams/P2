package jungle;
import jungle.pieces.Lion;
import jungle.pieces.Piece;
import jungle.pieces.Rat;
import jungle.pieces.Tiger;
import jungle.squares.Den;
import jungle.squares.PlainSquare;
import jungle.squares.Square;
import jungle.squares.WaterSquare;
import jungle.squares.Trap;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Welcome to the board game Jungle.
 * This is a two player game, played on a 7 by 9 board.
 * Each player has 8 pieces of varying strengths.
 * The Board:
 * The board is made up 63 squares. There are 4 types of squares
 *  1. 43 Plain squares
 *  2. 12 Water squares that only certain pieces can traverse
 *  3. 2 Den Squares owned by one player each
 *     If a square lands on this piece, the game ends.
 *  4. 6 Trap squares owned by one player 3 each.
 *     These squares affect the strength of pieces.
 * The Pieces:
 * Each player owns 8 pieces differing by strength.
 * A piece can defeat any piece with equal or less strength.
 * Some piece have special abilities.
 * Pieces must move one square at a time.
 * They can only move up, down, left or right.
 * They cannot move diagonally.
 *  1. Elephant strength: 8
 *  2. Lion strength: 7; can vertically jump over water squares
 *  3. Tiger strength: 6; can horizontally jump over waters squares
 *  4. Leopard strength: 5;
 *  5. Wolf strength: 4;
 *  6. Dog strength: 3;
 *  7. Cat strength: 2;
 *  8. Rat strength: 1; can swim in water squares & can defeat Elephant
 * The Objective:
 * The objective of the game is to win by one of two ways:
 * 1. Enter opponents Den square
 * 2. Defeat all pieces owned by opponent
 *
 * @author 240030041
 * @version 1
 */
public class Game {

    public static int HEIGHT = 9;
    public static int WIDTH = 7;
    public static int[] WATER_ROWS = {3, 4, 5};
    public static int[] WATER_COLS = {1, 2, 4, 5};
    public static int DEN_COL = 3;
    public Square[][] board;
    private HashMap<Coordinate, Piece> piecesHashMap;
    private Player p0;
    private Player p1;

    /**
     * Game constructor creates two Player objects
     * creates and initialises the board[][]
     */
    public Game(Player p0, Player p1) {
        this.p0 = p0;
        this.p1 = p1;
        this.board = new Square[HEIGHT][WIDTH];
        this.piecesHashMap = new HashMap<>();
        initialiseBoard();
    }

    public void initialiseBoard() {
        // Fill in Plain squares
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                board[row][col] = new PlainSquare();
            }
        }
        // Fill in Water squares
        for (int row : WATER_ROWS) {
            for (int col : WATER_COLS) {
                board[row][col] = new WaterSquare();
            }
        }
        // Fill in Den squares of both players
        board[0][DEN_COL] = new Den(p0);
        board[HEIGHT - 1][DEN_COL] = new Den(p1);

        // Fill in Trap squares of both players
        board[0][DEN_COL - 1] = new Trap(p0);
        board[0][DEN_COL + 1] = new Trap(p0);
        board[1][DEN_COL] = new Trap(p0);

        board[HEIGHT - 1][DEN_COL - 1] = new Trap(p1);
        board[HEIGHT - 1][DEN_COL + 1] = new Trap(p1);
        board[HEIGHT - 2][DEN_COL] = new Trap(p1);

    }

    // Helper method for creating coordinates
    private Coordinate createCoordinate(int row, int col) {
        return new Coordinate(row, col);
    }

    // Add all starting pieces
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

    // Create pieces of various strengths and abilities
    public void addPiece(int row, int col, int rank, int playerNumber) {
        Player owner = getPlayer(playerNumber); // stores Player
        Square square = getSquare(row, col); // stores square
        Piece piece;
        if (rank == 7) {
            piece = new Lion(owner, square);
        } else if (rank == 6) {
            piece = new Tiger(owner, square);
        } else if (rank == 1) {
            piece = new Rat(owner, square);
        } else {
            piece = new Piece(owner, square, rank);
        }
        // Place pieces in a HashMap
        piecesHashMap.put(createCoordinate(row, col), piece);
    }

    public Piece getPiece(int row, int col) {
        if (row < 0 || row >= HEIGHT || col < 0 || col >= WIDTH) {
            throw new IndexOutOfBoundsException("No such square");
        }
        return piecesHashMap.get(createCoordinate(row, col));
    }

    /** the move method checks for legal moves
     * and updates the piece position on the board.
     * It takes 4 parameters
     * @param fromRow
     * @param fromCol
     * @param toRow
     * @param toCol
     */
    public void move(int fromRow, int fromCol, int toRow, int toCol) {
        // Validate if parameters are out of bounds
        if (fromRow < 0 || fromRow >= HEIGHT || toRow < 0 || toRow >= HEIGHT
                || fromCol < 0 || fromCol >= WIDTH || toCol < 0 || toCol >= WIDTH) {
            throw new IndexOutOfBoundsException("Invalid move");
        }

        Piece movingPiece = getPiece(fromRow, fromCol);

        // Validate is moving piece doesn't exist
        if (movingPiece == null) {
            throw new NullPointerException();
        }

        Square toSquare = getSquare(toRow, toCol);
        Piece targetPiece = getPiece(toRow, toCol);

        // Validate move
        if (!isValidMove(fromRow, toRow, fromCol, toCol)) {
            throw new IllegalMoveException("Invalid move");
        }
        // Validate if moving piece can defeat target piece
        if (targetPiece != null && !movingPiece.canDefeat(targetPiece)) {
                throw new IllegalMoveException("Cannot capture that piece");
        }
        // Validate if target piece can be captured
        if (targetPiece != null && movingPiece.canDefeat(targetPiece)) {
            targetPiece.beCaptured();
        }
        // Move piece
        movingPiece.move(toSquare);
        // Add movingPiece new square coordinates
        piecesHashMap.put(createCoordinate(toRow, toCol), movingPiece);
        // Remove movingPiece old square coordinates
        piecesHashMap.remove(createCoordinate(fromRow, fromCol));

    }

    /**
     * isValidMove() checks for valid moves on the board.
     * For any piece to make a valid move, it must move
     * only one square either to the left, right, up, or down.
     * It takes 4 parameters
     * @param fromRow
     * @param toRow
     * @param fromCol
     * @param toCol
     * @return boolean
     */
    private boolean isValidMove(int fromRow, int toRow, int fromCol, int toCol) {
        Piece piece = getPiece(fromRow, fromCol);
        if (piece == null) {
            return false;
        }
        // Boolean variable to check if movement is valid
        boolean isAdjacent = Math.abs(fromRow - toRow) + Math.abs(fromCol - toCol) == 1;
        Square toSquare = getSquare(toRow, toCol);

        // Check if piece can horizontally jump over water squares
        if (piece.canLeapHorizontally() && fromRow == toRow) {

            int minCol = Math.min(fromCol, toCol);
            int maxCol = Math.max(fromCol, toCol);

            for (int col = minCol + 1; col < maxCol; col++) {
                Square square = getSquare(fromRow, col);
                if (!square.isWater()) {
                    return false;
                }
                // Piece cannot jump if Rat is in water square
                Piece ratInWater = getPiece(fromRow, col);
                if (ratInWater != null && ratInWater.canSwim()) {
                    return false;
                }
            } return true;
        }

        // Check if piece can vertically jump over water squares
        if (piece.canLeapVertically() && fromCol == toCol) {

            int minRow = Math.min(fromRow, toRow);
            int maxRow = Math.max(fromRow, toRow);

            for (int row = minRow + 1; row < maxRow; row++) {
                Square square = getSquare(row, fromCol);
                if (!square.isWater()) {
                    return false;
                }
                Piece ratInWater = getPiece(row, fromCol);
                // Piece cannot jump if Rat is in water square
                if (ratInWater != null && ratInWater.canSwim()) {
                    return false;
                }
            } return true;
        }
        if (!isAdjacent && !piece.canLeapHorizontally() && !piece.canLeapVertically()) {
            return false;
        }
        // Check if any piece other than Rat tries to enter water
        if (toSquare.isWater() && !piece.canSwim()) {
            return false;
        }
        return true;
    }

    public Player getPlayer(int playerNumber) {
        if (playerNumber > 1 || playerNumber < 0) {
            throw new IllegalArgumentException("Invalid player");
        }
        // if playerNumber is 0, return p0, else return p1
        return playerNumber == 0 ? p0 : p1;
    }

    public Player getWinner() {
        if (p0.hasCapturedDen() || !p1.hasPieces()) {
            return p0;
        } else if (p1.hasCapturedDen() || !p0.hasPieces()) {
            return p1;
        } return null;
    }

    public boolean isGameOver() {
        return getWinner() != null;
    }

    public Square getSquare(int row, int col) {
        if (row < 0 || row >= HEIGHT || col < 0 || col >= WIDTH) {
            throw new IndexOutOfBoundsException();
        } return board[row][col];
    }

    public List<Coordinate> getLegalMoves(int row, int col) {
        List<Coordinate> legalMoves = new ArrayList<>();

        for (int newRow = 0; newRow < HEIGHT; newRow++) {
            for (int newCol = 0; newCol < WIDTH; newCol++) {
                if (isValidMove(row, newRow, col, newCol)) {
                    legalMoves.add(new Coordinate(newRow, newCol));
                }
            }
        } return legalMoves;
        }
}
