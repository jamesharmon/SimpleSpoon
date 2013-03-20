package com.nfjs.helloworld.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbAdapter {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "hello.db";

	private DbHelper dbHelper;

	public SQLiteDatabase database;

	public DbAdapter(Context context) {
		dbHelper = new DbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public DbAdapter open() {
		database = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	private Cursor getAllEntries() {
		String[] columns = new String[1];
		columns[0] = "name";
		return database.query("names", columns, null, null, null, null, null);
	}

	public List<String> getAllNames() {
		ArrayList<String> names = new ArrayList<String>();
		Cursor cursor = getAllEntries();
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return names;
	}

	public long insertName(String name) {
		ContentValues values = new ContentValues();
		values.put("name", name);
		return database.insert("names", null, values);
	}

	public int deleteName(String name) {
		String whereClause = "name = ?";
		String[] whereArgs = new String[1];
		whereArgs[0] = name;
		return database.delete("names", whereClause, whereArgs);
	}

	public int deleteAllNames() {
		return database.delete("names", null, null);
	}

	public int updateName(String name) {
		String whereClause = "name = ?";
		String[] whereArgs = new String[1];
		whereArgs[0] = name;
		ContentValues values = new ContentValues();
		values.put("name", name);
		return database.update("names", values, whereClause, whereArgs);
	}
}
