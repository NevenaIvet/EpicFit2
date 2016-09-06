package com.example.user.epicfitproject;

import android.content.Intent;
import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainMenuActivity extends AppCompatActivity {
    private Button profile ;
    private  Button activities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        profile= (Button) findViewById(R.id.button_profile_menu);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this,AthleteActivity.class);
                startActivity(intent);
            }
        });
        activities= (Button) findViewById(R.id.button_activities_menu);
        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this,ActivitiesActivity.class);
                startActivity(intent);
            }
        });
    }
}
