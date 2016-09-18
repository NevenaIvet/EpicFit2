package com.example.user.epicfitproject.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.model.exercise.ActualExercise;
import com.example.user.epicfitproject.model.exercise.ExerciseManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseFragment extends Fragment {
    Activity activity;
    private TextView nameOFExercise;
    private TextView numberOFSets;
    private TextView numberOfReps;

    public interface Communicator{
        public void changeWithNext();
    }

    public ExerciseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity= (Activity) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_exercise, container, false);
        nameOFExercise = (TextView) view.findViewById(R.id.fragment_exercise_name_tv);
        numberOfReps = (TextView) view.findViewById(R.id.number_of_reps_tv);
        numberOFSets = (TextView) view.findViewById(R.id.number_of_sets_tv);

        if(getArguments().getParcelable("exercise")!=null){
            ActualExercise e = getArguments().getParcelable("exercise");
            nameOFExercise.setText(e.getName());
            numberOfReps.setText(Integer.toString(e.getRepetitions()));
            numberOFSets.setText(Integer.toString(e.getSets()));
        }


        return view;
    }

}
