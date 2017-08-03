package com.example.android.eventcountdown;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class UpdateEventActivity extends AppCompatActivity {

    private Event event;
    private EditText etTitle;
    private EditText etDesc;
    private EditText etDate;
    private EditText etTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        event = (Event) intent.getSerializableExtra(ViewEventActivity.EXTRA_EVENT);

        etTitle = (EditText) findViewById(R.id.title);
        etDesc = (EditText) findViewById(R.id.description);
        etDate = (EditText) findViewById(R.id.date_selection);
        etTime = (EditText) findViewById(R.id.time_selection);

        String date = new java.text.SimpleDateFormat("EEEE, d MMMM, yyyy").format(event.getDate());
        String time = new java.text.SimpleDateFormat("hh:mm").format(event.getDate());

        etTitle.setText(event.getTitle());
        etDesc.setText(event.getDescription());
        etDate.setText(date);
        etTime.setText(time);

    }
}
