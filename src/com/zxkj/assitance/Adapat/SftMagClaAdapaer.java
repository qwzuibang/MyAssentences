package com.zxkj.assitance.Adapat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxkj.assitance.R;

public class SftMagClaAdapaer extends BaseAdapter{
	protected Context context;
	protected String[] sftManagerCla;

	public SftMagClaAdapaer(Context context, String[] sftManagerCla) {
		this.context = context;
		this.sftManagerCla = sftManagerCla;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return sftManagerCla != null ? sftManagerCla.length : 0;
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
		if(convertView==null){
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.sft_manager_cla_list, null);
			viewHolder.textSftCla = (TextView) convertView
					.findViewById(R.id.text_sft_manager_cla);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.textSftCla.setText(sftManagerCla[position]);
		return convertView;
	}

	public class ViewHolder {
		TextView textSftCla;
		ImageView imgSftla;
	}
}
