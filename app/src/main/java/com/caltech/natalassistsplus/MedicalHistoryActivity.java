package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MedicalHistoryActivity extends AppCompatActivity {

    TextView medicalHistory;
    String username;
    String docID;
    Toolbar toolbar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);

        toolbar = findViewById(R.id.medicalHistoryToolbar);
        medicalHistory = findViewById(R.id.medicalHistoryTxt);
        username = getIntent().getStringExtra("Username");
        docID = getIntent().getStringExtra("DocID");

        toolbar.setTitle("Medical History");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fetchData();

    }

    private void fetchData(){
        db.collection("users").document(docID)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    String medicalHistoryStr = "Ongoing medication : " + task.getResult().getString("OngoingMedication") + "\n" +
                            "Last updated date : " + task.getResult().getString("LastUpdate") + "\n" +
                            "Patient name : " + task.getResult().getString("PatientName") + "\n" +
                            "Chronic Illness : " + task.getResult().getString("ChronicIllness") + "\n" +
                            "Blood Type : " + task.getResult().getString("BloodType") + "\n" +
                            "Number of miscarriage : " + task.getResult().getString("NumMiscarriage");

                    medicalHistory.setText(medicalHistoryStr);
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}