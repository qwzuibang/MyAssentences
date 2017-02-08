package com.zxkj.assitance.main;

import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.zxkj.assitance.R;
import com.zxkj.assitance.Adapat.LeadAdapater;


public class LeadActivity extends BaseActivity {
	public static final String SHARED_PERS_FIRST = "first";
	protected SharedPreferences msharedPre;
	protected Editor editor;
	protected ViewPager mVPager;
	private final int[] iconIds = { R.drawable.adware_style_applist,
			R.drawable.adware_style_banner, R.drawable.adware_style_creditswall };
	private List<ImageView> icons;
	protected Button mButtonLeadSkip;
	protected ImageView[] imagePoint = new ImageView[3];

	public ImageView[] bindPoint() {
		imagePoint[0] = (ImageView) this
.findViewById(R.id.image_point1);
		imagePoint[1] = (ImageView) this
.findViewById(R.id.image_point2);
		imagePoint[2] = (ImageView) this
.findViewById(R.id.image_point3);
		return imagePoint;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 创建参数文件
		msharedPre = this.getSharedPreferences(SHARED_PERS_FIRST,
				CONTEXT_INCLUDE_CODE);
		// 获取值
		boolean isFirst = msharedPre.getBoolean("isFirst", true);
		// 判断
		if (!isFirst) {
			toActivity(LogoActivity.class);
		} else {
			// 添加数据
			editor = msharedPre.edit();
			// 修改为false
			editor.putBoolean("isFirst", false);
			// 提交
			editor.commit();
			setContentView(R.layout.activity_lead);
		mVPager = (ViewPager) this.findViewById(R.id.viewPager);
		mButtonLeadSkip = (Button) this.findViewById(R.id.button_lead_skip);
		imagePoint = bindPoint();
		// 获取数据
		icons = getData(iconIds);
		// 将适配器对象设置到mVPager
		LeadAdapater adapater = new LeadAdapater(icons);
		mVPager.setAdapter(adapater);
		// 设置页面改变监听事件
		mVPager.setOnPageChangeListener(new OnPageChangeListener() {
			// 页面被选中
			@Override
			public void onPageSelected(int position) {
				// 最后一页 VISIBLE:可见 INVISIBLE:不可见(保留空间) GONE:不可见(不保留空间)
				mButtonLeadSkip.setVisibility(position == 2 ? View.VISIBLE
						: View.GONE);
				// 控制选中的点
				for (int i = 0; i < imagePoint.length; i++) {
					imagePoint[i]
							.setImageResource(position == i ? R.drawable.adware_style_selected
									: R.drawable.adware_style_default);

				}

			}

			@Override
			public void onPageScrolled(int position, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int position) {
				// TODO Auto-generated method stub

			}
		});
		mButtonLeadSkip.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				toActivity(LogoActivity.class);
			}
		});
		}
	}

	public List<ImageView> getData(int[] iconIds) {
		icons = new ArrayList<ImageView>();
		for (int i = 0; i < iconIds.length; i++) {
			ImageView icon = new ImageView(LeadActivity.this);
			icon.setImageResource(iconIds[i]);
			icons.add(icon);
		}
		return icons;
	}
}
