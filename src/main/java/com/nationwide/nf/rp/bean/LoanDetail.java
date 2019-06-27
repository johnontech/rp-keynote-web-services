package com.nationwide.nf.rp.bean;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

/**
 * Created by John Jorgensen on 4/17/2017.
 */
@XmlRootElement(name="loanDetail")
public class LoanDetail {
    private String returnCode;
    private String returnMessage;
    private PrincipalAndInterest[] principalAndInterest;

    public LoanDetail() {}

    public void setPrincipalAndInterest(PrincipalAndInterest[] principalAndInterest) {
        this.principalAndInterest = principalAndInterest;
    }

    public PrincipalAndInterest[] getPrincipalAndInterest() {
        return principalAndInterest;
    }

    public LoanDetail(String returnCode, String returnMessage) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    @Override
    public String toString() {
        return "LoanDetail{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMessage='" + returnMessage + '\'' +
                ", principalAndInterest=" + Arrays.toString(principalAndInterest) +
                '}';
    }
}
