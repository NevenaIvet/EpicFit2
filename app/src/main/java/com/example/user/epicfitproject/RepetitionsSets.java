package com.example.user.epicfitproject;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.epicfitproject.UI.ExerciseActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class RepetitionsSets extends DialogFragment {
    private EditText sets;
    private EditText reps;
    private Button next;
    private Button cancel;
    OnDoneListener activity ;
    private String set,rep;

    public RepetitionsSets() {
        // Required empty public constructor
    }



    public interface OnDoneListener{
        public void onNextClicked(int s ,int p);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (OnDoneListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View view = inflater.inflate(R.layout.fragment_repetitions_sets, container, false);
        sets= (EditText) view.findViewById(R.id.sets_count_TF);
        reps = (EditText) view.findViewById(R.id.repetitions_count_TF);
        next = (Button) view.findViewById(R.id.next_button);
        cancel= (Button) view.findViewById(R.id.cancel_button);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String setsF=sets.getText().toString();
                int setsH=Integer.parseInt(setsF);

                String repsF=reps.getText().toString();
                int repsH=Integer.parseInt(repsF);
                if(setsH<1||setsF.isEmpty()){
                    sets.setError("Not valid");
                    sets.setText("1");
                    setsF="0";
                    sets.requestFocus();
                    return;
                }
                if(repsF.isEmpty()||repsH<1){
                    reps.setError("Not valid");
                    reps.setText("1");
                    repsF="0";
                    reps.requestFocus();
                    return;
                }

                activity.onNextClicked(setsH,repsH);
                dismiss();
            }

        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sets.setText(null);
                reps.setText(null);
                dismiss();

            }
        });

        return view;
    }


}
