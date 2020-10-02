package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class UpcomingScheduleActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView upcomingScheduleRV;
    RecyclerView.LayoutManager layoutManager;
    UpcomingScheduleRecyclerViewAdapter upcomingScheduleRecyclerViewAdapter;
    ArrayList<Schedule> schedules = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String username;
    String docID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_schedule);

        username = getIntent().getStringExtra("Username");
        docID = getIntent().getStringExtra("DocID");

        toolbar = findViewById(R.id.upcomingScheduleToolbar);
        upcomingScheduleRV = findViewById(R.id.upcomingScheduleRV);

        upcomingScheduleRecyclerViewAdapter = new UpcomingScheduleRecyclerViewAdapter(schedules);

        layoutManager = new LinearLayoutManager(this);
        upcomingScheduleRV.setLayoutManager(layoutManager);
        upcomingScheduleRV.setHasFixedSize(true);
        upcomingScheduleRV.setAdapter(upcomingScheduleRecyclerViewAdapter);

        fetchData();

    }

    private void fetchData() {
        db.collection("users").document(docID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            schedules.clear();
                            schedules.add(task.getResult().toObject(Schedule.class));
                            upcomingScheduleRecyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}