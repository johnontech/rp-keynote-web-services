package com.nationwide.nf.rp.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by John Jorgensen on 5/3/2017.
 */
@XmlRootElement(name="reverseRepaymentDetail")
public class ReverseRepaymentDetail {
    private String transactionNumber;
    private String rvTranNumber;
    private String effectiveDate;

    public ReverseRepaymentDetail() {
    }

    public ReverseRepaymentDetail(String transactionNumber, String rvTranNumber, String effectiveDate) {
        this.transactionNumber = transactionNumber;
        this.rvTranNumber = rvTranNumber;
        this.effectiveDate = effectiveDate;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getRvTranNumber() {
        return rvTranNumber;
    }

    public void setRvTranNumber(String rvTranNumber) {
        this.rvTranNumber = rvTranNumber;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public String toString() {
        return "ReverseRepaymentDetail{" +
                "transactionNumber='" + transactionNumber + '\'' +
                ", rvTranNumber='" + rvTranNumber + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                '}';
    }
}
