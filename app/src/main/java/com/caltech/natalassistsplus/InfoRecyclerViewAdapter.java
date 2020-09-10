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

public class InfoRecyclerViewAdapter extends RecyclerView.Adapter<InfoRecyclerViewAdapter.ViewHolder> {

    ArrayList<Information> info;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView infoTitleTxt;
        public TextView infoDescTxt;
        public ImageView infoImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            infoTitleTxt = itemView.findViewById(R.id.infoTitleRV);
            infoDescTxt = itemView.findViewById(R.id.infoDescRV);
            infoImg = itemView.findViewById(R.id.infoImgRV);
        }
    }

    public InfoRecyclerViewAdapter(ArrayList<Information> info){
        this.info = info;
    }

    @NonNull
    @Override
    public InfoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View infoView = inflater.inflate(R.layout.layout_info, parent, false);

        return new ViewHolder(infoView);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoRecyclerViewAdapter.ViewHolder holder, int position) {
        TextView infoTitle = holder.infoTitleTxt;
        TextView infoDesc = holder.infoDescTxt;
        ImageView infoImg = holder.infoImg;

        infoTitle.setText(info.get(position).getInfoTitile());
        infoDesc.setText(info.get(position).getInfoShortDesc());
        infoImg.setImageResource(info.get(position).getInfoBgPic());
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

}
