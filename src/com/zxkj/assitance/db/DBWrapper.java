package com.zxkj.assitance.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zxkj.assitance.entity.LogWrapper;
import com.zxkj.assitance.entity.TelInfo;

public class DBWrapper {
	public static final String DB_FILE_NAME = "commonnum.db";
	public static final String TABLE_CLASS_LIST_NAME = "classlist";
	static File mFile;

	public static class FileHelper {
		// 拷贝文件
		public void CopyFromAssets(Context context) {
			// 管理assets文件夹
			AssetManager assetManager = context.getAssets();
			InputStream in = null;
			FileOutputStream fos = null;
			try {
				in = assetManager.open("db/" + DB_FILE_NAME);
				File pFile = new File("/data/data/com.zxkj.assitance/databases");
				if (!pFile.exists()) {
					pFile.mkdirs();
				}
				if (null == mFile) {
					mFile = new File(pFile, DB_FILE_NAME);
					LogWrapper.e("asdddddd", "" + mFile.length());
					fos = new FileOutputStream(mFile);
					byte[] bs = new byte[1024];
					int length = 0;
					while ((length = in.read(bs)) != -1) {
						fos.write(bs, 0, length);
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	// 查询
	public static class DBHelp {
		// 查询通话大全的菜单信息

		public List<String> readClassList() {
			SQLiteDatabase db = SQLiteDatabase
					.openOrCreateDatabase(mFile, null);
			Cursor cursor = db.query(TABLE_CLASS_LIST_NAME, null, null, null,
					null, null, null);
			List<String> menus = new ArrayList<String>();
			while (cursor.moveToNext()) {
				String menhuName = cursor.getString(cursor
						.getColumnIndex("name"));
				menus.add(menhuName);
			}
			cursor.close();
			return menus;

		}

		// 查询子列表
		public List<TelInfo> readItemList(int position) {
			SQLiteDatabase db = SQLiteDatabase
					.openOrCreateDatabase(mFile, null);
			Cursor cursor = db.query("table" + (position + 1), null, null,
					null, null, null, null);
			List<TelInfo> telInfo = new ArrayList<TelInfo>();
			while (cursor.moveToNext()) {
				String telName = cursor
						.getString(cursor.getColumnIndex("name"));
				String telNum = cursor.getString(cursor
						.getColumnIndex("number"));
				TelInfo tInfo = new TelInfo(telName, telNum);
				telInfo.add(tInfo);
			}
			cursor.close();
			return telInfo;

		}
	}

}
