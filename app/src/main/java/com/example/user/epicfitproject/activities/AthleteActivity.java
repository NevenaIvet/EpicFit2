package com.example.user.epicfitproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.fragments.ChangeUsernameFragment;
import com.example.user.epicfitproject.model.user.UsersManager;
import com.example.user.epicfitproject.model.athlete.Athlete;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class AthleteActivity extends AppCompatActivity implements ChangeUsernameFragment.IUsernameChanged {
    private TextView heading;
    private TextView username;
    private TextView gender;
    private TextView height;
    private TextView weight;
    private TextView generateBMI;
    private String [] weights;
    private Button logout;
    private Button addGoal;
    private ImageView profilePicture;
    private Switch genderSwitch;
    private Switch BMISwitch;
    private ArrayList<Double> doubleValues;
    private TextView BMIinfo;
    private NumberPicker pickHeight;
    private NumberPicker pickWeight;
    private String [] heights;
    private static final int PICK_IMAGE =55;
    private Athlete athlete;
    private static final int RESULT_GOAL_TIME_ADDED=22;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete);
        genderSwitch= (Switch) findViewById(R.id.genderSwitch);
        gender = (TextView) findViewById(R.id.genderText);
        gender.setFocusable(false);
        gender.setClickable(false);

        heading = (TextView) findViewById(R.id.heading);
        heading.setFocusable(false);
        heading.setClickable(false);
        doubleValues=new ArrayList<>();
        username = (TextView) findViewById(R.id.usernameText);

        SharedPreferences preffs = getSharedPreferences("CurrentlyLogged", Context.MODE_PRIVATE);
        String loggedUserEmail = preffs.getString("loggedUser", "nope");
        if(!loggedUserEmail.equals("nope") || UsersManager.getInstance(this).existsUser(loggedUserEmail)){
            username.setText(UsersManager.getInstance(this).getUser(loggedUserEmail).getUsername());
        }
        SharedPreferences preferences = getSharedPreferences("athleteLogged", Context.MODE_PRIVATE);
        String athleteH = preferences.getString("athlete","nope");
        if(!athleteH.equals("nope")){
            //Ako e lognat mu zapazi neshtata
            //genderSwitch.setChecked();
            String json = this.getSharedPreferences("athlete", Context.MODE_PRIVATE).getString("athlete", "nope");

            JSONObject obj = null;
            try {
                obj = new JSONObject(json);
                Athlete aT = new Athlete(obj.getInt("picture"),obj.getDouble("weight"),obj.getDouble("height"),obj.getString("gender"));
                if(aT.getGender().equals("male")){
                    genderSwitch.setChecked(false);
                    gender.setText("Male");
                }else{
                    genderSwitch.setChecked(true);
                    gender.setText("Female");
                }
                //TODO tuk oshte da se promenqt vsichki neshta pikerite da im se sloji ot zapamet
            } catch (JSONException e1) {
                e1.printStackTrace();
            }







        }



        username.setFocusable(false);
        username.setClickable(false);

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
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tuk maha tikcheto na Logina i trqbva pak da se vpishesh ....
            }
        });
        addGoal = (Button) findViewById(R.id.addGoalButton);
        profilePicture = (ImageView) findViewById(R.id.profilePicture);
        profilePicture.setBackgroundResource(R.drawable.fit);

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
                    BMIinfo.setVisibility(View.GONE);
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
        pickHeight.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
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
        pickWeight.setOnLongPressUpdateInterval(100000000);
        pickWeight.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
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
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AthleteActivity.this,LogInActivity.class);
                SharedPreferences preferences = getSharedPreferences("CurrentlyLogged",Context.MODE_PRIVATE);
                preferences.edit().remove("loggedUser").commit();
                startActivity(intent);
                finish();

            }
        });
        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nameAthlete  = username.getText().toString();
                String gender ;
                if(genderSwitch.isChecked()){
                    gender="female";
                }else{
                    gender="male";
                }
                int indexHeight=pickHeight.getValue();
                int indexWeight=pickWeight.getValue();
                String heightH=heights[indexHeight];
                String weightH=weights[indexWeight];
                double weightD= Double.parseDouble(weightH);
                double heightD = Double.parseDouble(heightH);

               athlete = new Athlete(R.mipmap.ic_launcher,heightD,weightD,gender);
                SharedPreferences preferences = getSharedPreferences("athleteLogged", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                JSONObject obj = new JSONObject();
                try {
                    //iskam da svurja tekushtiq lognat potrebitel sus suzdadeniq atlet i ne staa
                    //String email = getIntent().getDataString();

                    //obj.put("email",email);
                    obj.put("picture",athlete.getPicture());
                    obj.put("height",athlete.getHeight());
                    obj.put("weight",athlete.getWeight());
                    obj.put("gender",athlete.getGender());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String value = obj.toString();
                Log.e("JSON",value);

                editor.putString("athlete",value);
                editor.commit();

                Intent intent = new Intent(AthleteActivity.this, GoalActivity.class);
                startActivityForResult(intent,RESULT_GOAL_TIME_ADDED);
            }
        });

    }
    private  static double calculatorForBMI(Double height, Double weight){
        return weight/(height*height);
    }
    private void openGallery() {
        Intent gallery =
                new Intent();
        gallery.addCategory(Intent. ACTION_GET_CONTENT);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == PICK_IMAGE) {
            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.profilePicture);
                imageView.setImageBitmap(bitmap);
                //TODO zapazi s novata kartinka

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (resultCode==RESULT_GOAL_TIME_ADDED){
            finish();
        }
    }

    @Override
    public void changedUsername(String name) {
        username.setText(name);
    }
}
