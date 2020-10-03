package com.caltech.natalassistsplus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PatientListRecyclerViewAdapter extends RecyclerView.Adapter<PatientListRecyclerViewAdapter.ViewHolder>{

    ArrayList<Patient> patients;
    int toActivity;
    String doctorUsername;

    public PatientListRecyclerViewAdapter(ArrayList<Patient> patients, int toActivity, String doctorUsername){
        this.patients = patients;
        this.toActivity = toActivity;
        this.doctorUsername = doctorUsername;
    }

    @NonNull
    @Override
    public PatientListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View patientListView = inflater.inflate(R.layout.layout_patient_list, parent, false);

        return new ViewHolder(patientListView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientListRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.patientName.setText(patients.get(position).getUsername());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)v.getContext()).finish();
                switch (toActivity){
                    case 0:
                        Intent intentUpdateHealth = new Intent(v.getContext(), UpdateHealthActivity.class);
                        intentUpdateHealth.putExtra("Username", patients.get(position).getUsername());
                        intentUpdateHealth.putExtra("DoctorUsername", doctorUsername);
                        intentUpdateHealth.putExtra("DocID", patients.get(position).getDocID());
                        v.getContext().startActivity(intentUpdateHealth);
                        break;
                    case 1:
                        Intent intentMedicalReport = new Intent(v.getContext(), MedicalReportActivity.class);
                        intentMedicalReport.putExtra("Username", patients.get(position).getUsername());
                        intentMedicalReport.putExtra("DoctorUsername", doctorUsername);
                        intentMedicalReport.putExtra("DocID", patients.get(position).getDocID());
                        v.getContext().startActivity(intentMedicalReport);
                        break;
                    case 2:
                        Intent intentMedicalHistory = new Intent(v.getContext(), MedicalHistoryActivity.class);
                        intentMedicalHistory.putExtra("Username", patients.get(position).getUsername());
                        intentMedicalHistory.putExtra("DoctorUsername", doctorUsername);
                        intentMedicalHistory.putExtra("DocID", patients.get(position).getDocID());
                        v.getContext().startActivity(intentMedicalHistory);
                        break;
                    case 3:
                        Intent intentCheckupSchedule = new Intent(v.getContext(), UpcomingScheduleActivity.class);
                        intentCheckupSchedule.putExtra("Username", patients.get(position).getUsername());
                        intentCheckupSchedule.putExtra("DoctorUsername", doctorUsername);
                        intentCheckupSchedule.putExtra("DocID", patients.get(position).getDocID());
                        v.getContext().startActivity(intentCheckupSchedule);
                        break;

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView patientName;
        public CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.patientListTxt);
            cardView = itemView.findViewById(R.id.patientListCardView);
        }
    }
}
