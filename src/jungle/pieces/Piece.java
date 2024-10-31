package jungle.pieces;
import jungle.Player;
import jungle.squares.Square;

public class Piece {

    public Player owner;
    public Square square;
    public int rank;

    public Piece(Player owner, Square square, int rank) {
        this.owner = owner;
        this.square = square;
        this.rank = rank;
    }
    public boolean isOwnedBy(Player player) {
        if (this.owner == null) {
            return false;
        }
        return this.owner.equals(player);
    }
    public int getStrength() {
        return rank;
    }
    public boolean canSwim() {
        return rank == 1;
    }
    public boolean canLeapHorizontally() {
        if (rank == 6) {return true;}
        return false;
    }
    public boolean canLeapVertically() {
        if (rank == 7) {return true;}
        return false;
    }
    public void move(Square toSquare) {
        // moveToDen
        //
    }
    public boolean canDefeat(Piece target) {
        // rat can defeat elephant
        return false;
    }
    public void beCaptured() {}




}
