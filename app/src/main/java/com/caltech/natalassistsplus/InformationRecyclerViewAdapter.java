package com.caltech.natalassistsplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InformationRecyclerViewAdapter extends RecyclerView.Adapter<InformationRecyclerViewAdapter.ViewHolder> {

    ArrayList<Information> informations;

    public InformationRecyclerViewAdapter(ArrayList<Information> informations) {
        this.informations = informations;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView infoCat;
        public TextView infoDesc;
        public ImageView infoBg;
        public ImageView infoIcon;
        public Button exploreBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            infoCat = itemView.findViewById(R.id.infocategoryRV);
            infoDesc = itemView.findViewById(R.id.infoDescRV);
            infoBg = itemView.findViewById(R.id.infoBgImgRV);
            infoIcon = itemView.findViewById(R.id.infoIconRV);
            exploreBtn = itemView.findViewById(R.id.exploreBtnRV);
        }
    }

    @NonNull
    @Override
    public InformationRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View infoView = inflater.inflate(R.layout.layout_information, parent, false);

        return new ViewHolder(infoView);
    }

    @Override
    public void onBindViewHolder(@NonNull InformationRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.infoCat.setText(informations.get(position).getInfoCategory());
        holder.infoDesc.setText(informations.get(position).getInfoShortDesc());
        holder.infoBg.setImageResource(informations.get(position).getInfoBgPic());
        holder.infoIcon.setImageResource(informations.get(position).getInfoSmallPic());
        holder.exploreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return informations.size();
    }
}
