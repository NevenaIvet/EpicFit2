package com.example.user.epicfitproject.model.exercise;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 3.9.2016 г..
 */
public class ActualExercise extends Exercise{
    int repetitions;
    int sets;


    public static final Parcelable.Creator<Exercise> CREATOR = new Parcelable.Creator<Exercise>() {
        public ActualExercise createFromParcel(Parcel in) {

            return new ActualExercise(in);
        }

        public ActualExercise[] newArray(int size) {
            return new ActualExercise[size];
        }
    };

    public ActualExercise(int picture, String name, String url, int information, int repetitions, int sets) {
        super(picture, name, url, information);
        this.repetitions = repetitions;
        this.sets = sets;
    }

    public ActualExercise(Parcel in) {
        super(in);
        repetitions=in.readInt();
        sets=in.readInt();

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
