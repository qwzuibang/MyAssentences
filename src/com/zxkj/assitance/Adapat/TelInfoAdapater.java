package com.zxkj.assitance.Adapat;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zxkj.assitance.R;
import com.zxkj.assitance.entity.TelInfo;

public class TelInfoAdapater extends BaseAdapter {
	Context context;
	List<TelInfo> telInfos;

	public TelInfoAdapater(Context context, List<TelInfo> telInfos) {
		super();
		this.context = context;
		this.telInfos = telInfos;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return telInfos != null ? telInfos.size() : 0;
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
	public View getView(int position, View convertview, ViewGroup arg2) {
		ViewHander viewHander;
		if (convertview == null) {
			viewHander = new ViewHander();
			convertview = LayoutInflater.from(context).inflate(
					R.layout.tel_info_item, null);
			viewHander.txtName = (TextView) convertview
					.findViewById(R.id.text_tel_info_name);
			viewHander.txtNum = (TextView) convertview
					.findViewById(R.id.text_tel_info_num);
			convertview.setTag(viewHander);
		} else {
			viewHander = (ViewHander) convertview.getTag();
		}

		viewHander.txtName.setText(telInfos.get(position).telName);
		viewHander.txtNum.setText(telInfos.get(position).telNum);
		return convertview;
	}

	class ViewHander {
		TextView txtName;
		TextView txtNum;
	}
}
