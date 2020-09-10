package com.caltech.natalassistsplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.Button;

public class RoleActivity extends AppCompatActivity {

    CardView motherCard;
    CardView fatherCard;
    CardView mpCard;
    Button backbtn;
    Button continuebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        motherCard = findViewById(R.id.motherCardView);
        fatherCard = findViewById(R.id.fatherCardView);
        mpCard = findViewById(R.id.mpCardView);
        backbtn = findViewById(R.id.backBtnRole);
        continuebtn = findViewById(R.id.continueBtnRole);

    }
}