package com.example.user.epicfitproject.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.epicfitproject.fragments.NavigationMSG;
import com.example.user.epicfitproject.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class GoalActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,DialogInterface.OnCancelListener,NavigationMSG.OnGotItTap {

    private Button next;
    private Button from;
    private TextView startDateTV;
    private TextView endDateTV;
    private Button to;
    private Calendar startDate ;
    private Calendar endDate;
    Calendar cHelp;
    private static  final int GOAL_UPDATED=55;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        startDateTV = (TextView) findViewById(R.id.dates_save_field);
        endDateTV= (TextView) findViewById(R.id.dates_save_field2);
        next = (Button) findViewById(R.id.button_addExercise);
        to= (Button) findViewById(R.id.button_to);
        from= (Button) findViewById(R.id.button_from);
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endDateTV.setText(null);
                day=0;
                month=0;
                year=0;
                showMeCalendar(view);
               // startDate=cHelp;
              //  endDateTV.setText(day+"/"+(month+1)+"/"+year);
            }
        });
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDateTV.setText(null);

                showMeCalendar(view);
              //  startDate=cHelp;
                //String s = year+"/"+month+1+"/"+day;
                //startDateTV.setText(day+"/"+(month+1)+"/"+year);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoalActivity.this, ActivitiesActivity.class);
                intent.putExtra("endDate",endDateTV.getText().toString());
                intent.putExtra("startDate",startDateTV.getText().toString());
                SharedPreferences sp = getSharedPreferences("activeGoal", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                JSONObject obj=new JSONObject();
                try {
                    obj.put("startDate",startDateTV.getText().toString());
                    obj.put("endDate",endDateTV.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                editor.putString("goal",obj.toString());
                Log.e("ivet",obj.toString());
                editor.commit();
                startActivity(intent);



                //msgDialog.changeText("Now add all of your favourite exercises");
               // msgDialog.show(fm, "gotIt");
            }
        });



    }




    private int day,month,year;
    private void showMeCalendar(View view){

        initializeTimeData();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

        Calendar cMin=Calendar.getInstance();
        Calendar cMax = Calendar.getInstance();
        cMax.set(cMax.get(Calendar.YEAR)+2,11,31);
        datePickerDialog.setMaxDate(cMax);
        datePickerDialog.setMinDate(cMin);
        datePickerDialog.show(getFragmentManager(),"DatePickerDialog");
        datePickerDialog.setOnCancelListener(this);
        datePickerDialog.setOnDateSetListener(this);

    }


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
        cHelp = Calendar.getInstance();
        this.year = year;
        this.month = month;
        this.day = day;
        cHelp.set(Calendar.YEAR,year);
        cHelp.set(Calendar.MONTH,month);
        cHelp.set(Calendar.DAY_OF_MONTH,day);




    }



    @Override
    public void onCancel(DialogInterface dialogInterface) {
        day=0;
        month=0;
        year=0;
        cHelp=Calendar.getInstance();
        startDateTV.setText(null);
        endDateTV.setText(null);
    }

    @Override
    public void onGotItTapped() {
        Intent intent = new Intent(GoalActivity.this, ActivitiesActivity.class);
        startActivityForResult(intent,55);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //TODO
    }
}


