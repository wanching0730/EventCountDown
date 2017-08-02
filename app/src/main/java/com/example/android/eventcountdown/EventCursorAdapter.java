package com.example.android.eventcountdown;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by WanChing on 2/8/2017.
 */

public class EventCursorAdapter extends CursorAdapter {

    private LayoutInflater inflater;

    public EventCursorAdapter(Context context, Cursor cursor, int flags){
        super(context, cursor, flags);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvTitle = (TextView)view.findViewById(R.id.item_title);
        TextView tvDate = (TextView)view.findViewById(R.id.item_date);
        String title = cursor.getString(cursor.getColumnIndex(EventContract.EventEntry.COLUMN_NAME_TITLE));
        Long date = cursor.getLong(cursor.getColumnIndex(EventContract.EventEntry.COLUMN_NAME_DATE));

        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM d, yyyy");
        String formattedDate = formatter.format(date);

        tvTitle.setText(title);
        tvDate.setText(formattedDate);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.list_item, parent,false);
    }
}