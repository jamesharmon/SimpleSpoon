package com.nfjs.helloworld.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = DbHelper.class.getSimpleName();

    //@formatter:off
	private static final String DATABASE_CREATE = 
			  "create table names ("
			+ "_id integer primary key autoincrement, " 
			+ "name text" 
			+ ");";
	//@formatter:on

    private static final String DATABASE_DROP = 
		"drop table if exists names";

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.v(TAG, "Create Table using " + DATABASE_CREATE);
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
            int newVersion) {
        Log.v(TAG, "Drop Table, Upgrading from " + oldVersion + " to "
                + newVersion);
        database.execSQL(DATABASE_DROP);
        onCreate(database);
    }

    public DbHelper(Context context, String name, CursorFactory factory,
            int version) {
        super(context, name, factory, version);
    }

}
