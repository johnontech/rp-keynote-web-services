package com.nationwide.nf.rp.util;

/**
 * Created by jorgej2 on 6/10/2017.
 */
public enum FileTypeEnum {
    CENSUS("NW_Census"),
    SALARY_REDUCTION("NW_SRA"),
    ONLINE_ENROLLMENT("NW_Enrollment"),
    ENROLLMENT_PDF("NW_Enrollment_PDF");

    private String fileType;

    FileTypeEnum(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return fileType;
    }
}
