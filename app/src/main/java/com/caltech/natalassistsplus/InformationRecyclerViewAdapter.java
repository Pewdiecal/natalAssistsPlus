package com.caltech.natalassistsplus;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
                Intent browserIntent;
                switch (position){
                    case 0:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.healthline.com/nutrition/healthy-eating-tips"));
                        break;
                    case 1:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.whattoexpect.com/first-year/baby-care/"));
                        break;
                    case 2:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.glamour.com/gallery/maternity-fashion"));
                        break;
                    case 3:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jordangrayconsulting.com/how-to-give-a-woman-a-sensual-massage/"));
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
        return informations.size();
    }
}
