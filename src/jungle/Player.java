package jungle;

public class Player {

    private String name;
    private int playerNumber;

    private boolean hasCapturedDen;
    private boolean losePiece;
    private boolean gainPiece;

    // constructor
    public Player(String name, int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
        this.hasCapturedDen = false;
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

    public boolean hasPieces() {
        return true;
    }

    public void gainOnePiece() {
        hasCapturedDen = false;
    }

    public void loseOnePiece() {
        hasCapturedDen = true;
        losePiece = true;
    }
}
