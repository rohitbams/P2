package jungle.squares;
import jungle.Player;

/**
 * Trap class.
 * This is a subclass of the Square class
 * It creates Trap square objects.
 * Each player owns three trap squares.
 * If a piece enters an opponent Trap square,
 * that pieces strength becomes 0 while it
 * remains in that Trap squares.
 */
public class Trap extends Square {

    // Constructor
    public Trap(Player owner) {
        super(owner);
    }

    @Override
    public boolean isDen() {
        return false;
    }

    @Override
    public boolean isTrap() {
        return true;
    }

    @Override
    public boolean isWater() {
        return false;
    }
}
