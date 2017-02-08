package com.zxkj.assitance.util;

import java.lang.reflect.Method;
import java.text.DecimalFormat;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;

/**
 * �ļ�������
 * 
 * @author Administrator
 * 
 */
public class FileUitls {

	/**
	 * �ж��Ƿ����ĵ�
	 * 
	 * @param suffix
	 * @return
	 */
	public static boolean isTextType(String suffix) {
		String[] str = { "txt", "doc", "docx", "xls", "xlsx", "ppt", "pptx",
				"pdf", "c", "h", "cpp", "hpp", "java", "js", "html", "xml",
				"xhtml", "css" };
		for (String suff : str) {
			if (suff.equals(suffix)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * �����ļ�����׺�жϸ��ļ��Ƿ�Ϊ��Ƶ���ļ�
	 * 
	 * @param suffix
	 *            �ļ����ĺ�׺
	 * 
	 * @return �������Ƶ���ļ�����true�����򷵻�false
	 */
	public static boolean isVideoFile(String suffix) {
		String[] str = { "avi", "mp4", "rm", "rmvb", "3gp", "flash" };
		for (String string : str) {
			if (suffix.equals(string)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * �����ļ�����׺�жϸ��ļ��Ƿ�Ϊ��Ƶ���ļ�
	 * 
	 * @param suffix
	 *            �ļ����ĺ�׺
	 * 
	 * @return �������Ƶ���ļ�����true�����򷵻�false
	 */
	public static boolean isAudioFile(String suffix) {
		String[] str = { "mp3", "wav", "wma", };
		for (String string : str) {
			if (suffix.equals(string)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * �����ļ�����׺�жϸ��ļ��Ƿ�Ϊͼ�����ļ�
	 * 
	 * @param suffix
	 *            �ļ����ĺ�׺
	 * 
	 * @return �����ͼ�����ļ�����true�����򷵻�false
	 */
	public static boolean isImageFile(String suffix) {
		String[] str = { "bmp", "jpg", "gif", "png", "jpeg", "ico", "tiff",
				"xcf" };
		for (String string : str) {
			if (suffix.equals(string)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * �����ļ�����׺�жϸ��ļ��Ƿ�Ϊѹ���ļ�
	 * 
	 * @param suffix
	 *            �ļ����ĺ�׺
	 * 
	 * @return �����ѹ���ļ�����true�����򷵻�false
	 */
	public static boolean isZipFile(String suffix) {
		String[] str = { "zip", "rar", "gz", "tar" };
		for (String string : str) {
			if (suffix.equals(string)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * �����ļ�����׺�жϸ��ļ��Ƿ�Ϊ������ļ�
	 * 
	 * @param suffix
	 *            �ļ����ĺ�׺
	 * 
	 * @return ����ǳ�����ļ�����true�����򷵻�false
	 */
	public static boolean isProgramFile(String suffix) {
		String[] str = { "apk" };
		for (String string : str) {
			if (suffix.equals(string)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ��ʽ���ļ�����
	 * 
	 * @param length
	 *            һ��long���͵�������ʾ�ļ��ĳ��ȣ���λ�ǣ�B
	 * 
	 * @return ����һ�����к��ʵ�λ�ģ���ʾ�ļ����ȵ�С��1024���ַ������͵���
	 */
	public static String formatLength(long length) {
		DecimalFormat format = new DecimalFormat(".0");

		if (length >= 1024 && length < 1024 * 1024) {
			double len = length * 1.0 / 1024;
			String string = format.format(len);
			return string + "K";
		} else if (length >= 1024 * 1024 && length < 1024 * 1024 * 1024) {
			double len = length * 1.0 / (1024 * 1024);
			String string = format.format(len);
			return string + "M";
		} else if (length >= 1024 * 1024 * 1024) {
			double len = length * 1.0 / (1024 * 1024 * 1024);
			String string = format.format(len);
			return string + "G";
		}

		return length + "B";
	}

	/**
	 * sdcard all path
	 * 
	 * @param context
	 * @return
	 */
	public static String[] getSDCARDPaths(Context context) {
		StorageManager storageManager = (StorageManager) context
				.getSystemService(Context.STORAGE_SERVICE);
		try {
			Method m = StorageManager.class.getMethod("getVolumePaths");

			m.setAccessible(true);
			String paths[] = (String[]) m.invoke(storageManager);
			m.setAccessible(false);

			StatFs s = new StatFs(paths[0]);
			return paths;
		} catch (Exception e) {
			return new String[] { Environment.getExternalStorageDirectory()
					.getAbsolutePath() };
		}

	}

	/**
	 * inner sdcard
	 * 
	 * @return
	 */
	public static String getInnerSDCARDPath(Context context) {
		String[] paths = getSDCARDPaths(context);
		for (String path : paths) {
			if (Environment.getExternalStorageDirectory().getAbsolutePath()
					.equals(path)) {
				return path;
			}
		}
		return Environment.getExternalStorageDirectory().getAbsolutePath();

	}

	/**
	 * 
	 * @return
	 */
	public static String getExternelSDCARDPath(Context context) {
		String[] paths = getSDCARDPaths(context);
		for (String path : paths) {
			if (!Environment.getExternalStorageDirectory().getAbsolutePath()
					.equals(path)) {
				return path;
			}
		}
		return null;

	}
}
