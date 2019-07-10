package com.nationwide.nf.rp.entity;

import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class FeedEntity {

	private Integer seqId;
	private String subscriberName;
	private String fileXferMethod;
	private String fileXferId;
	private String fileXferDir;
	private Date subscrBeginDate;
	private Date subscrEndDate;
	private String subscrStatus;

	public Integer getSeqId() {
		return seqId;
	}
	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
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
	public Date getSubscrBeginDate() {
		return subscrBeginDate;
	}
	public void setSubscrBeginDate(Date subscrBeginDate) {
		this.subscrBeginDate = subscrBeginDate;
	}
	public Date getSubscrEndDate() {
		return subscrEndDate;
	}
	public void setSubscrEndDate(Date subscrEndDate) {
		this.subscrEndDate = subscrEndDate;
	}
	public String getSubscrStatus() {
		return subscrStatus;
	}
	public void setSubscrStatus(String subscrStatus) {
		this.subscrStatus = subscrStatus;
	}
}
