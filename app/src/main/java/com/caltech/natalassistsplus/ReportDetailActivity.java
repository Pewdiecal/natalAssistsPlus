package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ReportDetailActivity extends AppCompatActivity {

    private TextView motherReport;
    private TextView babyReport;
    private ImageView ultrasonicImg;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);

        motherReport = findViewById(R.id.motherReportDetailsTxt);
        babyReport = findViewById(R.id.babyReportDetailsTxt);
        ultrasonicImg = findViewById(R.id.babyUltrasoundImg);
        toolbar = findViewById(R.id.reportDetailsToolbar);

        toolbar.setTitle("Report Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String reportTitle = getIntent().getStringExtra("reportTitle");
        String reportDate = getIntent().getStringExtra("reportDate");
        String patientName = getIntent().getStringExtra("patientName");
        String doctorName = getIntent().getStringExtra("doctorName");
        String hospitalName = getIntent().getStringExtra("hospitalName");
        String overallHealthStats = getIntent().getStringExtra("overallHealthStats");
        String doctorAdvise = getIntent().getStringExtra("doctorAdvise");

        String babyOverallHealthStats = getIntent().getStringExtra("babyOverallHealthStats");
        String babyWeight = getIntent().getStringExtra("babyWeight");
        String heartStats = getIntent().getStringExtra("heartStats");
        String brainStats = getIntent().getStringExtra("brainStats");
        String lungsStats = getIntent().getStringExtra("lungsStats");
        String liverStats = getIntent().getStringExtra("liverStats");
        String kidneysStats = getIntent().getStringExtra("kidneysStats");
        String babyUltrasonicImg = getIntent().getStringExtra("babyUltrasonicImg");

        String motherHealth = "Checkup : " + reportTitle + "\n" +
                "Date : " + reportDate + "\n" +
                "Patient Name : " + patientName + "\n" +
                "Doctor name : " + doctorName + "\n" +
                "Hospital Name : " + hospitalName + "\n" +
                "Overall health Status : " + overallHealthStats + "\n" +
                "Doctor advise : " + doctorAdvise + "\n";

        String babyHealth = "Overall Health : " + babyOverallHealthStats + "\n" +
                "Weight : " + babyWeight + "\n" +
                "Heart Status : " + heartStats + "\n" +
                "Brain Status : " + brainStats + "\n" +
                "Lungs Status : " + lungsStats + "\n" +
                "Liver Status : " + liverStats + "\n" +
                "Kidneys Status : " + kidneysStats + "\n";

        motherReport.setText(motherHealth);
        babyReport.setText(babyHealth);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}