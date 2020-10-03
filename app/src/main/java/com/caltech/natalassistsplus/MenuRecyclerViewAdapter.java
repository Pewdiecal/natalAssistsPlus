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

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.ViewHolder> {

    ArrayList<FoodMenu> foodMenus;
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView foodTitleTxt;
        public TextView foodShortDescTxt;
        public ImageView foodImageView;
        public CardView foodCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodTitleTxt = itemView.findViewById(R.id.foodTitleRV);
            foodShortDescTxt = itemView.findViewById(R.id.foodDescRV);
            foodImageView = itemView.findViewById(R.id.foodImgRV);
            foodCardView = itemView.findViewById(R.id.foodCardView);
        }
    }

    public MenuRecyclerViewAdapter(ArrayList<FoodMenu> foodMenus){
        this.foodMenus = foodMenus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View foodView = inflater.inflate(R.layout.layout_food, parent,false);

        return new ViewHolder(foodView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TextView foodTitle = holder.foodTitleTxt;
        TextView foodShortDesc = holder.foodShortDescTxt;
        ImageView foodImg = holder.foodImageView;

        foodTitle.setText(foodMenus.get(position).getFoodName());
        foodShortDesc.setText(foodMenus.get(position).getFoodShortDesc());
        foodImg.setImageResource(foodMenus.get(position).getFoodPicture());
        holder.foodCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent;
                switch (position){
                    case 0:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.indianhealthyrecipes.com/chilli-chicken-dry-recipe-indo-chinese-style/"));
                        break;
                    case 1:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cafedelites.com/butter-chicken/"));
                        break;
                    case 2:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://tasty.co/recipe/hainanese-chicken-rice"));
                        break;
                    case 3:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.thechunkychef.com/family-favorite-baked-mac-and-cheese/"));
                        break;
                    case 4:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bonappetit.com/recipe/nasi-lemak"));
                        break;
                    case 5:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://rasamalaysia.com/chicken-rendang/"));
                        break;
                    case 6:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pinchofyum.com/soft-scrambled-eggs"));
                        break;
                    case 7:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dinneratthezoo.com/new-york-strip-steak/"));
                        break;
                    case 8:
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cookieandkate.com/thai-red-curry-recipe/"));
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
        return foodMenus.size();
    }
}
