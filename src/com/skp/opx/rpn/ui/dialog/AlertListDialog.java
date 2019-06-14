package com.skp.opx.rpn.ui.dialog;

import com.skp.opx.rpn.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @���� : ����Ʈ ���� ���� Dialog
 * @Ŭ������ : AlertListDialog 
 *
 */
public class AlertListDialog {
	private AlertDialog dialog;
    private AlertDialog.Builder builder;
	private ListView list;
	
	public AlertListDialog(Context context, String title, String [] itemsArray){
		
		builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setNegativeButton(android.R.string.no, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		list = new ListView(context);
		list.setBackgroundColor(Color.WHITE);
        list.setScrollingCacheEnabled(false);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.dialog_list_item, itemsArray);		
		list.setAdapter(adapter);			
		dialog = builder.create();
		dialog.setView(list,0,0,0,0);
	}

	public AlertListDialog(Context context, String title, String [] itemsArray, boolean notBtn){
		
		builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		
		list = new ListView(context);
		list.setBackgroundColor(Color.WHITE);
        list.setScrollingCacheEnabled(false);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.dialog_list_item, itemsArray);		
		list.setAdapter(adapter);			
		dialog = builder.create();
		dialog.setView(list,0,0,0,0);
	}	

	public void setOnItemSelectedListener (OnItemClickListener listener){		
		list.setOnItemClickListener(listener);		
	}	

	public void show(){
		dialog.show();
	}
	
	public void dismiss(){
		dialog.dismiss();
	}
}
