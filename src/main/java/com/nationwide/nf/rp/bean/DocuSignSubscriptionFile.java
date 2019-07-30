package com.nationwide.nf.rp.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="docuSignSubscriptionFile")
public class DocuSignSubscriptionFile {

    String fileType;
    String fileNamePrefix;
    String fileExtension;
    String createFileWhenEmpty;
    String fileBeginDate;
    String fileEndDate;
    String internalEmailNotifAdr;
    String mftUserName;

    public DocuSignSubscriptionFile() {
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileNamePrefix() {
        return fileNamePrefix;
    }

    public void setFileNamePrefix(String fileNamePrefix) {
        this.fileNamePrefix = fileNamePrefix;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getCreateFileWhenEmpty() {
        return createFileWhenEmpty;
    }

    public void setCreateFileWhenEmpty(String createFileWhenEmpty) {
        this.createFileWhenEmpty = createFileWhenEmpty;
    }

    public String getFileBeginDate() {
        return fileBeginDate;
    }

    public void setFileBeginDate(String fileBeginDate) {
        this.fileBeginDate = fileBeginDate;
    }

    public String getFileEndDate() {
        return fileEndDate;
    }

    public void setFileEndDate(String fileEndDate) {
        this.fileEndDate = fileEndDate;
    }

    public String getInternalEmailNotifAdr() {
        return internalEmailNotifAdr;
    }

    public void setInternalEmailNotifAdr(String internalEmailNotifAdr) {
        this.internalEmailNotifAdr = internalEmailNotifAdr;
    }

    public String getMftUserName() {
        return mftUserName;
    }

    public void setMftUserName(String mftUserName) {
        this.mftUserName = mftUserName;
    }

    @Override
    public String toString() {
        return "DocuSignSubscriptionFile{" +
                "fileType='" + fileType + '\'' +
                ", fileNamePrefix='" + fileNamePrefix + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", createFileWhenEmpty='" + createFileWhenEmpty + '\'' +
                ", fileBeginDate='" + fileBeginDate + '\'' +
                ", fileEndDate='" + fileEndDate + '\'' +
                ", internalEmailNotifAdr='" + internalEmailNotifAdr + '\'' +
                ", mftUserName='" + mftUserName + '\'' +
                '}';
    }
}
