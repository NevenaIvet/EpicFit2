package com.example.user.epicfitproject.fragments;


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

import com.example.user.epicfitproject.R;


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
                final  String setsH = sets.getText().toString().trim();
                final String repsH = reps.getText().toString().trim();
                if(setsH.isEmpty()){
                    sets.setError("Required");
                    sets.requestFocus();
                    return;
                }
                if(repsH.isEmpty()){
                    reps.setError("Required");
                    reps.requestFocus();
                    return;
                }
                if(!(setsH.isEmpty()&&repsH.isEmpty())){
                    //Commas are forbiden in xml but still
                    if(!((setsH.contains(".")&&repsH.contains(".")&&setsH.contains(",")&&repsH.contains(",")))) {
                        int repHelper=0 ;
                        int setHelper=0;
                        try{
                            repHelper = Integer.parseInt(repsH);
                        }catch (NumberFormatException e){
                            repHelper=0;
                            reps.setText(null);
                            reps.setError("Only integers");
                            reps.requestFocus();
                            return;
                        }
                        try{
                            setHelper = Integer.parseInt(setsH);
                        }catch (NumberFormatException e){
                            setHelper=0;
                            sets.setText("0");
                            sets.setError("Only integers");
                            sets.requestFocus();
                            return;
                        }
                        if (repHelper < 0) {
                            sets.setError("Negative values are not acceptable");
                            sets.setText("0");
                            sets.requestFocus();
                            return;
                        }
                        if (setHelper < 0) {
                            reps.setError("Negative values are not acceptable");
                            reps.setText(null);
                            reps.requestFocus();
                            return;
                        }
                        if (repHelper == 0 && setHelper > 0) {
                            reps.setError("Repeats are 0");
                            reps.setText(null);
                            reps.requestFocus();
                            return;
                        }

                        activity.onNextClicked(setHelper,repHelper);

                        dismiss();
                    }
                }


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
