package com.zxkj.assitance.biz;

import java.util.List;
import java.util.Vector;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Debug.MemoryInfo;

import com.zxkj.assitance.entity.LogWrapper;
import com.zxkj.assitance.entity.ProgressInfo;

public class PhoneInfoManager {
	protected Context context;
	protected PackageManager mPkgMana;
	protected List<ProgressInfo> mPgsInfo;
	protected ActivityManager mActMge;

	protected Vector<ProgressInfo> systemPro = new Vector<ProgressInfo>();
	protected Vector<ProgressInfo> userPro = new Vector<ProgressInfo>();

	public static String getDevName() {
		return Build.MODEL;
	}

	public static String getDevVersion() {
		return Build.VERSION.RELEASE;
	}

	public PhoneInfoManager(Context context) {
		this.context = context;
		mActMge = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		mPkgMana = context.getPackageManager();
		initDate();
	}

	public void initDate() {

		List<ActivityManager.RunningAppProcessInfo> allProgessInfo = mActMge
				.getRunningAppProcesses();
		for (RunningAppProcessInfo rInfo : allProgessInfo) {
			int[] pids = { rInfo.pid };
			String pkgList[] = rInfo.pkgList;
			String pkg = pkgList[0];
			try {
				PackageInfo pInfo = mPkgMana.getPackageInfo(pkg, 0);
				// 包名
				String pkgName = pInfo.packageName;
				LogWrapper.e("baoming", pkgName);
				// 图标
				ApplicationInfo application = pInfo.applicationInfo;
				Drawable proIcon = application.loadIcon(mPkgMana);
				// 名称
				String proName = application.loadLabel(mPkgMana).toString();
				// 内存
				MemoryInfo[] memInfo = mActMge.getProcessMemoryInfo(pids);
				long proMum = memInfo[0].dalvikPrivateDirty * 1024;
				ProgressInfo progressInfo = new ProgressInfo(proIcon, pkgName,
						proName, proMum, false);
				if ((application.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
					userPro.add(progressInfo);
				} else {
					systemPro.add(progressInfo);
				}

			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public Vector<ProgressInfo> getSystemPro() {
		return systemPro;
	}

	public Vector<ProgressInfo> getUserPro() {
		return userPro;
	}
}
