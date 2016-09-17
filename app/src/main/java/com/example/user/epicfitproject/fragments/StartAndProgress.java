package com.example.user.epicfitproject.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.epicfitproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartAndProgress extends Fragment {
IChoice activity;
    Button start;
    Button progress;

    public interface IChoice{
        public void chosen();
    }
    public StartAndProgress() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (IChoice) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_start_and_progress, container, false);
        start = (Button) v.findViewById(R.id.begin_workout);
        progress = (Button) v.findViewById(R.id.progress_button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.chosen();
            }
        });
        return v;
    }

}
