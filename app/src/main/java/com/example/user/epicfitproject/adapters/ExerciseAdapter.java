package com.example.user.epicfitproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.activities.ExerciseActivity;
import com.example.user.epicfitproject.model.exercise.Exercise;

import java.util.List;

/**
 * Created by User on 13-Sep-16.
 */
public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.MyViewHolder> {
    private List<Exercise> exercises ;
    private Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView information;
        LinearLayout listRow;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name_exercise);
            information = (TextView) view.findViewById(R.id.info_exercise);
            listRow = (LinearLayout) view.findViewById(R.id.list_row);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercises_list_row, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Exercise exercise = exercises.get(position);
        holder.name.setText(exercise.getName());
        holder.listRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(activity,ExerciseActivity.class);
                 intent.putExtra("exercise",exercise);
                activity.startActivity(intent);
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public ExerciseAdapter (Context context, List<Exercise> exercises){
        this.exercises = exercises;
        this.activity = (Activity) context;
    }
}
