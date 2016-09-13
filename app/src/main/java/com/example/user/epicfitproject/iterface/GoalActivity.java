package com.example.user.epicfitproject.iterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.user.epicfitproject.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GoalActivity extends AppCompatActivity {


    private static boolean periodTypeFirstPick = true;

    private Button next;
    private CalendarView calendar;
    private TextView dates;
    private String start;
    private String end;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        dates = (TextView) findViewById(R.id.dates_save_field);
        next = (Button) findViewById(R.id.button_addExercise);
        calendar= (CalendarView) findViewById(R.id.calendar);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = df.format(c.getTime());
                String endH= (i2+"/"+i1+"/"+i);

                dates.setText("From today "+ formattedDate+"\nEnd date  "+endH);
                start=formattedDate;
                end=endH;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoalActivity.this,ActivitiesActivity.class);
                intent.putExtra("endDate",dates.getText().toString());

                //predavane na tekushtata data i datata za final na celta
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = df.format(c.getTime());
                intent.putExtra("startDate",start);
                intent.putExtra("endDate",end);
                startActivity(intent);
                finish();
            }
        });

    }
}
