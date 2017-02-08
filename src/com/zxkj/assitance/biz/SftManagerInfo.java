package com.zxkj.assitance.biz;

import android.graphics.drawable.Drawable;

public class SftManagerInfo {
	public String appName;
	public Drawable appIcon;
	public String appPakge;
	public String appVersion;
	public boolean isChecked;

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public SftManagerInfo(String appName, Drawable appIcon, String appPakge,
			String appVersion) {
		super();
		this.appName = appName;
		this.appIcon = appIcon;
		this.appPakge = appPakge;
		this.appVersion = appVersion;
	}

}
