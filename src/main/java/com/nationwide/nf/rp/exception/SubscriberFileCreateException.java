package com.nationwide.nf.rp.exception;

/**
 * Created by jorgej2 on 6/18/2017.
 */
public class SubscriberFileCreateException extends ApplicationException{

    public SubscriberFileCreateException() {
        super();
    }

    public SubscriberFileCreateException(String message) {
        super(message);
    }

    public SubscriberFileCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubscriberFileCreateException(Throwable cause) {
        super(cause);
    }
}
