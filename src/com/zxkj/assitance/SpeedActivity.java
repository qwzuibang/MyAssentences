package com.zxkj.assitance;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zxkj.assitance.Adapat.SpeedAdapater;
import com.zxkj.assitance.biz.MumMenager;
import com.zxkj.assitance.biz.PhoneInfoManager;
import com.zxkj.assitance.entity.ProgressInfo;
import com.zxkj.assitance.util.FileUitls;

public class SpeedActivity extends Activity {
	protected TextView mTxtDevName;
	protected TextView mTxtDevVersion;
	protected TextView mTxtRunUsedMum;
	protected TextView mTxtRunTotalMum;
	protected ProgressBar mPgb;
	protected ListView mLstRunInfo;
	protected Button mButClean;
	protected Button mButSystemProgress;
	protected CheckBox mCheck;
	protected List<ProgressInfo> mListProInfo;
	protected SpeedAdapater spdAdapater;
	protected ActivityManager mActMag;
	protected boolean isUser = true;
	PhoneInfoManager pInfoManag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_speed);
		initView();
		initDate();
		initEvent();
	}

	public void initView() {
		mTxtDevName = (TextView) this.findViewById(R.id.text_spd_dev_name);
		mTxtDevVersion = (TextView) this
				.findViewById(R.id.text_spd_dev_version);
		mPgb = (ProgressBar) this.findViewById(R.id.pgb_spd_run_mum);
		mTxtRunUsedMum = (TextView) this.findViewById(R.id.text_spd_used_mum);
		mTxtRunTotalMum = (TextView) this.findViewById(R.id.text_spd_total_mum);
		mButClean = (Button) this.findViewById(R.id.button_spd_clean);
		mLstRunInfo = (ListView) this.findViewById(R.id.list_spd_clean);
		mCheck = (CheckBox) this.findViewById(R.id.check_spd_clean);
		mButSystemProgress = (Button) this
				.findViewById(R.id.button_spd_system_progress);



		mTxtDevName.setText(PhoneInfoManager.getDevName());
		mTxtDevVersion.setText(PhoneInfoManager.getDevVersion());

		mTxtRunUsedMum.setText(FileUitls.formatLength(MumMenager
				.getRuntimeUsedMem(this)));
		mTxtRunTotalMum.setText(FileUitls.formatLength(MumMenager
				.getRuntimeTotalMem(this)));
		mPgb.setProgress((int) ((MumMenager.getRuntimeUsedMem(this) * 1.0 / MumMenager
				.getRuntimeTotalMem(this)) * 100));
		mButClean.setBackgroundResource(R.drawable.file_delet_button_shap);
		mButSystemProgress
				.setBackgroundResource(R.drawable.file_delet_button_shap);
	}

	public void initDate() {
		new Thread() {
			@Override
			public void run() {
				pInfoManag = new PhoneInfoManager(
						SpeedActivity.this);
				mListProInfo = pInfoManag.getUserPro();

				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// 创建适配器
						spdAdapater = new SpeedAdapater(SpeedActivity.this,
								mListProInfo, isUser);
						mLstRunInfo.setAdapter(spdAdapater);
					}
				});
			}
		}.start();
	}

	public void initEvent() {
		mLstRunInfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				ProgressInfo pgsInfo = mListProInfo.get(position);
				CheckBox check = (CheckBox) view
						.findViewById(R.id.chock_speed_item);
				pgsInfo.isChecked = pgsInfo.isChecked ? false : true;
				check.setChecked(pgsInfo.isChecked ? true : false);

			}
		});
		mButClean.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				cleanProgess(mListProInfo);

			}
		});
		mButSystemProgress.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (isUser) {
					mButSystemProgress.setText("显示用户进程");
					pInfoManag = new PhoneInfoManager(SpeedActivity.this);
					mListProInfo = pInfoManag.getSystemPro();
					isUser = false;

				}
 else {


					pInfoManag = new PhoneInfoManager(SpeedActivity.this);
					mListProInfo = pInfoManag.getUserPro();
					isUser = true;
					mButSystemProgress.setText("显示系统进程");
				}
				spdAdapater.update(mListProInfo, isUser);
			}
		});
		mCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isChocked) {
				for (int i = 0; i < mListProInfo.size(); i++) {
					mListProInfo.get(i).isChecked = isChocked;
				}
				spdAdapater.update(mListProInfo, isUser);
			}
		});

	}

	public void cleanProgess(List<ProgressInfo> mListProInfo) {
		for (int i = 0; i < mListProInfo.size(); i++) {
			ProgressInfo proInfo = mListProInfo.get(i);
			if (proInfo.isChecked) {
				String pkg = proInfo.pkgName;
				mActMag = (ActivityManager) this
						.getSystemService(this.ACTIVITY_SERVICE);
				mActMag.killBackgroundProcesses(pkg);
				mCheck.setChecked(false);
				initDate();
			}
		}
	}
}
