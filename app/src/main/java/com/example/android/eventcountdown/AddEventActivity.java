package com.example.android.eventcountdown;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity {

    //
    private EditText etTitle;
    private EditText etDesc;
    private EditText etDate;
    private EditText etTime;

    private Date newDate;

    private boolean saved = false;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences("spSaveState", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        etTitle = (EditText) findViewById(R.id.title);
        etDesc = (EditText) findViewById(R.id.description);
        etDate = (EditText) findViewById(R.id.date_selection);
        etTime = (EditText) findViewById(R.id.time_selection);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String title = etTitle.getText().toString();
                    String desc = etDesc.getText().toString();
                    String date = etDate.getText().toString();
                    String time = etTime.getText().toString();
                    SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM d, yyyy HH:mm", Locale.ENGLISH);

                    String combination = date + " " + time;
                    newDate = formatter.parse(combination);

                    EventDbQueries dbqueries = new EventDbQueries(new EventDbHelper(getApplicationContext()));
                    Event event = new Event(0, title, desc, newDate, false);

                    if (dbqueries.insert(event) != 0) {
                        saved = true;
                        Toast.makeText(AddEventActivity.this, "Event inserted successfully!", Toast.LENGTH_SHORT).show();

                        //onPause(), onStop(), onDestroy()
                        finish();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(AddEventActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
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

    @Override
    protected void onPause() {
        super.onPause();

        if(saved){
            editor.clear();
        }
        else{
            String title = etTitle.getText().toString();
            String desc = etDesc.getText().toString();
            String date = etDate.getText().toString();
            String time = etTime.getText().toString();

            editor.putString("SAVE_STATE_TITLE", title);
            editor.putString("SAVE_STATE_DESC", desc);
            editor.putString("SAVE_STATE_DATE", date);
            editor.putString("SAVE_STATE_TIME", time);
        }

        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String title = sharedPreferences.getString("SAVE_STATE_TITLE", "");
        String desc = sharedPreferences.getString("SAVE_STATE_DESC", "");
        String date = sharedPreferences.getString("SAVE_STATE_DATE", "");
        String time = sharedPreferences.getString("SAVE_STATE_TIME", "");

        etTitle.setText(title);
        etDesc.setText(desc);
        etDate.setText(date);
        etTime.setText(time);
    }
}
