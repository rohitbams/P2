package jungle.squares;

/**
 * Water class.
 * This is a subclass of the Square class
 */
public class WaterSquare extends Square {

    // Constructor
    public WaterSquare() {
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
        return true;
    }
}
