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
			// 处理结果
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
	// 定义数据源
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
		initTitle(R.drawable.btn_homeasup_default, "文件管理", View.GONE);
		initEvent();
		File file = Environment.getExternalStorageDirectory();
		FileManager fileManager = FileManager.getInstance();
		fileManager.seaerch(file, fileInfos, mHanlder);

	}
	// 初始化视图
	public void initView() {
			mImageAbout = (ImageView) this.findViewById(R.id.img_mina_about);
			mImageSeting = (ImageView) this.findViewById(R.id.img_mina_set);
			mtixtView = (TextView) this.findViewById(R.id.txt_mina_title);
			mListView =  (ListView) this.findViewById(R.id.list_file);
			mTextFile=(TextView) this.findViewById(R.id.text_file_total_menu);
		adapater = new FileMgrAdapater(fileInfos, this);
		mListView.setAdapter(adapater);

		}

		// 初始化标题
		public void initTitle(int resId, String title, int visible) {
			mImageAbout.setImageResource(resId);
			mtixtView.setText(title);
		mImageSeting.setImageResource(visible);
		}

	// 初始化数据源
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

	// 初始化事件
	public void initEvent() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// 跳转
				Bundle bundle = new Bundle();
				bundle.putInt("position", position);
				toActivity(FileDetailActivity.class, bundle);
			}
		});
	}

}
