package jungle.pieces;
import jungle.Player;
import jungle.squares.Square;

/**
 * Tiger class.
 * This is a subclass of the Piece class.
 * It creates the special piece Tiger which
 * has a rank of 6 and has the ability to
 * Horizontally jump over water squares
 * given that there is not Rat piece in
 * the water.
 */
public class Tiger extends Piece {

    // Constructor
    public Tiger(Player owner, Square square) {
        super(owner, square, 6);
    }
}
