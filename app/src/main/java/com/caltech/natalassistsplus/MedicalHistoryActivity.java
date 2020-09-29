package com.caltech.natalassistsplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MedicalHistoryActivity extends AppCompatActivity {

    TextView medicalHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);

        medicalHistory = findViewById(R.id.medicalHistoryTxt);
    }
}