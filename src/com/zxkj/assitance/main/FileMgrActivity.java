package com.zxkj.assitance.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zxkj.assitance.R;
import com.zxkj.assitance.Adapat.FileMgrAdapater;
import com.zxkj.assitance.biz.FileManager;
import com.zxkj.assitance.entity.FileInfo;
import com.zxkj.assitance.util.FileUitls;

public class FileMgrActivity extends BaseActivity {
	Handler mHanlder = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			// ������
			long totalFile = (long) fileInfos.get(0).getFileSize();
			mTextFile.setText(FileUitls.formatLength(totalFile));
			adapater.update(fileInfos);
		};
	};
	protected TextView mtixtView;
	protected ListView mListView;
	protected ImageView mImageSeting;
	protected ImageView mImageAbout;
	protected TextView mTextFile;
	protected FileMgrAdapater adapater;
	// ��������Դ
	protected List<FileInfo> fileInfos;
	protected String[] fileTypes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_mgr);
		initData();

	}

	@Override
	protected void onStart() {
		super.onStart();
		initView();
		initTitle(R.drawable.btn_homeasup_default, "�ļ�����", View.GONE);
		initEvent();
		File file = Environment.getExternalStorageDirectory();
		FileManager fileManager = FileManager.getInstance();
		fileManager.seaerch(file, fileInfos, mHanlder);

	}
	// ��ʼ����ͼ
	public void initView() {
			mImageAbout = (ImageView) this.findViewById(R.id.img_mina_about);
			mImageSeting = (ImageView) this.findViewById(R.id.img_mina_set);
			mtixtView = (TextView) this.findViewById(R.id.txt_mina_title);
			mListView =  (ListView) this.findViewById(R.id.list_file);
			mTextFile=(TextView) this.findViewById(R.id.text_file_total_menu);
		adapater = new FileMgrAdapater(fileInfos, this);
		mListView.setAdapter(adapater);

		}

		// ��ʼ������
		public void initTitle(int resId, String title, int visible) {
			mImageAbout.setImageResource(resId);
			mtixtView.setText(title);
		mImageSeting.setImageResource(visible);
		}

	// ��ʼ������Դ
	public void initData() {
		fileTypes = this.getResources().getStringArray(
				R.array.file_mgr_type_list);
		fileInfos = new ArrayList<FileInfo>();
		for (int i = 0; i < fileTypes.length; i++) {
		FileInfo fileInfo = new FileInfo();
			fileInfo.setFileType(fileTypes[i]);
		fileInfos.add(fileInfo);
		}

	}

	// ��ʼ���¼�
	public void initEvent() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// ��ת
				Bundle bundle = new Bundle();
				bundle.putInt("position", position);
				toActivity(FileDetailActivity.class, bundle);
			}
		});
	}

}
