package com.zxkj.assitance.biz;

import java.io.File;
import java.util.List;
import java.util.Vector;

import android.os.Handler;

import com.zxkj.assitance.R;
import com.zxkj.assitance.entity.FileDetailInfo;
import com.zxkj.assitance.entity.FileInfo;
import com.zxkj.assitance.util.FileUitls;

public class FileManager {
	private static FileManager mFilManager;

	private FileManager() {

	}

	public static FileManager getInstance() {
		if (mFilManager == null) {
			synchronized (FileManager.class) {
				if (null == mFilManager) {
					mFilManager = new FileManager();
				}

			}

		}
		return mFilManager;
	}

	private final Vector<FileDetailInfo> allFeilDetail = new Vector<FileDetailInfo>();
	private final Vector<FileDetailInfo> textFeilDetail = new Vector<FileDetailInfo>();
	private final Vector<FileDetailInfo> videoFeilDetail = new Vector<FileDetailInfo>();
	private final Vector<FileDetailInfo> audioFeilDetail = new Vector<FileDetailInfo>();
	private final Vector<FileDetailInfo> imageFeilDetail = new Vector<FileDetailInfo>();
	private final Vector<FileDetailInfo> zipFeilDetail = new Vector<FileDetailInfo>();
	private final Vector<FileDetailInfo> progressFeilDetail = new Vector<FileDetailInfo>();

	public Vector<FileDetailInfo> getFileDetailByPosition(int position) {
		switch (position) {
		case 0:
			return allFeilDetail;
		case 1:
			return textFeilDetail;
		case 2:
			return videoFeilDetail;
		case 3:
			return audioFeilDetail;
		case 4:
			return imageFeilDetail;
		case 5:
			return zipFeilDetail;
		case 6:
			return progressFeilDetail;

		}
		return allFeilDetail;
	}

	protected List<FileInfo> mFileInfos;
	protected File mFileSdcard;
	protected Handler mHandler;
	public static final int MSG_LOAD_FINALLY = 1;
	protected int allFileNum;
	protected float allFileSize;
	protected int textFileNum;
	protected float textFileSize;
	protected int videoFileNum;
	protected float videoFileSize;
	protected int audioFileNum;
	protected float audioFileSize;
	protected int imageFileNum;
	protected float imageFileSize;
	protected int zipFileNum;
	protected float zipFileSize;
	protected int progessFileNum;
	protected float progessFileSize;

	// 重置数据
	public void resetData() {
		allFileNum = 0;
		allFileSize = 0;

		textFileNum = 0;
		textFileSize = 0;

		videoFileNum = 0;
		videoFileSize = 0;

		audioFileNum = 0;
		audioFileSize = 0;

		imageFileNum = 0;
		imageFileSize = 0;

		zipFileNum = 0;
		zipFileSize = 0;

		progessFileNum = 0;
		progessFileSize = 0;

		if (allFeilDetail != null) {
			allFeilDetail.clear();
		}

		if (textFeilDetail != null) {
			textFeilDetail.clear();
		}

		if (videoFeilDetail != null) {
			videoFeilDetail.clear();
		}

		if (audioFeilDetail != null) {
			audioFeilDetail.clear();
		}

		if (imageFeilDetail != null) {
			imageFeilDetail.clear();
		}

		if (zipFeilDetail != null) {
			zipFeilDetail.clear();
		}

		if (progressFeilDetail != null) {
			progressFeilDetail.clear();
		}

	}
	public void seaerch(File fileSdcard, List<FileInfo> fileInfos,
			Handler mHandler) {
		this.mFileSdcard = fileSdcard;
		this.mFileInfos = fileInfos;
		this.mHandler = mHandler;
		resetData();
		new Thread() {
			@Override
			public void run() {
				searchFile(mFileSdcard);
			};
		}.start();
	}

	public void searchFile(File sdcard) {
		File allFile[] = sdcard.listFiles();
		for (File file : allFile) {
			if (file.isFile()) {
				allFileSize += file.length();
				allFileNum++;
				FileInfo allFileInfo = mFileInfos.get(0);
				allFileInfo.setFileNum(allFileNum);
				allFileInfo.setFileSize(allFileSize);
				String fileName = file.getName();
				String fileSuffix = suffix(fileName);
				allFeilDetail.add(creatFileDetailInfoEntity(fileName,
 file,
						fileSuffix));
				if (FileUitls.isTextType(fileSuffix)) {
					textFileSize += file.length();
					textFileNum++;
					FileInfo textFileInfo = mFileInfos.get(1);
					textFileInfo.setFileNum(textFileNum);
					textFileInfo.setFileSize(textFileSize);
					textFeilDetail.add(creatFileDetailInfoEntity(fileName,
							file, fileSuffix));
				} else if (FileUitls.isVideoFile(fileSuffix)) {
					videoFileSize += file.length();
					videoFileNum++;
					FileInfo videoFileInfo = mFileInfos.get(2);
					videoFileInfo.setFileNum(videoFileNum);
					videoFileInfo.setFileSize(videoFileSize);
					videoFeilDetail.add(creatFileDetailInfoEntity(fileName,
							file, fileSuffix));
				} else if (FileUitls.isAudioFile(fileSuffix)) {
					audioFileSize += file.length();
					audioFileNum++;
					FileInfo audioFileInfo = mFileInfos.get(3);
					audioFileInfo.setFileNum(audioFileNum);
					audioFileInfo.setFileSize(audioFileSize);

					audioFeilDetail.add(creatFileDetailInfoEntity(fileName,
							file, fileSuffix));
				} else if (FileUitls.isImageFile(fileSuffix)) {
					imageFileSize += file.length();
					imageFileNum++;
					FileInfo imageFileInfo = mFileInfos.get(4);
					imageFileInfo.setFileNum(imageFileNum);
					imageFileInfo.setFileSize(imageFileSize);

					imageFeilDetail.add(creatFileDetailInfoEntity(fileName,
							file, fileSuffix));
				} else if (FileUitls.isZipFile(fileSuffix)) {
					zipFileSize += file.length();
					zipFileNum++;
					FileInfo zipFileInfo = mFileInfos.get(5);
					zipFileInfo.setFileNum(zipFileNum);
					zipFileInfo.setFileSize(zipFileSize);

					zipFeilDetail.add(creatFileDetailInfoEntity(fileName,
 file,
							fileSuffix));
				} else if (FileUitls.isProgramFile(fileSuffix)) {
					progessFileSize += file.length();
					progessFileNum++;
					FileInfo progessFileInfo = mFileInfos.get(6);
					progessFileInfo.setFileNum(progessFileNum);
					progessFileInfo.setFileSize(progessFileSize);

					progressFeilDetail.add(creatFileDetailInfoEntity(fileName,
							file, fileSuffix));
				}
			} else {
				searchFile(file);
			}
			// 给主线程发消息
			mHandler.sendEmptyMessage(MSG_LOAD_FINALLY);
		}
	}

	public String suffix(String fileName) {
		return fileName == null ? "default" : fileName.substring(fileName
				.lastIndexOf(".") + 1);
	}

	public FileDetailInfo creatFileDetailInfoEntity(String feilName, File file,
			String suffix) {
		return new FileDetailInfo(feilName, R.drawable.ic_launchabc, file,
				suffix);
	}
}
