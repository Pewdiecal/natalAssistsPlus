package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class MotherSetupActivity extends AppCompatActivity {

    TextInputLayout fatherUserNameInputLayout;
    TextInputLayout fatherEmailInputLayout;
    TextInputEditText partnerUserNameEdt;
    TextInputEditText partnerEmailEdt;
    TextView setupTitle;
    Button finishbtn;
    Button skipBtn;
    ProgressBar progressBar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_setup);

        String username = getIntent().getStringExtra("Username");
        String email = getIntent().getStringExtra("Email");
        String role = getIntent().getStringExtra("Role");
        String pwd = getIntent().getStringExtra("Password");

        fatherEmailInputLayout = findViewById(R.id.fatherEmailInputLayout);
        fatherUserNameInputLayout = findViewById(R.id.fatherUserNameInputLayout);
        partnerUserNameEdt = findViewById(R.id.fatherUsernameEdt);
        partnerEmailEdt = findViewById(R.id.fatherEmailEdt);
        finishbtn = findViewById(R.id.finishBtn);
        skipBtn = findViewById(R.id.skipBtn);
        setupTitle = findViewById(R.id.titleMotherSetupTxt);
        progressBar = findViewById(R.id.motherSetupProgressBar);

        if(role.equals("Father")){
            setupTitle.setText("Link with mother account");
            fatherEmailInputLayout.setHint("Mother's email");
            fatherUserNameInputLayout.setHint("Mother's username");
        } else {
            setupTitle.setText("Link with father account");
            fatherEmailInputLayout.setHint("Father's email");
            fatherUserNameInputLayout.setHint("Father's username");
        }

        skipBtn.setOnClickListener(v -> {

            uploadData(username, pwd, email, role, null, null);
            progressBar.setVisibility(View.VISIBLE);
            skipBtn.setEnabled(false);
            finishbtn.setEnabled(false);
        });

        finishbtn.setOnClickListener(v -> {
            if(inputIsCorrect()){
                verifyLinkage(username, pwd, email, partnerEmailEdt.getText().toString(), partnerUserNameEdt.getText().toString(),
                        role);
                progressBar.setVisibility(View.VISIBLE);
                skipBtn.setEnabled(false);
                finishbtn.setEnabled(false);
            }
        });
    }

    private void verifyLinkage(String username, String pwd, String email, String partnerEmail, String partnerUsername, String role) {
        db.collection("users")
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("User", document.getId() + " => " + document.getData());

                        }
                        if(task.getResult().size() == 0){
                            Toast.makeText(MotherSetupActivity.this, "No matching user found, linking failed.", Toast.LENGTH_LONG).show();
                        }

                        for(int i = 0; i < task.getResult().size(); i++){
                            if(task.getResult().getDocuments().get(i).getString("Username").equals(partnerUsername) &&
                                    task.getResult().getDocuments().get(i).getString("Email").equals(partnerEmail)){

                                uploadData(username, pwd, email, role, partnerUsername,
                                        task.getResult().getDocuments().get(i).getId());

                                break;
                            }

                            if(i == task.getResult().size() - 1){
                                Toast.makeText(MotherSetupActivity.this, "No matching user found, linking failed.", Toast.LENGTH_LONG).show();
                            }
                        }

                    } else {
                        Log.w("Error", "Error getting documents.", task.getException());
                    }
                });
    }

    private void uploadData(String username, String pwd, String email, String role, String partnerUsername, String documentID){
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("Username", username);
        user.put("Password", pwd);
        user.put("Email", email);
        user.put("Role", role);
        if(role.equals("Father")){
            user.put("MotherLink", partnerUsername);
        } else {
            user.put("FatherLink", partnerUsername);
        }

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Document ID", "DocumentSnapshot added with ID: " + documentReference.getId());
                        completeSignUp(documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Error", "Error adding document", e);
                    }
                });

        Map<String, Object> updateUser = new HashMap<>();
        if(role.equals("Father")){
            updateUser.put("FatherLink", username);
        } else {
            updateUser.put("MotherLink", username);
        }
        if(documentID != null){
            db.collection("users").document(documentID).update(updateUser);
        }

    }

    private void completeSignUp(String documentID){

        Intent intent = new Intent(this, MainContentActivity.class);
        intent.putExtra("Document ID", documentID);
        startActivity(intent);
        finish();
    }

    private boolean inputIsCorrect(){
        if(partnerUserNameEdt.getText().toString().isEmpty()){
            fatherUserNameInputLayout.setError("Username cannot be empty");
        } else {
            fatherUserNameInputLayout.setError(null);
        }

        if(partnerEmailEdt.getText().toString().isEmpty()){
            fatherEmailInputLayout.setError("Email cannot be empty");
        } else {
            fatherEmailInputLayout.setError(null);
        }

        return fatherEmailInputLayout.getError() == null && fatherUserNameInputLayout.getError() == null;
    }
}