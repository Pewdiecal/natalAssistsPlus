package com.caltech.natalassistsplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MedicalReportActivity extends AppCompatActivity {

    RecyclerView medicalReportRecyclerView;
    RecyclerView.LayoutManager medicalReportLayoutManager;
    MedicalReportRecyclerViewAdapter medicalReportRecyclerViewAdapter;
    ArrayList<MedicalReport> medicalReports = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_report);

        medicalReportRecyclerView = findViewById(R.id.medicalReportRV);
        medicalReportLayoutManager = new LinearLayoutManager(this);
        medicalReportRecyclerViewAdapter = new MedicalReportRecyclerViewAdapter(medicalReports);
        medicalReportRecyclerView.setAdapter(medicalReportRecyclerViewAdapter);
        medicalReportRecyclerView.setHasFixedSize(true);

    }
}