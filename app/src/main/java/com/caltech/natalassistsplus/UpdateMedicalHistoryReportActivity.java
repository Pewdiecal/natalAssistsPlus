package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class UpdateMedicalHistoryReportActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextInputLayout onGoingMedicTxtInput;
    EditText onGoingMedicEdt;
    TextInputLayout lastUpdatedDateTxtInput;
    EditText lastUpdatedDateEdt;
    TextInputLayout chronicIllnessTxtInput;
    EditText chronicIllnessEdt;
    TextInputLayout bloodTypeTxtInput;
    EditText bloodTypeEdt;
    TextInputLayout miscarriageTxtInput;
    EditText miscarriageEdt;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String docID;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_medical_history_report);

        docID = getIntent().getStringExtra("DocID");
        username = getIntent().getStringExtra("Username");

        toolbar = findViewById(R.id.updateMedicHistoryToolbar);
        onGoingMedicTxtInput = findViewById(R.id.ongoingMedicationTxtInput);
        onGoingMedicEdt = findViewById(R.id.ongoingMedicationEdt);
        lastUpdatedDateTxtInput = findViewById(R.id.lastUpdatedDateTxtInput);
        lastUpdatedDateEdt = findViewById(R.id.lastUpdatedDateEdt);
        chronicIllnessTxtInput = findViewById(R.id.chronicIllnessTxtInput);
        chronicIllnessEdt = findViewById(R.id.chronicIllnessEdt);
        bloodTypeTxtInput = findViewById(R.id.bloodTypeTxtInput);
        bloodTypeEdt = findViewById(R.id.bloodTypeEdt);
        miscarriageTxtInput = findViewById(R.id.miscarriageTxtInput);
        miscarriageEdt = findViewById(R.id.miscarriageEdt);

        toolbar.setTitle("Update Medical History");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateData();

    }

    private void populateData() {
        db.collection("users").document(docID).collection("medicalHistoryReport").document("report")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){

                            onGoingMedicEdt.setText(task.getResult().getString("OngoingMedication"));
                            lastUpdatedDateEdt.setText(task.getResult().getString("LastUpdate"));
                            chronicIllnessEdt.setText(task.getResult().getString("ChronicIllness"));
                            bloodTypeEdt.setText(task.getResult().getString("BloodType"));
                            miscarriageEdt.setText(task.getResult().getString("NumMiscarriage"));

                        }
                    }
                });
    }

    private boolean inputIsCorrect(){
        if(onGoingMedicEdt.getText().toString().isEmpty()){
            onGoingMedicTxtInput.setError("Field cannot be blank");
        } else {
            onGoingMedicTxtInput.setError(null);
        }

        if(lastUpdatedDateEdt.getText().toString().isEmpty()){
            lastUpdatedDateTxtInput.setError("Field cannot be blank");
        } else {
            lastUpdatedDateTxtInput.setError(null);
        }

        if(chronicIllnessEdt.getText().toString().isEmpty()){
            chronicIllnessTxtInput.setError("Field cannot be blank");
        } else {
            chronicIllnessTxtInput.setError(null);
        }

        if(bloodTypeEdt.getText().toString().isEmpty()){
            bloodTypeTxtInput.setError("Field cannot be empty");
        } else {
            bloodTypeTxtInput.setError(null);
        }

        if(miscarriageEdt.getText().toString().isEmpty()){
            miscarriageTxtInput.setError("Field cannot be empty");
        } else {
            miscarriageTxtInput.setError(null);
        }

        return onGoingMedicTxtInput.getError() == null && lastUpdatedDateTxtInput.getError() == null &&
                chronicIllnessTxtInput.getError() == null && bloodTypeTxtInput.getError() == null &&
                miscarriageTxtInput.getError() == null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_update_medical_history, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.actionUpdateMedicalHistoryConfirm) {
            if(inputIsCorrect()){
                pushData();
            }
        } else {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void pushData() {

        Map<String, Object> reportData = new HashMap<>();
        reportData.put("OngoingMedication", onGoingMedicEdt.getText().toString());
        reportData.put("LastUpdate", lastUpdatedDateEdt.getText().toString());
        reportData.put("PatientName", username);
        reportData.put("ChronicIllness", chronicIllnessEdt.getText().toString());
        reportData.put("BloodType", bloodTypeEdt.getText().toString());
        reportData.put("NumMiscarriage", miscarriageEdt.getText().toString());

        db.collection("users").document(docID).collection("medicalHistoryReport").document("report")
                .set(reportData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UpdateMedicalHistoryReportActivity.this, "Data uploaded successfully", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }
}