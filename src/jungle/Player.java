package jungle;

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

    public boolean hasPieces() {
        if (pieceCount > 0) return true;
        else return false;
    }

    public void gainOnePiece() {
        pieceCount++;
    }

    public void loseOnePiece() {
        if (pieceCount > 0) {
            pieceCount--;
        }
    }
}
