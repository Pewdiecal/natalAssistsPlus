package com.caltech.natalassistsplus;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InfoRecyclerViewAdapter extends RecyclerView.Adapter<InfoRecyclerViewAdapter.ViewHolder> {

    ArrayList<Information> info;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView infoTitleTxt;
        public TextView infoDescTxt;
        public ImageView infoImg;
        public CardView infoCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            infoTitleTxt = itemView.findViewById(R.id.infoTitleRV);
            infoDescTxt = itemView.findViewById(R.id.infoDescRV);
            infoImg = itemView.findViewById(R.id.infoImgRV);
            infoCardView = itemView.findViewById(R.id.infoCardView);
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
        holder.infoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent;
                switch (position){
                    case 0:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.womenshealthmag.com/fitness/"));
                        break;
                    case 1:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.healthline.com/nutrition/weight-loss-for-women"));
                        break;
                    case 2:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cancer.org/latest-news/how-to-get-more-sleep.html"));
                        break;
                    case 3:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://thenationshealth.aphapublications.org/content/41/2/20"));
                        break;
                    default:
                        browserIntent = null;
                }

                PendingIntent pendingIntent = PendingIntent.getActivity(v.getContext(), 0, browserIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

}
