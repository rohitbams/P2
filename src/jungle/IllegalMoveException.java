package jungle;

/**
 * IllegalMoveException class.
 * This exception is called when a move is invalid.
 * This class extends the RuntimeException class.
 */
public class IllegalMoveException extends RuntimeException {

    // Constructor
    public IllegalMoveException(String message) {
        super(message);
    }
}
