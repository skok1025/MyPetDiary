package com.skp.opx.rpn.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.skp.opx.rpn.R;
import com.skp.openplatform.android.sdk.common.RequestBundle;
import com.skp.openplatform.android.sdk.oauth.Constants.HttpMethod;
import com.skp.openplatform.android.sdk.oauth.OAuthInfoManager;
import com.skp.opx.core.client.Define;
import com.skp.opx.rpn.contact.AddressBookDAO;
import com.skp.opx.rpn.database.DaoDesignatedContactBox;
import com.skp.opx.rpn.database.EntityDesignatedContactBox;
import com.skp.opx.rpn.entity.EntityContact;
import com.skp.opx.rpn.entity.EntityNateContact;
import com.skp.opx.rpn.entity.EntityNateContactForUI;
import com.skp.opx.rpn.ui.adapter.Adapter_Address_CheckBox;
import com.skp.opx.rpn.ui.adapter.Adapter_NateAddress_CheckBox;
import com.skp.opx.rpn.ui.dialog.GeneralAlertDialog;
import com.skp.opx.sdk.AsyncRequester;
import com.skp.opx.sdk.EntityParserHandler;
import com.skp.opx.sdk.OnEntityParseComplete;

/**
 * @���� : ���� ����ó ���� Activity
 * @Ŭ������ : AddressCheckBoxActivity 
 *
 */
public class AddressCheckBoxActivity extends ListActivity implements OnClickListener,  RadioGroup.OnCheckedChangeListener{

	private Adapter_Address_CheckBox mAddresssAdapter;
	private Adapter_NateAddress_CheckBox mNateAddressAdapter;
	private Button mConfirmButton;
	private ProgressDialog mProgressDialog;
	private boolean mIsNateContact; //�Ϲ� �ּҷ� ����, ����Ʈ�� ģ�� ������� 
	private TextView mSubTitle;
	//�ּҷ�
	private ArrayList<EntityContact> mAddressList = new ArrayList<EntityContact>();
    //����Ʈ ģ�� ���
	private ArrayList<EntityNateContactForUI> mNateContactList = new ArrayList<EntityNateContactForUI>();
	
