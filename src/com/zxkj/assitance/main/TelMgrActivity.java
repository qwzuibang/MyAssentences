package com.zxkj.assitance.main;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxkj.assitance.R;
import com.zxkj.assitance.Adapat.TelMainMenuAdapat;
import com.zxkj.assitance.db.DBWrapper;
import com.zxkj.assitance.entity.LogWrapper;


public class TelMgrActivity extends BaseActivity {

	// String[] telMenus = { "订餐电话", "公共服务", "运营商", "快递服务", "机票酒店", "银行证券",
	// "保险服务", "品牌服务" }

	List<String> telMenus;
	protected TextView mtixtView;
	protected GridView mGridView;
	protected ImageView mImageSeting;
	protected ImageView mImageAbout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tel_mgr);

		// 拷贝
		DBWrapper.FileHelper fileHelp = new DBWrapper.FileHelper();
		fileHelp.CopyFromAssets(this);

		// 查询
		DBWrapper.DBHelp dbHelp = new DBWrapper.DBHelp();
		telMenus = dbHelp.readClassList();

		initView();
		initTitle(R.drawable.btn_homeasup_default, "通讯大全", View.INVISIBLE);
		initEvent();
	}

	// 初始化视图
	public void initView() {
		mImageAbout = (ImageView) this.findViewById(R.id.img_mina_about);
		mImageSeting = (ImageView) this.findViewById(R.id.img_mina_set);
		mtixtView = (TextView) this.findViewById(R.id.txt_mina_title);
		mGridView = (GridView) this.findViewById(R.id.grd_tel_main_menu);
		TelMainMenuAdapat adapat = new TelMainMenuAdapat(this, telMenus);
		mGridView.setAdapter(adapat);

	}

	// 初始化标题
	public void initTitle(int resId, String title, int visible) {
		mImageAbout.setImageResource(resId);
		mtixtView.setText(title);
		mImageSeting.setImageResource(visible);
	}

	public void initEvent() {
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {

				Bundle bundle = new Bundle();
				bundle.putString("title", telMenus.get(position));
				bundle.putInt("position", position);
				toActivity(TelInfoActivity.class, bundle);
				LogWrapper.e("个数", "" + position);

			}
		});
	}
}
