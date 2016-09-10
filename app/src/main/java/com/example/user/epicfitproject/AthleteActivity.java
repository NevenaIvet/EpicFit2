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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AthleteActivity extends AppCompatActivity {
    private TextView heading;
    private TextView username;
    private TextView gender;
    private TextView height;
    private TextView weight;
    private TextView generateBMI;
    private String [] weights;
    private Button next;
    private Button addGoal;
    private ImageView profilePicture;
    private ImageButton changePenUsernameButton;
    private ImageButton changePenPictureButton;
    private Switch genderSwitch;
    private Switch BMISwitch;
    private ArrayList<Double> doubleValues;
    private TextView BMIinfo;

    private NumberPicker pickHeight;
    private NumberPicker pickWeight;
    private String [] heights;
    private Athlete athlete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_athlete);
        heading = (TextView) findViewById(R.id.heading);
        heading.setFocusable(false);
        heading.setClickable(false);
        doubleValues=new ArrayList<>();
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
                    gender.setText("Female");
                }else{
                    gender.setText("Male");
                }
            }
        });

        BMISwitch = (Switch) findViewById(R.id.generateBMISwitch);
        BMISwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    String pattern1="##.0";
                    DecimalFormat dfCal = new DecimalFormat(pattern1);
                    int indexHeight=pickHeight.getValue();
                    int indexWeight=pickWeight.getValue();
                    String heightH=heights[indexHeight];
                    String weightH=weights[indexWeight];
                    double weightD= Double.parseDouble(weightH);
                    double heightD = Double.parseDouble(heightH);
                    String calculated = dfCal.format(calculatorForBMI(heightD,weightD));
                    BMIinfo.setText("Estimated BMI for your measures "+calculated);
                    BMIinfo.setVisibility(View.VISIBLE);

                }else{
                    BMIinfo.setVisibility(View.INVISIBLE);
                }
            }
        });


        pickHeight = (NumberPicker) findViewById(R.id.heightPicker);
        Double helper = new Double(1);
        for(int i = 1;i<=257;i++){
            doubleValues.add(helper);
            helper+=0.01;
        }
        String patternHeight="##.00";
        DecimalFormat decimalFormat = new DecimalFormat(patternHeight);
        heights = new String[doubleValues.size()];
        for(int p = 0 ;p<=doubleValues.size()-1;p++){
            heights[p]=decimalFormat.format(doubleValues.get(p));
        }
        pickHeight.setMinValue(0);
        pickHeight.setMaxValue(heights.length-1);
        pickHeight.setDisplayedValues(heights);
        pickHeight.setWrapSelectorWheel(false);
        pickHeight.setSaveEnabled(true);
        pickHeight.setVerticalScrollBarEnabled(true);
        pickWeight = (NumberPicker) findViewById(R.id.weightPicker);
        doubleValues.clear();
        helper=new Double(20);
        for(int i = 20;i<=5000;i++){
            doubleValues.add(helper);
            helper+=0.1;
        }
        String patternWeight = "###.000";
        DecimalFormat decimalFormat2 = new DecimalFormat(patternWeight);
        weights = new String[doubleValues.size()];
        for(int p = 0 ;p<=doubleValues.size()-1;p++){
            weights[p]=decimalFormat.format(doubleValues.get(p));
        }
        pickWeight.setMinValue(0);
        pickWeight.setMaxValue(weights.length-1);
        pickWeight.setSaveEnabled(true);
        pickWeight.setDisplayedValues(weights);
        View.OnClickListener onClickPicker = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumberPicker nb= (NumberPicker) view;
                if(nb.isSelected()){
                    //TODO when numberpicker is touched BMI field should hide ????
                    BMIinfo.setText(null);
                    BMIinfo.setVisibility(View.INVISIBLE);
                    BMISwitch.setChecked(false);
                }
            }
        };
        pickHeight.setOnClickListener(onClickPicker);
        pickWeight.setOnClickListener(onClickPicker);
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
    private  static double calculatorForBMI(Double height, Double weight){
        return weight/(height*height);
    }

}
