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
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v= inflater.inflate(R.layout.fragment_chart, container, false);

        chart = (LineChartView) v.findViewById(R.id.linechart);
        //proverka dali ne e e json-a po podrabirane
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
        String[] labels = new String[exercises.size()];
       for(int i=0;i<exercises.size();i++){
           labels[i]=exercises.get(i).getName();
       }
        float[] values = new float[exercises.size()];
        for(int i=0;i<exercises.size();i++){
            int sumReps=calculatedAllRepeats(exercises.get(i));
            values[i]=sumReps;
        }

        LineSet dataset = new LineSet(labels, values);
        for(int i = 0 ; i<exercises.size() ;i++){
            dataset.addPoint(new Point(labels[i], values[i]));
        }

        dataset.setDotsColor(Color.GREEN);
        dataset.setDotsStrokeColor(Color.GREEN);
        dataset.setColor(Color.GREEN);
        dataset.setSmooth(true);
        dataset.beginAt(0);
        dataset.endAt(exercises.size());
        chart.addData(dataset);
        ArrayList<ActualExercise> original=new ArrayList<>(ExerciseManager.getInstance(this.getActivity()).getExercises().values()) ;
        float[] valuesInOriginal = new float[original.size()];
        for(int i=0;i<original.size();i++){
            int sumReps=calculatedAllRepeats(original.get(i));
            valuesInOriginal[i]=sumReps;
        }
        LineSet dataset2 = new LineSet(labels, valuesInOriginal);
        for(int i = 0 ; i<exercises.size() ;i++){
            dataset2.addPoint(new Point(labels[i], valuesInOriginal[i]));
        }
        for(int i = 0;i<values.length;i++){
            if(values[i]==valuesInOriginal[i]){
                Toast.makeText(this.getActivity(),"Goal completed", Toast.LENGTH_SHORT).show();
            }
        }
        dataset2.setDotsColor(Color.RED);
        dataset2.setColor(Color.RED);
        dataset2.setDotsStrokeColor(Color.RED);
        float[] dashes = new float[4];
        for (int i = 0;i<dashes.length;i++){
            dashes[i]= (float) 5.5;
        }
        dataset2.setDashed(dashes);
        dataset2.beginAt(0);
        //5 ili size
        dataset2.endAt(original.size());
        chart.addData(dataset2);
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
