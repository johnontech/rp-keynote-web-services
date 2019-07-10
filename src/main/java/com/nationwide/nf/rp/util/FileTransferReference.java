package com.nationwide.nf.rp.util;

import java.sql.Date;

/**
 * Created by jorgej2 on 8/6/2017.
 */
public class FileTransferReference {

    private int subscriberSeqId;
    private String fileType;
    private String fileName;
    private Date flowDate;
    private String status;
    private String subscriberName;
    private String fileXferMethod;
    private String fileXferId;
    private String fileXferDir;

    public int getSubscriberSeqId() {
        return subscriberSeqId;
    }

    public void setSubscriberSeqId(int subscriberSeqId) {
        this.subscriberSeqId = subscriberSeqId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getFlowDate() {
        return flowDate;
    }

    public void setFlowDate(Date flowDate) {
        this.flowDate = flowDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public String getFileXferMethod() {
        return fileXferMethod;
    }

    public void setFileXferMethod(String fileXferMethod) {
        this.fileXferMethod = fileXferMethod;
    }

    public String getFileXferId() {
        return fileXferId;
    }

    public void setFileXferId(String fileXferId) {
        this.fileXferId = fileXferId;
    }

    public String getFileXferDir() {
        return fileXferDir;
    }

    public void setFileXferDir(String fileXferDir) {
        this.fileXferDir = fileXferDir;
    }

    @Override
    public String toString() {
        return "FileTransferReference{" +
                "subscriberSeqId=" + subscriberSeqId +
                ", fileType='" + fileType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", flowDate=" + flowDate +
                ", status='" + status + '\'' +
                ", subscriberName='" + subscriberName + '\'' +
                ", fileXferMethod='" + fileXferMethod + '\'' +
                ", fileXferId='" + fileXferId + '\'' +
                ", fileXferDir='" + fileXferDir + '\'' +
                '}';
    }
}
