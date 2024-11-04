package jungle.pieces;
import jungle.Player;
import jungle.squares.Square;

/**
 * Rat class.
 * This is a subclass of the Piece class.
 * It creates the special piece Rat which
 * has a rank of 1 and has the ability to
 * traverse the water squares.
 * It can also defeat the Elephant piece
 * which has a rank 8.
 */
public class Rat extends Piece {

    // Constructor
    public Rat(Player owner, Square square) {
        super(owner, square, 1);
    }
}
