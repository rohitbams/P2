package jungle.squares;
import jungle.Player;
import jungle.squares.Den;
import jungle.squares.Trap;
import jungle.squares.PlainSquare;
import jungle.squares.WaterSquare;



public abstract class Square {

//    private Player player;
    private Player owner;

    public Square(Player owner) {
        this.owner = owner;
    }

    public boolean isOwnedBy(Player player) {
//        if (player == null) {
//            return false;
//        }
        if (this.owner == null) {
            return false;
        }
        return this.owner.equals(player);
    }

    public abstract boolean isWater();
    public abstract boolean isDen();
    public abstract boolean isTrap();
}
