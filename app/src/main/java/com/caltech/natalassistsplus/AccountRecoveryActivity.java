package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class AccountRecoveryActivity extends AppCompatActivity {
TextInputLayout usernamerecoverInputLayout;
EditText usernamerecoverEdt;
Button confirmbtn;
Button backbtn;
ProgressBar progressBar;
FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_recovery);

        usernamerecoverInputLayout = findViewById(R.id.usernamerecoverInputLayout);
        usernamerecoverEdt = findViewById(R.id.usernamerecoverEdt);
        confirmbtn = findViewById(R.id.confirmbtn);
        backbtn = findViewById(R.id.backbtn);
        progressBar = findViewById(R.id.accountRecoveryProgressBar);

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernamerecoverEdt.getText().toString().isEmpty()){
                    usernamerecoverInputLayout.setError("Username cannot be empty");
                } else {
                    verifyData(usernamerecoverEdt.getText().toString());
                    progressBar.setVisibility(View.VISIBLE);
                    confirmbtn.setEnabled(false);
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void verifyData(String username){
        db.collection("users")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("Document data", document.getId() + " => " + document.getData());
                    }

                    if(task.getResult().size() == 0){
                        Toast.makeText(AccountRecoveryActivity.this, "Username not found", Toast.LENGTH_LONG).show();
                    }

                    for(int i = 0; i < task.getResult().size(); i++){
                        if(task.getResult().getDocuments().get(i).getString("Username").equals(username)){
                            uploadData(task.getResult().getDocuments().get(i).getId());
                            break;
                        }

                        if(i == task.getResult().size() - 1){
                            Toast.makeText(AccountRecoveryActivity.this, "Username not found", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Log.w("Firebase Err", "Error getting documents.", task.getException());
                }
            }
        });
    }

    private void uploadData(String docID){
        Map<String, Object> recoveryStatus = new HashMap<>();
        recoveryStatus.put("RecoveryStatus", "In Progress");

        db.collection("users").document(docID)
                .update(recoveryStatus).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AccountRecoveryActivity.this, "Account recovery request sent", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Log.w("Firebase Err", "Error getting documents.", task.getException());
                }
            }
        });
    }
}