package com.example.android.eventcountdown;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewEventActivity extends AppCompatActivity {

    public static final String EXTRA_EVENT = "com.example.myeventcountdown.EVENT";
    private Event event;
    private FloatingActionButton fabEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
            TextView etDay = (TextView) findViewById(R.id.day);
            TextView etHour = (TextView) findViewById(R.id.hour);
            TextView etMinute = (TextView) findViewById(R.id.minute);
            TextView etSecond = (TextView) findViewById(R.id.second);
            TextView etLeft = (TextView) findViewById(R.id.left);

            Event.Countdown countdown = event.getCountdown();

            etTitle.setText(event.getTitle());
            etDesc.setText(event.getDescription());
            etDate.setText(new SimpleDateFormat("EEEE, d MMMM, yyyy").format(event.getDate()));
            etTime.setText(new SimpleDateFormat("hh:mm").format(event.getDate()));
            etDay.setText(Long.toString(countdown.getDays()));
            etHour.setText(Long.toString(countdown.getHours()));
            etMinute.setText(Long.toString(countdown.getMinutes()));
            etSecond.setText(Long.toString(countdown.getSeconds()));

            if(countdown.getDurationInMillis() > 0)
                etLeft.setText(getResources().getString(R.string.left));
            else
                etLeft.setText(getResources().getString(R.string.ago));

        }

        fabEdit = (FloatingActionButton) findViewById(R.id.fab_update);
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateEventActivity.class);
                intent.putExtra(EXTRA_EVENT, event);
                startActivity(intent);
            }
        });
    }

    public boolean changeBoolean(int notify){
        return notify > 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_update) {
            Intent intent = new Intent(getApplicationContext(), UpdateEventActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
