package jungle.squares;
import jungle.Player;

public class Den extends Square {

    // private Player owner;
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
