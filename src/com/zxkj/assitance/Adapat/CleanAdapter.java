package com.zxkj.assitance.Adapat;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxkj.assitance.R;
import com.zxkj.assitance.entity.ChildInfo;
import com.zxkj.assitance.entity.GroupInfo;
import com.zxkj.assitance.util.FileUitls;

public class CleanAdapter extends BaseExpandableListAdapter{
	protected Context mContext;
	protected List<GroupInfo> groupInfos;
	protected List<List<ChildInfo>> childInfos;
	
	public CleanAdapter(Context context, List<GroupInfo> groupInfos,
			List<List<ChildInfo>> childInfos) {
		this.mContext = context;
		this.groupInfos = groupInfos;
		this.childInfos = childInfos;
		
	}

	@Override
	public int getGroupCount() {

		return groupInfos != null ? groupInfos.size() : 0;
	}

	@Override
	public int getChildrenCount(int groupPosition) {

		return childInfos != null ? childInfos.get(groupPosition).size() : 0;
	}

	@Override
	public ChildInfo getChild(int groupPosition, int childPosition) {

		return childInfos.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View groupView, ViewGroup parent) {
		ViewGroupHolder viewGroupHolder;
		if (groupView == null) {
			viewGroupHolder = new ViewGroupHolder();
			groupView = LayoutInflater.from(mContext).inflate(
					R.layout.edl_clean_group_list, null);
			viewGroupHolder.textTitleGroup = (TextView) groupView
					.findViewById(R.id.text_clean_group_title);
			viewGroupHolder.textMumGroup = (TextView) groupView
					.findViewById(R.id.text_clean_group_mum);
			groupView.setTag(viewGroupHolder);

		} else {
			viewGroupHolder = (ViewGroupHolder) groupView.getTag();
		}
		viewGroupHolder.textTitleGroup.setText(getGroup(groupPosition)
				.getGroupTitle());
		viewGroupHolder.textMumGroup.setText(getGroup(groupPosition)
				.getGroupTotalMum());
		return groupView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView,
 ViewGroup parent) {
		ViewChildHolder viewChildHolder;
		if (convertView == null) {
			viewChildHolder = new ViewChildHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.edl_clean_child_list, null);
			viewChildHolder.checkBoxChild = (CheckBox) convertView
					.findViewById(R.id.check_clean_child);
			viewChildHolder.imageChild = (ImageView) convertView
					.findViewById(R.id.image_clean_child);
			viewChildHolder.textTitleChild = (TextView) convertView
					.findViewById(R.id.text_title_clean_child);
			viewChildHolder.textMumChild = (TextView) convertView
					.findViewById(R.id.text_mum_clean_child);
			convertView.setTag(viewChildHolder);
		} else {
			viewChildHolder = (ViewChildHolder) convertView.getTag();
		}
		viewChildHolder.imageChild.setImageDrawable(getChild(groupPosition,
				childPosition).getAppIcon());
		viewChildHolder.textTitleChild.setText(getChild(groupPosition,
				childPosition).getAppName());
		viewChildHolder.textMumChild.setText(FileUitls.formatLength(getChild(
				groupPosition, childPosition).getTotelMem()));

		boolean isCheaked = getChild(groupPosition, childPosition).isChocked();
		viewChildHolder.checkBoxChild.setChecked(isCheaked);
		return convertView;
	}


	@Override
	public GroupInfo getGroup(int groupPosition) {

		return groupInfos.get(groupPosition);
	}


	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPositon, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	class ViewGroupHolder {
		TextView textTitleGroup;
		TextView textMumGroup;

	}

	class ViewChildHolder {
		CheckBox checkBoxChild;
		ImageView imageChild;
		TextView textTitleChild;
		TextView textMumChild;
	}

	public void update(List<GroupInfo> groupInfos,
			List<List<ChildInfo>> childInfos) {
		this.groupInfos = groupInfos;
		this.childInfos = childInfos;
		notifyDataSetChanged();

	}

}
