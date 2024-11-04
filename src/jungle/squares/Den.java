package jungle.squares;
import jungle.Player;

/**
 * Den class.
 * This is a subclass of the Square class
 */
public class Den extends Square {

    // constructor
    public Den(Player owner) {
        super(owner);
    }

    @Override
    public boolean isDen() {
        return true;
    }

    @Override
    public boolean isTrap() {
        return false;
    }

    @Override
    public boolean isWater() {
        return false;
    }
}
