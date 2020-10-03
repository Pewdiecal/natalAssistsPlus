package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Date;

public class MedicalReportActivity extends AppCompatActivity {

    Toolbar toolbar;
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

        toolbar = findViewById(R.id.medicalReportToolbar);
        medicalReportRecyclerView = findViewById(R.id.medicalReportRV);
        medicalReportLayoutManager = new LinearLayoutManager(this);
        medicalReportRecyclerViewAdapter = new MedicalReportRecyclerViewAdapter(medicalReports);
        medicalReportRecyclerView.setLayoutManager(medicalReportLayoutManager);
        medicalReportRecyclerView.setAdapter(medicalReportRecyclerViewAdapter);
        medicalReportRecyclerView.setHasFixedSize(true);

        toolbar.setTitle("Medical Reports");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                                if(medicalReport.reportTitle.equals(document.getString("reportTitle")) &&
                                        medicalReport.reportDate.equals(document.getString("reportDate"))){
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}