package com.zxkj.assitance.Adapat;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zxkj.assitance.R;

public class TelMainMenuAdapat extends BaseAdapter {
	protected Context context;
	protected List<String> telMenu;

	public TelMainMenuAdapat(Context context, List<String> telMenu) {
		this.context = context;
		this.telMenu = telMenu;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return telMenu != null ? telMenu.size() : 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View contextView, ViewGroup arg2) {
		ViewHolder viewHolder;
		if (contextView == null) {
			viewHolder = new ViewHolder();
		LayoutInflater inflater = LayoutInflater.from(context);
		contextView = inflater.inflate(R.layout.item_tel_grd_main_menu, null);
		switch (position % 3) {
		case 0:
				contextView
						.setBackgroundResource(R.drawable.tel_main_menu_cricle_shap1);
			break;
		case 1:
				contextView
						.setBackgroundResource(R.drawable.tel_main_menu_cricle_shap2);
			break;
		case 2:
				contextView
						.setBackgroundResource(R.drawable.tel_main_menu_cricle_shap3);
			break;
		}
		// contextView.setLayoutParams(new
		// LayoutParams(LayoutParams.MATCH_PARENT,
		// 120));
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120);
		contextView.setLayoutParams(params);
			viewHolder.mTextIcons = (TextView) contextView
				.findViewById(R.id.text_item_tel_main_menu);
			contextView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) contextView.getTag();
		}
		viewHolder.mTextIcons.setText(telMenu.get(position));
		return contextView;
	}

	class ViewHolder {
		TextView mTextIcons;
	}
}
