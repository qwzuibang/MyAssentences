package com.zxkj.assitance.util;

import java.text.SimpleDateFormat;

public class DataUtil {
	public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static String millisToData(long millis) {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
		return sdf.format(millis);
	}

}
