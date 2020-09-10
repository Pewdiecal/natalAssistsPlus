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

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleRecyclerViewAdapter.ViewHolder> {

    ArrayList<Schedule> schedules;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView scheduleTitleTxt;
        public TextView scheduleDateTxt;
        public ImageView scheduleImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            scheduleTitleTxt = itemView.findViewById(R.id.scheduleTitleRV);
            scheduleDateTxt = itemView.findViewById(R.id.scheduleDateRV);
            scheduleImg = itemView.findViewById(R.id.scheduleImgRV);
        }
    }

    public ScheduleRecyclerViewAdapter(ArrayList<Schedule> schedules){
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View scheduleView = inflater.inflate(R.layout.layout_schedule, parent, false);

        return new ViewHolder(scheduleView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView scheduleTitle = holder.scheduleTitleTxt;
        TextView scheduleDate = holder.scheduleDateTxt;
        ImageView scheduleImg = holder.scheduleImg;

        scheduleTitle.setText(schedules.get(position).getScheduleName());
        scheduleDate.setText(schedules.get(position).getDate());
        scheduleImg.setImageResource(schedules.get(position).getPicDesc());
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }


}
