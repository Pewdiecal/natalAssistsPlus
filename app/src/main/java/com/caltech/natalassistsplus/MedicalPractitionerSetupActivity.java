package com.caltech.natalassistsplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MedicalPractitionerSetupActivity extends AppCompatActivity {

    TextInputLayout medicalIDTxtInput;
    EditText medicalIDEdt;
    Button finishBtn;
    Button backBtn;
    String username;
    String email;
    String password;
    String role;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_practitioner_setup);

        username = getIntent().getStringExtra("Username");
        email = getIntent().getStringExtra("Email");
        password = getIntent().getStringExtra("Password");
        role = getIntent().getStringExtra("Role");


        medicalIDTxtInput = findViewById(R.id.medicalSetupTxtInputLayout);
        medicalIDEdt = findViewById(R.id.medicalSetupEdt);
        finishBtn = findViewById(R.id.medicalPractitionerSetupFinishBtn);
        backBtn = findViewById(R.id.medicalPractionerSetupBackBtn);

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputIsCorrect()){
                    uploadData();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private boolean inputIsCorrect(){
        if(medicalIDEdt.getText().toString().isEmpty()){
            medicalIDTxtInput.setError("Medical ID cannot be empty");
        } else if(!medicalIDEdt.getText().toString().contains("MD")){
            medicalIDTxtInput.setError("Invalid medical ID");
        } else {
            medicalIDTxtInput.setError(null);
        }

        return medicalIDTxtInput.getError() == null;
    }

    private void uploadData(){
        Map<String, Object> newUser = new HashMap<>();
        newUser.put("Username", username);
        newUser.put("Email", email);
        newUser.put("Password", password);
        newUser.put("Role", role);
        newUser.put("MedicalID", medicalIDEdt.getText().toString());

        db.collection("users")
                .add(newUser)
                .addOnSuccessListener(documentReference -> {
                    Intent intent = new Intent(MedicalPractitionerSetupActivity.this, MedicAdminHomeActivity.class);
                    intent.putExtra("Username", username);
                    intent.putExtra("DocID", documentReference.getId());
                    intent.putExtra("Role", role);
                    startActivity(intent);
                });
    }
}