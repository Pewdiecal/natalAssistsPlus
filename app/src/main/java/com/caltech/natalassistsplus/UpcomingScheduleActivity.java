package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        toolbar.setTitle("Checkup Schedule");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        upcomingScheduleRecyclerViewAdapter = new UpcomingScheduleRecyclerViewAdapter(schedules);

        layoutManager = new LinearLayoutManager(this);
        upcomingScheduleRV.setLayoutManager(layoutManager);
        upcomingScheduleRV.setHasFixedSize(true);
        upcomingScheduleRV.setAdapter(upcomingScheduleRecyclerViewAdapter);

        fetchData();
        addData("Ultrasonic checkup", "30/9/2020", "Hospital Putrajaya", "12pm");
        addData("Medical checkup", "10/11/2020", "Hospital Putrajaya", "9am");
        addData("Clinical checkup", "12/12/2020", "Klinik Putrajaya", "10am");
    }

    private void fetchData() {
        db.collection("users").document(docID).collection("upcomingSchedule")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            schedules.clear();
                            for(int i = 0; i < task.getResult().getDocuments().size(); i++){
                                schedules.add(task.getResult().getDocuments().get(i).toObject(Schedule.class));
                            }
                            upcomingScheduleRecyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void addData(String scheduleName, String date, String location, String time){
        Map<String, Object> scheduleData = new HashMap<>();
        scheduleData.put("scheduleName", scheduleName);
        scheduleData.put("date", date);
        scheduleData.put("location", location);
        scheduleData.put("time", time);

        db.collection("users").document(docID).collection("upcomingSchedule")
                .add(scheduleData);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}