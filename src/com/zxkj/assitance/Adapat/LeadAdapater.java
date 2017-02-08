package com.zxkj.assitance.Adapat;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class LeadAdapater extends PagerAdapter{
	List<ImageView> icons;

	public LeadAdapater(List<ImageView> icons) {
		this.icons = icons;
	
}
	@Override
	public int getCount() {
		return null == icons ? 0 : icons.size();
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(icons.get(position));
		return icons.get(position);
	}
	@Override
	public boolean isViewFromObject(View view, Object object) {

		return view == object;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {

		container.removeView(icons.get(position));
	}
}
