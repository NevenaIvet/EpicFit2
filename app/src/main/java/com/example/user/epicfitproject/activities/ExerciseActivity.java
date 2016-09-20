package com.example.user.epicfitproject.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.fragments.RepetitionsSets;
import com.example.user.epicfitproject.model.exercise.ActualExercise;
import com.example.user.epicfitproject.model.exercise.Exercise;
import com.example.user.epicfitproject.model.exercise.ExerciseManager;

public class ExerciseActivity extends AppCompatActivity implements RepetitionsSets.OnDoneListener{
    private TextView heading;
    private TextView info;
    private ImageView image;
    private Button add;
    private TextView moreText;
    private Button video;
    private Exercise exercise;
    private ActualExercise actualExercise;
    private String urlH = "https://www.youtube.com/watch?v=";
    private RepetitionsSets repsSetsFr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        heading = (TextView) findViewById(R.id.name);
        info = (TextView) findViewById(R.id.infoForTheExercise);
        image = (ImageView) findViewById(R.id.image);
        add = (Button) findViewById(R.id.addButton);
        moreText = (TextView) findViewById(R.id.additionText);
        video = (Button) findViewById(R.id.button_video);
        heading.setClickable(false);
        heading.setFocusable(false);
        info.setClickable(false);
        info.setFocusable(false);
        if(getIntent().getParcelableExtra("exercise")!=null) {
            exercise = getIntent().getParcelableExtra("exercise");
            heading.setText(exercise.getName());
            info.setText(exercise.getInformation());
            image.setBackgroundResource(exercise.getPicture());
        }
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(video.isPressed()){
                    String vUrl = urlH+exercise.getUrl();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(vUrl));
                    startActivity(intent);
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repsSetsFr=new RepetitionsSets();
                FragmentManager fm = getSupportFragmentManager();
                repsSetsFr.show(fm, "setsReps");
            }
        });
        }

    @Override
    public void onNextClicked(int set,int rep ) {
        actualExercise=new ActualExercise(exercise.getPicture(),exercise.getName(),exercise.getUrl(),exercise.getInformation(),rep,set);
        if(ExerciseManager.getInstance(this).existsExercise(actualExercise.getName())){
            Toast.makeText(ExerciseActivity.this,"Exercise was already added to goal",Toast.LENGTH_SHORT).show();
            return;
        }else{
            ExerciseManager.getInstance(this).addExercise(this,actualExercise.getPicture(),actualExercise.getName(),actualExercise.getUrl(),actualExercise.getInformation(),actualExercise.getRepetitions(),actualExercise.getSets());
            Log.e("TAG","exercise is added in ExerciseManager since it does not exist there till now ");
        }
        Intent intent = new Intent(ExerciseActivity.this,ActivitiesActivity.class);
        startActivity(intent);
        finish();
    }
}


