package com.nationwide.nf.rp.bean;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

/**
 * Created by John Jorgensen on 4/26/2017.
 */
@XmlRootElement(name="repaymentDetail")
public class RepaymentDetail {
    private String batchNumber;
    private String effectiveDate;
    private String ppeDate;
    private PrincipalAndInterest[] principalAndInterest;

    public RepaymentDetail() {
    }

    public RepaymentDetail(String batchNumber, String effectiveDate, String ppeDate, PrincipalAndInterest[] principalAndInterest) {
        this.batchNumber = batchNumber;
        this.effectiveDate = effectiveDate;
        this.ppeDate = ppeDate;
        this.principalAndInterest = principalAndInterest;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getPpeDate() {
        return ppeDate;
    }

    public void setPpeDate(String ppeDate) {
        this.ppeDate = ppeDate;
    }

    public PrincipalAndInterest[] getPrincipalAndInterest() {
        return principalAndInterest;
    }

    public void setPrincipalAndInterest(PrincipalAndInterest[] principalAndInterest) {
        this.principalAndInterest = principalAndInterest;
    }

    @Override
    public String toString() {
        return "RepaymentDetail{" +
                "batchNumber='" + batchNumber + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", ppeDate='" + ppeDate + '\'' +
                ", principalAndInterest=" + Arrays.toString(principalAndInterest) +
                '}';
    }
}
