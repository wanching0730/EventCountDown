package com.example.android.eventcountdown;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewEventActivity extends AppCompatActivity {

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        long id = intent.getLongExtra(MainActivity.EXTRA_ID, 0);

        EventDbQueries dbq = new EventDbQueries((new EventDbHelper(getApplicationContext())));;

        final String[] columns = {
                EventContract.EventEntry._ID,
                EventContract.EventEntry.COLUMN_NAME_TITLE,
                EventContract.EventEntry.COLUMN_NAME_DESCRIPTION,
                EventContract.EventEntry.COLUMN_NAME_DATE,
                EventContract.EventEntry.COLUMN_NAME_NOTIFY};

        String selection = EventContract.EventEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};

        Cursor cursor = dbq.read(columns, selection, selectionArgs, null, null, null);

        if(cursor.moveToNext()){
            event = new Event(
                    cursor.getLong(cursor.getColumnIndex(EventContract.EventEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(EventContract.EventEntry.COLUMN_NAME_TITLE)),
                    cursor.getString(cursor.getColumnIndex(EventContract.EventEntry.COLUMN_NAME_DESCRIPTION)),
                    new Date(cursor.getLong(cursor.getColumnIndex(EventContract.EventEntry.COLUMN_NAME_DATE))),
                    changeBoolean(cursor.getInt(cursor.getColumnIndex(EventContract.EventEntry.COLUMN_NAME_NOTIFY)))
                    );

            setTitle(event.getTitle());

            TextView etTitle = (TextView) findViewById(R.id.title);
            TextView etDesc = (TextView) findViewById(R.id.description);
            TextView etDate = (TextView) findViewById(R.id.date);
            TextView etTime = (TextView) findViewById(R.id.time);

            etTitle.setText(event.getTitle());
            etDesc.setText(event.getDescription());
            etDate.setText(new SimpleDateFormat("EEEE, d MMMM, yyyy").format(event.getDate()));
            etTime.setText(new SimpleDateFormat("hh:mm").format(event.getDate()));
        }
    }

    public boolean changeBoolean(int notify){
        return notify > 0;
    }
}
