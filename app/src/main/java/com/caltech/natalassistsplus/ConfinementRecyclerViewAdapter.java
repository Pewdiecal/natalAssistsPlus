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

public class ConfinementRecyclerViewAdapter extends RecyclerView.Adapter<ConfinementRecyclerViewAdapter.ViewHolder>{

    ArrayList<ConfinementCenter> confinementCenters;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTxt;
        public TextView addressTxt;
        public ImageView img;
        public CardView confinementCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.confinementTitleRV);
            addressTxt = itemView.findViewById(R.id.confinementAddressRV);
            img = itemView.findViewById(R.id.confinementImgRV);
            confinementCardView = itemView.findViewById(R.id.confinementCardView);

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
        holder.confinementCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent;
                switch (position){
                    case 0:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.thesenses.com.my/"));
                        break;
                    case 1:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bestmonth.com.my/"));
                        break;
                    case 2:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/12dynastyconfinement/"));
                        break;
                    case 3:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/LovelyConfinement/"));
                        break;
                    case 4:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/bondacareconfinement/?referrer=whatsapp"));
                        break;
                    case 5:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://rasamalaysia.com/chicken-rendang/"));
                        break;
                    case 6:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/ijconfinementcentrepuchong"));
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
        return confinementCenters.size();
    }
}
