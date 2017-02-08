package com.zxkj.assitance.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void toActivity(Class cla) {
		toActivity(cla, null);
	}

	public void toActivity(Class cla, Bundle bundle) {
		Intent intent = new Intent(this, cla);
		if (bundle != null)
			intent.putExtra("bundle", bundle);
		startActivity(intent);
		// finish();
	}
}
