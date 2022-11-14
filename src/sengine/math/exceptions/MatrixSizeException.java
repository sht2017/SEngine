package sengine.math.exceptions;

public class MatrixSizeException extends IllegalArgumentException {
    public MatrixSizeException() {
        super();
    }

    public MatrixSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MatrixSizeException(String message) {
        super(message);
    }

    public MatrixSizeException(Throwable cause) {
        super(cause);
    }
}
