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

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.ViewHolder> {

    ArrayList<FoodMenu> foodMenus;
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView foodTitleTxt;
        public TextView foodShortDescTxt;
        public ImageView foodImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodTitleTxt = itemView.findViewById(R.id.foodTitleRV);
            foodShortDescTxt = itemView.findViewById(R.id.foodDescRV);
            foodImageView = itemView.findViewById(R.id.foodImgRV);
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

    }

    @Override
    public int getItemCount() {
        return foodMenus.size();
    }
}
