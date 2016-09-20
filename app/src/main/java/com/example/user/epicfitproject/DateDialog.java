package com.example.user.epicfitproject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.DatePicker;

import com.example.user.epicfitproject.UI.GoalActivity;

import java.util.Calendar;

/**
 * Created by User on 19-Sep-16.
 */
public class DateDialog extends  android.app.DialogFragment implements DatePickerDialog.OnDateSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, i);
        cal.set(Calendar.MONTH, i1);
        cal.set(Calendar.DAY_OF_MONTH, i2);
        ((GoalActivity)getActivity()).dateSet(getArguments().getString("type"), cal.getTime());

    }


}