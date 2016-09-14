package com.example.user.epicfitproject.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.model.User;
import com.example.user.epicfitproject.model.exercise.Exercise;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExerciseActivity extends AppCompatActivity {
    private TextView heading;
    private TextView info;
    private ImageView image;
    private Button add;
    private TextView moreText;
    private VideoView video;
    private Exercise exercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        heading = (TextView) findViewById(R.id.name);
        info = (TextView) findViewById(R.id.infoForTheExercise);
        image = (ImageView) findViewById(R.id.image);
        add = (Button) findViewById(R.id.addButton);
        moreText = (TextView) findViewById(R.id.additionText);
        video = (VideoView) findViewById(R.id.video);


        heading.setClickable(false);
        heading.setFocusable(false);
        info.setClickable(false);
        info.setFocusable(false);
//        if(getIntent().getParcelableExtra("exercise")!=null){
//            exercise=getIntent().getParcelableExtra("exercise");
//            heading.setText(exercise.getName());
//            info.setText(exercise.getInformation());
            //image.setImageDrawable(R.exercise.getPicture());



        }


    }


