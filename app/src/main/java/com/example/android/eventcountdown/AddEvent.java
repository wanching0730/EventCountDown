package com.example.android.eventcountdown;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class AddEvent extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDesc;
    private EditText etDate;
    private EditText etTime;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private boolean saved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(("spSaveState", Context.MODE_PRIVATE));
        editor = sharedPreferences.edit();

        etName = (EditText) findViewById(R.id.title);
        etDesc = (EditText) findViewById(R.id.description);
        etDate = (EditText) findViewById(R.id.date_selection);
        etTime = (EditText) findViewById(R.id.time_selection);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
