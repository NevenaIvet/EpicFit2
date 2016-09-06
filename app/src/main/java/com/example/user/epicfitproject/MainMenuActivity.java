package com.example.user.epicfitproject;

import android.content.Intent;
import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class MainMenuActivity extends AppCompatActivity {
    private Button profile ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        profile= (Button) findViewById(R.id.button_profile_menu);
        Intent intent = new Intent(MainMenuActivity.this,AthleteActivity.class);
        intent.putExtra("LoggedUser", getIntent().getStringExtra("LoggedUser").toString());
        startActivity(intent);
    }
}
