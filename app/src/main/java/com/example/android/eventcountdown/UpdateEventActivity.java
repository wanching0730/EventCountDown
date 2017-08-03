package com.example.android.eventcountdown;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.util.Date;

public class UpdateEventActivity extends AppCompatActivity {

    private Event event;
    private Date formattedDate;
    private String date, time;
    private EditText etTitle;
    private EditText etDesc;
    private EditText etDate;
    private EditText etTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event);

        Intent intent = getIntent();
        event = (Event) intent.getSerializableExtra(ViewEventActivity.EXTRA_EVENT);

        //to prevent clashing if user do not edit anything
        formattedDate = event.getDate();

        etTitle = (EditText) findViewById(R.id.title);
        etDesc = (EditText) findViewById(R.id.description);
        etDate = (EditText) findViewById(R.id.date_selection);
        etTime = (EditText) findViewById(R.id.time_selection);

        date = new java.text.SimpleDateFormat("EEEE, d MMMM, yyyy").format(event.getDate());
        time = new java.text.SimpleDateFormat("hh:mm").format(event.getDate());

        etTitle.setText(event.getTitle());
        etDesc.setText(event.getDescription());
        etDate.setText(date);
        etTime.setText(time);

        FloatingActionButton fabUpdate = (FloatingActionButton) findViewById(R.id.fab_done_update);
        fabUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date = etDate.getText().toString();
                time = etTime.getText().toString();

                try{
                    formattedDate = new java.text.SimpleDateFormat("EEEE, MMMM d, yyyy HH:mm").parse(date + " " + time);
                }catch (ParseException e){
                    e.printStackTrace();
                }

                event.setTitle(etTitle.getText().toString());
                event.setDescription(etDesc.getText().toString());
                event.setDate(formattedDate);

                EventDbQueries dbq = new EventDbQueries(new EventDbHelper(getApplicationContext()));
                dbq.update(event);

                finish();
            }
        });
    }

    public void SetDate(View view){
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "datePicker");

    }

    public void SetTime(View view){
        DialogFragment fragment = new TImePickerFragment();
        fragment.show(getSupportFragmentManager(), "timePicker");

    }

}

