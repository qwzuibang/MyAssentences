package com.zxkj.assitance.Adapat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxkj.assitance.R;

public class MenuAdapter extends BaseAdapter {
	protected Context context;
	protected String menuText[];
	protected int menuIcons[];

	public MenuAdapter(Context context, int menuIcons[], String menuText[]) {
		this.context = context;
		this.menuIcons = menuIcons;
		this.menuText = menuText;
	}
	@Override
	public int getCount() {
		return Math.min(menuIcons.length, menuText.length);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder viewHolder;
		if (null == convertView) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.item_grd_mina_menu, null);

			viewHolder.mImgIcons = (ImageView) convertView
					.findViewById(R.id.img_main_menu_icon);
			viewHolder.mTextIcons = (TextView) convertView
					.findViewById(R.id.txt_main_menu_icon);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.mImgIcons.setImageResource(menuIcons[position]);
		viewHolder.mTextIcons.setText(menuText[position]);
		return convertView;
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

	class ViewHolder {
		ImageView mImgIcons;
		TextView mTextIcons;
	}


}
