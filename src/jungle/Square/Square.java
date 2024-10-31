package jungle.Square;
import jungle.Player;


public abstract class Square {

    public Player owner;

    public Square(Player owner) {
        this.owner = owner;
    }

    public boolean isOwnedBy(Player player) {
        // if den or trap return true
        // if plain oor water square return false
    }

    public abstract boolean isWater();
    public abstract boolean isDen();
    public abstract boolean isTrap();
}
