package com.example.user.epicfitproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

import com.example.user.epicfitproject.athlete.Athlete;

public class AthleteActivity extends AppCompatActivity {
    private TextView heading;
    private TextView username;
    private TextView gender;
    private TextView height;
    private TextView weight;
    private TextView metricSystem;
    private TextView generateBMI;
    private Button next;
    private Button addGoal;
    private ImageView profilePicture;
    private ImageButton changePenUsernameButton;
    private ImageButton changePenPictureButton;
    private Switch genderSwitch;
    private Switch BMISwitch;
    TextView BMIinfo;
    private Switch metricSystemSwitch;
    private NumberPicker pickHeight;
    private NumberPicker pickWeight;
    private Athlete athlete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_athlete);
        heading = (TextView) findViewById(R.id.heading);
        heading.setFocusable(false);
        heading.setClickable(false);

        username = (TextView) findViewById(R.id.usernameText);
        username.setText(getIntent().getStringExtra("LoggedUser"));
        username.setFocusable(false);
        username.setClickable(false);

        gender = (TextView) findViewById(R.id.genderText);
        gender.setFocusable(false);
        gender.setClickable(false);
    BMIinfo = (TextView) findViewById(R.id.BMIcalculatedText);
        height = (TextView) findViewById(R.id.heightText);
        height.setFocusable(false);
        height.setClickable(false);

        weight = (TextView) findViewById(R.id.weightText);
        weight.setFocusable(false);
        weight.setClickable(false);

        metricSystem = (TextView) findViewById(R.id.metricSystemText);
        metricSystem.setFocusable(false);
        metricSystem.setClickable(false);

        generateBMI = (TextView) findViewById(R.id.generateBMIText);
        generateBMI.setFocusable(false);
        generateBMI.setClickable(false);

        next = (Button) findViewById(R.id.nextActionButton);
        addGoal = (Button) findViewById(R.id.addGoalButton);
        changePenPictureButton = (ImageButton) findViewById(R.id.changeProfilePictureButton);
        changePenUsernameButton = (ImageButton) findViewById(R.id.changeUsernameButton);

        profilePicture = (ImageView) findViewById(R.id.profilePicture);

        genderSwitch= (Switch) findViewById(R.id.genderSwitch);
        genderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    gender.setText("Female"); // setApplicationContext ?
                }else{
                    gender.setText("Male");
                }
            }
        });
        metricSystemSwitch = (Switch) findViewById(R.id.metricSystemSwitch);
        metricSystemSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    metricSystem.setText("Metric system");
                }else{
                    metricSystem.setText("Imperial metric system");
                }
            }
        });
        BMISwitch = (Switch) findViewById(R.id.generateBMISwitch);
        BMISwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //TODO fix BMI function and parseDouble

                    BMIinfo.setVisibility(View.VISIBLE);


                }else{
                    BMIinfo.setVisibility(View.INVISIBLE);
                }
            }
        });


        pickHeight = (NumberPicker) findViewById(R.id.heightPicker);
        pickHeight.setMinValue(100);
        pickHeight.setMaxValue(250);
        pickHeight.setWrapSelectorWheel(false);//ne znam kakvo pravi tova
        pickHeight.setSaveEnabled(true);//tova ne e qsno dali zapazva
        pickHeight.setVerticalScrollBarEnabled(true);
        pickWeight = (NumberPicker) findViewById(R.id.weightPicker);

        pickWeight.setMinValue(1);
        pickWeight.setMaxValue(500);// tova sa 500 kg no v lbs
        pickWeight.setSaveEnabled(true);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameAthlete  = username.getText().toString();
                String gender ;
                if(genderSwitch.isChecked()){
                    gender="female";
                }else{
                    gender="male";
                }
             double weight=   pickWeight.getValue();
                int height = pickHeight.getValue();

                athlete = new Athlete(nameAthlete,R.drawable.user96,height,weight,gender);

                Intent intent = new Intent(AthleteActivity.this,MainMenuActivity.class);
                //intent.putExtra("athlete",athlete);
                startActivity(intent);
            }
        });
        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AthleteActivity.this, GoalActivity.class);
                startActivity(intent);
            }
        });

    }

}
