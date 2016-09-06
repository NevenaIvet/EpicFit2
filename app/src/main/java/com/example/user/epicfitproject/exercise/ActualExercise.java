package com.example.user.epicfitproject.exercise;

/**
 * Created by user on 3.9.2016 г..
 */
public class ActualExercise extends Exercise{
    private String name;
    private String info;//??
    private int timeForCompletion;

    public ActualExercise(String name,int timeForCompletion,int picture, int repetitions, int sets){
        super(picture,repetitions,sets);
        this.name = name;
        this.timeForCompletion = timeForCompletion;
    }

    public void setName(String name) {
        if(!name.isEmpty() && name != null)
            this.name = name;
    }
    public void setTimeForCompletion(int timeForCompletion) {
        if(timeForCompletion>0)
            this.timeForCompletion = timeForCompletion;
    }
    public boolean equalsExercise(ActualExercise e){
        if(this.name.equals(e.getName())&&this.getRepetitions()==e.getRepetitions()&&this.getSets()==e.getSets())
            return true;
        return false;
    }


    public String getName() {
        return name;
    }
}
