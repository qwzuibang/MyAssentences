package com.zxkj.assitance.biz;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.StatFs;

public class MumMenager {
	// 总大小
	public static long getExtSdTotal(String path) {
		StatFs statfs = new StatFs(path);
		return statfs.getBlockCountLong() * statfs.getBlockSizeLong();

	}

	// 可用
	public static long getExtSdAvail(String path) {
		StatFs statfs = new StatFs(path);
		return statfs.getAvailableBlocksLong() * statfs.getBlockSizeLong();

	}

	// 已用
	public static long getExtSdUsed(String path) {

		return getExtSdTotal(path) - getExtSdAvail(path);

	}

	// 百分比
	public static float getExtSdPer(String path) {

		return getExtSdUsed(path) * 1.0f / getExtSdTotal(path);
	}

	// 获取运行的总内存
	public static long getRuntimeTotalMem(Context context) {

		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo outInfo = new MemoryInfo();
		activityManager.getMemoryInfo(outInfo);

		return outInfo.totalMem;
	}

	// 获取内存可用内存
	public static long getRuntimeAvailMem(Context context) {

		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo outInfo = new MemoryInfo();
		activityManager.getMemoryInfo(outInfo);

		return outInfo.availMem;
	}

	// 获取运行已用内存
	public static long getRuntimeUsedMem(Context context) {

		return getRuntimeTotalMem(context) - getRuntimeAvailMem(context);
	}

}
