package com.zxkj.assitance.entity;

public class FileInfo {
	private String fileType;
	private int fileNum;
	private float fileSize;
	private String suffix;
	public FileInfo() {

	}

	public FileInfo(String fileType, int fileNum, float fileSize) {
		super();
		this.fileType = fileType;
		this.fileNum = fileNum;
		this.fileSize = fileSize;

	}


	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getFileNum() {
		return fileNum;
	}

	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}

	public float getFileSize() {
		return fileSize;
	}

	public void setFileSize(float fileSize) {
		this.fileSize = fileSize;
	}

}
