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

public class AdminRecyclerViewAdapter extends RecyclerView.Adapter<AdminRecyclerViewAdapter.ViewHolder> {

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
        switch(position){
            case 0:
                holder.menuTitle.setText("Check Utilisation History");
                holder.menuIcon.setImageResource(R.drawable.ic_checklist);
                holder.menuCard.setOnClickListener(v -> {
                    Intent intent = new Intent(v.getContext(), UtilisationHistoryActivity.class);
                    v.getContext().startActivity(intent);
                });
                break;
            case 1:
                holder.menuTitle.setText("Manage User Account");
                holder.menuIcon.setImageResource(R.drawable.ic_person);
                holder.menuCard.setOnClickListener(v -> {
                    Intent intent = new Intent(v.getContext(), ManageUserAccountActivity.class);
                    v.getContext().startActivity(intent);
                });
                break;
            case 2:
                holder.menuTitle.setText("Monitor Forum");
                holder.menuIcon.setImageResource(R.drawable.ic_correct);
                holder.menuCard.setOnClickListener(v -> {
                    Intent intent = new Intent(v.getContext(), MonitorForumActivity.class);
                    v.getContext().startActivity(intent);
                });
                break;

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
