package com.nationwide.nf.rp.exception;

/**
 * Created by jorgej2 on 6/11/2017.
 */
public class CensusFileCreateException extends ApplicationException {

    public CensusFileCreateException() {
            super();
        }

    public CensusFileCreateException(String message) {
            super(message);
        }

    public CensusFileCreateException(String message, Throwable cause) {
            super(message, cause);
        }

    public CensusFileCreateException(Throwable cause) {
            super(cause);
        }
}
