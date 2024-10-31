package jungle;

public class Player {

    private String name;
    private int playerNumber;

    private boolean hasCapturedDen;

    // constructor
    public Player(String name, int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
        this.hasCapturedDen = false;

    }

   // static Player p0 = new Player("Player 1", 0);
   // static Player p1 = new Player("Player 2", 1);

    public String getName() {
        return name;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void captureDen() {
        hasCapturedDen = true;
        // Player enters opposite Player's Den
    }

    public boolean hasCapturedDen() {
        return ! hasCapturedDen;
    }

    public boolean hasPieces() {
        // if Player has >0 pieces
        // return true
        return false;
    }

    public void gainOnePiece() {

    }

    public void lostOnePiece() {}
}
