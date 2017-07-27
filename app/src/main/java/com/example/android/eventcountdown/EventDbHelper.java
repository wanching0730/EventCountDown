package com.example.android.eventcountdown;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by WanChing on 27/7/2017.
 */

public class EventDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contact.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE" + EventContract.EventEntry.TABLE_NAME + " (" +
                    EventContract.EventEntry._ID + " INTEGER PRIMARY KEY," +
                    EventContract.EventEntry.COLUMN_NAME_TITLE + " TEXT," +
                    EventContract.EventEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    EventContract.EventEntry.COLUMN_NAME_DATE + " INTEGER," +
                    EventContract.EventEntry.COLUMN_NAME_NOTIFY + " INTEGER)";

    public EventDbHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
