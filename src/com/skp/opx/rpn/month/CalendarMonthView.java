package com.skp.opx.rpn.month;



import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * �׸���並 ����Ͽ� ���ο� �並 ������ Ŭ����
 * ������ ���ڸ� ǥ���ϴ� ��ɵ��� ȿ�������� ó���ϱ� ���� ������ Ŭ������
 * 
 * @author Mike
 */
public class CalendarMonthView extends GridView {

	/**
	 * ���� ������ ���� ���� ������ ������ ��ü 
	 */
	private OnDataSelectionListener selectionListener;
	
	/**
	 * ����� ��ü
	 */
	CalendarMonthAdapter adapter;
	
	/**
	 * ������
	 * 
	 * @param context
	 */
	public CalendarMonthView(Context context) {
		super(context);

		init();
	}

	/**
	 * ������
	 * 
	 * @param context
	 * @param attrs
	 */
	public CalendarMonthView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}
	
	/**
	 * �Ӽ� �ʱ�ȭ
	 */
	private void init() {
		setBackgroundColor(Color.GRAY);
        setVerticalSpacing(1);
        setHorizontalSpacing(1);
        setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        
        // Į���� ���� ����
        setNumColumns(7);
        
        // �׸������ ���� �̺�Ʈ ó�� ������ ����
        setOnItemClickListener(new OnItemClickAdapter());
	}

	/**
	 * ����� ����
	 * 
	 * @param adapter
	 */
	public void setAdapter(BaseAdapter adapter) {
		super.setAdapter(adapter);
		
		this.adapter = (CalendarMonthAdapter) adapter;
	}

	/**
	 * ����� ��ü ����
	 * 
	 * @return
	 */
	public BaseAdapter getAdapter() {
		return (BaseAdapter)super.getAdapter();
	}
	
	/**
	 * ������ ����
	 * 
	 * @param listener
	 */
	public void setOnDataSelectionListener(OnDataSelectionListener listener) {
		this.selectionListener = listener;
	}

	/**
	 * ������ ��ü ����
	 * 
	 * @return
	 */
	public OnDataSelectionListener getOnDataSelectionListener() {
		return selectionListener;
	}
	
	class OnItemClickAdapter implements OnItemClickListener {
		
		public OnItemClickAdapter() {
			
		}

		public void onItemClick(AdapterView parent, View v, int position, long id) {
			 
			if (adapter != null) {
				adapter.setSelectedPosition(position);
				adapter.notifyDataSetInvalidated();
			}

			if (selectionListener != null) {
				selectionListener.onDataSelected(parent, v, position, id);
			}
			
			
		}
		
	}
	
}