package com.example.android.eventcountdown;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by WanChing on 13/7/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    //this cal only declared once and used throughout the class
    //use back the previous cal object that entered by user
    public static Calendar cal = null;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(cal == null){
            //used to get calendar according to the default time and date.
            cal = Calendar.getInstance();
        }

        //get CURRENT year, month, day from the calendar
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);

        return  dialog;
    }

//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        EditText dateSelection = (EditText) getActivity().findViewById(R.id.date_selection);
//        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
//        cal.set(year, month, dayOfMonth);
//        dateSelection.setText(dateFormat.format(cal.getTime()));
//
//    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        cal.set(year, month, dayOfMonth);

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        //DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);

        String date = dateFormat.format(cal.getTime());
        EditText dateSelection = (EditText) getActivity().findViewById(R.id.date_selection);
        dateSelection.setText(date);
    }
}
