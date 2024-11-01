package jungle.squares;
import jungle.Player;

public class Trap extends Square {

    // constructor
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
