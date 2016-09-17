package com.example.user.epicfitproject.UI;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.fragments.ExerciseFragment;
import com.example.user.epicfitproject.fragments.StartAndProgress;
import com.example.user.epicfitproject.model.exercise.ActualExercise;
import com.example.user.epicfitproject.model.exercise.ExerciseManager;
import com.example.user.epicfitproject.model.goal.Goal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Start extends AppCompatActivity implements ExerciseFragment.Communicator,StartAndProgress.IChoice{

    private Goal goal;
    static  int indexes;
    Fragment fragment;
    HashMap<String,ActualExercise> helper;
    ArrayList<ActualExercise> exercisesToDo;
    FrameLayout replaceable;

    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        replaceable = (FrameLayout) findViewById(R.id.replace_here);
        String  forName = getSharedPreferences("activeGoal", Context.MODE_PRIVATE).getString("goal","nope");
                if(!forName.equals("nope")) {
                    helper = ExerciseManager.getInstance(Start.this).getExercises();
                    exercisesToDo = new ArrayList<>();
                    for(ActualExercise e :helper.values()){
                        exercisesToDo.add(e);
                    }
        fragment=new StartAndProgress();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.replace_here,fragment);

        ft.commit();


    }

    }

    @Override
    public void chosen() {
        //replace fragment here
        Fragment start = new ExerciseFragment();
        data= new Bundle();
        //data.putParcelable("exercise",exercisesToDo.get(1));
        //tuk zadavam prosto stoinost za da vidq dali raboti
        //trqbva da si predavam samiq obekt
        data.putString("name",exercisesToDo.get(1).getName());
        data.putInt("sets",exercisesToDo.get(1).getSets());
        data.putInt("reps",exercisesToDo.get(1).getRepetitions());
        start.setArguments(data);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.start_and_progress_fragment, start);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void changeWithNext() {
        //update the bundle and start fragment again
    }
}
