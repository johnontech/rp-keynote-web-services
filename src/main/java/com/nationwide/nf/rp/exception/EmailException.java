package com.nationwide.nf.rp.exception;

/**
 * Created by jorgej2 on 9/17/2017.
 */
public class EmailException extends RuntimeException {

    public EmailException() {
        super();
    }

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailException(Throwable cause) {
        super(cause);
    }
}
