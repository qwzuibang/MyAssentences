package com.zxkj.assitance;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zxkj.assitance.Adapat.SftMagClaAdapaer;
import com.zxkj.assitance.main.BaseActivity;

public class SoftManagerActivity extends BaseActivity {
	protected TextView mtixtView;
	protected ImageView mImageSeting;
	protected ImageView mImageAbout;

	protected View mSftMngCricleView;
	protected ProgressBar mInPgb;
	protected TextView mInUsedInternal;
	protected TextView mInTotalInternal;

	protected ProgressBar mOutPgb;
	protected TextView mOutUsedInternal;
	protected TextView mOutTotalInternal;

	protected ListView mListView;
	protected String[] sftManagerCla = { "所有软件", "系统软件", "用户软件" };

	// String inPath = FileUitls.getInnerSDCARDPath(this);
	// String outPath = FileUitls.getExternelSDCARDPath(this);

	// long inUsed = MumMenager.getExtSdUsed(inPath);
	// long inTotal = MumMenager.getExtSdTotal(inPath);
	// int inParcent = (int) MumMenager.getExtSdPer(inPath) * 100;
	//
	// long outTotal = MumMenager.getExtSdTotal(outPath);
	// long outUsed = MumMenager.getExtSdUsed(outPath);
	// int outParcent = (int) MumMenager.getExtSdPer(outPath) * 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soft_manager);

		initView();
		initTitle(R.drawable.btn_homeasup_default, "软件管理", View.GONE);
		SftMagClaAdapaer adapat = new SftMagClaAdapaer(this, sftManagerCla);
		mListView.setAdapter(adapat);
		initEvent();
	}

	public void initView() {
		mImageAbout = (ImageView) this.findViewById(R.id.img_mina_about);
		mImageSeting = (ImageView) this.findViewById(R.id.img_mina_set);
		mtixtView = (TextView) this.findViewById(R.id.txt_mina_title);


		mSftMngCricleView = this.findViewById(R.id.sft_view_cricle);
		mInPgb = (ProgressBar) this.findViewById(R.id.progess_bar_sft_in);
		mInUsedInternal = (TextView) this
				.findViewById(R.id.text_in_used_instal);
		mInTotalInternal = (TextView) this
				.findViewById(R.id.text_in_total_instal);

		// mInPgb.setProgress(inParcent);
		// mInUsedInternal.setText(FileUitls.formatLength(inUsed));
		// mInTotalInternal.setText(FileUitls.formatLength(inTotal));

		mOutPgb = (ProgressBar) this.findViewById(R.id.progess_bar_sft_out);
		mOutUsedInternal = (TextView) this
				.findViewById(R.id.text_out_used_instal);
		mOutTotalInternal = (TextView) this
				.findViewById(R.id.text_out_total_instal);
		mListView = (ListView) this.findViewById(R.id.list_view_sft_manager);
		// mOutPgb.setProgress(outParcent);
		// mOutUsedInternal.setText(FileUitls.formatLength(outUsed));
		// mOutTotalInternal.setText(FileUitls.formatLength(outTotal));

}

	// 初始化标题
	public void initTitle(int resId, String title, int visible) {
		mImageAbout.setImageResource(resId);
		mtixtView.setText(title);
		mImageSeting.setImageResource(visible);
	}

	public void initEvent() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				Bundle bundle = new Bundle();
				bundle.putInt("position", position);
				toActivity(SftAppActivity.class, bundle);

			}
		});
	}
}
