package com.example.user.epicfitproject.model.exercise;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by User on 17-Sep-16.
 */
public class ExerciseManager {
    private static ExerciseManager ourInstance ;
    HashMap<String,ActualExercise> exercises;

    public static ExerciseManager getInstance(Activity activity) {
        if(ourInstance==null){
            return new ExerciseManager(activity);
        }
        return ourInstance;
    }

    private ExerciseManager(Activity activity  ) {
        this.exercises=new HashMap<>();
        String json = activity.getSharedPreferences("exercisesGoal",Context.MODE_PRIVATE).getString("exercisesInGoal","nope");
        Log.e("ivet",json);
        try {
            JSONArray arr = new JSONArray(json);
            for (int i =0 ; i < arr.length();i++){
                JSONObject o =arr.getJSONObject(i);
                ActualExercise e = new ActualExercise(o.getInt("picture"),
                        o.getString("name"),o.getString("url"),o.getInt("information"),o.getInt("repetitions"),o.getInt("sets"));
                exercises.put(e.getName(),e);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public boolean existsExercise(String name){
        return exercises.containsKey(name);
    }
    public ActualExercise getActualExercise(String name){
        return exercises.get(name);
    }


    public void addExercise(Activity activity, int picture, String name, String url, int information, int repetitions, int sets){
        ActualExercise exercise = new ActualExercise(picture,name,url,information,repetitions,sets);
        exercises.put(name,exercise);
        SharedPreferences preffs = activity.getSharedPreferences("exercisesGoal", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preffs.edit();
        String key = "exercisesInGoal";
        JSONArray exercisesInG = new JSONArray();
        try {
            for (ActualExercise e : exercises.values()) {
                JSONObject o = new JSONObject();
                o.put("picture", e.getPicture());
                o.put("name", e.getName());
                o.put("url", e.getUrl());
                o.put("information", e.getInformation());
                o.put("repetitions", e.getRepetitions());
                o.put("sets", e.getSets());
                exercisesInG.put(o);
            }
        }catch(JSONException e) {

            Log.e("ivet", e.getMessage());
        }
        String value = exercisesInG.toString();
        Log.e("ivet", value);
        editor.putString(key,value);
        editor.commit();
    }

    public HashMap<String, ActualExercise> getExercises() {
        return exercises;
    }
}
