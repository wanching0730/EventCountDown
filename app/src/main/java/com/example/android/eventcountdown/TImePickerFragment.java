package com.example.android.eventcountdown;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by WanChing on 13/7/2017.
 */

public class TImePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    private static Calendar calendar = null;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if(calendar == null)
            calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(getActivity(), this, hour, minute, true);

        return  dialog;

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, hourOfDay, minute, Calendar.SECOND);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a");
        String time = dateFormat.format(calendar.getTime());
        EditText timeSelection = (EditText) getActivity().findViewById(R.id.time_selection);
        timeSelection.setText(time);

    }
}
