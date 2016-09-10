package com.example.user.epicfitproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class GoalActivity extends AppCompatActivity {


    private static boolean periodTypeFirstPick = true;

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
                intent.putExtra("endDate",dateEnds.getText().toString());

                //predavane na tekushtata data i datata za final na celta
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = df.format(c.getTime());
                intent.putExtra("startDate",formattedDate);
                startActivity(intent);
                finish();
            }
        });

    }
}
