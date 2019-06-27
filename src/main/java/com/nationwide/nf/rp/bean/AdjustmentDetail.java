package com.nationwide.nf.rp.bean;

/**
 * Created by John Jorgensen on 4/25/2017.
 */
public class AdjustmentDetail {

    String key;
    String value;

    public AdjustmentDetail() {
    }

    public AdjustmentDetail(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AdjustmentDetail{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
