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

public class ContractionRecyclerViewAdapter extends RecyclerView.Adapter<ContractionRecyclerViewAdapter.ViewHolder>{

    ArrayList<ContractionCount> contractionCounts;

    public ContractionRecyclerViewAdapter(ArrayList<ContractionCount> contractionCounts) {
        this.contractionCounts = contractionCounts;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView startTime;
        public TextView duration;
        public ImageView cancel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            startTime = itemView.findViewById(R.id.startTimeContractionRV);
            duration = itemView.findViewById(R.id.durationContractionRV);
            cancel = itemView.findViewById(R.id.cancelContractionRV);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contractionView = inflater.inflate(R.layout.layout_contraction, parent, false);

        return new ViewHolder(contractionView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.startTime.setText("Start time : " + contractionCounts.get(position).getStartTime());
        holder.duration.setText("Duration : " + contractionCounts.get(position).getDuration());
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contractionCounts.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contractionCounts.size();
    }

    public void updateData(){
        notifyItemInserted(contractionCounts.size()-1);
    }
}
