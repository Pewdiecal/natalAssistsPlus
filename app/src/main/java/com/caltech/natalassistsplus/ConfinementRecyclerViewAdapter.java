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

public class ConfinementRecyclerViewAdapter extends RecyclerView.Adapter<ConfinementRecyclerViewAdapter.ViewHolder>{

    ArrayList<ConfinementCenter> confinementCenters;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTxt;
        public TextView addressTxt;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.confinementTitleRV);
            addressTxt = itemView.findViewById(R.id.confinementAddressRV);
            img = itemView.findViewById(R.id.confinementImgRV);

        }
    }

    public ConfinementRecyclerViewAdapter(ArrayList<ConfinementCenter> confinement){
        this.confinementCenters = confinement;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View confinementView = inflater.inflate(R.layout.layout_confinement, parent, false);

        return new ViewHolder(confinementView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView title = holder.titleTxt;
        TextView address = holder.addressTxt;
        ImageView img = holder.img;

        title.setText(confinementCenters.get(position).getTitle());
        address.setText(confinementCenters.get(position).getAddress());
        img.setImageResource(confinementCenters.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return confinementCenters.size();
    }
}
