package com.example.user.epicfitproject.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.epicfitproject.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class GoalActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,DialogInterface.OnCancelListener{


    private static boolean periodTypeFirstPick = true;
    private static boolean filledStart=false;
    private Button next;
    private Button from;
    private TextView startDateTV;
    private TextView endDateTV;

    private Button to;

    private Calendar startDate ;
    private Calendar endDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        startDateTV = (TextView) findViewById(R.id.dates_save_field);
        endDateTV= (TextView) findViewById(R.id.dates_save_field2);
        next = (Button) findViewById(R.id.button_addExercise);
        to= (Button) findViewById(R.id.button_to);
        View.OnClickListener onClickDates = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;

                int id = b.getId();
                showMeCalendar(view,id);
            }
        };


        from= (Button) findViewById(R.id.button_from);
        to.setOnClickListener(onClickDates);
        from.setOnClickListener(onClickDates);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //zapishi v shared prefs dvata obekta
                //intent na nqkude
            }
        });



    }


    private int day,month,year;
    private void showMeCalendar(View view,int id){
        if(filledStart){
            this.year=0;
            this.month=0;
            this.day=0;
        }
        initializeTimeData();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        Calendar cMin=Calendar.getInstance();
        Calendar cMax = Calendar.getInstance();
        cMax.set(cMax.get(Calendar.YEAR)+2,11,31);
        datePickerDialog.setMaxDate(cMax);
        datePickerDialog.setMinDate(cMin);
        datePickerDialog.setThemeDark(false);

        datePickerDialog.show(getFragmentManager(),"DatePickerDialog");
        datePickerDialog.setOnCancelListener(this);

    }
    //VIJ TAGOVETE

    private void initializeTimeData(){
        if(year==0){
            Calendar c = Calendar.getInstance();
            year=c.get(Calendar.YEAR);
            month=c.get(Calendar.MONTH);
            day=c.get(Calendar.DAY_OF_MONTH);
        }

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        this.year=year;
        this.month=month;
        this.day=day;
        c.set(year,month,day);


        if(filledStart){
            this.startDate=Calendar.getInstance();
            startDate.set(this.year,this.month,this.day);
            startDateTV.setText(day+"/"+(month+1)+"/"+year);
            filledStart=true;
            return;
        }
        this.endDate=Calendar.getInstance();
        endDate.set(this.year,this.month,this.day);
        endDateTV.setText(day+"/"+(month+1)+"/"+year);
//        if(startDate.before(endDate)){
//
//        }

    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        day=0;
        month=0;
        year=0;
        startDate=Calendar.getInstance();
        startDateTV.setText(null);
    }
}
