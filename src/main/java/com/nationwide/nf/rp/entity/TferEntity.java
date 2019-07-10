package com.nationwide.nf.rp.entity;

import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class TferEntity {
	
	private Integer docusign_subscr_feed_seq_id;
	private String fileName;
	private String fileType;
	private Date flowDate;
	private String status;
	

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
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
	public Integer getDocusign_subscr_feed_seq_id() {
		return docusign_subscr_feed_seq_id;
	}
	public void setDocusign_subscr_feed_seq_id(Integer docusign_subscr_feed_seq_id) {
		this.docusign_subscr_feed_seq_id = docusign_subscr_feed_seq_id;
	}
	
	
}
