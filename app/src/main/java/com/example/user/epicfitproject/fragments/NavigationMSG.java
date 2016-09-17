package com.example.user.epicfitproject.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.UI.ListOFExercisesActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationMSG extends DialogFragment {
    private TextView instructions;
    private Button gotIt;
    OnGotItTap clicked;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clicked = (OnGotItTap) context;
    }

    public NavigationMSG() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View root = inflater.inflate(R.layout.fragment_nav_msg, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        instructions = (TextView) root.findViewById(R.id.text_sys);
        gotIt = (Button) root.findViewById(R.id.continue_button_sys);

        gotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked.onGotItTapped();

                dismiss();
            }
        });
        return root;
    }

    public void changeText(String s) {
        //tova ne raboti
        if(instructions.getText()!=null)
        instructions.setText(s);
    }

    public interface OnGotItTap{
        void onGotItTapped();
    }

}
