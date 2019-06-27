package com.nationwide.nf.rp.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="docuSignSubscriptionFile")
public class DocuSignSubscriptionFile {

    String fileName;
    String beginDate;
    String endDate;
    String internalEmail;

    public DocuSignSubscriptionFile() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getInternalEmail() {
        return internalEmail;
    }

    public void setInternalEmail(String internalEmail) {
        this.internalEmail = internalEmail;
    }

    @Override
    public String toString() {
        return "DocuSignSubscriptionFile{" +
                "fileName='" + fileName + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", internalEmail='" + internalEmail + '\'' +
                '}';
    }
}
