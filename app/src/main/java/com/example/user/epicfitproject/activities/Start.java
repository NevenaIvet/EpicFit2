package com.example.user.epicfitproject.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.epicfitproject.fragments.ChartInformation;
import com.example.user.epicfitproject.fragments.Chart;
import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.fragments.UserEntersDataExercise;
import com.example.user.epicfitproject.fragments.ExerciseFragment;
import com.example.user.epicfitproject.fragments.StartAndProgress;
import com.example.user.epicfitproject.model.exercise.ActualExercise;
import com.example.user.epicfitproject.model.exercise.ExerciseManager;
import com.example.user.epicfitproject.model.goal.Goal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Start extends AppCompatActivity implements ExerciseFragment.Communicator,StartAndProgress.IChoice,UserEntersDataExercise.IUserInteract,Chart.IShowChart {


   private  static  int sizeOfList;
    private static int position;
    Fragment fragment;
    Fragment fragment2;
    HashMap<String,ActualExercise> helper;
    ArrayList<ActualExercise> exercisesDone;
    ArrayList<ActualExercise> exercisesToDo;
    FrameLayout replaceable;
    FrameLayout replaceSecondFragment;
    LinearLayout layout ;
    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        layout = (LinearLayout) findViewById(R.id.main_layout);
        replaceable = (FrameLayout) findViewById(R.id.replace_here);
        replaceSecondFragment = (FrameLayout) findViewById(R.id.second_fragment_here);
        String  forName = getSharedPreferences("activeGoal", Context.MODE_PRIVATE).getString("goal","nope");
                if(!forName.equals("nope")) {
                    helper = ExerciseManager.getInstance(Start.this).getExercises();
                    exercisesToDo = new ArrayList<>();

                    for(ActualExercise e :helper.values()){
                        exercisesToDo.add(e);
                    }
                    exercisesDone=new ArrayList<>(exercisesToDo);
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
                if(exercisesToDo.size()>0) {
                    SharedPreferences sp = getSharedPreferences("doneExercises", Context.MODE_PRIVATE);
                    sp.edit().remove("done").commit();
                    Log.e("TAG", "deleted exercises from last workout");
                    fragment = new ExerciseFragment();
                    data = new Bundle();
                    data.putParcelable("exercise", exercisesToDo.get(position));
                    fragment.setArguments(data);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.replace_here, fragment);
                    ft.commit();
                    FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
                    fragment2 = new UserEntersDataExercise();
                    tr.replace(R.id.second_fragment_here, fragment2);
                    tr.commit();
                }else{
                    Toast.makeText(Start.this, "No exercises are available", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.progress_button:
                String json = getSharedPreferences("doneExercises",Context.MODE_PRIVATE).getString("done","nope");
                if(!json.equals("nope")){
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    Start.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    fragment = new Chart();
                    FragmentTransaction fp = getSupportFragmentManager().beginTransaction();
                    fp.replace(R.id.replace_here,fragment);
                    fp.commit();

                }else{
                    Toast.makeText(Start.this,"Can't show progress no exercises done yet",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(exercisesToDo.size()==0){
                    Toast.makeText(Start.this, "No exercises are available", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void changeWithNext() {
        if(position<exercisesToDo.size()){
            data= new Bundle();
            data.putParcelable("exercise",exercisesToDo.get(position));
            fragment =new ExerciseFragment();
            fragment.setArguments(data);
            FragmentTransaction tr= getSupportFragmentManager().beginTransaction();
            tr.replace(R.id.replace_here,fragment);
            fragment2 = new UserEntersDataExercise();
            tr.replace(R.id.second_fragment_here,fragment2);
            tr.commit();
        }else{
            Toast.makeText(Start.this,"no more exercises",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
            recreate();
            //tuk
            Toast.makeText(Start.this, "You can now see your progress", Toast.LENGTH_SHORT).show();
        }
            SharedPreferences sp = getSharedPreferences("doneExercises",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            JSONArray arr = new JSONArray();
            try {
                for (ActualExercise e : exercisesDone) {
                    JSONObject o = new JSONObject();
                    o.put("picture", e.getPicture());
                    o.put("name", e.getName());
                    o.put("url", e.getUrl());
                    o.put("information", e.getInformation());
                    o.put("repetitions", e.getRepetitions());
                    o.put("sets", e.getSets());
                    arr.put(o);
                }
            }catch(JSONException e) {
                Log.e("TAG", e.getMessage());
            }
            String key = "done";
            String value = arr.toString();
            Log.e("TAG", "done exercises for training "+value);
            editor.putString(key,value);
            editor.commit();
        }


    @Override
    public void dataEntered(String s1, String s2) {
      if(!(s1==null||s2==null)){
          exercisesDone.get(position).setRepetitions(Integer.parseInt(s2));
          exercisesDone.get(position).setSets(Integer.parseInt(s1));
          ++position;
          changeWithNext();
      }
    }


    @Override
    public void showInfo() {
        fragment2=new ChartInformation();
        FragmentTransaction fp = getSupportFragmentManager().beginTransaction();
        fp.replace(R.id.second_fragment_here,fragment2);
        fp.commit();

    }
}
