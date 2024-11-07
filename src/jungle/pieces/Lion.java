package jungle.pieces;
import jungle.Player;
import jungle.squares.Square;

/**
 * Lion class.
 * This is a subclass of the Piece class.
 * It creates the special piece Lion which
 * has a rank of 7 and has the ability to
 * vertically or horizontally jump over water
 * squares given that there is not Rat piece
 * in the water.
 */
public class Lion extends Piece {

    public Lion(Player owner, Square square) {
        super(owner, square, 7);
    }


}
