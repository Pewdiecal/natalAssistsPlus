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

    }
}