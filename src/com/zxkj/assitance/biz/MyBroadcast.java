package com.zxkj.assitance.biz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

public class MyBroadcast extends BroadcastReceiver {
	int currentBattery;
	int tempBattery;
	OnReceiverBattery mOnReceiverBattery;
	@Override
	public void onReceive(Context context, Intent intent) {
		currentBattery = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		tempBattery = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);

		mOnReceiverBattery.onReceiverBattery(currentBattery, tempBattery);
	}

	public void setOnReceiverBattery(OnReceiverBattery onReceiverBattery) {
		this.mOnReceiverBattery = onReceiverBattery;
	}

	public interface OnReceiverBattery {
		void onReceiverBattery(int currentBattery, int tempBattery);

}
}
