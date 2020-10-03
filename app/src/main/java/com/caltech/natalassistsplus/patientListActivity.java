package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class patientListActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    PatientListRecyclerViewAdapter patientListRecyclerViewAdapter;
    ArrayList<Patient> patients = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String doctorUsername;
    int toActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        doctorUsername = getIntent().getStringExtra("Username");
        toActivity = getIntent().getIntExtra("ToActivity", 0);

        toolbar = findViewById(R.id.patientListToolbar);
        recyclerView = findViewById(R.id.patientListRV);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        patientListRecyclerViewAdapter = new PatientListRecyclerViewAdapter(patients, toActivity, doctorUsername);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(patientListRecyclerViewAdapter);

        fetchData();
    }

    private void fetchData() {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(Objects.equals(document.getString("DoctorLink"), doctorUsername)){
                                    patients.add(new Patient(document.getString("Username"), document.getId()));
                                }
                            }
                        }
                        patientListRecyclerViewAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}