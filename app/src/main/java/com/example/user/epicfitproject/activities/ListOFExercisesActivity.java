package com.example.user.epicfitproject.activities;

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

    private void fillWithExercises(int idButton){
        switch(idButton){
            case R.id.button_upper_body:
                exerciseList.add(new Exercise(R.drawable.arnold12,"Arnold Shoulder Press","gAS1oKgQcwU&feature=youtu.be",R.string.arnold_shoulder_press));
                exerciseList.add(new Exercise(R.drawable.burpees,"Burpee","1JYh1SMHRh4&feature=youtu.be",R.string.burpee_text));
                exerciseList.add(new Exercise(R.drawable.bentpress,"Bent Press","Icc6N4b8wSA",R.string.bent_press_text));
                exerciseList.add(new Exercise(R.drawable.ext_rotation,"External Rotation","NloFjBVOwhw&feature=youtu.be",R.string.external_rotation_text));
                exerciseList.add(new Exercise(R.drawable.glad_press,"Advanced Gladiator Press","vekeeylZEec&feature=youtu.be",R.string.advanced_gladiator_press));
                break ;

            case R.id.button_lower_body:
                exerciseList.add(new Exercise(R.drawable.squat,"Squat","ui913K0fspY&feature=youtu.be",R.string.squat));
                exerciseList.add(new Exercise(R.drawable.bulgarian_squat,"Bulgarian Squat","PiaEYqsk0Dw&feature=youtu.be",R.string.bulgarian_squat));
                break;

            case R.id.button_cardio:
               exerciseList.add(new Exercise(R.drawable.swimming,"Swimming","w6n_SrjLypA",R.string.swimming));

                break;
            case R.id.button_hiit:
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
