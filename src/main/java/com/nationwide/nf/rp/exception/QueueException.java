package com.nationwide.nf.rp.exception;

/**
 * This exception is thrown when an error occurs when reading messages from the queue
 *
 * Modification Log:
 *
 * Date        Modifier             Description
 * ----------  -------------------  -------------------------------------------------
 * 05/25/2017  J. Jorgensen         Initial version.
 */
public class QueueException extends ApplicationException {

    public QueueException() {
        super();
    }

    public QueueException(String message) {
        super(message);
    }

    public QueueException(String message, Throwable cause) {
        super(message, cause);
    }

    public QueueException(Throwable cause) {
        super(cause);
    }

}
