package com.nationwide.nf.rp.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="docuSignConfiguration")
public class DocuSignConfiguration {

    private String subscriptionName;
    private String subscriptionBeginDate;
    private String subscriptionEndDate;
    private String subscriptionStatus;

    private String fileTransferMethod;
    private String fileTransferId;
    private String fileTransferDirectory;
    private String fileSubscriptionCases;

    private DocuSignSubscriptionFile[] docuSignSubscriptionFiles;

    public DocuSignConfiguration() {
    }

    public DocuSignSubscriptionFile[] getDocuSignSubscriptionFiles() {
        return docuSignSubscriptionFiles;
    }

    public void setDocuSignSubscriptionFiles(DocuSignSubscriptionFile[] docuSignSubscriptionFiles) {
        this.docuSignSubscriptionFiles = docuSignSubscriptionFiles;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public String getSubscriptionBeginDate() {
        return subscriptionBeginDate;
    }

    public void setSubscriptionBeginDate(String subscriptionBeginDate) {
        this.subscriptionBeginDate = subscriptionBeginDate;
    }

    public String getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(String subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public String getFileTransferMethod() {
        return fileTransferMethod;
    }

    public void setFileTransferMethod(String fileTransferMethod) {
        this.fileTransferMethod = fileTransferMethod;
    }

    public String getFileTransferId() {
        return fileTransferId;
    }

    public void setFileTransferId(String fileTransferId) {
        this.fileTransferId = fileTransferId;
    }

    public String getFileTransferDirectory() {
        return fileTransferDirectory;
    }

    public void setFileTransferDirectory(String fileTransferDirectory) {
        this.fileTransferDirectory = fileTransferDirectory;
    }

    public String getFileSubscriptionCases() {
        return fileSubscriptionCases;
    }

    public void setFileSubscriptionCases(String fileSubscriptionCases) {
        this.fileSubscriptionCases = fileSubscriptionCases;
    }

    @Override
    public String toString() {
        return "DocuSignConfiguration{" +
                "subscriptionName='" + subscriptionName + '\'' +
                ", subscriptionBeginDate='" + subscriptionBeginDate + '\'' +
                ", subscriptionEndDate='" + subscriptionEndDate + '\'' +
                ", subscriptionStatus='" + subscriptionStatus + '\'' +
                ", fileTransferMethod='" + fileTransferMethod + '\'' +
                ", fileTransferId='" + fileTransferId + '\'' +
                ", fileTransferDirectory='" + fileTransferDirectory + '\'' +
                ", fileSubscriptionCases='" + fileSubscriptionCases + '\'' +
                '}';
    }
}

