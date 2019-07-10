package com.nationwide.nf.rp.exception;

/**
 * Created by jorgej2 on 7/9/2017.
 */
public class DatabaseException extends ApplicationException {

    public DatabaseException() {
        super();
    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }

}
