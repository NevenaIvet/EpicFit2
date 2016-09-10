package com.example.user.epicfitproject.exercise;

import java.io.File;

/**
 * Created by user on 6.9.2016 г..
 */
    public abstract class Exercise implements IUpperBody,ILowerBody,IHiit,ICardio {
        private int picture;
        private int repetitions=0;
        private int sets=0;

        public Exercise(int picture, int repetitions, int sets) {
            this.picture = picture;
            this.repetitions=repetitions;
            this.sets=sets;
        }


    public int getRepetitions() {
        return repetitions;
    }

    public int getSets() {
        return sets;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }
}


