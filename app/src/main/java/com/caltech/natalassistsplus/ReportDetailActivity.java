package com.caltech.natalassistsplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ReportDetailActivity extends AppCompatActivity {

    private TextView motherReport;
    private TextView babyReport;
    private ImageView ultrasonicImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);

        motherReport = findViewById(R.id.motherReportDetailsTxt);
        babyReport = findViewById(R.id.babyReportDetailsTxt);
        ultrasonicImg = findViewById(R.id.babyUltrasoundImg);

        String reportTitle = getIntent().getStringExtra("reportTitle");
        String reportDate = getIntent().getStringExtra("reportDate");
        String patientName = getIntent().getStringExtra("reportTitle");
        String doctorName = getIntent().getStringExtra("reportTitle");
        String hospitalName = getIntent().getStringExtra("reportTitle");
        String overallHealthStats = getIntent().getStringExtra("reportTitle");
        String doctorAdvise = getIntent().getStringExtra("reportTitle");

        String babyOverallHealthStats = getIntent().getStringExtra("reportTitle");
        String babyWeight = getIntent().getStringExtra("reportTitle");
        String toBeBorn = getIntent().getStringExtra("reportTitle");
        String heartStats = getIntent().getStringExtra("reportTitle");
        String brainStats = getIntent().getStringExtra("reportTitle");
        String lungsStats = getIntent().getStringExtra("reportTitle");
        String liverStats = getIntent().getStringExtra("reportTitle");
        String kidneysStats = getIntent().getStringExtra("reportTitle");
        String intestineStats = getIntent().getStringExtra("reportTitle");
        String babyUltrasonicImg = getIntent().getStringExtra("reportTitle");

        String motherHealth = "Checkup : " + reportTitle + "\n" +
                "Date : " + reportDate + "\n" +
                "Patient Name : " + patientName + "\n" +
                "Doctor name : " + doctorName + "\n" +
                "Hospital Name : " + hospitalName + "\n" +
                "Overall health Status" + overallHealthStats + "\n" +
                "Doctor advise : " + doctorAdvise + "\n";

        String babyHealth = "Overall Health : " + babyOverallHealthStats + "\n" +
                "Weight : " + babyWeight + "\n" +
                "To be born in :" + toBeBorn + "\n" +
                "Heart Status : " + heartStats + "\n" +
                "Brain Status : " + brainStats + "\n" +
                "Lungs Status : " + lungsStats + "\n" +
                "Liver Status : " + liverStats + "\n" +
                "Kidneys Status : " + kidneysStats + "\n" +
                "Intestine Status : " + intestineStats + "\n";

        motherReport.setText(motherHealth);
        babyReport.setText(babyHealth);


    }
}