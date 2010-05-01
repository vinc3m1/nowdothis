package com.makeramen.nowdothis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DoThisDb extends SQLiteOpenHelper {

	
	public final static String DB_NAME = "dothis";
	public final static int DB_VERSION = 1;
	
	public final static String TABLE_LISTS = "lists";
	public final static String FIELD_LIST_ID = "_id";
	public final static String FIELD_LIST_NAME= "name";

	public final static String TABLE_ITEMS = "items";
	public final static String FIELD_ITEM_ID = "_id";
	public final static String FIELD_ITEM_NOTE = "note";
	public final static String FIELD_ITEM_DETAILS = "details";
	public final static String FIELD_ITEM_CROSSED = "crossed";
	public final static String FIELD_ITEM_PRIORITY = "priority";
	public final static String FIELD_ITEM_ORDER = "sort_id";
	public final static String FIELD_ITEM_LIST_ID = "list";
	
	public final String[] doThisExamples;
	
	public DoThisDb(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DB_NAME, null, DB_VERSION);
		doThisExamples = context.getResources().getStringArray(R.array.sample);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}