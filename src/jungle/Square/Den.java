package jungle.Square;
import jungle.Player;

public class Den extends Square {

    // constructor
    public Den(Player owner) {
        super(null);
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
