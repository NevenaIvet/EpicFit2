package com.example.user.epicfitproject.model.exercise;

/**
 * Created by user on 6.9.2016 г..
 */
    public  class Exercise implements IUpperBody,ILowerBody,IHiit,ICardio {
        private int picture;
        private String name;
        private String url;
        private String information;

    public Exercise(String name){
        this.name=name;
    }
    public Exercise(String name,int picture,String information){
        this.picture = picture;
        this.name = name;
        this.information = information;
    }
    public Exercise(String name,String information){
        this.name = name;
        this.information = information;
    }


    public Exercise(int picture, String name, String url, String information) {
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

    public String getInformation() {
        return information;
    }
}


