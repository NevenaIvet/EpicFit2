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
import com.example.user.epicfitproject.UserEntersDataExercise;
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

public class Start extends AppCompatActivity implements ExerciseFragment.Communicator,StartAndProgress.IChoice,UserEntersDataExercise.IUserInteract{

    private Goal goal;
   private  static  int sizeOfList;
    private static int position;
    Fragment fragment;
    HashMap<String,ActualExercise> helper;
    ArrayList<ActualExercise> exercisesToDo;
    FrameLayout replaceable;
    FrameLayout replaceSecondFragment;


    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        replaceable = (FrameLayout) findViewById(R.id.replace_here);
        replaceSecondFragment = (FrameLayout) findViewById(R.id.second_fragment_here);
        String  forName = getSharedPreferences("activeGoal", Context.MODE_PRIVATE).getString("goal","nope");
                if(!forName.equals("nope")) {
                    helper = ExerciseManager.getInstance(Start.this).getExercises();
                    exercisesToDo = new ArrayList<>();
                    for(ActualExercise e :helper.values()){
                        exercisesToDo.add(e);
                    }
                    sizeOfList = exercisesToDo.size()-1;
                    position=0;
                      fragment=new StartAndProgress();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.replace_here,fragment);

        ft.commit();


         }

    }



    @Override
    public void chosen(View v) {

        switch (v.getId()){
            case R.id.begin_workout:
                Fragment start = new ExerciseFragment();
                data= new Bundle();
                data.putParcelable("exercise",exercisesToDo.get(position));
                start.setArguments(data);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.replace_here, start);
                ft.addToBackStack(null);
                ft.commit();
                FragmentTransaction tr= getSupportFragmentManager().beginTransaction();
                tr.replace(R.id.second_fragment_here,new UserEntersDataExercise());
                tr.addToBackStack(null);
                tr.commit();
                break;
            case R.id.progress_button:
                //tuk she vikam progresa ...
                break;
        }


        //data.putParcelable("exercise",exercisesToDo.get(1));
        //tuk zadavam prosto stoinost za da vidq dali raboti
        //trqbva da si predavam samiq obekt

    }

    @Override
    public void changeWithNext() {
        //update the bundle and start fragment again
        //ststichna za goleminata na lista -1
        //statichna za pazene na tekushtata mi poziciq
        if(position<=exercisesToDo.size()){
            data= new Bundle();
            data.putParcelable("exercise",exercisesToDo.get(position));
            Fragment next =new ExerciseFragment();
            next.setArguments(data);
            FragmentTransaction tr= getSupportFragmentManager().beginTransaction();
            tr.replace(R.id.replace_here,next);
            tr.addToBackStack(null);
            tr.commit();
        }else{
            Toast.makeText(Start.this,"no more exercises",Toast.LENGTH_SHORT).show();
        }

        //ako ima oshte neshta za pokazvane
        //else dai nekuv fragment sus suobshtenie
    }

    @Override
    public void dataEntered(String s1, String s2) {
        //sravni s reps i sets na taq poziciq na koqto beshe izvikan fragmenta
        //tuk she e changeWithNext
        String saveSets= s1;
        String saveReps=s2;
        ++position;
        changeWithNext();


    }
}
