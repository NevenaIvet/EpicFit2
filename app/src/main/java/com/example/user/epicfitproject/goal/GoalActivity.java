package com.example.user.epicfitproject.goal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.user.epicfitproject.R;

import java.util.ArrayList;

public class GoalActivity extends AppCompatActivity {

    //this variables are needed for the maded spinner
    private Spinner periodTypeSp;
    public static final String MONTH = "Month";
    public static final String DAY = "Day";
    public static final String YEAR = "Year";
    private static boolean periodTypeFirstPick = true;

    private EditText startDate;
    private EditText duratioN;
    private Button addEx;
    private EditText reps;
    private EditText series;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        periodTypeFirstPick = true;

        periodTypeSp = (Spinner) findViewById(R.id.spinner_periodType);

        ArrayList<String> periods = new ArrayList<>();
        periods.add(MONTH);
        periods.add(DAY);
        periods.add(YEAR);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,periods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodTypeSp.setAdapter(adapter);

        periodTypeSp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                periodTypeFirstPick = false;
            }
        });

        startDate = (EditText) findViewById(R.id.startDateET);
        duratioN = (EditText) findViewById(R.id.duration);
        addEx = (Button) findViewById(R.id.button_addExercise);
        reps = (EditText) findViewById(R.id.repsET);
        series = (EditText) findViewById(R.id.seriesET);
        confirm = (Button) findViewById(R.id.button_confirm);



    }
}
