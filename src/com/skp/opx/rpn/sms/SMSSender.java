package com.skp.opx.rpn.sms;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

/**
 * SMS�� �۽��Ѵ�.
 * @Ŭ������ : SMSSender
 * @�ۼ��� : �ڻ���
 * @�ۼ��� : 2012. 9. 27.
 * @���������� : 2012. 9. 27.
 * @�����̷� - ������, ������, ���� ����
 * TODO
 */
public final class SMSSender {

	public static final void SendSmsMessage(Context context, String strPhoneNumber, String strMessage) {
		
    	Intent sentIntent = new Intent("SENT_SMS_ACTION");
		PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, sentIntent, 0);
		
		Intent deliverIntent = new Intent("DELIVERED_SMS_ACTION");
		PendingIntent deliverPI = PendingIntent.getBroadcast(context, 0, deliverIntent, 0);
		
		SmsManager.getDefault().sendTextMessage(strPhoneNumber, null, strMessage, sentPI, deliverPI);
	}
}
