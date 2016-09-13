package com.example.user.epicfitproject.iterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.model.exercise.Exercise;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListOFExercisesActivity extends AppCompatActivity {


    //tuk trqbva da sa vsichkite uprajneniq i da se dobavqt dinamichno zashtoto she mi e resycle3r
    //trqbva mi proverka ot koi buton na prednoto activity sum doshla tuka
    //iskam da ponabluskam statichno nqkolko uprajnieniq
    //obektite v sledvashtoto activity shte sa mi exercise i chak kogato gi dobavq actual exercise


    private List<Exercise> exerciseList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ExerciseAdapter exerciseAdapter;
    private TextView heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ofexercises);
        heading= (TextView) findViewById(R.id.parent_activity);
        int idOfClicked = getIntent().getIntExtra("idButton",0);//slagam 0la che da ne vleze nikude
        String headingHolder=null;
        switch(idOfClicked){
            case R.id.button_upper_body:
                headingHolder="Upper body exercises";
                break ;
            case R.id.button_lower_body:
                headingHolder="Lower body exercises";

                break;
            case R.id.button_cardio:
                headingHolder="Cardio exercises";
              break;
            case R.id.button_hiit:
                headingHolder="Sample HIIT exercises";
                break;
        }
        heading.setText(headingHolder);

        fillWithExercises(idOfClicked);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        exerciseAdapter = new ExerciseAdapter(exerciseList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(exerciseAdapter);
        //TODO set onclick listener ????
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent kum exercise  activity
            }
        });

    }

    // za ruce http://travelstrong.net/arm-exercises-without-weights/
    //idbutton e zaradi tova da razbera ot koi buton sum i s kakvo da napulnq spisuka
    private void fillWithExercises(int idButton){

        switch(idButton){
            case R.id.button_upper_body:
                exerciseList.add(new Exercise("Chin-Up","Target muscle groups upper back,biceps,triceps"));
                exerciseList.add(new Exercise("Inverted Row","Target muscle groups middle back,biceps,triceps"));
                exerciseList.add(new Exercise("TRX/Resistance Band/Curl","vvvvvvvvvvvvvvvv"));

                break ;
            case R.id.button_lower_body:
                //http://www.bodybuilding.com/fun/6-leg-workouts-to-supersize-your-lower-body.html
                exerciseList.add(new Exercise("Full squat"));
                exerciseList.add(new Exercise("Barbel lunge"));
                exerciseList.add(new Exercise("Single leg deadlift"));
                break;
            case R.id.button_cardio:
                exerciseList.add(new Exercise("Swimming"));
                exerciseList.add(new Exercise("Cycling"));
                exerciseList.add(new Exercise("Jogging"));
                exerciseList.add(new Exercise("Sprints"));
                break;
            case R.id.button_hiit:
                http://dailyburn.com/life/db/hiit-workouts-for-beginners/
                exerciseList.add(new Exercise("Beginner"));
                exerciseList.add(new Exercise("Average"));
                exerciseList.add(new Exercise("Ninja"));
                break;
        }






    }
}
