package com.caltech.natalassistsplus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MedicAdminHomeRecyclerViewAdapter extends RecyclerView.Adapter<MedicAdminHomeRecyclerViewAdapter.ViewHolder> {

    String username;
    String docID;

    public MedicAdminHomeRecyclerViewAdapter(String username, String docID){
        this.username = username;
        this.docID = docID;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View menuView = inflater.inflate(R.layout.layout_medical_menu, parent, false);

        return new ViewHolder(menuView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (position){
            case 0:
                holder.menuTitle.setText("Update Health Status");
                holder.menuIcon.setImageResource(R.drawable.ic_refresh);
                holder.menuCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), patientListActivity.class);
                        intent.putExtra("Username", username);
                        intent.putExtra("DocID", docID);
                        intent.putExtra("ToActivity", 0);
                        v.getContext().startActivity(intent);
                    }
                });
                break;
            case 1:
                holder.menuTitle.setText("Medical report history");
                holder.menuIcon.setImageResource(R.drawable.ic_analysis);
                holder.menuCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), patientListActivity.class);
                        intent.putExtra("Username", username);
                        intent.putExtra("DocID", docID);
                        intent.putExtra("ToActivity", 1);
                        v.getContext().startActivity(intent);
                    }
                });
                break;
            case 2:
                holder.menuTitle.setText("Medical history");
                holder.menuIcon.setImageResource(R.drawable.ic_pills);
                holder.menuCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), patientListActivity.class);
                        intent.putExtra("Username", username);
                        intent.putExtra("DocID", docID);
                        intent.putExtra("ToActivity", 2);
                        v.getContext().startActivity(intent);
                    }
                });
                break;
            case 3:
                holder.menuTitle.setText("Checkup Schedules");
                holder.menuIcon.setImageResource(R.drawable.ic_calendar);
                holder.menuCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), patientListActivity.class);
                        intent.putExtra("Username", username);
                        intent.putExtra("DocID", docID);
                        intent.putExtra("ToActivity", 3);
                        v.getContext().startActivity(intent);
                    }
                });
                break;
            case 4:
                holder.menuTitle.setText("Add new patient");
                holder.menuIcon.setImageResource(R.drawable.ic_plus_patient);
                holder.menuCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), AddNewPatientActivity.class);
                        intent.putExtra("DoctorUsername", username);
                        intent.putExtra("DocID", docID);
                        intent.putExtra("ToActivity", 4);
                        v.getContext().startActivity(intent);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView menuTitle;
        public ImageView menuIcon;
        public CardView menuCard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuIcon = itemView.findViewById(R.id.medicalMenuIconRV);
            menuTitle = itemView.findViewById(R.id.medicalMenuTitleTxtRV);
            menuCard = itemView.findViewById(R.id.menuCardView);
        }
    }
}
