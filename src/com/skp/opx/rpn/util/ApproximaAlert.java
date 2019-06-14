package com.skp.opx.rpn.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.Uri;

import com.skp.opx.rpn.receiver.ProximityReceiver;


/**
 * @���� : ������ �ֺ� ���ٽ� �˸�
 * @Ŭ������ : ApproximaAlert 
 *
 */
public class ApproximaAlert {

	private static ApproximaAlert mTheInstance;
	private LocationManager locMgr = null;
	PendingIntent pIntent = null;

	public static synchronized ApproximaAlert getInstance() {
		if (mTheInstance == null) {
			mTheInstance = new ApproximaAlert();
		}
		return mTheInstance;
	}


	/** 
	 *  ������ ��ǥ ��� Method
	 * */
	public synchronized void registerLocation(Context context, double lon,double lat) {
		float radius = 700;
		Intent intent = new Intent(context, ProximityReceiver.class);
		pIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

		locMgr = (LocationManager) context
				.getSystemService(context.LOCATION_SERVICE);
		locMgr.addProximityAlert(lat, lon, radius, -1, pIntent);

	}

	/**
	 * ������ �˶� ���� Method
	 */
	public synchronized void removeLocation() {
		if (pIntent != null) {
			locMgr.removeProximityAlert(pIntent);
		}
	}
	
	public synchronized void release(){
		if(mTheInstance != null)
			mTheInstance = null;
		
	}
	
}