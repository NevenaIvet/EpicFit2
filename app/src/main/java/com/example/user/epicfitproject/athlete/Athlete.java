package com.example.user.epicfitproject.athlete;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by User on 06-Sep-16.
 */
public class Athlete implements Serializable {

    private String userName;
    private int picture;
    private String gender;
    private int height;
    private double weight;

    public   Athlete getInstance(){
        Log.e("athlete"," v get instance sme");
        return this;
        //validacii mai ?
    }

    public Athlete(String userName, int picture, int height, double weight,String gender) {
        this.userName = userName;
        this.picture = picture;
        this.height = height;
        this.weight = weight;
        this.gender=gender;
        Log.e("Athlete","we are saving athlete");
    }

    public String getUserName() {
        return userName;
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

    public int getHeight() {
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
