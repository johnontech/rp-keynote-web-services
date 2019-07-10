package com.nationwide.nf.rp.util;

import org.springframework.stereotype.Component;

/**
 * Created by jorgej2 on 9/17/2017.
 */
@Component
public class EmailConfiguration {
    private String emailToAddress;
    private String emailFromAddress;

    public void setEmailToAddress(String emailToAddress) {
        this.emailToAddress = emailToAddress;
    }

    public String getEmailToAddress() {
        return emailToAddress;
    }

    public void setEmailFromAddress(String emailFromAddress) {
        this.emailFromAddress = emailFromAddress;
    }

    public String getEmailFromAddress() {
        return emailFromAddress;
    }

    @Override
    public String toString() {
        return "EmailConfiguration{" +
                "emailToAddress='" + emailToAddress + '\'' +
                ", emailFromAddress='" + emailFromAddress + '\'' +
                '}';
    }
}
