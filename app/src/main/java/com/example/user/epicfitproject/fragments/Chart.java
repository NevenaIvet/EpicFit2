package com.example.user.epicfitproject.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Dimension;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.db.chart.listener.OnEntryClickListener;
import com.db.chart.model.LineSet;
import com.db.chart.model.Point;
import com.db.chart.view.AxisController;
import com.db.chart.view.ChartView;
import com.db.chart.view.LineChartView;
import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.activities.Start;
import com.example.user.epicfitproject.model.exercise.ActualExercise;
import com.example.user.epicfitproject.model.exercise.ExerciseManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Chart extends Fragment {

    IShowChart activity;
    TextView redB;
    LineChartView chart;
    private ArrayList<ActualExercise> exercises ;

    public interface IShowChart{
        void showInfo();
    }
    public Chart() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_chart, container, false);
        chart = (LineChartView) v.findViewById(R.id.linechart);
        exercises = new ArrayList<>();
            String json = this.getActivity().getSharedPreferences("doneExercises", Context.MODE_PRIVATE).getString("done", "nope");
            if (!json.equals("nope")) {
                Log.e("TAG", "ima napraveni uprajneniq");
                try {
                    JSONArray jsonArr = new JSONArray(json);
                    for (int i = 0; i < jsonArr.length(); i++) {
                        JSONObject o = jsonArr.getJSONObject(i);
                        ActualExercise exercise = new ActualExercise(o.getInt("picture"), o.getString("name"), o.getString("url"), o.getInt("information"), o.getInt("repetitions"), o.getInt("sets"));
                        exercises.add(exercise);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("TAG", "napraveni sa -" + exercises.size() + " broq uprajneniq");
                float[] doneExercisesValues = new float[exercises.size()];
                final LineSet doneData = new LineSet();
                final LineSet goal = new LineSet();
                float[] fromGoalValues = new float[ExerciseManager.getInstance(this.getActivity()).getExercises().size()];
                ArrayList<ActualExercise> fromGoal = new ArrayList<ActualExercise>(ExerciseManager.getInstance(this.getActivity()).getExercises().values());
                for (int i = 0; i < exercises.size(); i++) {
                    int sumReps = calculatedAllRepeats(exercises.get(i));
                    doneExercisesValues[i] = sumReps;
                    doneData.addPoint(exercises.get(i).getName(), doneExercisesValues[i]);
                    int sumsOriginalReps = calculatedAllRepeats(fromGoal.get(i));
                    fromGoalValues[i] = sumsOriginalReps;
                    goal.addPoint(exercises.get(i).getName(),fromGoalValues[i]);
                }
                doneData.setDotsColor(Color.BLUE);
                goal.setColor(Color.RED);
                goal.setDotsColor(Color.RED);
                goal.setSmooth(true);
                goal.beginAt(0);
                goal.endAt(exercises.size());
                doneData.setDotsStrokeColor(Color.BLUE);
                doneData.setColor(Color.BLUE);
                doneData.setSmooth(true);
                doneData.beginAt(0);
                doneData.endAt(exercises.size());
                chart.addData(doneData);
                chart.setYAxis(false);
                chart.setYLabels(AxisController.LabelPosition.NONE);
                Paint paint = new Paint();
                paint.setColor(getResources().getColor(R.color.greenFive));
                chart.setGrid(ChartView.GridType.HORIZONTAL, paint);
                chart.setTypeface(Typeface.DEFAULT_BOLD);
                chart.show();
                chart.setClickable(true);
                chart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        chart.addData(goal);
                        chart.show();
                        activity.showInfo();
                    }
                });

            }

        return v;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (IShowChart) context;


    }

    public int calculatedAllRepeats(ActualExercise e){
        int setsDone=e.getSets();
        int repeatsDone = e.getRepetitions();
        return setsDone*repeatsDone;

    }


}
