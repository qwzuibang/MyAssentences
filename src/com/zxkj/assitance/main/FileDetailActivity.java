package com.zxkj.assitance.main;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zxkj.assitance.R;
import com.zxkj.assitance.Adapat.FileDetailAdapater;
import com.zxkj.assitance.biz.FileManager;
import com.zxkj.assitance.entity.FileDetailInfo;
import com.zxkj.assitance.util.FileUitls;

public class FileDetailActivity extends Activity {
	protected List<FileDetailInfo> mFileDetailInfo;
	protected TextView mtixtView;
	protected ImageView mImageSeting;
	protected ImageView mImageAbout;
	protected String fileType[];


	protected TextView mTextFileTotalNum;
	protected TextView mTextFileTotalMum;
	protected ListView mListFileDetail;
	protected Button mDeletFileDetail;
	protected FileDetailAdapater adapater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_detail);


		Bundle bundle = this.getIntent().getBundleExtra("bundle");
		int position = bundle.getInt("position", -1);
		FileManager fileManager = FileManager.getInstance();
		mFileDetailInfo = fileManager.getFileDetailByPosition(position);
		initView();
		fileType = this.getResources().getStringArray(
				R.array.file_mgr_type_list);
		initTitle(R.drawable.btn_homeasup_default, fileType[position],
				View.GONE);

		initEvent();
		initNumMum();
	}

	// 初始化视图
	public void initView() {
		mImageAbout = (ImageView) this.findViewById(R.id.img_mina_about);
		mImageSeting = (ImageView) this.findViewById(R.id.img_mina_set);
		mtixtView = (TextView) this.findViewById(R.id.txt_mina_title);

		mTextFileTotalNum = (TextView) this
				.findViewById(R.id.text_feil_detail_num);
		mTextFileTotalMum = (TextView) this
				.findViewById(R.id.text_feil_detail_mum);
		mListFileDetail = (ListView) this.findViewById(R.id.list_file_detail);
		mDeletFileDetail = (Button) this.findViewById(R.id.button_file_detail);
		adapater = new FileDetailAdapater(this,
				mFileDetailInfo);
		mListFileDetail.setAdapter(adapater);
		mDeletFileDetail
				.setBackgroundResource(R.drawable.file_delet_button_shap);

	}

	// 初始化标题
	public void initTitle(int resId, String title, int visible) {
		mImageAbout.setImageResource(resId);
		mtixtView.setText(title);
		mImageSeting.setImageResource(visible);
	}

	public void initEvent() {
		mListFileDetail.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(
						Uri.fromFile(mFileDetailInfo.get(position).getFile()),
						"*/*");
				startActivity(intent);
			}
		});
		mListFileDetail
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> arg0,
							View view, int position, long arg3) {
						// 获取CheckBox对象
						CheckBox checkBoxd = (CheckBox) view
								.findViewById(R.id.check_file_detail);
						// 获取文件
						FileDetailInfo fileDetailInfo = mFileDetailInfo
								.get(position);
						if (fileDetailInfo.isCheak()) {
							// 未选中
							checkBoxd.setChecked(false);
							fileDetailInfo.setCheak(false);
						} else {
							checkBoxd.setChecked(true);
							fileDetailInfo.setCheak(true);
						}
						return false;
					}
				});
		// 删除文件
		mDeletFileDetail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				for (int i = 0; i < mFileDetailInfo.size(); i++) {

					if (mFileDetailInfo.get(i).isCheak()) {
						boolean isSuccess = mFileDetailInfo.get(i).getFile()
								.delete();
						if (isSuccess) {
							mFileDetailInfo.remove(mFileDetailInfo.remove(i));
							// 界面更新
							adapater.update(mFileDetailInfo);
							// 初始化
							initNumMum();

						}
					}

				}
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_detail, menu);
		return true;
	}

	long fileSize;

	public long calMum() {
		fileSize = 0;
		for (FileDetailInfo mFileDIF : mFileDetailInfo) {

			fileSize += mFileDIF.getFile().length();
		}
		return fileSize;
	}

	public void initNumMum() {
		mTextFileTotalNum.setText(mFileDetailInfo.size() + "个");
		mTextFileTotalMum.setText(FileUitls.formatLength(calMum()));
	}
}
