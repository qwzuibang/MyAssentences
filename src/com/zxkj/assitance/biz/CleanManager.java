package com.zxkj.assitance.biz;

import java.io.File;
import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;

import com.zxkj.assitance.entity.ChildInfo;

public class CleanManager {
	protected Context mContext;
	protected PackageManager mPackageManager;

	// ����ģʽ
	private static CleanManager mCleanManager;

	private CleanManager(Context context) {
		this.mContext = context;
	}

	public static CleanManager getInstance(Context context) {
		if (mCleanManager == null) {
			synchronized (CleanManager.class) {
				if (mCleanManager == null) {
					mCleanManager = new CleanManager(context);
				}
			}
		}
		return mCleanManager;
	}

	// �����ĸ�����
	private final Vector<ChildInfo> phoneCacheInfo = new Vector<ChildInfo>();
	private final Vector<ChildInfo> phoneFileInfo = new Vector<ChildInfo>();
	private final Vector<ChildInfo> SDCacheInfo = new Vector<ChildInfo>();
	private final Vector<ChildInfo> SDFileInfo = new Vector<ChildInfo>();

	public Vector<ChildInfo> getPhoneCacheInfo() {

		return phoneCacheInfo;
	}

	public Vector<ChildInfo> getPhoneFileInfo() {
		return phoneFileInfo;
	}

	public Vector<ChildInfo> getSDCacheInfo() {
		return SDCacheInfo;
	}

	public Vector<ChildInfo> getSDFileInfo() {
		return SDFileInfo;
	}

	// ��������
	public void reset() {
		phoneCacheInfo.clear();
		phoneFileInfo.clear();
		SDCacheInfo.clear();
		SDFileInfo.clear();
	}

	// �����ļ��� �������
	public void search() {
		reset();
		// ��ȡ����Ӧ�ó���İ���
		mPackageManager = mContext.getPackageManager();
		// ��ȡ�����Ѿ���װ�ĳ���İ�����Ϣ
		List<PackageInfo> mPackageInfo = mPackageManager
				.getInstalledPackages(0);
		for (PackageInfo pInfo : mPackageInfo) {
			if (isUserApp(pInfo)) {
				String pkgName = pInfo.packageName;
			Context context = creatContext(pkgName);

			if (context != null) {
				File phoneCacheFile = context.getCacheDir();


					if (null != phoneCacheFile && !phoneCacheFile.exists()) {
						continue;
					}

				ChildInfo pChildCacheFile = creatChildInfo(phoneCacheFile,
						pInfo);
					if (null != pChildCacheFile
							&& pChildCacheFile.getTotelMem() != 0) {

					phoneCacheInfo.add(pChildCacheFile);
					}

				File phoneFile = context.getFilesDir();
					if (null != phoneFile && !phoneFile.exists()) {
					continue;
				}
					ChildInfo pChildFile = creatChildInfo(phoneFile, pInfo);
					if (null != pChildFile && pChildFile.getTotelMem() != 0) {
					phoneFileInfo.add(pChildFile);
				}

				File SDCacheFile = context.getExternalCacheDir();
					if (null != SDCacheFile && !SDCacheFile.exists()) {
					continue;
				}
					ChildInfo sChildSDCatcheFile = creatChildInfo(SDCacheFile,
						pInfo);
				if (null != sChildSDCatcheFile
						&& sChildSDCatcheFile.getTotelMem() != 0) {
					SDCacheInfo.add(sChildSDCatcheFile);
				}


				File SDFile = context.getExternalFilesDir(null);
					if (null != SDFile && !SDFile.exists()) {
					continue;
				}
				ChildInfo sdChildFile = creatChildInfo(SDFile, pInfo);
				if (null != sdChildFile && sdChildFile.getTotelMem() != 0) {
					SDFileInfo.add(sdChildFile);

				}

			}

		}
		}

	}

	// ��ȡ��Ӧ����������Ķ���
	public Context creatContext(String pkgName) {
		Context context = null;
		try {
			context = mContext.createPackageContext(pkgName,
					Context.CONTEXT_IGNORE_SECURITY);
		} catch (NameNotFoundException e) {
			return null;
		} catch (SecurityException e) {
			return null;
		}
		return context;
	}

	public ChildInfo creatChildInfo(File file, PackageInfo pInfo) {
		ChildInfo childInfo = new ChildInfo();
		childInfo.setAppFile(file);

		ApplicationInfo appInfo = pInfo.applicationInfo;
		Drawable appIcon = appInfo.loadIcon(mPackageManager);
		childInfo.setAppIcon(appIcon);

		String appName = appInfo.loadLabel(mPackageManager).toString();
		childInfo.setAppName(appName);

		childInfo.setTotelMem(calAppMum(file));

		return childInfo;
	}

	int sum;

	public int calAppMum(File file) {
		sum = 0;
		if (file != null && file.exists()) {
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile()) {
						sum += files[i].length();
					} else {
						calAppMum(files[i]);
					}
				}
			}

		}
		return sum;
	}

	// �ж��Ƿ����û�����
	public boolean isUserApp(PackageInfo pInfo) {
		ApplicationInfo aInfo = pInfo.applicationInfo;
		// 0001 0001
		if ((aInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
			// �û�
			return true;
		} else {
			// ϵͳ
		}
		return false;

	}
}
