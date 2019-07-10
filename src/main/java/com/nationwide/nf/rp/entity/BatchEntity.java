package com.nationwide.nf.rp.entity;

import java.util.Date;

/**
 * Created by jorgej2 on 6/15/2017.
 */
public class BatchEntity {

    public static int UNSET_SORT_ORDER = -1;

    private String envelopeId;
    private String documentId;
    private int subscriberFeedSeqId;
    private int caseSeqId;
    private String socialSecurityNumber;
    private int sortOrder;
    private String status;
    private Date flowDate;

    public BatchEntity(String envelopeId, String documentId, int subscriberFeedSeqId, int caseSeqId,
                       String socialSecurityNumber, int sortOrder, String status, Date flowDate) {
        this.envelopeId = envelopeId;
        this.documentId  = documentId;
        this.subscriberFeedSeqId = subscriberFeedSeqId;
        this.caseSeqId = caseSeqId;
        this.socialSecurityNumber = socialSecurityNumber;
        this.sortOrder = sortOrder;
        this.status = status;
        this.flowDate = flowDate;
    }

    public BatchEntity() {
    }

    public BatchEntity(String envelopeId, String documentId) {
        this.envelopeId = envelopeId;
        this.documentId= documentId;
    }

    public BatchEntity(int subscriberFeedSeqId, int caseSeqId, String envelopeId, String documentId,
                       int sortOrder, String socialSecurityNumber, String status, Date flowDate) {
        this.subscriberFeedSeqId = subscriberFeedSeqId;
        this.caseSeqId = caseSeqId;
        this.envelopeId = envelopeId;
        this.documentId = documentId;
        this.sortOrder = sortOrder;
        this.socialSecurityNumber = socialSecurityNumber;
        this.flowDate = flowDate;
        this.status = status;
    }

    public int getSubscriberFeedSeqId() {
        return subscriberFeedSeqId;
    }

    public void setSubscriberFeedSeqId(int subscriberFeedSeqId) {
        this.subscriberFeedSeqId = subscriberFeedSeqId;
    }

    public String getEnvelopeId() {
        return envelopeId;
    }

    public void setEnvelopeId(String envelopeId) {
        this.envelopeId = envelopeId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getCaseSeqId() {
        return caseSeqId;
    }

    public void setCaseSeqId(int caseSeqId) {
        this.caseSeqId = caseSeqId;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getFlowDate() {
        return flowDate;
    }

    public void setFlowDate(Date flowDate) {
        this.flowDate = flowDate;
    }

    @Override
    public String toString() {
        return "BatchEntity{" +
                "envelopeId='" + envelopeId + '\'' +
                ", documentId='" + documentId + '\'' +
                ", subscriberFeedSeqId=" + subscriberFeedSeqId +
                ", caseSeqId=" + caseSeqId +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", sortOrder=" + sortOrder +
                ", status='" + status + '\'' +
                ", flowDate=" + flowDate +
                '}';
    }
}
