package com.zxkj.assitance.biz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Build;
import android.util.DisplayMetrics;

import com.zxkj.assitance.R;
import com.zxkj.assitance.entity.PhoneDecetionInfo;
import com.zxkj.assitance.util.FileUitls;

public class DecetionManager {


	public static final String CPU_INFO_PATH = "proc/cpuinfo";
	public static final String CPU_NUM_PATH = "sys/devices/system/cpu";

	public static List<PhoneDecetionInfo> getDecetionInfo(Activity context) {
		List<PhoneDecetionInfo> mPhoneDecrtionInfos = new ArrayList<PhoneDecetionInfo>();
		// �豸���ƺͰ汾
		PhoneDecetionInfo mPhoneDecInfo1 = new PhoneDecetionInfo(
				R.drawable.setting_info_icon_version, "�豸���ƣ�"
 + Build.MODEL,
				"ϵͳ�汾"
						+ PhoneInfoManager.getDevVersion());
		mPhoneDecrtionInfos.add(mPhoneDecInfo1);
		// �����ڴ��ʣ���ڴ�
		String runTatalMum = FileUitls.formatLength(MumMenager
				.getRuntimeTotalMem(context));
		String runUseMum = FileUitls.formatLength(MumMenager
				.getRuntimeUsedMem(context));

		PhoneDecetionInfo mPhoneDecInfo2 = new PhoneDecetionInfo(
				R.drawable.setting_info_icon_space, "ȫ�������ڴ棺" + runTatalMum,
				"ʣ�������ڴ棺" + runUseMum);
		mPhoneDecrtionInfos.add(mPhoneDecInfo2);
		// cpu���ƺ�����
		PhoneDecetionInfo mPhoneDecInfo3 = new PhoneDecetionInfo(
				R.drawable.setting_info_icon_cpu, "CPU���ƣ�" + getCpuName(),
				"CPU������" + getCpuCount());
		mPhoneDecrtionInfos.add(mPhoneDecInfo3);

		// �ֻ��ֱ��ʺ�����ֱ���
		PhoneDecetionInfo mPhoneDecInfo4 = new PhoneDecetionInfo(
				R.drawable.setting_info_icon_camera, "�ֻ��ֱ��ʣ�"
						+ getPhoneResolution(context), "����ֱ��ʣ�"
						+ getCameraResolution());
		mPhoneDecrtionInfos.add(mPhoneDecInfo4);
		// �����汾
		PhoneDecetionInfo mPhoneDecInfo5 = new PhoneDecetionInfo(
				R.drawable.setting_info_icon_root, "�����汾��" + getRadVision(),
				"�Ƿ�ROOT" + (isRoot() ? "��" : "��"));
		mPhoneDecrtionInfos.add(mPhoneDecInfo5);

		return mPhoneDecrtionInfos;

	}

	// ��ȡcpu����
	public static String getCpuName() {
		File file = new File(CPU_INFO_PATH);
		FileInputStream fis = null;
		BufferedReader buf = null;
		try {
			fis = new FileInputStream(file);
			buf = new BufferedReader(new InputStreamReader(fis));
			String readLine = buf.readLine();
			String[] strs = readLine.split(":\\s+");
			return strs[1];

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (buf != null) {
				try {
					buf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}

	// cpu����
	public static int getCpuCount() {
		File cpuCore = new File(CPU_NUM_PATH);
		File[] files = cpuCore.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				String fileName = pathname.getName();
				if (Pattern.matches("cpu[0-9]", fileName)) {
					return true;
				}
				return false;
			}
		});

		return null != files ? files.length : 0;
	}

	// �ֻ��ֱ���
	public static String getPhoneResolution(Activity activity) {
		DisplayMetrics outMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels + "*" + outMetrics.heightPixels;
	}

	// ����ֱ���
	public static String getCameraResolution() {
		Camera camera = Camera.open();
		Parameters par = camera.getParameters();
		List<Size> sizes = par.getSupportedPictureSizes();
		Size size = sizes.get(0);
		return size.width + "*" + size.height;

	}

	// �ֻ������汾
	public static String getRadVision() {
		if (Build.getRadioVersion() == null) {
			return "unknown";
		} else {
		return Build.getRadioVersion();
		}
	}

	// �ж��Ƿ�root
	public static boolean isRoot() {
		Runtime runtime = Runtime.getRuntime();
		// �л�������Ա����
		try {
			Process pro = runtime.exec("su");
			// д����
			OutputStream ops = pro.getOutputStream();
			ops.write("ls data \n".getBytes());
			ops.write("exit \n".getBytes());
		} catch (IOException e) {
			return false;
		}
		return true;

	}

}