package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CrashReportActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CrashReportRecyclerViewAdapter crashReportRecyclerViewAdapter;
    ArrayList<CrashReport> crashReports = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_report);

        toolbar = findViewById(R.id.crashReportToolbar);
        recyclerView = findViewById(R.id.crashReportRV);
        toolbar.setTitle("Crash reports");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layoutManager = new LinearLayoutManager(this);
        crashReportRecyclerViewAdapter = new CrashReportRecyclerViewAdapter(crashReports);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(crashReportRecyclerViewAdapter);

        fetchData();
    }

    private void fetchData() {
        db.collection("crashReport")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("User", document.getId() + " => " + document.getData());
                            crashReports.add(new CrashReport(document.getString("ErrorCode"),
                                    document.getString("ErrorDetails"), document.getString("ErrorMessage")));
                        }
                        crashReportRecyclerViewAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}