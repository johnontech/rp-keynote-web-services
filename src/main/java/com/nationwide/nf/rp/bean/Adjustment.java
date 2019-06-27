package com.nationwide.nf.rp.bean;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * Created by John Jorgensen on 4/24/2017.
 */
public class Adjustment {
    private String effectiveDate;
    private String ppeDate;
    private AdjustmentDetail[] adjustmentDetail;

    public Adjustment() {
    }

    public Adjustment(String effectiveDate, String ppeDate, Map<String, String> sourcePrincipalAdjustment) {
        this.effectiveDate = effectiveDate;
        this.ppeDate = ppeDate;
        this.adjustmentDetail = getSourcePrincipalAdjustment(sourcePrincipalAdjustment);
    }

    public Adjustment(String effectiveDate, String ppeDate, AdjustmentDetail[] adjustmentDetail) {
        this.effectiveDate = effectiveDate;
        this.ppeDate = ppeDate;
        this.adjustmentDetail = adjustmentDetail;
    }

    public AdjustmentDetail[] getAdjustmentDetail() {
        return adjustmentDetail;
    }

    public void setAdjustmentDetail(AdjustmentDetail[] adjustmentDetail) {
        this.adjustmentDetail = adjustmentDetail;
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

    @Override
    public String toString() {
        return "Adjustment{" +
                "effectiveDate='" + effectiveDate + '\'' +
                ", ppeDate='" + ppeDate + '\'' +
                ", adjustmentDetail=" + Arrays.toString(adjustmentDetail) +
                '}';
    }

    public AdjustmentDetail[] getSourcePrincipalAdjustment(Map<String, String> sourcePrincipalAdjustmentMap) {
        Set<String> keySet = sourcePrincipalAdjustmentMap.keySet();

        AdjustmentDetail[] adjustmentDetails = new AdjustmentDetail[keySet.size()];
        int idx = 0;
        for (String key : keySet) {
            String value = sourcePrincipalAdjustmentMap.get(key);
            AdjustmentDetail adjustmentDetail = new AdjustmentDetail(key, value);
            adjustmentDetails[idx++] = adjustmentDetail;
        }

        return adjustmentDetails;
    }
}
