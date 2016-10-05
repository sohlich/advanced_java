package cz.edhouse.persistence.exception;

import static java.lang.String.format;

/**
 *
 * @author Frantisek Spacek
 */
public class RecordNotFoundException extends InternalStorageException {

    public RecordNotFoundException(String message, Object... params) {
        super(format(message, params));
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
