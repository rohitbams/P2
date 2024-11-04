package jungle.squares;
import jungle.Player;

/**
 * Square class.
 * This is an abstract class.
 * The classes Den, PlainSquare, Trap, and Water
 * extend this class.
 */
public abstract class Square {

    private final Player owner;

    // Constructor
    public Square(Player owner) {
        this.owner = owner;
    }

    public boolean isOwnedBy(Player player) {
        if (this.owner == null) {
            return false;
        } return this.owner.equals(player);
    }

    public abstract boolean isWater();
    public abstract boolean isDen();
    public abstract boolean isTrap();
}
