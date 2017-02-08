package com.zxkj.assitance.biz;

import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;


public class SftManager {
	protected PackageManager mPkgm;
	protected List<PackageInfo> mPkInfo;
	protected Vector<SftManagerInfo> allApp = new Vector<SftManagerInfo>();
	protected Vector<SftManagerInfo> systemApp = new Vector<SftManagerInfo>();
	protected Vector<SftManagerInfo> userApp = new Vector<SftManagerInfo>();

	public Vector<SftManagerInfo> getAllApp() {
		return allApp;
	}

	public Vector<SftManagerInfo> getSystemApp() {
		return systemApp;
	}

	public Vector<SftManagerInfo> getUserApp() {
		return userApp;
	}

	public SftManager(Context context) {
		mPkgm = context.getPackageManager();
		initData();
	}

	// 获取软件信息
	public void initData() {
		mPkInfo = mPkgm.getInstalledPackages(0);
		for (PackageInfo pInfo : mPkInfo) {
			String appPackage = pInfo.packageName;
			String appVersion = pInfo.versionName;

			if (appVersion != null && appVersion.length() >= 10) {
				appVersion = appVersion.substring(0, 10);
			}
			ApplicationInfo aInfo = pInfo.applicationInfo;
			Drawable appIcon = aInfo.loadIcon(mPkgm);
			String appName = aInfo.loadLabel(mPkgm).toString();
			SftManagerInfo sMInfo = new SftManagerInfo(appName, appIcon,
					appPackage, appVersion);
			allApp.add(sMInfo);
			// 判断为系统软件还是用户软件
			int flag = aInfo.flags;
			int returnCount = flag & ApplicationInfo.FLAG_SYSTEM;
			if (returnCount != 0) {
				// 系统
				systemApp.add(sMInfo);
			} else {
				// 用户
				userApp.add(sMInfo);
			}
		}

}

	public Vector<SftManagerInfo> getSftMgeInfo(int position) {
		switch (position) {
		case 0:
			return getAllApp();
		case 1:
			return getSystemApp();
		case 2:
			return getUserApp();

		}

		return null;

	}
}
