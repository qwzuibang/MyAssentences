package com.zxkj.assitance.Adapat;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxkj.assitance.R;
import com.zxkj.assitance.entity.FileDetailInfo;
import com.zxkj.assitance.util.DataUtil;
import com.zxkj.assitance.util.FileUitls;
import com.zxkj.assitance.util.IconMap;

public class FileDetailAdapater extends BaseAdapter {
	protected Context context;
	protected List<FileDetailInfo> mFileDetailInfo;

	public FileDetailAdapater(Context context,
			List<FileDetailInfo> mFileDetailInfo) {
		this.context = context;
		this.mFileDetailInfo = mFileDetailInfo;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return context != null ? mFileDetailInfo.size() : 0;
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
			LayoutInflater flater = LayoutInflater.from(context);
			contextView = flater.inflate(R.layout.item_file_list_detail, null);
			viewHolder.textFileName = (TextView) contextView
					.findViewById(R.id.text_file_name_detail);
			viewHolder.textFileData = (TextView) contextView
					.findViewById(R.id.text_file_data_detail);
			viewHolder.textFileSize = (TextView) contextView
					.findViewById(R.id.text_file_size_detail);
			viewHolder.checkBox = (CheckBox) contextView
					.findViewById(R.id.check_file_detail);
			viewHolder.imageView = (ImageView) contextView
					.findViewById(R.id.img_file_detail_mum);
			contextView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) contextView.getTag();
		}
			FileDetailInfo fileDetailInfo = mFileDetailInfo.get(position);

			viewHolder.textFileName.setText(fileDetailInfo.getFeilName());
			long creatLastTime = fileDetailInfo.getFile().lastModified();
			viewHolder.textFileData.setText(DataUtil
					.millisToData(creatLastTime));

			viewHolder.textFileSize.setText(FileUitls
					.formatLength(fileDetailInfo.getFile().length()));

		viewHolder.checkBox.setChecked(mFileDetailInfo.get(position).isCheak());
		String suffix = fileDetailInfo.getSuffix();
		Map<String, Integer> icons = IconMap.getIcon();
		if (suffix != null && icons.containsKey(suffix)) {

			viewHolder.imageView.setImageResource(icons.get(suffix));

		} else {
			viewHolder.imageView.setImageResource(R.drawable.ic_launchabc);
		}

		return contextView;
	}

	class ViewHolder {
		TextView textFileName;
		TextView textFileData;
		TextView textFileSize;
		CheckBox checkBox;
		ImageView imageView;
	}

	// 更新数据
	public void update(List<FileDetailInfo> newFileDetailInfo) {
		this.mFileDetailInfo = newFileDetailInfo;
		// 更新适配器
		notifyDataSetChanged();
	}
}
