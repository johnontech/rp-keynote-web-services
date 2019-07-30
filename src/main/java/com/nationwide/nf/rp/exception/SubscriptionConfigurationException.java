package com.nationwide.nf.rp.exception;

public class SubscriptionConfigurationException  extends ApplicationException {

    public SubscriptionConfigurationException() {
        super();
    }

    public SubscriptionConfigurationException(String message) {
        super(message);
    }

    public SubscriptionConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubscriptionConfigurationException(Throwable cause) {
        super(cause);
    }
}
