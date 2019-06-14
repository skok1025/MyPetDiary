package com.skp.opx.sdk;

import java.io.IOException;
import java.net.MalformedURLException;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.skp.openplatform.android.sdk.common.RequestListener;
import com.skp.openplatform.android.sdk.common.ResponseMessage;
import com.skp.openplatform.android.sdk.oauth.SKPOPException;

/**
 * @���� : SDK���� �����Ǵ� �񵿱� �ۼ��ſ��� RequestListener�� ��ӹ޾� ���� �� �Ľ��ڵ鷯�� �� ���� �� ����ó���ϴ� Ŭ����
 * @Ŭ������ : AsyncReceiver
 */
public class AsyncReceiver implements RequestListener {

	private static AsyncReceiver mAsyncReceiver = null; //singleton ���� ����� ���� �ڽ��� ��ü
	private static Context mContext;							  //������Ʈ �ڿ� ����� ���� context
	private static boolean mIsErrorShow = true;			  //������ �������� �˾��� ���� �÷���
	private Handler mHandler;										  //������ �Ľ� Ŭ���� �ڵ鷯. �ܺη� ���� �Ѱܹ޴� ��

	private AsyncReceiver() {}

	/** 
	 * �޸��� ���� �����ϱ� ���Ͽ� Singleton ���������� ���� ��ü�� �����Ѵ�.
	 * */
	public static AsyncReceiver getInstance(Context context) {

		mIsErrorShow = true;
		return getInstance(context, true);
	}

	/** 
	 * �޸��� ���� �����ϱ� ���Ͽ� Singleton ���������� ���� ��ü�� �����Ѵ�.
	 * */
	public static AsyncReceiver getInstance(Context context, boolean isErrorShow) {

		mIsErrorShow = isErrorShow;
		mContext = context;

		if(mAsyncReceiver == null) {
			mAsyncReceiver = new AsyncReceiver();
		}

		return mAsyncReceiver;
	}

	/** 
	 * ���� �Ϸ�� ResponseMessage ��ü�� �޾� �Ľ� �ڵ鷯 Ŭ������ ���޵ȴ�.
	 * */
	@Override
	public void onComplete(ResponseMessage result) {

		
		//200 : �����Ϸ�, 201 : �����߼� ��û ����, 204 : ������ ������ ���� ��� --> ������ status code�� ����ó���Ѵ�.
		if(result.getStatusCode().equalsIgnoreCase("200") == false && result.getStatusCode().equalsIgnoreCase("201") == false && result.getStatusCode().equalsIgnoreCase("204") == false ) {
			//�����˾� �÷��� ������ ���� ������ �˾��Ѵ�.
			if(mIsErrorShow) {
				ErrorMessage.showErrorDialog(mContext, result.getResultMessage());
			}
		//���������� �����Ͱ� ���ŵǾ��� ���
		} else {
			if(mHandler != null) {
				//�ļ� �ڵ鷯 Ŭ������ ���ŵ� �����͸� �����Ѵ�.
				Message msg = mHandler.obtainMessage();
				msg.obj = result.getResultMessage();
				mHandler.sendMessage(msg);
			}
		}
	}

	/** 
	 * IOException �߻����� ����ó��
	 * */
	@Override
	public void onIOException(IOException e) {

		if(mIsErrorShow)
			ErrorMessage.showErrorDialog(mContext, e.getMessage());
	}

	/** 
	 * onMalformedURLException �߻����� ����ó��
	 * */
	@Override
	public void onMalformedURLException(MalformedURLException e) {

		if(mIsErrorShow)
			ErrorMessage.showErrorDialog(mContext, e.getMessage());
	}

	/** 
	 * onSKPOPException �߻����� ����ó��
	 * */
	@Override
	public void onSKPOPException(SKPOPException e) {

		if(mIsErrorShow)
			ErrorMessage.showErrorDialog(mContext, e.getMessage());
	}

	/** 
	 * �Ľ� �ڵ鷯�� �ܺη� ���� ���޹޴´�.
	 * */
	public void setHandler(Handler handler) {

		mHandler = handler;
	}
}
