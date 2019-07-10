package com.nationwide.nf.rp.util;

import java.io.File;
import java.util.List;

/**
 * Created by jorgej2 on 9/17/2017.
 */
public class FailedEnrollment {
    private String errorMessage;
    private FailedEnrollmentType typeOfFailure = null;
    private String envelopeId;
    private List<File> files;

    public enum FailedEnrollmentType {
        INVALID_PDF_SORT_ORDER,
        MISSING_VALUE_FOR_PPA_NBR,
        MISSING_VALUE_SOCIAL_SECURITY_NUMBER,
        MISSING_MESSAGE_COUNT_TAG
    }

    public FailedEnrollment(String errorMessage, String envelopeId, List<File> files, FailedEnrollmentType typeOfFailure) {
        this.errorMessage = errorMessage;
        this.envelopeId = envelopeId;
        this.files = files;
        this.typeOfFailure = typeOfFailure;
    }

    public FailedEnrollment(String envelopeId) {
        this.envelopeId = envelopeId;
    }

    public String getEnvelopeId() {
        return envelopeId;
    }

    public void setEnvelopeId(String envelopeId) {
        this.envelopeId = envelopeId;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> messageFiles) {
        this.files = messageFiles;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "FailedEnrollment{" +
                "envelopeId='" + envelopeId + '\'' +
                ", files=" + files +
                '}';
    }
}
