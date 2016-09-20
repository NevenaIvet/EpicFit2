package com.example.user.epicfitproject;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Dimension;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.db.chart.model.LineSet;
import com.db.chart.model.Point;
import com.db.chart.view.AxisController;
import com.db.chart.view.ChartView;
import com.db.chart.view.LineChartView;
import com.example.user.epicfitproject.model.exercise.ActualExercise;
import com.example.user.epicfitproject.model.exercise.ExerciseManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Chart extends Fragment {

    IShowChart activity;
    LineChartView chart;
    private ArrayList<ActualExercise> exercises ;

    public interface IShowChart{
        void showChart();
    }

    public Chart() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_chart, container, false);
        chart = (LineChartView) v.findViewById(R.id.linechart);
        exercises = new ArrayList<>();
        String json = this.getActivity().getSharedPreferences("doneExercises",Context.MODE_PRIVATE).getString("done","nope");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        try {
            JSONArray jsonArr = new JSONArray(json);
            for(int i = 0; i < jsonArr.length(); i++){
                JSONObject o = jsonArr.getJSONObject(i);
                ActualExercise exercise = new ActualExercise(o.getInt("picture"),o.getString("name"),o.getString("url"),o.getInt("information"),o.getInt("repetitions"),o.getInt("sets"));
                exercises.add(exercise);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String[] namesExercises = new String[exercises.size()];
       for(int i=0;i<exercises.size();i++){
           namesExercises[i]=exercises.get(i).getName();
       }
        float[] doneExercisesValues = new float[exercises.size()];
        for(int i=0;i<exercises.size();i++){
            int sumReps=calculatedAllRepeats(exercises.get(i));
            doneExercisesValues[i]=sumReps;
        }
        LineSet doneData = new LineSet(namesExercises, doneExercisesValues);
        for(int i = 0 ; i<exercises.size() ;i++){
            //doneData.addPoint(new Point(namesExercises[i], doneExercisesValues[i]));
            doneData.addPoint(namesExercises[i],doneExercisesValues[i]);
        }
        doneData.setDotsColor(Color.GREEN);
        doneData.setDotsStrokeColor(Color.GREEN);
        doneData.setColor(Color.GREEN);
        doneData.setSmooth(true);
        doneData.beginAt(0);
        doneData.endAt(exercises.size());
        chart.addData(doneData);
        ArrayList<ActualExercise> exercisesInGoal=new ArrayList<>(ExerciseManager.getInstance(this.getActivity()).getExercises().values()) ;
        float[] valuesInOriginal = new float[exercisesInGoal.size()];
        for(int i=0;i<exercisesInGoal.size();i++){
            int sumReps=calculatedAllRepeats(exercisesInGoal.get(i));
            valuesInOriginal[i]=sumReps;
        }
        LineSet goalData = new LineSet(namesExercises, valuesInOriginal);
        for(int i = 0 ; i<exercises.size() ;i++){
           // goalData.addPoint(new Point(namesExercises[i], valuesInOriginal[i]));
            goalData.addPoint(namesExercises[i],valuesInOriginal[i]);
        }
        for(int i = 0;i<doneExercisesValues.length;i++){
            if(doneExercisesValues[i]==valuesInOriginal[i]){
                Toast.makeText(this.getActivity(),"Goal completed", Toast.LENGTH_SHORT).show();
            }
        }
        goalData.setDotsColor(Color.RED);
        goalData.setColor(Color.RED);
        goalData.setDotsStrokeColor(Color.RED);
        float[] dashesForGoalLine = new float[4];
        for (int i = 0;i<dashesForGoalLine.length;i++){
            dashesForGoalLine[i]= (float) 5.5;
        }
        goalData.setDashed(dashesForGoalLine);
        goalData.beginAt(0);
        //tuk da ne e exercise siz
        goalData.endAt(exercisesInGoal.size());
        chart.addData(goalData);
        chart.setYAxis(false);
        chart.setYLabels(AxisController.LabelPosition.NONE);

        chart.show();
        //tuka ima mn neshta

        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (IShowChart) context;
    }

    private int calculatedAllRepeats(ActualExercise e){
        int setsDone=e.getSets();
        int repeatsDone = e.getRepetitions();
        return setsDone*repeatsDone;

    }


}
