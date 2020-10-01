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

public class BabyKickRecyclerViewAdapter extends RecyclerView.Adapter<BabyKickRecyclerViewAdapter.ViewHolder>{

    ArrayList<BabyKickCount> babyKickCounts;

    public BabyKickRecyclerViewAdapter(ArrayList<BabyKickCount> babyKickCounts) {
        this.babyKickCounts = babyKickCounts;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView startTime;
        public TextView duration;
        public TextView kickNum;
        public ImageView cancel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            startTime = itemView.findViewById(R.id.startTimeBabyKickTxtRV);
            duration = itemView.findViewById(R.id.durationTxtBabyKickRV);
            kickNum = itemView.findViewById(R.id.kickNumTxtBabyKickRV);
            cancel = itemView.findViewById(R.id.cancelBabyKickRV);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View babyKickView = inflater.inflate(R.layout.layout_baby_kick, parent, false);

        return new ViewHolder(babyKickView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.startTime.setText("Start time : " + babyKickCounts.get(position).getStartTime());
        holder.duration.setText("Duration : " + babyKickCounts.get(position).getDuration());
        holder.kickNum.setText("Number of kick : " + babyKickCounts.get(position).getNumOfKicks());
        holder.cancel.setOnClickListener(v -> {
            babyKickCounts.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return babyKickCounts.size();
    }

}
