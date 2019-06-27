package com.nationwide.nf.rp.bean;

import rploanscommon.KNCorrectionResponse;
import rploanscommon.KNPrincipalAdjustment;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Created by John Jorgensen on 4/24/2017.
 */
@XmlRootElement(name = "loanCorrectionDetail")
public class LoanCorrectionDetail {

    private String detailMessage;
    private String returnCode;
    private Adjustment[] adjustment;

    public LoanCorrectionDetail() {
    }

    public LoanCorrectionDetail(String returnCode, String detailMessage) {
        this.returnCode = returnCode;
        this.detailMessage = detailMessage;
    }

    public LoanCorrectionDetail(KNCorrectionResponse knCorrectionResponse) {
        this.setDetailMessage(knCorrectionResponse.getDetailMessage());
        this.setReturnCode(knCorrectionResponse.getReturnCode());

        Adjustment[] adjustments = new Adjustment[knCorrectionResponse.getAdjustments().size()];
        Iterator<KNPrincipalAdjustment> knCorrectionResponseIterator = knCorrectionResponse.getAdjustments().iterator();

        for (int i = 0; i < knCorrectionResponse.getAdjustments().size(); i++) {
            KNPrincipalAdjustment knPrincipalAdjustment = knCorrectionResponseIterator.next();

            Adjustment adjustment = new Adjustment(knPrincipalAdjustment.getEffectiveDate(),
                                                                              knPrincipalAdjustment.getPpeDate(),
                                                                              knPrincipalAdjustment.getSourcePrincipalAdjustment());
            adjustments[i] = adjustment;
        }
        setAdjustment(adjustments);
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public Adjustment[] getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(Adjustment[] adjustment) {
        this.adjustment = adjustment;
    }

    @Override
    public String toString() {
        return "LoanCorrectionDetail{" +
                "detailMessage='" + detailMessage + '\'' +
                ", returnCode='" + returnCode + '\'' +
                ", adjustment=" + Arrays.toString(adjustment) +
                '}';
    }
}
