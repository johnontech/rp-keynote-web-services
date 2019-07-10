package com.nationwide.nf.rp.util;

/**
 * Created by jorgej2 on 5/29/2017.
 */
public enum BatchStatusEnum {
    PENDING,
    CREATED,
    SENT,
    ERROR;

    public static String getAbbreviation(BatchStatusEnum status) {
        String statusAbbreviation = null;
        if (status == BatchStatusEnum.PENDING) {
            statusAbbreviation = "P";
        } else if (status == BatchStatusEnum.CREATED) {
            statusAbbreviation = "C";
        } else if (status == BatchStatusEnum.SENT) {
            statusAbbreviation = "S";
        }
        else if (status == BatchStatusEnum.ERROR) {
            statusAbbreviation = "E";
        }
        return statusAbbreviation;
    }
}
