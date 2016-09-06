package com.example.user.epicfitproject.goal;

import com.example.user.epicfitproject.exercise.ActualExercise;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.datatype.Duration;

/**
 * Created by user on 3.9.2016 г..
 */
public class Goal {

    //defining the start date of our challenge
    private Date startDate;
    private Duration durationOfGoal;
    private List<ActualExercise> exercises;

    //when we define a start date in our activity we must set it to or variable startDate
    public void setStartDate(Date startDate) {


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
    public void changeRepetitions(ActualExercise e ,int reps){
        for(Iterator<ActualExercise> it = exercises.iterator();it.hasNext();){
            ActualExercise toChange = it.next();
            if(toChange.equalsExercise(e)){
                toChange.setRepetitions(reps);
            }

        }
    }
    public void changeSets(ActualExercise e ,int sets){
        for(Iterator<ActualExercise> it = exercises.iterator();it.hasNext();){
            ActualExercise toChange = it.next();
            if(toChange.equalsExercise(e)){
                toChange.setSets(sets);
            }

        }
    }
}
