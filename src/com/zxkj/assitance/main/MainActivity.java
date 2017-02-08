package com.zxkj.assitance.main;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxkj.assitance.CleanActivity;
import com.zxkj.assitance.DecetionActivity;
import com.zxkj.assitance.R;
import com.zxkj.assitance.SettingActivity;
import com.zxkj.assitance.SoftManagerActivity;
import com.zxkj.assitance.SpeedActivity;
import com.zxkj.assitance.Adapat.MenuAdapter;
import com.zxkj.assitance.biz.MumMenager;
import com.zxkj.assitance.view.CricleView;

public class MainActivity extends BaseActivity {
	protected GridView mGridMenu;
	protected int[] menuIcons = { R.drawable.icon_rocket,
			R.drawable.icon_softmgr, R.drawable.icon_phonemgr,
			R.drawable.icon_telmgr, R.drawable.icon_filemgr,
			R.drawable.icon_sdclean };
	protected String menuText[];
	protected CricleView mCricleView;
	protected TextView mTxtPro;
	protected int radian;

	ImageView mImageAbout;
	ImageView mImageSeting;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageSeting = (ImageView) this.findViewById(R.id.img_mina_set);
		mImageAbout = (ImageView) this.findViewById(R.id.img_mina_about);
		menuText = this.getResources().getStringArray(R.array.text_main_menu);
		mGridMenu = (GridView) this.findViewById(R.id.grd_main);
		mTxtPro = (TextView) this.findViewById(R.id.text_main_cricle_num);
		mTxtPro.setText(""
				+ (int) (MumMenager.getRuntimeUsedMem(this) * 1.0
						/ MumMenager.getRuntimeTotalMem(this) * 100));
		radian = (int) (MumMenager.getRuntimeUsedMem(this) * 1.0
				/ MumMenager.getRuntimeTotalMem(this) * 360);
		MenuAdapter adapater = new MenuAdapter(this, menuIcons, menuText);
		mGridMenu.setAdapter(adapater);
		mCricleView = (CricleView) this.findViewById(R.id.cricle_arc_center);
		mCricleView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mCricleView.setAngleWithAnim(radian);

			}
		});
		mGridMenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long arg3) {
				switch (position) {
				case 0:
					toActivity(SpeedActivity.class);
					break;
				case 1:
					toActivity(SoftManagerActivity.class);
					break;
				case 2:
					toActivity(DecetionActivity.class);
					break;
				case 3:
					toActivity(TelMgrActivity.class);
					break;
				case 4:
					toActivity(FileMgrActivity.class);
					break;
				case 5:
					toActivity(CleanActivity.class);
					break;

				}

			}
		});
		
		mImageSeting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				toActivity(SettingActivity.class);
			}
		});
	}

}
