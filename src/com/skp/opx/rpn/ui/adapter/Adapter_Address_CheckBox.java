
package com.skp.opx.rpn.ui.adapter;

import java.util.ArrayList;

import com.skp.opx.rpn.R;
import com.skp.opx.rpn.database.EntityDesignatedContactBox;
import com.skp.opx.rpn.entity.EntityContact;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * @���� : üũ �ڽ� �ִ� �ּҷ� Adapter
 * @Ŭ������ : Adapter_Address_CheckBox 
 *
 */
public class Adapter_Address_CheckBox extends BaseAdapter {

	private Context	mContext;
	private ArrayList<EntityContact> mContactsList = new ArrayList<EntityContact>();
	private LayoutInflater mLiInflater;

	public Adapter_Address_CheckBox(Context context) {

		mContext = context;
		mLiInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setAddressList(ArrayList<EntityContact> list) {

		mContactsList = list;
	}
	
	public int getCount() {
		return mContactsList.size();
	}

	public EntityContact getItem(int position) {
		return mContactsList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;

		if (convertView == null) {
			convertView = mLiInflater.inflate(R.layout.list_item_contact_checkbox, null);
			viewHolder = new ViewHolder();
			viewHolder.subTitle = (TextView)convertView.findViewById(R.id.sub_title);
			viewHolder.name = (TextView)convertView.findViewById(R.id.name);
			viewHolder.contact = (TextView)convertView.findViewById(R.id.contact);
			viewHolder.checkBox = (CheckBox)convertView.findViewById(R.id.check_box);
			
			convertView.setTag(viewHolder);
		}
		else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		EntityContact contactInfo = (EntityContact)mContactsList.get(position);
		if(contactInfo.getTitleType() == 1){
			viewHolder.subTitle.setVisibility(View.VISIBLE);
			viewHolder.subTitle.setText(R.string.my_address);
		}else if(contactInfo.getTitleType() == 2){
			viewHolder.subTitle.setVisibility(View.VISIBLE);
			viewHolder.subTitle.setText(R.string.nate_on);
		}else{
			viewHolder.subTitle.setVisibility(View.GONE);
		}
		viewHolder.name.setText(contactInfo.getName());
		viewHolder.contact.setText(contactInfo.getContact());

		viewHolder.checkBox.setFocusable(false);
		viewHolder.checkBox.setClickable(false);
		viewHolder.checkBox.setChecked(contactInfo.getChecked());		

		return convertView;
	}	

	public static class ViewHolder {

		public TextView 	subTitle;
		public TextView		name;
		public TextView		contact;
		public CheckBox	    checkBox;

	}



}
