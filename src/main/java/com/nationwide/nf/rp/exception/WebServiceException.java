package com.nationwide.nf.rp.exception;

/**
 * Created by jorgej2 on 6/13/2017.
 */
public class WebServiceException extends ApplicationException {

    public WebServiceException() {
        super();
    }

    public WebServiceException(String message) {
        super(message);
    }

    public WebServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebServiceException(Throwable cause) {
        super(cause);
    }
}
