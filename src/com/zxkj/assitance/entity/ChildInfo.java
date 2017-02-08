package com.zxkj.assitance.entity;

import java.io.File;

import android.graphics.drawable.Drawable;

public class ChildInfo {
	private String appName;
	private Drawable appIcon;
	private int TotelMem;
	private File appFile;
	private boolean isChocked;

	public boolean isChocked() {
		return isChocked;
	}

	public void setChocked(boolean isChocked) {
		this.isChocked = isChocked;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Drawable getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(Drawable appIcon) {
		this.appIcon = appIcon;
	}

	public int getTotelMem() {
		return TotelMem;
	}

	public void setTotelMem(int totelMem) {
		TotelMem = totelMem;
	}

	public File getAppFile() {
		return appFile;
	}

	public void setAppFile(File appFile) {
		this.appFile = appFile;
	}

}
