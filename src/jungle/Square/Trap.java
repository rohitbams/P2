package jungle.Square;
import jungle.Player;

public class Trap extends Square {

    public Trap(Player owner) {
        super();

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
