package jungle.squares;

public class WaterSquare extends Square {
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
