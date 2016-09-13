package com.example.user.epicfitproject.model.athlete;

import android.util.Log;

/**
 * Created by User on 06-Sep-16.
 */
public class Athlete {

    private String userName;
    private int picture;
    private String gender;
    private double height;
    private double weight;



    public Athlete( int picture, double height, double weight,String gender) {

        this.picture = picture;
        this.height = height;
        this.weight = weight;
        this.gender=gender;
        Log.e("Athlete","we are saving athlete");
    }



    public int getPicture() {
        return picture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
