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

public class MedicalReportRecyclerViewAdapter extends RecyclerView.Adapter<MedicalReportRecyclerViewAdapter.ViewHolder>{

    ArrayList<MedicalReport> medicalReports;

    public MedicalReportRecyclerViewAdapter(ArrayList<MedicalReport> medicalReports) {
        this.medicalReports = medicalReports;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View medicalReportView = inflater.inflate(R.layout.layout_medical_report, parent, false);

        return new ViewHolder(medicalReportView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.reportTitle.setText(medicalReports.get(position).reportTitle);
        holder.reportDate.setText(medicalReports.get(position).reportDate);
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return medicalReports.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView reportTitle;
        public TextView reportDate;
        public ImageView share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reportTitle = itemView.findViewById(R.id.medicHistoryTitleTxtRV);
            reportDate = itemView.findViewById(R.id.medicalDateTxtRV);
            share = itemView.findViewById(R.id.shareMedicalReportRV);
        }
    }

}
