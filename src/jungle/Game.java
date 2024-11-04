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
import java.util.Map;
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
    private Player owner;
    private Square square;
    private Piece piece;

    // constructor
    public Game(Player p0, Player p1) {
        this.p0 = p0;
        this.p1 = p1;
        this.board = new Square[HEIGHT][WIDTH];
        this.piecesHashMap = new HashMap<>();
        initialiseBoard();
    }

    public void initialiseBoard() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                board[row][col] = new PlainSquare();
            }
        }
        for (int row : WATER_ROWS) {
            for (int col : WATER_COLS) {
                board[row][col] = new WaterSquare();
            }
        }
        board[0][DEN_COL] = new Den(p0);
        board[HEIGHT - 1][DEN_COL] = new Den(p1);

        board[0][DEN_COL - 1] = new Trap(p0);
        board[0][DEN_COL + 1] = new Trap(p0);
        board[1][DEN_COL] = new Trap(p0);

        board[HEIGHT - 1][DEN_COL - 1] = new Trap(p1);
        board[HEIGHT - 1][DEN_COL + 1] = new Trap(p1);
        board[HEIGHT - 2][DEN_COL] = new Trap(p1);

    }

    // helper method for creating coordinates
    private Coordinate createCoordinate(int row, int col) {
        return new Coordinate(row, col);
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
        owner = getPlayer(playerNumber); // stores Player
        square = getSquare(row, col); // stores square
        if (rank == 7) {
            this.piece = new Lion(owner, square);
        } else if (rank == 6) {
            this.piece = new Tiger(owner, square);
        } else if (rank == 1) {
            this.piece = new Rat(owner, square);
        } else {
            this.piece = new Piece(owner, square, rank);
        }
        piecesHashMap.put(createCoordinate(row, col), this.piece);
    }

    public Piece getPiece(int row, int col) {
        if (row < 0 || row >= HEIGHT || col < 0 || col >= WIDTH) {
            throw new IndexOutOfBoundsException("No such square");
        }
        return piecesHashMap.get(createCoordinate(row, col));
    }

    public void move(int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow < 0 || fromRow >= HEIGHT || toRow < 0 || toRow >= HEIGHT
                || fromCol < 0 || fromCol >= WIDTH || toCol < 0 || toCol >= WIDTH) {
            throw new IndexOutOfBoundsException("Invalid move");
        }
        Piece movingPiece = getPiece(fromRow, fromCol);
        if (movingPiece == null) {
            throw new NullPointerException();
        }
        Square toSquare = getSquare(toRow, toCol);
        Piece targetPiece = getPiece(toRow, toCol);

        if (!isValidMove(fromRow, toRow, fromCol, toCol)) {
            throw new IllegalMoveException("Invalid move");
        }
        if (targetPiece == null) {
            if (!movingPiece.canDefeat(null)) {
                throw new IllegalMoveException("Cannot capture that piece");
            }
            // Add movingPiece new square coordinates
            piecesHashMap.put(createCoordinate(toRow, toCol), movingPiece);
            // Remove movingPiece old square coordinates
            piecesHashMap.remove(createCoordinate(fromRow, fromCol));
            movingPiece.move(toSquare);
        }
        if (movingPiece.canDefeat(targetPiece)) {
            // Add movingPiece new square coordinates
            piecesHashMap.put(createCoordinate(toRow, toCol), movingPiece);
            // Remove movingPiece old square coordinates
            piecesHashMap.remove(createCoordinate(fromRow, fromCol));
            movingPiece.move(toSquare);
        }
        if (targetPiece != null && !movingPiece.canDefeat(targetPiece)) {
            throw new IllegalMoveException("Cannot capture that piece");
        }
    }

    private boolean isValidMove(int fromRow, int toRow, int fromCol, int toCol) {
        piece = getPiece(fromRow, fromCol);

        if (piece == null) {
            return false;
        }

        boolean isAdjacent = Math.abs(fromRow - toRow) + Math.abs(fromCol - toCol) == 1;
        Square toSquare = getSquare(toRow, toCol);

        if (piece.canLeapHorizontally() && fromRow == toRow) {
            return false;
        }
        if (piece.canLeapVertically() && fromRow == toRow) {
            return false;
        }
        if (!isAdjacent && !piece.canLeapHorizontally() && !piece.canLeapVertically()) {
            return false;
        }
        // if any piece other than Rat tries to enter water
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
                if (isValidMove(newRow, newCol, row, col)) {
                    legalMoves.add(new Coordinate(newRow, newCol));
                }
            }
        } return legalMoves;
        }

    public HashMap<Coordinate, Piece> getPiecesHashMap() {
        return piecesHashMap;
    }

    public void setPiecesHashMap(HashMap<Coordinate, Piece> piecesHashMap) {
        this.piecesHashMap = piecesHashMap;
    }

//    public static void main(String[] args) {
//        Player p0 = new Player("Player 1", 0);
//        Player p1 = new Player("Player 2", 1);
//
//        Game game = new Game(p0, p1);
//
//
//        System.out.println("Adding piece");
//        game.addPiece(0, 0, 7, 0); // P0 Lion
//        game.addPiece(0, 6, 6, 0); // P0 Tiger
//        game.addPiece(1, 1, 3, 0); // P0 Dog
//        game.addPiece(1, 5, 2, 0); // P0 Cat
//        game.addPiece(2, 0, 1, 0); // P0 Rat
//        game.addPiece(2, 2, 5, 0); // P0 Leopard
//        game.addPiece(2, 4, 4, 0); // P0 Wolf
//        game.addPiece(2, 6, 8, 0); // P0 Elephant
//
//        // add P1 starting pieces
//        game.addPiece(8, 6, 7, 1); // P1 Lion
//        game.addPiece(8, 0, 6, 1); // P1 Tiger
//        game.addPiece(7, 5, 3, 1); // P1 Dog
//        game.addPiece(7, 1, 2, 1); // P1 Cat
//        game.addPiece(6, 6, 1, 1); // P1 Rat
//        game.addPiece(6, 4, 5, 1); // P1 Leopard
//        game.addPiece(6, 2, 4, 1); // P1 Wolf
//        game.addPiece(6, 0, 8, 1); // P1 Elephant
//
//        System.out.println("HashMap Size: " + game.getPiecesHashMap().size());
//
//        //for (Coordinate coord: game.getPiecesHashMap().keySet()) {
//            //System.out.println("row: " + coord.row() + " col: " + coord.col());
//        //}
//        Piece piece = game.getPiece(2, 4);
//        // game.move(2, 4, 2, 5);
//        System.out.println(piece);
//
//            System.out.println("\n" + piece != null ? "found" : "Null piece");
//
//        // assert piece != null;
//        // System.out.println(piece + "strength: " + piece.getStrength());
//
//        System.out.println("\n All pieces in HashMap");
//        for (Map.Entry<Coordinate, Piece> entry : game.getPiecesHashMap().entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//
//
//    }

}


