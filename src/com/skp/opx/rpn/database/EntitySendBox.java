package com.skp.opx.rpn.database;

/**
 * @���� : �߽��� Entity
 * @Ŭ������ : EntitySendBox 
 *
 */
public class EntitySendBox {

	private long 	ID; //��� �̸��� ���� �ؾ���
	
	public String 	mMdn;
	public String   mReceiver; //�޴� ���
	public String	mStartLocation;
	public String 	mDestnationLocation;
	public long 	mDeliveryTime;//���۽ð�
	public String 	mExpectionArrivedTime; //���� �ð�
	public String 	mMessage;
	public int		mMessageType;

	public EntitySendBox() {}

	public long getID() {
		
		return ID;
	}
}
