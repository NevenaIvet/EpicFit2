package com.example.user.epicfitproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.epicfitproject.R;

import java.util.ArrayList;

public class GoalActivity extends AppCompatActivity {

    //this variables are needed for the maded spinner
    private Spinner periodTypeSp;
    public static final String MONTH = "Month";
    public static final String DAY = "Day";
    public static final String YEAR = "Year";
    private static boolean periodTypeFirstPick = true;

//    private EditText startDate;
//    private EditText duratioN;
//    private Button addEx;
//    private EditText reps;
//    private EditText series;
    private Button addExercises;
    private CalendarView calendar;
    private TextView dateEnds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        dateEnds = (TextView) findViewById(R.id.textView4);
        addExercises = (Button) findViewById(R.id.button_addExercise);
        calendar= (CalendarView) findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                dateEnds.setText("Date your challenge edns : "+i2+ "/"+i1+"/"+i);
            }
        });
        addExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoalActivity.this,ActivitiesActivity.class);
                startActivity(intent);
            }
        });

    }
}
