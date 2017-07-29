package com.example.android.eventcountdown;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by WanChing on 28/7/2017.
 */

public class EventDbQueries {

    private EventDbHelper helper;

    public EventDbQueries (EventDbHelper helper){
        this.helper = helper;
    }

    public Cursor read (String[] columns, String selection, String[] selectionArgs, String groupby, String having, String orderBy){
        SQLiteDatabase db = helper.getReadableDatabase();

        return db.query(
                EventContract.EventEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                groupby,
                having,
                orderBy
        );
    }

    public long insert (Event event){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EventContract.EventEntry.COLUMN_NAME_TITLE, event.getTitle());
        values.put(EventContract.EventEntry.COLUMN_NAME_DESCRIPTION, event.getDescription());
        //values.put(EventContract.EventEntry.COLUMN_NAME_DATE, event.getDate());

        long id = db.insert(EventContract.EventEntry.TABLE_NAME, null, values);
        event.setId(id);

        return id;
    }

    public int update(Event event){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EventContract.EventEntry.COLUMN_NAME_TITLE, event.getTitle());
        values.put(EventContract.EventEntry.COLUMN_NAME_DESCRIPTION, event.getDescription());

        String selection = EventContract.EventEntry._ID + " = ?";
        String [] selectionArgs = {Long.toString(event.getId())};

        return db.update(
                EventContract.EventEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

    public void delete (long id){
        SQLiteDatabase db = helper.getWritableDatabase();

        String selection = EventContract.EventEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};

        db.delete(EventContract.EventEntry.TABLE_NAME, selection, selectionArgs);

    }
}
