package com.example.user.epicfitproject.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.RepetitionsSets;
import com.example.user.epicfitproject.model.exercise.ActualExercise;
import com.example.user.epicfitproject.model.exercise.Exercise;

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
        //add to goal
        Log.e("ivet","mi znachi sum suzdala obekta uspeshno s "+rep+" povt i "+set+" seta");


        SharedPreferences preferences = getSharedPreferences("athleteLogged", Context.MODE_PRIVATE);
        String athleteH = preferences.getString("athlete","nope");
        if(!athleteH.equals("nope")){
            //SharedPreferences sp= getSharedPreferences("goalExercises",Context.MODE_PRIVATE);
            //SharedPreferences.Editor editor = sp.edit();
            //AKO ne sushtestvuva tova kato se prochete zaglavieto

        }

    }


}


