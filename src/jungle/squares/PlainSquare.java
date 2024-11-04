package jungle.squares;

/**
 * PlainSquare class.
 * This is a subclass of the Square class
 */
public class PlainSquare extends Square {

    // constructor
    public PlainSquare() {
        super(null);
    }

    @Override
    public boolean isDen() {
        return false;
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
