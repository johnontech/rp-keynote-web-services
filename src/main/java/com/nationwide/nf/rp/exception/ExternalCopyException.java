package com.nationwide.nf.rp.exception;

public class ExternalCopyException extends ApplicationException {

    public ExternalCopyException() {
        super();
    }

    public ExternalCopyException(String message) {
        super(message);
    }

    public ExternalCopyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExternalCopyException(Throwable cause) {
        super(cause);
    }
}
