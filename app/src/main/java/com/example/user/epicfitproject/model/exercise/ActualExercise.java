package com.example.user.epicfitproject.model.exercise;

/**
 * Created by user on 3.9.2016 г..
 */
public class ActualExercise extends Exercise{
    int repetitions;
    int sets;

    public ActualExercise(int picture, String name, String url, int information, int repetitions, int sets) {
        super(picture, name, url, information);
        this.repetitions = repetitions;
        this.sets = sets;
    }

    public boolean equalsExercise(ActualExercise e) {
        if(this.getName().equals(e.getName())){
            return true;
        }
        return false;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }
}
