package com.caltech.natalassistsplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UpcomingScheduleRecyclerViewAdapter extends RecyclerView.Adapter<UpcomingScheduleRecyclerViewAdapter.ViewHolder> {

    ArrayList<Schedule> schedules;

    public UpcomingScheduleRecyclerViewAdapter(ArrayList<Schedule> schedules){
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public UpcomingScheduleRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View scheduleView = inflater.inflate(R.layout.layout_upcoming_schedule, parent, false);

        return new ViewHolder(scheduleView);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingScheduleRecyclerViewAdapter.ViewHolder holder, int position) {
        String scheduleStr = schedules.get(position).getScheduleName() + "\n" +
                "Date : " + schedules.get(position).getDate() + "\n" +
                "Time : " + schedules.get(position).getTime() + "\n" +
                "Location : " + schedules.get(position).getLocation();

        holder.textView.setText(scheduleStr);
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView locationBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.upcomingScheduleTxt);
            locationBtn = itemView.findViewById(R.id.upcomingScheduleLocateBtn);
        }
    }


}
