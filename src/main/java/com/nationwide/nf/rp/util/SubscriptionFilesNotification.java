package com.nationwide.nf.rp.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorgej2 on 1/31/2018.
 */
public class SubscriptionFilesNotification {

    List<String> emailAddresses = new ArrayList();
    List<String> subscriptionFiles = new ArrayList();

    public void addToEmailAddressesList(String emailAddress) {
        this.emailAddresses.add(emailAddress.toLowerCase());
    }

    public void addToEmailAddressesList(List<String> emailAddresses) {
        for (String emailAddress : emailAddresses) {
            this.emailAddresses.add(emailAddress.toLowerCase());
        }
    }

    public void addToSubscriptionFilesList(String subscriptionFileName) {
        this.subscriptionFiles.add(subscriptionFileName);
    }

    public void addToSubscriptionFilesList(List<String> subscriptionFiles) {
        this.subscriptionFiles.addAll(subscriptionFiles);
    }

    public List<String> getSubscriptionFilesList() {
        return subscriptionFiles;
    }

    public List<String> getEmailAddressesList() {
        return emailAddresses;
    }

    @Override
    public String toString() {
        return "SubscriptionFilesNotification{" +
                "emailAddresses=" + emailAddresses +
                ", subscriptionFiles=" + subscriptionFiles +
                '}';
    }

    public String getEmailAddresses() {
        if (emailAddresses.size() == 0) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int idx = 0; idx < emailAddresses.size(); ++idx) {
                sb.append(emailAddresses.get(idx));
                if (!(idx == emailAddresses.size()-1)) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
    }
}
