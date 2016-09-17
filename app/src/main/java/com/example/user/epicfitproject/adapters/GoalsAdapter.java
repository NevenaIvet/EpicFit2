package com.example.user.epicfitproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.model.goal.Goal;

import java.util.List;

/**
 * Created by User on 16-Sep-16.
 */
public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.GoalViewHolder> {
    private List<Goal> goals;
    private Activity activity;

    public GoalsAdapter(Context context, List<Goal> goals){
        this.goals =goals;
        activity= (Activity) context;
    }

    public class GoalViewHolder extends RecyclerView.ViewHolder{
        public TextView nameOfGoal;
        LinearLayout listRow;
        public GoalViewHolder(View itemView) {
            super(itemView);
            nameOfGoal = (TextView) itemView.findViewById(R.id.active_goal);
            listRow = (LinearLayout) itemView.findViewById(R.id.layout_active_goal);
            Log.e("ivet","suzdava se goalviewholder ");

        }
    }
    @Override
    public GoalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.active_goals_row, parent, false);
        return new GoalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GoalViewHolder holder, int position) {
        final Goal goal = goals.get(position);
       //TODO
        holder.nameOfGoal.setText("Goal for period "+goal.getStartDate()+" to "+goal.getEndDate());
        holder.listRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity,"open to see",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return goals.size();
    }
}
