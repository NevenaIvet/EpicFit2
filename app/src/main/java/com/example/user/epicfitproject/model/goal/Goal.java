package com.example.user.epicfitproject.model.goal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.user.epicfitproject.model.exercise.ActualExercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by user on 3.9.2016 г..
 */
public class Goal{


    private String startDate;
    private String endDate;
    private List<ActualExercise> exercises=new ArrayList<>();

    //priemam string zashtoto ot view shte e po -lesno da se vzeeme
    public Goal(String startDate,String endDate,List<ActualExercise> exercises){

        this.exercises=exercises;
        this.startDate=startDate;
        this.endDate=endDate;
    }

    //adding exercise to our challenge program
    public void addExercise(ActualExercise e){
        this.exercises.add(e);
    }

    public void removeExercise(ActualExercise e){
        if(!exercises.isEmpty()){
            if(exercises.contains(e)){
                for(Iterator<ActualExercise> it = exercises.iterator(); it.hasNext();){
                    ActualExercise toRemove = it.next();
                    if(toRemove.equalsExercise(e)){
                        it.remove();
                        break;
                    }
                }
            }
        }
    }
    public void changeRepetitions(ActualExercise e , int reps){
        for(Iterator<ActualExercise> it = exercises.iterator(); it.hasNext();){
            ActualExercise toChange = it.next();
            if(toChange.equalsExercise(e)){
                toChange.setRepetitions(reps);
            }

        }
    }
    public void changeSets(ActualExercise e , int sets){
        for(Iterator<ActualExercise> it = exercises.iterator(); it.hasNext();){
            ActualExercise toChange = it.next();
            if(toChange.equalsExercise(e)){
                toChange.setSets(sets);
            }

        }
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public List<ActualExercise> getExercises() {
        return exercises;
    }


}
