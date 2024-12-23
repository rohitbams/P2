package jungle;

/**
 * Player class.
 * This class creates Player objects
 * and contains attributes and behaviours
 * for Player objects.
 */
public class Player {

    private final String name;
    private final int playerNumber;

    private boolean hasCapturedDen;
    private int pieceCount;

    // constructor
    public Player(String name, int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
        this.hasCapturedDen = false;
        this.pieceCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void captureDen() {
        hasCapturedDen = true;
    }

    public boolean hasCapturedDen() {
        return hasCapturedDen;
    }

    public int getPieceCount() {
        return pieceCount;
    }

    public void gainOnePiece() {
        pieceCount++;
    }

    public void loseOnePiece() {
        if (pieceCount > 0) {
            pieceCount--;
        }
    }
    public boolean hasPieces() {
        return pieceCount > 0;
    }
}
