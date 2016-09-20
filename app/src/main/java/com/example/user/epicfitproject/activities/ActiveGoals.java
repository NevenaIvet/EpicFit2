package com.example.user.epicfitproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.adapters.GoalsAdapter;
import com.example.user.epicfitproject.model.exercise.ActualExercise;
import com.example.user.epicfitproject.model.exercise.ExerciseManager;
import com.example.user.epicfitproject.model.goal.Goal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActiveGoals extends AppCompatActivity {
    List<Goal> goal =  new ArrayList<>();
    private RecyclerView recyclerView;
    private GoalsAdapter goalAdapter;
    private List<ActualExercise> exercises;
    private Button newGoalButton;
    private static  int ADDED_NEW_GOAL_OK;
    private Button delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_goals);
        newGoalButton= (Button) findViewById(R.id.button_add_new_goal);
        delete= (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("activeGoal", Context.MODE_PRIVATE);
                preferences.edit().remove("goal").commit();
                SharedPreferences s = getSharedPreferences("exercisesGoal",Context.MODE_PRIVATE);
                s.edit().remove("exercisesInGoal").commit();
                Log.e("TAG","iztrito e vsichko");
                //mai fill
                fillWithData();

            }
        });
        newGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActiveGoals.this, GoalActivity.class);
                startActivityForResult(intent,ADDED_NEW_GOAL_OK);
            }
        });
        fillWithData();
        recyclerView= (RecyclerView) findViewById(R.id.recycler_view_goals);
        goalAdapter= new GoalsAdapter(this,goal);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(goalAdapter);
    }

    private void fillWithData() {
        String  forName = getSharedPreferences("activeGoal", Context.MODE_PRIVATE).getString("goal","nope");
        if(!forName.equals("nope")){
            String startDate=null;
            String endDate=null;
            try {
                JSONObject o = new JSONObject(forName);
                startDate=o.getString("startDate");
                endDate=o.getString("endDate");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            HashMap<String,ActualExercise> helper = new HashMap<>(ExerciseManager.getInstance(this).getExercises());
            Log.e("TAG","broi dobaveni uprajneniq -"+helper.size());
            exercises= new ArrayList<>();
            for (ActualExercise ex:helper.values()){
                exercises.add(ex);
                Log.e("TAG","dobavqm v celta "+ex.getName());
            }
            goal.add(new Goal(startDate,endDate,exercises));
        }else{
            Log.e("TAG","problem s preffs");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==ADDED_NEW_GOAL_OK){
            fillWithData();
            //tova e refresh edin vid
        }
    }
}
