package com.caltech.natalassistsplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlarmRecyclerViewAdapter extends RecyclerView.Adapter<AlarmRecyclerViewAdapter.ViewHolder> {

    ArrayList<AlarmInfo> alarmInfos;

    public AlarmRecyclerViewAdapter(ArrayList<AlarmInfo> alarmInfos) {
        this.alarmInfos = alarmInfos;
    }

    @NonNull
    @Override
    public AlarmRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View alarmView = inflater.inflate(R.layout.layout_alarm, parent, false);

        return new ViewHolder(alarmView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.bedtime.setText(alarmInfos.get(position).getBedtime());
        holder.sleepHours.setText(alarmInfos.get(position).getSleepHours());
        holder.alarmTime.setText(alarmInfos.get(position).getAlarmTime());
        holder.repeatOn.setText(alarmInfos.get(position).getRepeatOn());
        holder.alarmToggle.setChecked(alarmInfos.get(position).isEnabled());
        holder.deleteAlarm.setOnClickListener(v -> {
            alarmInfos.remove(position);
            notifyItemRemoved(position);
        });
        holder.alarmToggle.setOnCheckedChangeListener((buttonView, isChecked) -> alarmInfos.get(position).isEnabled(isChecked));
    }

    @Override
    public int getItemCount() {
        return alarmInfos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView bedtime;
        public TextView sleepHours;
        public TextView alarmTime;
        public TextView repeatOn;
        public ToggleButton alarmToggle;
        public ImageView deleteAlarm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bedtime = itemView.findViewById(R.id.bedtimeTxtRV);
            sleepHours = itemView.findViewById(R.id.sleephrsTxtRV);
            alarmTime = itemView.findViewById(R.id.alarmTimeTxtRV);
            repeatOn = itemView.findViewById(R.id.repeatAlarmTxtRV);
            alarmToggle = itemView.findViewById(R.id.alarmToggleBtn);
            deleteAlarm = itemView.findViewById(R.id.deleteAlarmRV);
        }
    }

    public void updateData(){
        notifyItemInserted(alarmInfos.size()-1);
    }
}
