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
import com.zxkj.assitance.entity.ProgressInfo;
import com.zxkj.assitance.util.FileUitls;

public class SpeedAdapater extends BaseAdapter {
	protected Context context;
	protected List<ProgressInfo> mListProInfo;
	boolean isUser;

	public SpeedAdapater(Context context, List<ProgressInfo> listProInfo,
			boolean isUser) {
		this.context = context;
		this.mListProInfo = listProInfo;
		this.isUser = isUser;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListProInfo != null ? mListProInfo.size() : 0;
	}

	ViewHandler viewHandler;
	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {

		if (convertView == null) {
			viewHandler = new ViewHandler();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.spped_list_view, null);
			viewHandler.mCheckBox = (CheckBox) convertView
					.findViewById(R.id.chock_speed_item);
			viewHandler.mImageView = (ImageView) convertView
					.findViewById(R.id.img_speed_iem);
			viewHandler.mTxtProName = (TextView) convertView
					.findViewById(R.id.text_speed_name);
			viewHandler.mTxtProMum = (TextView) convertView
					.findViewById(R.id.text_speed_mum);
			convertView.setTag(viewHandler);
			viewHandler.mTxtProgess = (TextView) convertView
					.findViewById(R.id.text_speed_item_progess);
		} else {
			viewHandler = (ViewHandler) convertView.getTag();
		}
		viewHandler.mImageView
				.setImageDrawable(mListProInfo.get(position).proIcon);
		viewHandler.mTxtProName.setText(mListProInfo.get(position).proName);
		viewHandler.mTxtProMum.setText(FileUitls.formatLength(mListProInfo
				.get(position).proMum));
		viewHandler.mCheckBox.setChecked(mListProInfo.get(position).isChecked);
		if (isUser) {
			viewHandler.mTxtProgess.setVisibility(View.GONE);
		} else {
			viewHandler.mTxtProgess.setVisibility(View.VISIBLE);
		}
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

	class ViewHandler {
		CheckBox mCheckBox;
		ImageView mImageView;
		TextView mTxtProName;
		TextView mTxtProMum;
		TextView mTxtProgess;
	}

	public void update(List<ProgressInfo> listProInfo, boolean isUser) {
		this.mListProInfo = listProInfo;
		this.isUser = isUser;
		notifyDataSetChanged();
	}
}
