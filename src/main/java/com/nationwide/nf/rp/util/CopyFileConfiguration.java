package com.nationwide.nf.rp.util;

import org.springframework.stereotype.Component;

@Component
public class CopyFileConfiguration {

	private String sourceFilePath;
	private String archiveFilePath;

	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

	public String getArchiveFilePath() {
		return archiveFilePath;
	}

	public void setArchiveFilePath(String archiveFilePath) {
		this.archiveFilePath = archiveFilePath;
	}

	@Override
	public String toString() {
		return "CopyFileConfiguration{" +
				"sourceFilePath='" + sourceFilePath + '\'' +
				", archiveFilePath='" + archiveFilePath + '\'' +
				'}';
	}
}
