package com.skp.opx.rpn.entity;

import com.skp.opx.sdk.EntityAbstract;


/**
 * @���� : ����Ʈ�� ����ó UI Entity
 * @Ŭ������ : EntityNateContactForUI 
 *
 */
public class EntityNateContactForUI extends EntityAbstract {

	public String name;		  //�̸�	
	public String nateId;    //����ó
	public boolean	checkedState;// üũ �ڽ� üũ ����
	
	public EntityNateContactForUI() {}
	public boolean getChecked() {
		return checkedState;
	}
	
	public void setChecked(boolean checked){
		checkedState = checked;
	}
	
}