	private static final int GET_CONTACT = 0; //�ּҷ�
    private static final int GET_NATE_FRIENDS = 1;//����Ʈ�� ģ�� ���
    private static final int DIALOG_DISMISS = 2; //�ּҷ�
	private ArrayList<Integer> mCheckedDesignateNumberList = new ArrayList<Integer>(); // üũ ���� ID ����Ʈ
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.loading));
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);          
        setContentView(R.layout.activity_address_checkbox);
        mSubTitle = (TextView)findViewById(R.id.sub_title);
        mConfirmButton = (Button)findViewById(R.id.confirm_button);
        mConfirmButton.setOnClickListener(this);
        RadioGroup tabRadioGroup = (RadioGroup)findViewById(R.id.address_rg);
		tabRadioGroup.setOnCheckedChangeListener(this);		
		//���� �ּҷϿ� �ִ� ����Ʈ
		mHandler.sendEmptyMessage(GET_CONTACT);
        
    }
    
    private Handler mHandler = new Handler()
	{
		public void handleMessage(Message msg) {

			switch(msg.what) {
			 
				case GET_CONTACT: 
					mProgressDialog.show();
					getContactList(getApplicationContext());
				break;			

				case GET_NATE_FRIENDS:
					getNateFriends();
					
				break;
				
				case DIALOG_DISMISS:
					if(mProgressDialog != null)
						mProgressDialog.dismiss();
				break;
				
			}
			super.handleMessage(msg);
		}
	};	
	
	/** 
	 *  ������ ��� �Ǿ� �ִ� �̸��� üũ ǥ�� Method 
	 * */
	private boolean isAlradyExistInContact(String mdn){		
        DaoDesignatedContactBox dao = DaoDesignatedContactBox.getInstance();
		ArrayList<EntityDesignatedContactBox>  list = dao.getDesignatedContactInfoList(this);
		
		for (int i = 0; i < list.size(); i++) {
			
			if(list.get(i).mContact.equals(mdn)){
				return true;
			}
		}
		
		return false;
	}
	
	/** 
	 *  ��� �����ϴ� ����Ʈ �������� üũ ǥ�� Method
	 * */
	private boolean isAlradyExistInNate(String account){
		// ������ ��� �Ǿ� �ִ� �̸��� üũ ǥ�� 
		 DaoDesignatedContactBox dao = DaoDesignatedContactBox.getInstance();
			ArrayList<EntityDesignatedContactBox>  list = dao.getDesignatedNateContactInfoList(this);	
		
		for (int i = 0; i < list.size(); i++) {
			
			if(list.get(i).mContact.equals(account)){
				return true;
			}
		}
		
		return false;
	}

    /** 
     *  �ּҷ� ��ȸ Method
     * */
    private void getContactList(Context context){ 
    	
        //�ּҷ� ��񿡼� �̸�, ��ȭ��ȣ ��������
        List<com.skp.opx.rpn.contact.EntityContact>  contactList = AddressBookDAO.getContactList(this);
        if(mAddressList.size()>0){
        	mAddressList.clear();
        }
        
        EntityContact contact= null;         
        
        for (int i = 0; i < contactList.size(); i++) {
        	contact = new EntityContact();
        	contact.setName(contactList.get(i).getmName());
        	contact.setContact(contactList.get(i).getmPhoneNumber());	
        	if(isAlradyExistInContact(contactList.get(i).getmPhoneNumber())){
        		mCheckedDesignateNumberList.add(Integer.valueOf(i));
        		contact.setChecked(true);
        	}
        	mAddressList.add(contact);
		}        
        
		mSubTitle.setText("");
		mSubTitle.setText(getResources().getString(R.string.my_address) + "("+ mAddressList.size() + ")");
        mAddresssAdapter = new Adapter_Address_CheckBox(this);    	
        mAddresssAdapter.setAddressList(mAddressList);
        setListAdapter(mAddresssAdapter);
        mHandler.sendEmptyMessage(DIALOG_DISMISS);
    }
    
	/**
	 * @���� : ����Ʈ�� ģ�� ��� ��ȸ
	 * @RequestURI : https://apis.skplanetx.com/nateon/buddies 
	 */
    private void getNateFriends(){
    	//Querystring Parameters	
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("version", "1");//API�� ���� �����Դϴ�
		map.put("page", "1");
		map.put("count", "50");
		
		//Bundle ����
		RequestBundle bundle = AsyncRequester.make_GET_DELTE_bundle(this, Define.NATEON_FRIENDS_SEARCH_URI, map);

		try {
			//API ȣ��
			AsyncRequester.request(this, bundle, HttpMethod.GET, new EntityParserHandler(new EntityNateContact(), new OnEntityParseComplete() {

				@Override
				public void onParsingComplete(Object entityArray) {
					ArrayList<EntityNateContact> array = (ArrayList<EntityNateContact>)entityArray;
					
					if(mNateContactList.size()>0){
						mNateContactList.clear();
					}
	                //üũ ������ UI ��ƼƼ�� ����			
					EntityNateContactForUI entity = null;
					
					for (int i = 0; i < array.size(); i++) {
						entity = new EntityNateContactForUI();
						entity.name = array.get(i).name;
						entity.nateId = array.get(i).nateId;
						if(isAlradyExistInNate(entity.nateId)){ //db �� �ִ� ���� üũ ó�� �Ѵ�. 
						entity.checkedState = true;	
						mCheckedDesignateNumberList.add(Integer.valueOf(i));
						}
						mNateContactList.add(entity);
					}
					mSubTitle.setText("");
					mSubTitle.setText(getResources().getString(R.string.my_address) + "("+ mNateContactList.size() + ")");
					// üũ ǥ�� ���� ��� ��� ����� �ѱ��.					
					mNateAddressAdapter = new Adapter_NateAddress_CheckBox(getApplicationContext());    	
					mNateAddressAdapter.setNateAddressList(mNateContactList);
				    setListAdapter(mNateAddressAdapter);
				    mHandler.sendEmptyMessage(DIALOG_DISMISS);
				}
				
			}));
		} catch (CloneNotSupportedException e) {
			mHandler.sendEmptyMessage(DIALOG_DISMISS);
			e.printStackTrace();
		}	
    }
    
	@Override
	public void onCheckedChanged(RadioGroup group, int checkId) {
		// TODO Auto-generated method stub
		if(checkId == R.id.myAddress_rb){
			mHandler.sendEmptyMessage(GET_CONTACT);
			mIsNateContact = false;
			mCheckedDesignateNumberList.clear();
		}else if(checkId == R.id.nate_rb){
			//����Ʈ�� ģ�� ��� ��ȸ ��û
			//auth �Ǿ� �ִ��� Ȯ�� 
			if(OAuthInfoManager.authorInfo.getAccessToken() ==null || OAuthInfoManager.authorInfo.getAccessToken().equals("")){
				GeneralAlertDialog dialog = new GeneralAlertDialog(this, getResources().getString(R.string.require_private_auth));
				dialog.setPostiveButton(new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					
					}
				});
				dialog.show();
			}else{
				mHandler.sendEmptyMessage(GET_NATE_FRIENDS);
				mIsNateContact = true;
				mCheckedDesignateNumberList.clear();				
			}
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		onCheckBoxClick(position);
		if(mIsNateContact){
			mNateAddressAdapter.notifyDataSetChanged();
		}else{
			mAddresssAdapter.notifyDataSetChanged();
		}
	}
	
	public void onCheckBoxClick(int position) { 
		
		boolean isChecked = false;
        if(mIsNateContact){
        	isChecked = mNateContactList.get(position).getChecked();
        }else{
        	isChecked = mAddressList.get(position).getChecked();
        }	

		isChecked = !isChecked;
		
		if(mIsNateContact){
			mNateContactList.get(position).setChecked(isChecked);
		}else{
			mAddressList.get(position).setChecked(isChecked);
		}	
		
		if (isChecked == true) {
		mCheckedDesignateNumberList.add(Integer.valueOf(position));
		} else {
		mCheckedDesignateNumberList.remove(Integer.valueOf(position));
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {		

		case R.id.confirm_button://Ȯ��
			//���� �� ���� ���� ��� ���̾� �α� ���� 
			//����Ʈ ���� �ּҷ����� ����
			GeneralAlertDialog dialog = new GeneralAlertDialog(this, getResources().getString(R.string.choose_as_designated_contact));
			dialog.setPostiveButton(new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub					
		    		EntityDesignatedContactBox  entity = null;
		    		//��񿡼� ����. //�ߺ� ��ȭ ��ȣ Ȯ��		    		
		    		DaoDesignatedContactBox dao = DaoDesignatedContactBox.getInstance(); 
		    		//���� ���� ���Ŀ� üũ �Ȱ� �� �����ϱ� 		    		
		    		if(mIsNateContact){
		    			dao.deleteNateAllData(getApplicationContext());
		    		}else{
		    			dao.deleteContactAllData(getApplicationContext());
		    		}
		    		//���� ����
		    		for (int i = 0; i < mCheckedDesignateNumberList.size(); i++) {
		    			EntityContact  entityContact = null;
		    			EntityNateContactForUI entityNateContact= null;
		    			entity = new EntityDesignatedContactBox();
		    			if(mIsNateContact){
		    				 entityNateContact = mNateContactList.get(mCheckedDesignateNumberList.get(i));
		    				 entity.mType = 1;		   
		    				 entity.mName = entityNateContact.name;
		    				 entity.mContact = entityNateContact.nateId;	
		    			}else{
		    				 entityContact = mAddressList.get(mCheckedDesignateNumberList.get(i));
		    				 entity.mType = 0;
		    				 entity.mName = entityContact.name;
				    		 entity.mContact = entityContact.contact;	
		    			}	    	    				    		   			
		    			
		    			try {	    				
							dao.insertContactInfo(getApplicationContext(),entity );
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						mProgressDialog.dismiss();
					
					}  
		    		//���� ���� 
					Toast.makeText(getApplicationContext(), R.string.saved_as_designated_contact, Toast.LENGTH_SHORT).show(); 
					finish();
				}
			});
			dialog.setNegativeButton(new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
  		   dialog.show();
			break;
		default:
			break;
		}
	}
    
}
