package com.zxkj.assitance.main;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.zxkj.assitance.R;

public class LogoActivity extends BaseActivity {
	// protected ImageView imageLogo;
	protected Timer mTimer;

	TimerTask mTimeTask = new TimerTask() {
		@Override
		public void run() {
			toActivity(MainActivity.class);
			mTimer.cancel();
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		// imageLogo = (ImageView) this.findViewById(R.id.image_logo);
		mTimer = new Timer();
		mTimer.schedule(mTimeTask, 2000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logo, menu);
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
