package com.skp.opx.rpn.ui;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.skp.opx.rpn.R;
import com.skp.openplatform.android.sdk.common.RequestListener;
import com.skp.openplatform.android.sdk.common.ResponseMessage;
import com.skp.openplatform.android.sdk.oauth.SKPOPException;
import com.skp.opx.rpn.util.CoordinateUtil;
import com.skp.opx.rpn.util.PreferenceUtil;
import com.skp.opx.sdk.ErrorMessage;

/**
 * @���� : ������ �ٷ� ���� ���� Activity
 * @Ŭ������ : ShortcutsSettingHomeActivity 
 *
 */
public class ShortcutsSettingHomeActivity extends PreferenceActivity {

	
	private ProgressDialog mProgressDialog;
	private Location mCurruntLocation;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	getWindow().requestFeature(Window.FEATURE_NO_TITLE);  
        super.onCreate(savedInstanceState);
        
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.doing_setting));
        addPreferencesFromResource(R.xml.activity_shortcut_setting_home);
    }
    
    private Handler mHandler = new Handler()
	{
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			case 0:
				mProgressDialog.show();		
				CoordinateUtil.getInstance(getApplicationContext());
				mCurruntLocation = CoordinateUtil.requestLocationUpdate(listener);
				CoordinateUtil.getLocatinNameFromCoordinate(String.valueOf(mCurruntLocation.getLongitude()), String.valueOf(mCurruntLocation.getLatitude()), new RequestListener() {
					
					@Override
					public void onSKPOPException(SKPOPException e) {
						// TODO Auto-generated method stub
						mHandler.sendEmptyMessage(1);
						ErrorMessage.showErrorDialog(ShortcutsSettingHomeActivity.this, e.getMessage());
						
					}
					
					@Override
					public void onMalformedURLException(MalformedURLException e) {
						// TODO Auto-generated method stub
						mHandler.sendEmptyMessage(1);
						ErrorMessage.showErrorDialog(ShortcutsSettingHomeActivity.this, e.getMessage());
					}
					
					@Override
					public void onIOException(IOException e) {
						// TODO Auto-generated method stub
						mHandler.sendEmptyMessage(1);
						ErrorMessage.showErrorDialog(ShortcutsSettingHomeActivity.this, e.getMessage());
					}

					@Override
					public void onComplete(ResponseMessage result) {
						
						if(result.getStatusCode().equalsIgnoreCase("200") == false && result.getStatusCode().equalsIgnoreCase("201") == false ) {
							ErrorMessage.showErrorDialog(ShortcutsSettingHomeActivity.this, result.getResultMessage());
							if(mProgressDialog != null){
								mProgressDialog.dismiss();					
							}
							return;
						}
						try {
							JSONObject thedata = new JSONObject(result.getResultMessage());
							JSONObject object = thedata.getJSONObject("addressInfo");
							String fullAddress = object.getString("fullAddress") ;
							String info = fullAddress + ";" + String.valueOf(mCurruntLocation.getLongitude()) + ";" +String.valueOf(mCurruntLocation.getLatitude());
							PreferenceUtil.setHomeAsMyDestination(getApplicationContext(), info);
							mHandler.sendEmptyMessage(1);
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									Toast.makeText(getApplicationContext(), R.string.setting_ok, Toast.LENGTH_LONG).show();
									
								}
							});
						} catch (Exception e) {
							e.printStackTrace();
							// TODO: handle exception
							mHandler.sendEmptyMessage(1);
						}	
					}
				});
				break;
				
			case 1:		
				if(mProgressDialog != null)
					mProgressDialog.dismiss();				
				break;
			default:
				break;
			}		
			
			super.handleMessage(msg);
		}
	};
    
	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
		///������ġ ��ǥ��, �ּҸ� ����ϵ���		
    	if(preference.getKey().equals(getString(R.string.current_location))) { // ������ġ		
			 //������ǥ, �̸���û    		
    		mHandler.sendEmptyMessage(0);			

		} else if(preference.getKey().equals(getString(R.string.search))) { //�˻�			
			Intent intent = new Intent(this, SearchActivity.class); 
			intent.putExtra("isFromHomeSetting",true);
			startActivity(intent);
		} 		
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}
    
    private final LocationListener listener = new LocationListener() {

        public void onLocationChanged(Location location) {
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };
}

