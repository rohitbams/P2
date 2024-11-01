package jungle;

public class Player {

    private String name;
    private int playerNumber;

    private boolean hasCapturedDen;
    // private int pieces = 8;
    // private boolean losePiece;
    // private boolean gainPiece;

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
        // if owner has pieces return true

        // if !owner has pieces return false
        return false;
    }

    public void gainOnePiece() {
        hasPieces();
    }

    public void loseOnePiece() {
       hasPieces();
    }
}
