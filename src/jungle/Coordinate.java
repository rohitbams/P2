package jungle;
import java.util.Objects;

/**
 * Coordinate class.
 * This class is used for creating and storing
 * coordinates of squares and pieces
 * as they move along the board.
 */
public class Coordinate {
    private int row;
    private int col;

    // Constructor
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coordinate other = (Coordinate) obj;
        return row == other.row && col == other.col;
    }

    @Override
    public int hashCode() {
        // Simple but effective hash code implementation
        return row * 31 + col;
    }
}