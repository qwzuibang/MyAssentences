package com.zxkj.assitance.biz;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.StatFs;

public class MumMenager {
	// �ܴ�С
	public static long getExtSdTotal(String path) {
		StatFs statfs = new StatFs(path);
		return statfs.getBlockCountLong() * statfs.getBlockSizeLong();

	}

	// ����
	public static long getExtSdAvail(String path) {
		StatFs statfs = new StatFs(path);
		return statfs.getAvailableBlocksLong() * statfs.getBlockSizeLong();

	}

	// ����
	public static long getExtSdUsed(String path) {

		return getExtSdTotal(path) - getExtSdAvail(path);

	}

	// �ٷֱ�
	public static float getExtSdPer(String path) {

		return getExtSdUsed(path) * 1.0f / getExtSdTotal(path);
	}

	// ��ȡ���е����ڴ�
	public static long getRuntimeTotalMem(Context context) {

		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo outInfo = new MemoryInfo();
		activityManager.getMemoryInfo(outInfo);

		return outInfo.totalMem;
	}

	// ��ȡ�ڴ�����ڴ�
	public static long getRuntimeAvailMem(Context context) {

		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo outInfo = new MemoryInfo();
		activityManager.getMemoryInfo(outInfo);

		return outInfo.availMem;
	}

	// ��ȡ���������ڴ�
	public static long getRuntimeUsedMem(Context context) {

		return getRuntimeTotalMem(context) - getRuntimeAvailMem(context);
	}

}
