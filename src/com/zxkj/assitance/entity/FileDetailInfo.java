package com.zxkj.assitance.entity;

import java.io.File;

public class FileDetailInfo {
	private String feilName;
	private int fileIcon;
	private File file;
	private boolean isCheak;
	private String suffix;

	public int getFileIcon() {
		return fileIcon;
	}

	public void setFileIcon(int fileIcon) {
		this.fileIcon = fileIcon;
	}

	public boolean isCheak() {
		return isCheak;
	}

	public void setCheak(boolean isCheak) {
		this.isCheak = isCheak;
	}

	public FileDetailInfo() {
	}

	public FileDetailInfo(String feilName, int fileIcon, File file,
			String suffix) {
		super();
		this.feilName = feilName;
		this.fileIcon = fileIcon;
		this.file = file;
		this.suffix = suffix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getFeilName() {
		return feilName;
	}

	public void setFeilName(String feilName) {
		this.feilName = feilName;
	}

	public int getFileNum() {
		return fileIcon;
	}

	public void setFileNum(int fileNum) {
		this.fileIcon = fileIcon;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
