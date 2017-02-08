package com.zxkj.assitance;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.zxkj.assitance.Adapat.SettingAdapater;

public class SettingActivity extends Activity {

	protected String[] setItem;
	protected ListView mListView;
	protected SettingAdapater setAdapater;
	protected boolean isOpen[] = { false, false, false };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		mListView = (ListView) this.findViewById(R.id.list_setting);
		setItem = this.getResources().getStringArray(R.array.setting_item);
		setAdapater = new SettingAdapater(setItem, this, isOpen);
		mListView.setAdapter(setAdapater);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				if (position < 3) {
					if (isOpen[position] == false) {
						isOpen[position] = true;
					} else {
						isOpen[position] = false;
					}
					setAdapater.update(isOpen);
				} else {

				}
			}
		});
	}


}
