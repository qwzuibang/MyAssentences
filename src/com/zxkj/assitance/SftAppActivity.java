package com.zxkj.assitance;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zxkj.assitance.Adapat.SftAppMsgAdapter;
import com.zxkj.assitance.biz.SftManager;
import com.zxkj.assitance.biz.SftManagerInfo;

public class SftAppActivity extends Activity {
	protected String[] sftTitle = { "所有软件", "系统软件", "用户软件" };
	protected int position;
	protected SftManager mSftManager;
	protected List<SftManagerInfo> mSftMgInfo;
	protected TextView mSftTitle;
	protected SftAppMsgAdapter sftMesgAdapater;
	protected ListView mLstView;
	protected Button mBut;
	protected CheckBox mChock;
	ImageView img;
	Animation aim;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sft_app);
		img = (ImageView) this.findViewById(R.id.img_rotate);
		aim = AnimationUtils.loadAnimation(this, R.anim.logic_rotate);
		img.startAnimation(aim);

		Bundle bundle = this.getIntent().getBundleExtra("bundle");
		position = bundle.getInt("position", -1);

		initView();
		initDadt();
		initEvent();
	}


	public void initView() {
		mSftTitle = (TextView) this.findViewById(R.id.text_sft_title);
		mLstView = (ListView) this.findViewById(R.id.list_sft_app_content);
		mChock = (CheckBox) this.findViewById(R.id.check_sft_all);
		mBut = (Button) this.findViewById(R.id.button_sft_delet);
		mSftTitle.setText(sftTitle[position]);
		mBut.setBackgroundResource(R.drawable.file_delet_button_shap);
	}

	public void initDadt() {
		new Thread() {
			@Override
			public void run() {
				mSftManager = new SftManager(SftAppActivity.this);
				mSftMgInfo = mSftManager.getSftMgeInfo(position);
				img.clearAnimation();
				runOnUiThread(new Runnable() {

					@Override
					public void run() {

						// 在主线程运行
						sftMesgAdapater = new SftAppMsgAdapter(
								SftAppActivity.this, mSftMgInfo);
						mLstView.setAdapter(sftMesgAdapater);
						img.clearAnimation();
						img.setVisibility(View.GONE);
					}
				});
			};
		}.start();

	}

	public void initEvent() {

		mLstView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				SftManagerInfo sftMgInfo = mSftMgInfo.get(position);
				CheckBox itemCheck = (CheckBox) view
						.findViewById(R.id.check_sft_item_msg);
				sftMgInfo.isChecked = sftMgInfo.isChecked ? false : true;
				itemCheck.setChecked(sftMgInfo.isChecked ? true : false);
			}
		});
		mBut.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				deletApp(mSftMgInfo);
				mChock.setChecked(false);
			}
		});
		mChock.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
				for (int i = 0; i < mSftMgInfo.size(); i++) {
					SftManagerInfo sMI = mSftMgInfo.get(i);
					sMI.setChecked(isChecked);
				}
				sftMesgAdapater.update(mSftMgInfo);

			}
		});

	}

	public void deletApp(List<SftManagerInfo> sftMgnInfo) {
		for (int i = 0; i < sftMgnInfo.size(); i++) {
			SftManagerInfo sftMInfo = sftMgnInfo.get(i);
			if (sftMInfo.isChecked) {
				String pkg = sftMInfo.appPakge;
				Intent intent = new Intent(Intent.ACTION_DELETE);
				intent.setData(Uri.parse("package:" + pkg));
				startActivityForResult(intent, 2);
			}
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {

		super.onActivityResult(requestCode, resultCode, intent);
		if (requestCode == 2) {
			initDadt();
		}
	}
}
