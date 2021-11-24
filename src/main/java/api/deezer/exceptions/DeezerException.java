package api.deezer.exceptions;

/**
 * Exceptions that occur in <b>Deezer API</b> library.
 */
public class DeezerException extends Exception {
    public DeezerException(Throwable cause) {
        super(cause);
    }

    public DeezerException(String message) {
        super(message);
    }
}
