package com.zxkj.assitance;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zxkj.assitance.Adapat.DecetionAdapater;
import com.zxkj.assitance.biz.DecetionManager;
import com.zxkj.assitance.biz.MyBroadcast;
import com.zxkj.assitance.biz.MyBroadcast.OnReceiverBattery;
import com.zxkj.assitance.entity.PhoneDecetionInfo;

public class DecetionActivity extends Activity {
	MyBroadcast mbcoast;
	protected int mCurrectBatery;
	protected int mTempBattery;

	ProgressBar mProgessBar;
	TextView mTxtBattery;
	List<PhoneDecetionInfo> mPhoneDecetionInfo;
	ListView mListDec;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_decetion);
		// 注册系统广播
		mbcoast = new MyBroadcast();
		mbcoast.setOnReceiverBattery(new OnReceiverBattery() {

			@Override
			public void onReceiverBattery(int currentBattery, int tempBattery) {
				mCurrectBatery = currentBattery;
				mTempBattery = tempBattery;

				mProgessBar.setProgress(mCurrectBatery);
				String mCb = Integer.toString(mCurrectBatery);
				mTxtBattery.setText(mCb);
				initEvent();
			}

		});

		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(mbcoast, filter);

		initView();
		initDate();

	}

	public void initView() {
		mProgessBar = (ProgressBar) this.findViewById(R.id.pgb_dection_battery);
		mTxtBattery = (TextView) this
				.findViewById(R.id.text_dec_currty_battery);
		mListDec = (ListView) this.findViewById(R.id.list_dection);

	}

	public void initDate() {
		new Thread() {
			@Override
			public void run() {
				// 添加数据
				mPhoneDecetionInfo = DecetionManager
						.getDecetionInfo(DecetionActivity.this);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// 添加适配器
						DecetionAdapater decAdapater = new DecetionAdapater(
								DecetionActivity.this, mPhoneDecetionInfo);
						mListDec.setAdapter(decAdapater);
					}
				});
			};
		}.start();
	}

	public void initEvent(){
	mProgessBar.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
				AlertDialog.Builder build = new AlertDialog.Builder(
						DecetionActivity.this);
			build.setTitle("电池电量信息");
				build.setItems(
new String[] { "当前电量:" + mCurrectBatery,
						"电池温度:" + mTempBattery }, null);
				build.show();
		}
	});

}
}
