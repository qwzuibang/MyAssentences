package com.zxkj.assitance.Adapat;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxkj.assitance.R;
import com.zxkj.assitance.entity.PhoneDecetionInfo;

public class DecetionAdapater extends BaseAdapter {
	Context context;
	List<PhoneDecetionInfo> mPhoneDecetionInfo;

	public DecetionAdapater(Context context,
			List<PhoneDecetionInfo> mPhoneDecetionInfo) {
		super();
		this.context = context;
		this.mPhoneDecetionInfo = mPhoneDecetionInfo;
	}

	@Override
	public int getCount() {

		return mPhoneDecetionInfo != null ? mPhoneDecetionInfo.size() : 0;
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
	public View getView(int position, View conterView, ViewGroup arg2) {
		ViewHanlder viewHandler;
		if (conterView == null) {
			viewHandler = new ViewHanlder();
			conterView = LayoutInflater.from(context).inflate(
					R.layout.decetion_item, null);
			viewHandler.mImgIcon = (ImageView) conterView
					.findViewById(R.id.img_decetion_item);
			viewHandler.mTxtMum = (TextView) conterView
					.findViewById(R.id.text_decetion_mum_item);
			viewHandler.mTxtName = (TextView) conterView
					.findViewById(R.id.text_decetion_name_item);
			conterView.setTag(viewHandler);
		} else {
			viewHandler = (ViewHanlder) conterView.getTag();
		}
		viewHandler.mImgIcon
				.setImageResource(mPhoneDecetionInfo.get(position).phoneDecIcon);
		viewHandler.mTxtMum
				.setText(mPhoneDecetionInfo.get(position).phoneDecMum);
		viewHandler.mTxtName
				.setText(mPhoneDecetionInfo.get(position).phoneDecName);
		switch (position) {
		case 0:
			viewHandler.mImgIcon.setBackgroundColor(Color.BLUE);
			break;
		case 1:
			viewHandler.mImgIcon.setBackgroundColor(Color.RED);
			break;
		case 2:
			viewHandler.mImgIcon.setBackgroundColor(Color.BLUE);
			break;
		case 3:
			viewHandler.mImgIcon.setBackgroundColor(context.getResources()
					.getColor(R.color.main_cricle));
			break;
		case 4:
			viewHandler.mImgIcon.setBackgroundColor(Color.RED);
			break;
		}
		return conterView;
	}

	class ViewHanlder {
		ImageView mImgIcon;
		TextView mTxtName;
		TextView mTxtMum;
	}
}
