package com.zxkj.assitance.Adapat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxkj.assitance.R;

public class SettingAdapater extends BaseAdapter {
	protected String[] setItem;
	protected Context context;
	boolean[] isOpen;

	public SettingAdapater(String[] setItem, Context context, boolean[] isOpen) {
		super();
		this.setItem = setItem;
		this.context = context;
		this.isOpen = isOpen;
	}



	@Override
	public int getCount() {
		
		return setItem != null ? setItem.length : 0;
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
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.seeting_list_item, null);
			viewHolder.mTextItem = (TextView) convertView
					.findViewById(R.id.text_set_item);
			viewHolder.mChockOn = (ImageView) convertView
					.findViewById(R.id.img_set_on);
			viewHolder.mChockOff = (ImageView) convertView
					.findViewById(R.id.img_set_off);
			viewHolder.mChockItem = (ImageView) convertView
					.findViewById(R.id.img_set_item);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (position <= 2) {
			viewHolder.mChockItem.setVisibility(View.GONE);
			if (isOpen[position] == true) {
				viewHolder.mChockOff.setVisibility(View.GONE);
				viewHolder.mChockOn.setVisibility(View.VISIBLE);
			} else {
				viewHolder.mChockOn.setVisibility(View.GONE);
				viewHolder.mChockOff.setVisibility(View.VISIBLE);
			}
		} else {
			viewHolder.mChockOn.setVisibility(View.GONE);
			viewHolder.mChockOff.setVisibility(View.GONE);
		}
		viewHolder.mTextItem.setText(setItem[position]);
		return convertView;
	}

	class ViewHolder {
		TextView mTextItem;
		ImageView mChockOn;
		ImageView mChockOff;
		ImageView mChockItem;

	}

	public void update(boolean[] isOpen) {
		this.isOpen = isOpen;
		notifyDataSetChanged();
	}
}
