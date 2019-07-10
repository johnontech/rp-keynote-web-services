package com.nationwide.nf.rp.exception;

/**
 * Created by jorgej2 on 5/30/2017.
 */
public class ParticipantEnrollmentException extends ApplicationException{

    public ParticipantEnrollmentException() {
        super();
    }

    public ParticipantEnrollmentException(String message) {
        super(message);
    }

    public ParticipantEnrollmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParticipantEnrollmentException(Throwable cause) {
        super(cause);
    }

}
