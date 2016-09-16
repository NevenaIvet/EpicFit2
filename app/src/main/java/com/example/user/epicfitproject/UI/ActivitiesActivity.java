package com.example.user.epicfitproject.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.epicfitproject.R;

public class ActivitiesActivity extends AppCompatActivity {
    private Button upperBody;
    private Button lowerBody;

    private Button cardio;
    private Button hiit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
        //tuk ima dostup do datata startDate i endDate ot predniq intent
        upperBody = (Button) findViewById(R.id.button_upper_body);
        lowerBody = (Button) findViewById(R.id.button_lower_body);

        cardio = (Button) findViewById(R.id.button_cardio);
        hiit = (Button) findViewById(R.id.button_hiit);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;

                Intent intent = new Intent(ActivitiesActivity.this,ListOFExercisesActivity.class);
                intent.putExtra("idButton",button.getId());
                startActivity(intent);
            }
        };
        upperBody.setOnClickListener(onClickListener);
        lowerBody.setOnClickListener(onClickListener);

        cardio.setOnClickListener(onClickListener);
        hiit.setOnClickListener(onClickListener);

    }
}