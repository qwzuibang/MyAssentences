package com.zxkj.assitance.Adapat;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zxkj.assitance.R;
import com.zxkj.assitance.entity.FileInfo;
import com.zxkj.assitance.util.FileUitls;

public class FileMgrAdapater extends BaseAdapter {
	protected List<FileInfo> fileInfos;
	protected Context context;

	public FileMgrAdapater(List<FileInfo> fileInfos, Context context) {
		this.fileInfos = fileInfos;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fileInfos != null ? fileInfos.size() : 0;
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
     LayoutInflater inflater=LayoutInflater.from(context);
     contextView=inflater.inflate(R.layout.item_list_file_mgr, null);
     
			viewHolder.textFileType = (TextView) contextView
					.findViewById(R.id.txt_item_file_total_mum);
			viewHolder.textFileSize = (TextView) contextView
				.findViewById(R.id.txt1_item_file_total_mum);
			viewHolder.imageFileReturn = (ImageView) contextView
				.findViewById(R.id.img_item_file_total_mum);
			viewHolder.pgbFile = (ProgressBar) contextView
				.findViewById(R.id.pgb_file_load);
			contextView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) contextView.getTag();
		}

		FileInfo fileInfo = fileInfos.get(position);
		viewHolder.textFileType.setText(fileInfo.getFileType());
		long totalMum = (long) fileInfo.getFileSize();
		viewHolder.textFileSize.setText(FileUitls.formatLength(totalMum));
		return contextView;
	}

	class ViewHolder {
		TextView textFileType;
		TextView textFileSize;
		ImageView imageFileReturn;
		ProgressBar pgbFile;

	}

	public void update(List<FileInfo> newFileInfos) {
		this.fileInfos = newFileInfos;
		notifyDataSetChanged();
	}
}
