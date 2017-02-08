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
		// ���������ļ�
		msharedPre = this.getSharedPreferences(SHARED_PERS_FIRST,
				CONTEXT_INCLUDE_CODE);
		// ��ȡֵ
		boolean isFirst = msharedPre.getBoolean("isFirst", true);
		// �ж�
		if (!isFirst) {
			toActivity(LogoActivity.class);
		} else {
			// �������
			editor = msharedPre.edit();
			// �޸�Ϊfalse
			editor.putBoolean("isFirst", false);
			// �ύ
			editor.commit();
			setContentView(R.layout.activity_lead);
		mVPager = (ViewPager) this.findViewById(R.id.viewPager);
		mButtonLeadSkip = (Button) this.findViewById(R.id.button_lead_skip);
		imagePoint = bindPoint();
		// ��ȡ����
		icons = getData(iconIds);
		// ���������������õ�mVPager
		LeadAdapater adapater = new LeadAdapater(icons);
		mVPager.setAdapter(adapater);
		// ����ҳ��ı�����¼�
		mVPager.setOnPageChangeListener(new OnPageChangeListener() {
			// ҳ�汻ѡ��
			@Override
			public void onPageSelected(int position) {
				// ���һҳ VISIBLE:�ɼ� INVISIBLE:���ɼ�(�����ռ�) GONE:���ɼ�(�������ռ�)
				mButtonLeadSkip.setVisibility(position == 2 ? View.VISIBLE
						: View.GONE);
				// ����ѡ�еĵ�
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
