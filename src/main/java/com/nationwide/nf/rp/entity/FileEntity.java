package com.nationwide.nf.rp.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FileEntity {

    public static final String CREATE_FILE_WHEN_EMPTY = "Y";
    public static final String SCHOOLS_FIRST_NW_CENSUS = "SchoolsFirst_NW_Census";
    public static final String SCHOOLS_FIRST_NW_SRA = "SchoolsFirst_NW_SRA";
    public static final String SUNCOAST_ENROLLMENT = "Suncoast_NW_Enrollment";
    public static final String SUNCOAST_CENSUS = "Suncoast_NW_Census";
    private int subscriberFeedSeqId;
    private String fileType;
    private String fileNamePrefix;
    private String fileExtension;
    private String createFileWhenEmpty;
    private Date file_begin_date;
    private Date file_end_date;
    private String internalEmailNotifAddr;
    private String mftUserName;

    public int getSubscriberFeedSeqId() {
        return subscriberFeedSeqId;
    }

    public void setSubscriberFeedSeqId(int subscriberFeedSeqId) {
        this.subscriberFeedSeqId = subscriberFeedSeqId;
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

    public Date getFileBeginDate() {
        return file_begin_date;
    }

    public Date getFile_begin_date() {
        return file_begin_date;
    }

    public void setFile_begin_date(Date file_begin_date) {
        this.file_begin_date = file_begin_date;
    }

    public Date getFileEndDate() {
        return file_end_date;
    }

    public Date getFile_end_date() {
        return file_end_date;
    }

    public void setFile_end_date(Date file_end_date) {
        this.file_end_date = file_end_date;
    }

    public boolean isCreateFileWhenEmpty() {
        return (CREATE_FILE_WHEN_EMPTY.equalsIgnoreCase(getCreateFileWhenEmpty()));
    }

    public String getInternalEmailNotifAddr() {
        return internalEmailNotifAddr;
    }

    public void setInternalEmailNotifAddr(String internalEmailNotifAddr) {
        this.internalEmailNotifAddr = internalEmailNotifAddr;
    }

    public String getMftUserName() {
        return mftUserName;
    }

    public void setMftUserName(String mftUserName) {
        this.mftUserName = mftUserName;
    }

    public boolean isSchoolsFirstCensusFile() {
        return SCHOOLS_FIRST_NW_CENSUS.equalsIgnoreCase(fileNamePrefix);
    }

    public boolean isSuncoastEnrollmentFile() {
        return SUNCOAST_ENROLLMENT.equalsIgnoreCase(fileNamePrefix);
    }

    public boolean isSuncoastCensusFile() {
        return SUNCOAST_CENSUS.equalsIgnoreCase(fileNamePrefix);
    }

    public boolean isSchoolsFirstSalaryReductionFile() {
        return SCHOOLS_FIRST_NW_SRA.equalsIgnoreCase(fileNamePrefix);
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "subscriberFeedSeqId=" + subscriberFeedSeqId +
                ", fileType='" + fileType + '\'' +
                ", fileNamePrefix='" + fileNamePrefix + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", createFileWhenEmpty='" + createFileWhenEmpty + '\'' +
                ", file_begin_date=" + file_begin_date +
                ", file_end_date=" + file_end_date +
                ", internalEmailNotifAddr='" + internalEmailNotifAddr + '\'' +
                ", mftUserName='" + mftUserName + '\'' +
                '}';
    }
}
