package com.example.user.epicfitproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.user.epicfitproject.model.goal.Goal;

import java.util.ArrayList;
import java.util.List;

public class ActiveGoals extends AppCompatActivity {
    List<Goal> goals=  new ArrayList<>();
    private RecyclerView recyclerView;
    private GoalsAdapter exerciseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_goals);
        //TODO add adapter

    }
}
