package com.example.user.epicfitproject.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.activities.Start;


public class UserEntersDataExercise extends Fragment {
    private EditText sets;
    private EditText reps;
    IUserInteract activity;
    private Button next;

    public interface IUserInteract{
        void dataEntered(String s1,String s2);
    }


    public UserEntersDataExercise() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_enters_data_exercise, container, false);
        sets = (EditText) v.findViewById(R.id.number_of_sets_user_enters);
        reps = (EditText) v.findViewById(R.id.number_of_reps_user_enters);
        next= (Button) v.findViewById(R.id.show_next_exercise);
        String setsH = sets.getText().toString();
        String repsH = reps.getText().toString();

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

                        activity.dataEntered(setsH, repsH);
                       }
                    }

            }
        });
        return v ;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (IUserInteract) context;

    }


}
