package cz.edhouse.persistence.exception;

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
