package com.zxkj.assitance.main;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.zxkj.assitance.R;
import com.zxkj.assitance.Adapat.TelInfoAdapater;
import com.zxkj.assitance.db.DBWrapper;
import com.zxkj.assitance.entity.TelInfo;

public class TelInfoActivity extends BaseActivity {
	protected TextView mTextTitle;
	protected ListView mListView;
	protected List<TelInfo> telInfos;
	protected String title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tel_info);

		Intent intent = this.getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		title = bundle.getString("title");
		int position = bundle.getInt("position", -1);


		// 获取数据源
		DBWrapper.DBHelp dbHelper = new DBWrapper.DBHelp();
		telInfos = dbHelper.readItemList(position);
		initView();
		initEvent();
	}

	public void initView() {
		mTextTitle = (TextView) this.findViewById(R.id.text_tel_title);
		mListView = (ListView) this.findViewById(R.id.list_tel_item_info);

		mTextTitle.setText(title);

		TelInfoAdapater telInfoAdapater = new TelInfoAdapater(this, telInfos);
		mListView.setAdapter(telInfoAdapater);
	}

	public void initEvent() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				showTelDialog(position);
			}
		});
	}

	public void showTelDialog(int position) {
		final TelInfo telInfo = telInfos.get(position);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("请选择");
		builder.setItems(new String[] { "打电话", "发信息" }, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					Intent dial = new Intent(Intent.ACTION_DIAL);
					dial.setData(Uri.parse("tel:" + telInfo.telNum));
					startActivity(dial);
					break;
				case 1:
					Intent msg = new Intent(Intent.ACTION_SENDTO);
					msg.setData(Uri.parse("smsto:" + telInfo.telNum));
					startActivity(msg);
					break;
				}

			}
		});
		builder.show();
	}
}
