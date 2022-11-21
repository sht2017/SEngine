package sengine.math.exceptions;

public class VectorSizeException extends IllegalArgumentException {
    public VectorSizeException() {
        super();
    }

    public VectorSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public VectorSizeException(String message) {
        super(message);
    }

    public VectorSizeException(Throwable cause) {
        super(cause);
    }
}
