package cz.edhouse.jpa.exception;

/**
 *
 * @author Frantisek Spacek
 */
public class InternalStorageException extends RuntimeException {

    public InternalStorageException(String message) {
        super(message);
    }
    
    public InternalStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
