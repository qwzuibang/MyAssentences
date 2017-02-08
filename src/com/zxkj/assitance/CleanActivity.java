package com.zxkj.assitance;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxkj.assitance.Adapat.CleanAdapter;
import com.zxkj.assitance.biz.CleanManager;
import com.zxkj.assitance.entity.ChildInfo;
import com.zxkj.assitance.entity.GroupInfo;
import com.zxkj.assitance.util.FileUitls;

public class CleanActivity extends Activity {
	protected TextView mtixtView;
	protected ImageView mImageSeting;
	protected ImageView mImageAbout;

	protected TextView mTextExist;
	protected ExpandableListView mEbv;
	protected CheckBox mCheckClean;
	protected Button mBtnDelet;
	protected List<GroupInfo> mGroupInfos = new ArrayList<GroupInfo>();
	protected List<List<ChildInfo>> mChildInfos = new ArrayList<List<ChildInfo>>();
	protected Handler mHandle = new Handler();
	protected long mGroupMum;
	protected long sumMum;
	protected CleanManager cleanManager;
	protected ProgressDialog mPgd;
	protected CleanAdapter cleanAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clean);

		mPgd = ProgressDialog.show(this, "请稍等", "正在加载中...");

		initData();
		initView();
		initTitle(R.drawable.btn_homeasup_default, "垃圾清理", View.GONE);
		initEvent();
	}

	public void initView() {
		mImageAbout = (ImageView) this.findViewById(R.id.img_mina_about);
		mImageSeting = (ImageView) this.findViewById(R.id.img_mina_set);
		mtixtView = (TextView) this.findViewById(R.id.txt_mina_title);

		mTextExist = (TextView) this.findViewById(R.id.text_clean_total_menu);
		mEbv = (ExpandableListView) this.findViewById(R.id.edl_clean_main_mum);
		mBtnDelet = (Button) this.findViewById(R.id.button_clean);
		mCheckClean = (CheckBox) this.findViewById(R.id.check_clean);
		mBtnDelet.setBackgroundResource(R.drawable.file_delet_button_shap);

	}

	// 初始化标题
	public void initTitle(int resId, String title, int visible) {
		mImageAbout.setImageResource(resId);
		mtixtView.setText(title);
		mImageSeting.setImageResource(visible);
	}
	// 初始化数据
	public void initData() {
		String[] groupList = this.getResources().getStringArray(
				R.array.clean_edl_group);
		for (int i = 0; i < groupList.length; i++) {
			GroupInfo groupInfo = new GroupInfo();
			groupInfo.setGroupTitle(groupList[i]);
			mGroupInfos.add(groupInfo);


		}
		new Thread() {
			@Override
			public void run() {
				cleanManager = CleanManager
						.getInstance(CleanActivity.this);
				cleanManager.search();
				mChildInfos.add(cleanManager.getPhoneCacheInfo());
				mChildInfos.add(cleanManager.getPhoneFileInfo());
				mChildInfos.add(cleanManager.getSDCacheInfo());
				mChildInfos.add(cleanManager.getSDFileInfo());
				mHandle.post(new Runnable() {
					@Override
					public void run() {
						sumMum=0;
						for (int i = 0; i < mChildInfos.size(); i++) {
							long totalMum = calGroupMum(mChildInfos.get(i));
							mGroupInfos.get(i).setGroupTotalMum(
									FileUitls.formatLength(totalMum));
							sumMum += totalMum;
						}
						mTextExist.setText(FileUitls.formatLength(sumMum));
						// 主线程 创建适配器
						cleanAdapter = new CleanAdapter(CleanActivity.this,
								mGroupInfos, mChildInfos);
						mEbv.setAdapter(cleanAdapter);
						mPgd.cancel();

					}

				});
			};

		}.start();

	}

	public void initEvent() {

		mEbv.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View view,
					int groupPosition, int childPosition, long arg4) {
				ChildInfo cInfo = mChildInfos.get(groupPosition).get(
						childPosition);
				CheckBox chockChild = (CheckBox) view
						.findViewById(R.id.check_clean_child);
				cInfo.setChocked(cInfo.isChocked() ? false : true);
				chockChild.setChecked(cInfo.isChocked() ? true : false);

				return false;
			}
		});

		mBtnDelet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				deletFile(mChildInfos);
			}
		});

		// 删除全部
		mCheckClean.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// 获取所有的子条目
				for (int i = 0; i < mChildInfos.size(); i++) {
					for (int j = 0; j < mChildInfos.get(i).size(); j++) {
						ChildInfo cInfo = mChildInfos.get(i).get(j);
						cInfo.setChocked(isChecked);
					}
				}
				cleanAdapter.update(mGroupInfos, mChildInfos);

			}
		});
	}

	// 删除
	public void deletFile(List<List<ChildInfo>> mChildInfos) {
		sumMum = 0;
		for (int i = 0; i < mChildInfos.size(); i++) {
			for (int j = 0; j < mChildInfos.get(i).size(); j++) {
				ChildInfo cInfo = mChildInfos.get(i).get(j);
				if (cInfo.isChocked()) {
					File cInfoFile = cInfo.getAppFile();
					if (delet(cInfoFile)) {
						mChildInfos.get(i).remove(j);
						long total = calGroupMum(mChildInfos.get(i));
						String newGroupMum = FileUitls.formatLength(total);
						mGroupInfos.get(i).setGroupTotalMum(newGroupMum);

						mTextExist.setText(FileUitls.formatLength(sumMum));

						cleanAdapter.update(mGroupInfos, mChildInfos);
						mCheckClean.setChecked(false);


					}
				}

			}
		}
	}

	// 删除方法
	public boolean delet(File file) {
		boolean isSuccess = true;
		if (file != null) {
			File[] files = file.listFiles();
			for (File itemFile : files) {
				if (itemFile.isFile()) {
					isSuccess = itemFile.delete();
				} else {
					delet(itemFile);
				}
			}
		}
		return isSuccess;
	}

	public long calGroupMum(List<ChildInfo> childInfos) {
		mGroupMum = 0;
		for (ChildInfo cInfo : childInfos) {
			mGroupMum += cInfo.getTotelMem();
		}

		return mGroupMum;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clean, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
