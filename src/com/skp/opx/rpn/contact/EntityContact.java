
package com.skp.opx.rpn.contact;

/**
 * @���� : �ּҷ� ��ƼƼ
 * @Ŭ������ : EntityContact 
 *
 */
public class EntityContact {
	
	public String mName;
	public String mPhoneNumber;
	
	public EntityContact(String strName, String strPhoneNumber) {
		
		mName = strName;
		mPhoneNumber = strPhoneNumber;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmPhoneNumber() {
		return mPhoneNumber;
	}

	public void setmPhoneNumber(String mPhoneNumber) {
		this.mPhoneNumber = mPhoneNumber;
	}
	
	
}
