package jungle;

public class Coordinate {
    private final int row;
    private final int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int row() {
        // if row is less than 6 && more than 0
        return row;
    }

    public int col() {
        // if col is less than 8 && more than 0
        return col;
    }
}
