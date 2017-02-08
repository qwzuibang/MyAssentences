package com.zxkj.assitance.entity;

import android.util.Log;

import com.zxkj.assitance.BuildConfig;

public class LogWrapper {
	static boolean isDebug = true;

	public static void e(String tag, String msg) {
		if (isDebug && BuildConfig.DEBUG) {
			Log.e(tag, msg);
		}

	}
}
