package com.zxkj.assitance.entity;

import android.graphics.drawable.Drawable;

public class ProgressInfo {
	public Drawable proIcon;
	public String pkgName;
	public String proName;
	public long proMum;
	public boolean isChecked;
	public ProgressInfo(Drawable proIcon, String pkgName, String proName,
			long proMum, boolean isChecked) {
		super();
		this.proIcon = proIcon;
		this.pkgName = pkgName;
		this.proName = proName;
		this.proMum = proMum;
		this.isChecked = isChecked;
	}

}
