package com.example.user.epicfitproject.model.exercise;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 6.9.2016 г..
 */
    public  class Exercise implements IUpperBody,ILowerBody,IHiit,ICardio,Comparable<Exercise>,Parcelable {

    private int picture;
    private String name;
    private String url;
    private int information;




    public Exercise(int picture, String name, String url, int information) {
        this.picture = picture;
        this.name = name;
        this.url = url;
        this.information = information;
    }

    public int getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getInformation() {
        return information;
    }

    @Override
    public int compareTo(Exercise exercise) {
        return this.getName().compareTo(exercise.getName());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}


