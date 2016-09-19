package com.example.user.epicfitproject.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.adapters.ExerciseAdapter;
import com.example.user.epicfitproject.model.exercise.Exercise;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        int idOfClicked = getIntent().getIntExtra("idButton",0);
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
        exerciseAdapter = new ExerciseAdapter(this, exerciseList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        recyclerView.setAdapter(exerciseAdapter);


    }

    // za ruce http://travelstrong.net/arm-exercises-without-weights/
    //idbutton e zaradi tova da razbera ot koi buton sum i s kakvo da napulnq spisuka
    private void fillWithExercises(int idButton){

        switch(idButton){
            case R.id.button_upper_body:

                exerciseList.add(new Exercise(R.drawable.arnold12,"Arnold Shoulder Press","gAS1oKgQcwU&feature=youtu.be",R.string.arnold_shoulder_press));
                exerciseList.add(new Exercise(R.drawable.burpees,"Burpee","1JYh1SMHRh4&feature=youtu.be",R.string.burpee_text));
                exerciseList.add(new Exercise(R.drawable.bentpress,"Bent Press","Icc6N4b8wSA",R.string.bent_press_text));
                exerciseList.add(new Exercise(R.drawable.fit,"External Rotation","NloFjBVOwhw&feature=youtu.be",R.string.external_rotation_text));
                exerciseList.add(new Exercise(R.drawable.fit,"Advanced Gladiator Press","vekeeylZEec&feature=youtu.be",R.string.advanced_gladiator_press));


                break ;
            case R.id.button_lower_body:
                //http://www.bodybuilding.com/fun/6-leg-workouts-to-supersize-your-lower-body.html
               // exerciseList.add(new Exercise(R.drawable.fit,"Squat","ui913K0fspY&feature=youtu.be",R.string.squat));
                //exerciseList.add(new Exercise(R.drawable.fit,"Bulgarian Squat","PiaEYqsk0Dw&feature=youtu.be",R.string.bulgarian_squat));

                break;
            case R.id.button_cardio:
              //  exerciseList.add(new Exercise(R.drawable.fit,"Swimming"));
               // exerciseList.add(new Exercise("Cycling"));
//                exerciseList.add(new Exercise("Jogging"));
//                exerciseList.add(new Exercise("Sprints"));
                break;
            case R.id.button_hiit:
                http://dailyburn.com/life/db/hiit-workouts-for-beginners/
//                exerciseList.add(new Exercise("Beginner"));
//                exerciseList.add(new Exercise("Average"));
//                exerciseList.add(new Exercise("Ninja"));
                break;

        }
        SharedPreferences preffs = getSharedPreferences("exercisesList", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preffs.edit();
        Set<Exercise> exercisesSet = new HashSet<>(exerciseList);
        String key = "exercises";

        JSONArray jsonUsers = new JSONArray();
        try {

            for (Exercise u : exercisesSet) {
                JSONObject obj = new JSONObject();
                obj.put("image", u.getPicture());
                obj.put("name", u.getName());
                obj.put("url", u.getUrl());
                obj.put("information", u.getInformation());

                jsonUsers.put(obj);
            }
        }
        catch(JSONException e){
            Log.e("JSON", e.getMessage());
        }
       String value = jsonUsers.toString();
        Log.e("JSON", value);
        editor.putString(key, value);
        editor.commit();








    }
}
