package com.skp.opx.rpn.database;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

/**
 * @���� : �˻��� ��� DAO
 * @Ŭ������ : DaoSearchedPathBox 
 *
 */
public class DaoSearchedPathBox {

	private static DaoSearchedPathBox sTheInstance = null;
	private DaoSearchedPathBox() {}
	
	public static synchronized DaoSearchedPathBox getInstance() {
		
		if(sTheInstance == null) {
			sTheInstance = new DaoSearchedPathBox();
		}
		
		return sTheInstance;
	}
	
	/** 
	 *  �˻��� ��� ��ü ���� Method
	 * */
	public void deleteAllData(Context context) throws SQLiteException {
		
		String sql = "delete from " + EntitySearchdPathBox.class.getSimpleName();
		Helper.getInstance(context).getWritableDatabase().execSQL(sql, new String[0]);
		Helper.getInstance(context).close();
	}	

	/** 
	 *  ���̵�� �˻��� ��� ���� Method
	 * */
	public void deleteData(Context context, long id) {

		String sql ="delete from " + EntitySearchdPathBox.class.getSimpleName() + " where ID = " + id;
		Helper.getInstance(context).getWritableDatabase().execSQL(sql);
		Helper.getInstance(context).close();
	}			
	
	/** 
	 *  �˻��� ��� Insert Method
	 * */
	public long insertSearchedPathInfo(Context context, EntitySearchdPathBox entity) throws Exception {
		
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
	 * �˻��� ��� ���� Method (20�� ����)
	 */
	public ArrayList<EntitySearchdPathBox> getAllSearchedPathInfoList(Context context) throws SQLiteException {
		String sql = "select * from " + EntitySearchdPathBox.class.getSimpleName() + " group by mSearchedTime order by mSearchedTime desc limit 10";
		return selectSearchedPathInfoList(context, sql);
	}
	/**
	 * �ֽ� ��� �ϳ��� Select Method (�޽��� ���۽� ���)
	 */
	public ArrayList<EntitySearchdPathBox> getLatestSearchedPathInfoList(Context context) throws SQLiteException {
		
		String sql = "select * from " + EntitySearchdPathBox.class.getSimpleName() + " order by mSearchedTime desc limit 1";
		return selectSearchedPathInfoList(context, sql);
	}
	
	/**
	 * ��� �����ð����� ��� Select Method (���� ��ο� ���� ���̵�)
	 */
	public ArrayList<EntitySearchdPathBox> getSearchedPathInfoListById(Context context, long searchedTime) throws SQLiteException {
		
		String sql = "select * from " + EntitySearchdPathBox.class.getSimpleName() + " where mSearchedTime = " + searchedTime + " limit 50";
		return selectSearchedPathInfoList(context, sql);
	}
	
	protected ArrayList<EntitySearchdPathBox> selectSearchedPathInfoList(Context context, String sql) throws SQLiteException {
		
		ArrayList<EntitySearchdPathBox> rowList = new ArrayList<EntitySearchdPathBox>();
		Cursor cursor = null;
		
		try {
			cursor = Helper.getInstance(context).getWritableDatabase().rawQuery(sql, null);

			if(cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				EntitySearchdPathBox callInfo = null;
				
				while(!cursor.isAfterLast()) {
					callInfo = setSendInfo(cursor);
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
	
	private EntitySearchdPathBox setSendInfo(Cursor cursor) throws Exception {
		
		EntitySearchdPathBox entity = new EntitySearchdPathBox();
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
	
	protected int selectSendInfoCount(Context context, String sql) throws SQLiteException {
		
		Cursor cursor = null;
		
		try{
			cursor = Helper.getInstance(context).getWritableDatabase().rawQuery(sql, null);
			int count  = 0;
			if(cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				count = cursor.getInt(cursor.getColumnIndex("RowCount"));
			}
			
			return count;
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
