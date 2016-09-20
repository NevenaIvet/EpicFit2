package com.example.user.epicfitproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.epicfitproject.fragments.DateDialog;
import com.example.user.epicfitproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;


public class GoalActivity extends AppCompatActivity  {
    private Button start;
    private Button next;
    private Button end;
    private TextView startDateTV;
    private Date startDateObj;
    private Date endDateObj;
    private TextView endDateTV;
    private static  final int GOAL_UPDATED=55;
    private static  final int EXERCISES_ADDED = 66;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        startDateTV = (TextView) findViewById(R.id.dates_save_field);
        endDateTV = (TextView) findViewById(R.id.dates_save_field2);
        start= (Button) findViewById(R.id.button_from);
        end = (Button) findViewById(R.id.button_to);
        next = (Button) findViewById(R.id.button_addExercise);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateDialog d = new DateDialog();
                Bundle bundle = new Bundle();
                bundle.putString("type", "start");
                d.setArguments(bundle);
                d.show(GoalActivity.this.getFragmentManager(),"startDate");
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateDialog dialog = new DateDialog();
                Bundle bundle = new Bundle();
                bundle.putString("type", "end");
                dialog.setArguments(bundle);
                dialog.show(getFragmentManager(), "endDate");
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startDateObj.after(endDateObj)||endDateObj.before(Calendar.getInstance().getTime())||startDateObj.before(Calendar.getInstance().getTime())){
                    startDateTV.setText(null);
                    endDateTV.setText(null);
                    Toast.makeText(GoalActivity.this, "Incompatible dates", Toast.LENGTH_SHORT).show();
                    return;
                }

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
                Log.e("TAG",obj.toString());
                editor.commit();
                startActivityForResult(intent,66);
            }
        });

    }
    public void dateSet(String type, Date date) {
        if(type.equals("start")){
            startDateObj=date;
            startDateTV.setText(date.toString());
        }
        else
        if(type.equals("end")){
            endDateObj=date;
            endDateTV.setText(date.toString());
        }
    }

}


