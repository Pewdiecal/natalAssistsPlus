package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UpdateHealthActivity extends AppCompatActivity {


    Toolbar toolbar;
    TextInputLayout overallHealthStatsInputLayout;
    EditText overallHealthStatsEdt;
    TextInputLayout adviseInputLayout;
    EditText adviseEdt;
    TextInputLayout babyHealthStatsInputLayout;
    EditText babyHealthStatsEdt;
    TextInputLayout babyWeightInputLayout;
    EditText babyWeightEdt;
    TextInputLayout babyHeartStatsInputLayout;
    EditText babyHeartStatsEdt;
    TextInputLayout babyBrainStatsInputLayout;
    EditText babyBrainStatsEdt;
    TextInputLayout babyLungStatsInputLayout;
    EditText babyLungStatsEdt;
    TextInputLayout babyLiverStatsInputLayout;
    EditText babyLiverStatsEdt;
    TextInputLayout babyKidneysStatsInputLayout;
    EditText babyKidneysStatsEdt;
    TextInputLayout hospitalNameTxtInputLayout;
    EditText hospitalNameEdt;
    TextInputLayout reportTitleInputLayout;
    EditText reportTitleEdt;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String username;
    String doctorUsername;
    String docID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_health);

        username = getIntent().getStringExtra("Username");
        doctorUsername = getIntent().getStringExtra("DoctorUsername");
        docID = getIntent().getStringExtra("DocID");

        overallHealthStatsInputLayout = findViewById(R.id.overallHealthTxtInputLayout);
        overallHealthStatsEdt = findViewById(R.id.overallHealthEdt);
        adviseInputLayout = findViewById(R.id.adviceTxtInputLayout);
        adviseEdt = findViewById(R.id.adviceEdt);
        babyHealthStatsInputLayout = findViewById(R.id.babyHealthStatsTxtInputLayout);
        babyHealthStatsEdt = findViewById(R.id.babyHealthStatsEdt);
        babyWeightInputLayout = findViewById(R.id.babyWeightTxtInputLayout);
        babyWeightEdt = findViewById(R.id.babyWeightEdt);
        babyHeartStatsInputLayout = findViewById(R.id.babyHeartTxtInputLayout);
        babyHeartStatsEdt = findViewById(R.id.babyHeartEdt);
        babyBrainStatsInputLayout = findViewById(R.id.babyBrainTxtInputLayout);
        babyBrainStatsEdt = findViewById(R.id.babyBrainEdt);
        babyLungStatsInputLayout = findViewById(R.id.babyLungTxtInputLayout);
        babyLungStatsEdt = findViewById(R.id.babyLungEdt);
        babyLiverStatsInputLayout = findViewById(R.id.babyLiverTxtInputLayout);
        babyLiverStatsEdt = findViewById(R.id.babyLiverEdt);
        babyKidneysStatsInputLayout = findViewById(R.id.babyKidneyTxtInputLayout);
        babyKidneysStatsEdt = findViewById(R.id.babyKidneyEdt);
        hospitalNameTxtInputLayout = findViewById(R.id.hospitalNameInputLayout);
        hospitalNameEdt = findViewById(R.id.hospitalNameEdt);
        reportTitleInputLayout = findViewById(R.id.reportTitleTxtInputLayout);
        reportTitleEdt = findViewById(R.id.reportTitleEdt);
        toolbar = findViewById(R.id.updateHealthToolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private boolean inputIsCorrect(){
        if(overallHealthStatsEdt.getText().toString().isEmpty()){
            overallHealthStatsInputLayout.setError("Field cannot be blank");
        } else {
            overallHealthStatsInputLayout.setError(null);
        }

        if(adviseEdt.getText().toString().isEmpty()){
            adviseInputLayout.setError("Field cannot be blank");
        } else {
            adviseInputLayout.setError(null);
        }

        if(babyHealthStatsEdt.getText().toString().isEmpty()){
            babyHealthStatsInputLayout.setError("Field cannot be blank");
        } else {
            babyHealthStatsInputLayout.setError(null);
        }

        if(babyWeightEdt.getText().toString().isEmpty()){
            babyWeightInputLayout.setError("Field cannot be blank");
        } else {
            babyWeightInputLayout.setError(null);
        }

        if(babyHeartStatsEdt.getText().toString().isEmpty()){
            babyHeartStatsInputLayout.setError("Field cannot be blank");
        } else {
            babyHeartStatsInputLayout.setError(null);
        }

        if(babyBrainStatsEdt.getText().toString().isEmpty()){
            babyBrainStatsInputLayout.setError("Field cannot be blank");
        } else {
            babyBrainStatsInputLayout.setError(null);
        }

        if(babyLungStatsEdt.getText().toString().isEmpty()){
            babyLungStatsInputLayout.setError("Field cannot be blank");
        } else {
            babyLungStatsInputLayout.setError(null);
        }

        if(babyLiverStatsEdt.getText().toString().isEmpty()){
            babyLiverStatsInputLayout.setError("Field cannot be blank");
        } else {
            babyLiverStatsInputLayout.setError(null);
        }

        if(babyKidneysStatsEdt.getText().toString().isEmpty()){
            babyKidneysStatsInputLayout.setError("Field cannot be blank");
        } else {
            babyKidneysStatsInputLayout.setError(null);
        }

        if(hospitalNameEdt.getText().toString().isEmpty()){
            hospitalNameTxtInputLayout.setError("Field cannot be blank");
        } else {
            hospitalNameTxtInputLayout.setError(null);
        }

        if(reportTitleEdt.getText().toString().isEmpty()){
            reportTitleInputLayout.setError("Field cannot be blank");
        } else {
            reportTitleInputLayout.setError(null);
        }

        return overallHealthStatsInputLayout.getError() == null && adviseInputLayout.getError() == null &&
                babyHealthStatsInputLayout.getError() == null && babyWeightInputLayout.getError() == null &&
                babyHeartStatsInputLayout.getError() == null && babyBrainStatsInputLayout.getError() == null &&
                babyLungStatsInputLayout.getError() == null && babyLiverStatsInputLayout.getError() == null &&
                babyKidneysStatsInputLayout.getError() == null && hospitalNameTxtInputLayout.getError() == null &&
                reportTitleInputLayout.getError() == null;

    }

    private void uploadData(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault());
        Map<String, Object> reportData = new HashMap<>();
        reportData.put("reportTitle", reportTitleEdt.getText().toString());
        reportData.put("reportDate", simpleDateFormat.format(new Date()));
        reportData.put("overallHealthStats", overallHealthStatsEdt.getText().toString());
        reportData.put("doctorAdvise", adviseEdt.getText().toString());
        reportData.put("babyOverallHealthStats", babyHealthStatsEdt.getText().toString());
        reportData.put("babyWeight", babyWeightEdt.getText().toString());
        reportData.put("heartStats", babyHeartStatsEdt.getText().toString());
        reportData.put("brainStats", babyBrainStatsEdt.getText().toString());
        reportData.put("lungsStats", babyLungStatsEdt.getText().toString());
        reportData.put("liverStats", babyLiverStatsEdt.getText().toString());
        reportData.put("kidneysStats", babyKidneysStatsEdt.getText().toString());
        reportData.put("hospitalName", hospitalNameEdt.getText().toString());
        reportData.put("patientName", username);
        reportData.put("doctorName", doctorUsername);

        db.collection("users").document(docID).collection("medicalReport")
                .add(reportData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(UpdateHealthActivity.this, "Report Upload Success", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_update_health, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionUpdateHealthConfirm:
                if(inputIsCorrect()){
                    uploadData();
                }
                break;
            default:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}