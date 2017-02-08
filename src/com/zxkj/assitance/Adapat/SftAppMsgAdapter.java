package com.zxkj.assitance.Adapat;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxkj.assitance.R;
import com.zxkj.assitance.biz.SftManagerInfo;

public class SftAppMsgAdapter extends BaseAdapter {

	protected Context mContext;
	protected List<SftManagerInfo> mSftManagerInfo;

	public SftAppMsgAdapter(Context context, List<SftManagerInfo> sftManagerInfo) {
		this.mContext = context;
		this.mSftManagerInfo = sftManagerInfo;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mSftManagerInfo != null ? mSftManagerInfo.size() : 0;
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
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.sft_app_item_list, null);
			viewHolder.imgItemIcon = (ImageView) convertView
					.findViewById(R.id.img_sft_item_icon);
			viewHolder.textItemName = (TextView) convertView
					.findViewById(R.id.text_sft_item_name);
			viewHolder.textItemPkg = (TextView) convertView
					.findViewById(R.id.text_sft_item_pkg);
			viewHolder.textItemVersion = (TextView) convertView
					.findViewById(R.id.text_sft_item_vesition);
			viewHolder.checkDeletItem = (CheckBox) convertView
					.findViewById(R.id.check_sft_item_msg);
			convertView.setTag(viewHolder);
		}
 else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		SftManagerInfo sftMngInfo = mSftManagerInfo.get(position);
		viewHolder.imgItemIcon.setImageDrawable(sftMngInfo.appIcon);
		viewHolder.textItemName.setText(sftMngInfo.appName);
		viewHolder.textItemPkg.setText(sftMngInfo.appPakge);
		viewHolder.textItemVersion.setText(sftMngInfo.appVersion);
		viewHolder.checkDeletItem.setChecked(sftMngInfo.isChecked());
		return convertView;
	}

	public class ViewHolder {
		CheckBox checkDeletItem;
		ImageView imgItemIcon;
		TextView textItemName;
		TextView textItemPkg;
		TextView textItemVersion;
	}

	public void update(List<SftManagerInfo> mSftManagerInfo) {
		this.mSftManagerInfo = mSftManagerInfo;

		notifyDataSetChanged();

	}
}
