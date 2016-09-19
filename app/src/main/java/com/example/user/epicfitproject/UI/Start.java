package com.example.user.epicfitproject.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.epicfitproject.Chart;
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

public class Start extends AppCompatActivity implements ExerciseFragment.Communicator,StartAndProgress.IChoice,UserEntersDataExercise.IUserInteract,Chart.IShowChart{

    private Goal goal;
   private  static  int sizeOfList;
    private static int position;
    Fragment fragment;
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
                    ft.addToBackStack(null);

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
                String json = getSharedPreferences("doneExercises",Context.MODE_PRIVATE).getString("done","nope");
                if(!json.equals("nope")){
//                    layout.setBackgroundColor(Color.LTGRAY);
                    Fragment f = new Chart();
                    FragmentTransaction fp = getSupportFragmentManager().beginTransaction();
                    fp.replace(R.id.replace_here,f);
                    fp.addToBackStack(null);
                    fp.commit();
                }else{
                    Toast.makeText(Start.this,"Can't show progress no exercises done yet",Toast.LENGTH_SHORT).show();
                }

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
        if(position<exercisesToDo.size()){
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
            //save in shared
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

                Log.e("ivet", e.getMessage());
            }
            String key = "done";
            String value = arr.toString();
            Log.e("ivet", "NAPRAVENI UPRAJNENIQ ZA TRENIROVKA "+value);
            editor.putString(key,value);
            editor.commit();
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
        exercisesDone.get(position).setRepetitions(Integer.parseInt(saveReps));
        exercisesDone.get(position).setSets(Integer.parseInt(saveSets));
        ++position;
        changeWithNext();


    }

    @Override
    public void showChart() {
        //
    }
}
