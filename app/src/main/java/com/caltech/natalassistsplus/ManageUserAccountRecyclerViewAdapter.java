package com.caltech.natalassistsplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManageUserAccountRecyclerViewAdapter extends RecyclerView.Adapter<ManageUserAccountRecyclerViewAdapter.ViewHolder> {

    ArrayList<UserAccount> userAccounts;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ManageUserAccountRecyclerViewAdapter(ArrayList<UserAccount> userAccounts){
        this.userAccounts = userAccounts;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View menuView = inflater.inflate(R.layout.layout_manager_user_account, parent, false);

        return new ViewHolder(menuView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String reqStr = "Username : " + userAccounts.get(position).username + "\n" +
                "User requests : " + userAccounts.get(position).recoveryStatus;
        holder.userDetailsTxt.setText(reqStr);
        holder.resetPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> updatePassword = new HashMap<>();
                updatePassword.put("Password", "0000");
                db.collection("users").document(userAccounts.get(position).docID)
                        .update(updatePassword)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(v.getContext(), "Password reset successfully.", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
        holder.removeAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("users").document(userAccounts.get(position).docID)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(v.getContext(), "User account removed successfully.", Toast.LENGTH_LONG).show();
                                userAccounts.remove(position);
                                notifyDataSetChanged();
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return userAccounts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userDetailsTxt;
        public Button resetPwdBtn;
        public Button removeAccBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userDetailsTxt = itemView.findViewById(R.id.userDetailTxt);
            resetPwdBtn = itemView.findViewById(R.id.resetPwdBtn);
            removeAccBtn = itemView.findViewById(R.id.removeAccBtn);
        }
    }

}
