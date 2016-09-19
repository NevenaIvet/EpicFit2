package com.example.user.epicfitproject.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.epicfitproject.DateDialog;
import com.example.user.epicfitproject.fragments.NavigationMSG;
import com.example.user.epicfitproject.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;


public class GoalActivity extends AppCompatActivity  {
    private Button start;
    private Button next;
    LinearLayout l ;
    private Button end;
    private TextView startDateTV;
    private TextView endDateTV;
    private static  final int GOAL_UPDATED=55;
    private static  final int EXERCISES_ADDED = 66;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        startDateTV = (TextView) findViewById(R.id.dates_save_field);
        endDateTV = (TextView) findViewById(R.id.dates_save_field2);
        l = (LinearLayout) findViewById(R.id.placeforfragment);
        start= (Button) findViewById(R.id.button_from);
        end = (Button) findViewById(R.id.button_to);
        next = (Button) findViewById(R.id.button_addExercise);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DateDialog d = new DateDialog();
//                Bundle bundle = new Bundle();
//                bundle.putString("type", "start");
//                d.setArguments(bundle);
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.add(R.id.placeforfragment, new DateDialog());
//                fragmentTransaction.commit();

            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(22);
                Intent intent = new Intent(GoalActivity.this, ActivitiesActivity.class);
                intent.putExtra("endDate",endDateTV.getText().toString());
                intent.putExtra("startDate",startDateTV.getText().toString());
                SharedPreferences sp = getSharedPreferences("activeGoal", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                JSONObject obj=new JSONObject();
                try {
                    obj.put("startDate",startDateTV.getText().toString());
                    obj.put("endDate",endDateTV.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                editor.putString("goal",obj.toString());
                Log.e("ivet",obj.toString());
                editor.commit();
                startActivityForResult(intent,66);

            }
        });


    }


}


