package com.skp.opx.rpn.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.skp.opx.rpn.R;
import com.skp.opx.rpn.receiver.AlarmReceiver;

/**
 * @���� : ��� �˸��̿� �˶� �Ŵ���
 * @Ŭ������ : RpnAlarmManager 
 *
 */
public class RpnAlarmManager {

	private static RpnAlarmManager mTheInstance;
	private AlarmManager am;
	private PendingIntent pendingIntent;
	private long alarmInterval; //�˸� ����
	
	public static synchronized RpnAlarmManager getInstance() {
		if (mTheInstance == null) {
			mTheInstance = new RpnAlarmManager();
		}
		return mTheInstance;
	}

	/** 
	 *  �˸� ���� Method
	 * */
	public synchronized void startAlarm(Context context) {
		
		am = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

		stopAlarm();
		readAlarmCycle(context);
		Intent amIntent= null;
		PendingIntent sender = null;
		
		amIntent = new Intent(context, AlarmReceiver.class);
		pendingIntent = sender = PendingIntent.getBroadcast(context, 0, amIntent,0);
		am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), alarmInterval, sender);

	}	
	
	/** 
	 * �˸� ���� Method  
	 * */
	public synchronized void stopAlarm() {
		if (pendingIntent != null) {
			am.cancel(pendingIntent);
			
		}
	}
	/**
	 * ���� ���� �Ǵ� �˸��� �ִ��� Ȯ�� Method
	 */
	public synchronized boolean isPendingIntentExist(){
		
		if(pendingIntent != null){
			return true;
		}else{
			return false;
		}
	}
	
	/** 
	 *  �˸� Cycle ��ȯ Method
	 * */
	private void readAlarmCycle(Context context){
	
		String cycle = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.message_send_tick), "120000");

		alarmInterval = Long.parseLong(cycle);
	}	
	
	/** 
	 * �ڿ��ݳ� Method  
	 * */
	public synchronized void release(){
		if(mTheInstance != null){
			mTheInstance = null;
		}
	}
}
