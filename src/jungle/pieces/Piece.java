package jungle.pieces;
import jungle.Player;
import jungle.squares.Trap;
import jungle.squares.Square;

public class Piece {

    private final Player owner;
    private final Square square;
    private int rank;

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
        if (square.isTrap()) {
            rank = 0;
        }
        return rank;
    }

    public boolean canSwim() {
        return rank == 1;
//        if (rank == 1) {
//            return true;
//        };
//        return false;
    }

    public boolean canLeapHorizontally() {
        if (rank == 6) {
            return true;
        } return false;
    }

    public boolean canLeapVertically() {
        if (rank == 7) {
            return true;
        } return false;
    }

    public void move(Square toSquare) {
        if (toSquare.isDen() && !toSquare.isOwnedBy(owner)) {
            owner.captureDen();
        } else if (toSquare.isTrap()) {
            getStrength();
        }
    }

    public boolean canDefeat(Piece target) {
        if (rank == 1 && target.rank == 8) {
            return true; // rat defeats elephant
        } else if (target.rank <= rank) {
            return true; // strength >= defeat lower
        } else if (target.square.isTrap()) {
            return true; // pieces of lower strength can defeat trapped pieces of higher strength
        } return false;
    }

    public void beCaptured() {


        if (owner.hasPieces()) {
            owner.loseOnePiece();
        }
    }
}
