package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UtilisationHistoryActivity extends AppCompatActivity {

    TextView totalUsageNumberTxt;
    RatingBar ratingBar;
    CardView viewUserFeedbackBtn;
    CardView viewCrashReport;
    Toolbar toolbar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilisation_history);

        totalUsageNumberTxt = findViewById(R.id.totalUserUsageTxt);
        ratingBar = findViewById(R.id.UtilisationRatingBar);
        viewUserFeedbackBtn = findViewById(R.id.viewUserFeedbackBtn);
        viewCrashReport = findViewById(R.id.viewCrashReportBtn);
        toolbar = findViewById(R.id.utilisationHistoryToolbar);
        toolbar.setTitle("Utilisation History");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        totalUsageNumberTxt.setText("...");
        ratingBar.setNumStars(5);

        fetchData();

        viewUserFeedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtilisationHistoryActivity.this, UserFeedbackActivity.class);
                startActivity(intent);
            }
        });

        viewCrashReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtilisationHistoryActivity.this, CrashReportActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fetchData() {
        db.collection("feedbacks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            totalUsageNumberTxt.setText("10");
                            float totalRatings = 0;
                            float avgRating = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                totalRatings += document.getLong("Ratings");
                            }
                            avgRating = totalRatings/task.getResult().size();
                            ratingBar.setRating(avgRating);
                        }
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}