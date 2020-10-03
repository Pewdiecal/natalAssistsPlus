package com.caltech.natalassistsplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ForumRecyclerViewAdapter extends RecyclerView.Adapter<ForumRecyclerViewAdapter.ViewHolder> {

    ArrayList<ForumPost> forumPosts;
    boolean isAdmin;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView postTime;
        public TextView postDesc;
        public ImageView postImg;
        public ImageView userProfileImg;
        public ImageView removePost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.usernameForumRV);
            postTime = itemView.findViewById(R.id.forumTimeRV);
            postDesc = itemView.findViewById(R.id.forumDescRV);
            postImg = itemView.findViewById(R.id.forumPicRV);
            userProfileImg = itemView.findViewById(R.id.userImgForumRV);
            removePost = itemView.findViewById(R.id.removeForumBtn);

        }
    }

    public ForumRecyclerViewAdapter(ArrayList<ForumPost> forumPosts){
        this.forumPosts = forumPosts;
    }

    public ForumRecyclerViewAdapter(ArrayList<ForumPost> forumPosts, boolean isAdmin){
        this.forumPosts = forumPosts;
        this.isAdmin = isAdmin;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View forumView = inflater.inflate(R.layout.layout_forum, parent, false);

        return new ViewHolder(forumView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView username = holder.username;
        TextView postTime = holder.postTime;
        TextView postDesc = holder.postDesc;
        ImageView postImg = holder.postImg;
        ImageView userProfilePic = holder.userProfileImg;
        if(isAdmin){
            holder.removePost.setVisibility(View.VISIBLE);
        }

        username.setText(forumPosts.get(position).getUsername());
        postTime.setText(forumPosts.get(position).getPostTime());
        postDesc.setText(forumPosts.get(position).getPostDesc());
        if(forumPosts.get(position).getPostImg() == 0){
            postImg.setVisibility(View.GONE);
        } else{
            postImg.setImageResource(forumPosts.get(position).getPostImg());
        }
        userProfilePic.setImageResource(forumPosts.get(position).getUserProfileImg());

        holder.removePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("forumPosts").document(forumPosts.get(position).getDocID())
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                forumPosts.remove(position);
                                Toast.makeText(v.getContext(), "Post removed successfully.", Toast.LENGTH_LONG).show();
                                notifyDataSetChanged();
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return forumPosts.size();
    }
}
