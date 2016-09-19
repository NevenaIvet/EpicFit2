package com.example.user.epicfitproject.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.UserManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.model.user.UsersManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeUsernameFragment extends DialogFragment {
    EditText changedUsername;
    Button next;
    IUsernameChanged activity ;
    public interface IUsernameChanged{
        void changedUsername(String name);
    }

    public ChangeUsernameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (IUsernameChanged) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_username, container, false);
        next = (Button) v.findViewById(R.id.save_button_new_username);
        changedUsername = (EditText) v.findViewById(R.id.new_username);


        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newUserN=changedUsername.getText().toString().trim();
                if(newUserN.isEmpty()){
                    changedUsername.setError("Please enter new username");
                    changedUsername.requestFocus();
                    newUserN="";
                    return;
                }

                //TODO update Sharedpreffs after this
                SharedPreferences preffs = getActivity().getSharedPreferences("CurrentlyLogged", Context.MODE_PRIVATE);
                String loggedUserEmail = preffs.getString("loggedUser", "nope");
                if(!loggedUserEmail.equals("nope")){
                    UsersManager.getInstance(getActivity()).getUser(loggedUserEmail).setUsername(newUserN);
                }

                activity.changedUsername(newUserN);
            }
        });
        return v;
    }

}
