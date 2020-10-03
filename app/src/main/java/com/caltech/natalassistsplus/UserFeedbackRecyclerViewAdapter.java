package com.caltech.natalassistsplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserFeedbackRecyclerViewAdapter extends RecyclerView.Adapter<UserFeedbackRecyclerViewAdapter.ViewHolder> {

    private ArrayList<UserFeedback> userFeedbacks;

    public UserFeedbackRecyclerViewAdapter(ArrayList<UserFeedback> userFeedbacks){
        this.userFeedbacks = userFeedbacks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View menuView = inflater.inflate(R.layout.layout_user_feedback, parent, false);

        return new ViewHolder(menuView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.feedbackMsg.setText(userFeedbacks.get(position).FeedbackMessage);
        holder.ratingBar.setRating(userFeedbacks.get(position).Ratings);
    }

    @Override
    public int getItemCount() {
        return userFeedbacks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView feedbackMsg;
        public RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            feedbackMsg = itemView.findViewById(R.id.userFeedbackTxt);
            ratingBar = itemView.findViewById(R.id.userFeedbackRatingBar);
        }
    }
}
