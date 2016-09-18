package com.example.user.epicfitproject.model.exercise;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by user on 3.9.2016 г..
 */
public class ActualExercise extends Exercise {
    int repetitions;
    int sets;


    public static final Parcelable.Creator<ActualExercise> CREATOR = new Parcelable.Creator<ActualExercise>() {
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
     picture=in.readInt();
        name=in.readString();
        url=in.readString();
        information=in.readInt();
        repetitions=in.readInt();
        sets=in.readInt();
        Log.e("ivet",name+" /"+url+" /"+information+" /"+repetitions+" /"+sets);

    }

    @Override
    public void writeToParcel(Parcel out, int i) {
       out.writeInt(picture);
        out.writeString(name);
        out.writeString(url);
        out.writeInt(information);
        out.writeInt(repetitions);
        out.writeInt(sets);
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

    public int getSets() {
        return sets;
    }

    public int getRepetitions() {
        return repetitions;
    }
}
