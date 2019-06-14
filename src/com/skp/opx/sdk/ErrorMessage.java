package com.skp.opx.sdk;
/**
 * 
 * @Ŭ������ : Define
 * @�ۼ��� : �ڻ���
 * @�ۼ��� : 2012. 10. 8.
 * @���������� : 2012. 10. 8.
 * @�����̷� - ������, ������, ���� ����
 * TODO
 */


import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * @���� : �ۼ��Ž� Error�� �߻��ϸ� Error������ �˾��ϴ� Ŭ����
 * @Ŭ������ : ErrorMessage
 */
public final class ErrorMessage {
	
	public static String LASTEST_REQUEST_INFO = new String();
	
	/** 
	 * ���� ����ǥ�� ���̾�α׸� �˾��Ѵ�.
	 * */
	public static final void showErrorDialog(final Context context, final String strMessage) {
		
		Log.e("ErrorMessage", strMessage == null ? "unknown error" : strMessage);
	
		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				//���α׷��� ���̾�α׸� �����Ѵ�.
				PopupDialogUtil.dismissProgressDialog();
				
				if(Constants.DEBUG_MODE == false) {
					return;
				}
				
				//���������� �˾��Ѵ�.
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle("ERROR MESSAGE !");
				builder.setMessage(LASTEST_REQUEST_INFO + "\r\n" + "<SERVER>" + strMessage);
				builder.setPositiveButton("Confirm", null);
				builder.create().show();
			}
		});
	}
}
