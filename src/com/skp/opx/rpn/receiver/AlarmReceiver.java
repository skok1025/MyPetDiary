package com.skp.opx.rpn.receiver;

import com.skp.opx.rpn.service.MsgSendService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @���� : �˶� �Ŵ��� Receiver
 * @Ŭ������ : AlarmReceiver 
 *
 */
public class AlarmReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//��ε� ���̽� ������ ���� ����
		intent.setAction("send");
		intent.setClass( context, MsgSendService.class );
		context.startService( intent );
	}
	
}
