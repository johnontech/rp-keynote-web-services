package com.nationwide.nf.rp.bean;

/**
 * Created by John Jorgensen on 4/18/2017.
 */
public class PrincipalAndInterest {
    String source;
    String principal;
    String interest;

    public PrincipalAndInterest() {
    }

    public PrincipalAndInterest(String source, String principal, String interest) {
        this.source = source;
        this.principal = principal;
        this.interest = interest;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "PrincipalAndInterest{" +
                "source='" + source + '\'' +
                ", principal='" + principal + '\'' +
                ", interest='" + interest + '\'' +
                '}';
    }
}
