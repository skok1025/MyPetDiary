package com.skp.opx.rpn.database;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

/**
 * @���� : ���� ����ó ������ DAO
 * @Ŭ������ : DaoDesignatedContactBox 
 *
 */
public class DaoDesignatedContactBox {

	private static DaoDesignatedContactBox sTheInstance = null;
	private DaoDesignatedContactBox() {}
	
	public static synchronized DaoDesignatedContactBox getInstance() {
		
		if(sTheInstance == null) {
			sTheInstance = new DaoDesignatedContactBox();
		}
		
		return sTheInstance;
	}
	
	/** 
	 *  ���� ����ó ��� ����
	 * */
	public void deleteAllData(Context context) throws SQLiteException {
		
		String sql = "delete from " + EntityDesignatedContactBox.class.getSimpleName();
		Helper.getInstance(context).getWritableDatabase().execSQL(sql, new String[0]);
		Helper.getInstance(context).close();
	}
	
	/** 
	 *  �ּҷ� ���� ����ó ��� ����
	 * */
	public void deleteContactAllData(Context context) throws SQLiteException {
		
		String sql = "delete from " + EntityDesignatedContactBox.class.getSimpleName() + " where mType = 0 ";
		Helper.getInstance(context).getWritableDatabase().execSQL(sql, new String[0]);
		Helper.getInstance(context).close();
	}

	/** 
	 *  ����Ʈ ���� ����ó ��� ����
	 * */
	public void deleteNateAllData(Context context) throws SQLiteException {
		
		String sql = "delete from " + EntityDesignatedContactBox.class.getSimpleName() + " where mType = 1 ";
		Helper.getInstance(context).getWritableDatabase().execSQL(sql, new String[0]);
		Helper.getInstance(context).close();
	}
	
	/** 
	 *  ���̵�� ��������ó ����
	 * */
	public void deleteData(Context context, long id) {

		String sql ="delete from " + EntityDesignatedContactBox.class.getSimpleName() + " where ID = " + id;
		Helper.getInstance(context).getWritableDatabase().execSQL(sql);
		Helper.getInstance(context).close();
	}		
	
	/** 
	 * ���� ����ó Insert
	 * */
	public long insertContactInfo(Context context, EntityDesignatedContactBox entity) throws Exception {
		
		ContentValues values = new ContentValues();
		
		Field fieldArray[] = entity.getClass().getFields();
		for(int count = 0, maxCount = fieldArray.length; count < maxCount; count++) {
			if(fieldArray[count].getType() == int.class) {
				values.put(fieldArray[count].getName(), (Integer)fieldArray[count].get(entity));
			} else if(fieldArray[count].getType() == long.class) {
				values.put(fieldArray[count].getName(), (Long)fieldArray[count].get(entity));
			} else if(fieldArray[count].getType() == String.class) {
				values.put(fieldArray[count].getName(), (String)fieldArray[count].get(entity));
			}
		}
		
		long extraID = Helper.getInstance(context).getWritableDatabase().insert(entity.getClass().getSimpleName(), null, values);
		Helper.getInstance(context).close();
		return extraID;
	}	

	/** 
	 * ���� ��ȣ ��� Select  
	 * */
	public ArrayList<EntityDesignatedContactBox> getAllDesignatedContactInfoList(Context context) throws SQLiteException {
		
		String sql = "select * from " + EntityDesignatedContactBox.class.getSimpleName() + " order by mType DESC limit 50";
		return selectContactInfoList(context, sql);
	}
	

	/** 
	 *  �ּҷ� ���� ��ȣ Select
	 * */
	public ArrayList<EntityDesignatedContactBox> getDesignatedContactInfoList(Context context) throws SQLiteException {
		
		String sql = "select * from " + EntityDesignatedContactBox.class.getSimpleName() + " where mType = 0 limit 50" ;
		return selectContactInfoList(context, sql);
	}

	/** 
	 *  ����Ʈ�� ģ�� ������������
	 * */
	public ArrayList<EntityDesignatedContactBox> getDesignatedNateContactInfoList(Context context) throws SQLiteException {
		
		String sql = "select * from " + EntityDesignatedContactBox.class.getSimpleName() + " where mType = 1 limit 50";
		return selectContactInfoList(context, sql);
	}
	
	protected ArrayList<EntityDesignatedContactBox> selectContactInfoList(Context context, String sql) throws SQLiteException {
		
		ArrayList<EntityDesignatedContactBox> rowList = new ArrayList<EntityDesignatedContactBox>();
		Cursor cursor = null;
		
		try {
			cursor = Helper.getInstance(context).getWritableDatabase().rawQuery(sql, null);

			if(cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				EntityDesignatedContactBox callInfo = null;
				
				while(!cursor.isAfterLast()) {
					callInfo = setContactInfo(cursor);
					rowList.add(callInfo);
					cursor.moveToNext();
				}

			} 
		} catch(Exception e) 	{
			throw new SQLiteException(e.getMessage());
		} finally {
			if(cursor != null) {
				cursor.deactivate();
				cursor.close();
				Helper.getInstance(context).close();
			}
		}

		return rowList;
	}
	
	private EntityDesignatedContactBox setContactInfo(Cursor cursor) throws Exception {
		
		EntityDesignatedContactBox entity = new EntityDesignatedContactBox();
		Field fieldArray[] = entity.getClass().getDeclaredFields();
		
		for(int index = 0, maxIndex = fieldArray.length; index < maxIndex; index++) {
			int colIndex = 0;
			colIndex = cursor.getColumnIndex(fieldArray[index].getName());
			
			if(colIndex >= 0){
				if(fieldArray[index].getType() == int.class) {
					fieldArray[index].set(entity, cursor.getInt(colIndex));
				} else if(fieldArray[index].getType() == long.class) {
					fieldArray[index].setAccessible(true);
					fieldArray[index].set(entity, cursor.getLong(colIndex));
				} else if(fieldArray[index].getType() == String.class) {
					fieldArray[index].set(entity, cursor.getString(colIndex));
				}
			}
		}
		
		return entity;
	}
	
	public int selectDesignageInfoCount(Context context, String sql) throws SQLiteException {
		
		Cursor cursor = null;
		
		try{
			cursor = Helper.getInstance(context).getWritableDatabase().rawQuery(sql, null);
			cursor.getCount();			
			return cursor.getCount();
		} catch(Exception e) 	{
			throw new SQLiteException(e.getMessage());
		} finally {
			if(cursor != null) {
				cursor.deactivate();
				cursor.close();
				Helper.getInstance(context).close();
			}
		}
	}
}
