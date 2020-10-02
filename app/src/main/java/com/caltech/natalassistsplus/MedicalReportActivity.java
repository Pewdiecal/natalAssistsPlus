package com.caltech.natalassistsplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Date;

public class MedicalReportActivity extends AppCompatActivity {

    RecyclerView medicalReportRecyclerView;
    RecyclerView.LayoutManager medicalReportLayoutManager;
    MedicalReportRecyclerViewAdapter medicalReportRecyclerViewAdapter;
    ArrayList<MedicalReport> medicalReports = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String username;
    String docID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_report);

        username = getIntent().getStringExtra("Username");
        docID = getIntent().getStringExtra("DocID");

        medicalReportRecyclerView = findViewById(R.id.medicalReportRV);
        medicalReportLayoutManager = new LinearLayoutManager(this);
        medicalReportRecyclerViewAdapter = new MedicalReportRecyclerViewAdapter(medicalReports);
        medicalReportRecyclerView.setLayoutManager(medicalReportLayoutManager);
        medicalReportRecyclerView.setAdapter(medicalReportRecyclerViewAdapter);
        medicalReportRecyclerView.setHasFixedSize(true);

        fetchData();

    }

    private void fetchData(){

        db.collection("users").document(docID).collection("medicalReport")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        boolean isExists;
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            isExists = false;
                            for(MedicalReport medicalReport:medicalReports){
                                if(medicalReport.reportTitle.equals(document.getString("ReportTitle")) &&
                                        medicalReport.reportDate.equals(document.getString("ReportDate"))){
                                    isExists = true;
                                    break;
                                }
                            }

                            if(!isExists){
                                medicalReports.add(document.toObject(MedicalReport.class));
                            }
                        }
                        medicalReportRecyclerViewAdapter.notifyDataSetChanged();
                    }
                });
    }
}