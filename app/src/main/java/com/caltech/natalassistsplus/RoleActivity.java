package com.caltech.natalassistsplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.firestore.FirebaseFirestore;

public class RoleActivity extends AppCompatActivity {

    CardView motherCard;
    CardView fatherCard;
    CardView mpCard;
    Button backbtn;
    Button continuebtn;
    ImageView motherCheckIcon;
    ImageView fatherCheckIcon;
    ImageView mdCheckIcon;
    StringBuilder selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        motherCard = findViewById(R.id.motherCardView);
        fatherCard = findViewById(R.id.fatherCardView);
        mpCard = findViewById(R.id.mpCardView);
        backbtn = findViewById(R.id.backBtnRole);
        continuebtn = findViewById(R.id.continueBtnRole);
        motherCheckIcon = findViewById(R.id.motherCheckIcon);
        fatherCheckIcon = findViewById(R.id.fatherCheckIcon);
        mdCheckIcon = findViewById(R.id.medicalPractitionerCheckIcon);

        String username = getIntent().getStringExtra("Username");
        String pwd = getIntent().getStringExtra("Password");
        String email = getIntent().getStringExtra("Email");

        motherCard.setOnClickListener(v -> {
            selection = new StringBuilder("Mother");
            motherCheckIcon.setVisibility(View.VISIBLE);
            fatherCheckIcon.setVisibility(View.GONE);
            mdCheckIcon.setVisibility(View.GONE);
        });

        fatherCard.setOnClickListener(v -> {
            selection = new StringBuilder("Father");
            fatherCheckIcon.setVisibility(View.VISIBLE);
            motherCheckIcon.setVisibility(View.GONE);
            mdCheckIcon.setVisibility(View.GONE);
        });

        mpCard.setOnClickListener(v -> {
            selection = new StringBuilder("Medical Practitioner");
            mdCheckIcon.setVisibility(View.VISIBLE);
            fatherCheckIcon.setVisibility(View.GONE);
            motherCheckIcon.setVisibility(View.GONE);
        });

        continuebtn.setOnClickListener(v -> {
            if(selection != null){
                switch(selection.toString()){
                    case "Mother":
                    case "Father":
                        Intent intentMother = new Intent(RoleActivity.this, MotherSetupActivity.class);
                        intentMother.putExtra("Username", username);
                        intentMother.putExtra("Email", email);
                        intentMother.putExtra("Password", pwd);
                        intentMother.putExtra("Role", selection.toString());
                        startActivity(intentMother);
                        break;

                    case "Medical Practitioner":
                        Intent intentMP = new Intent(RoleActivity.this, MedicalPractitionerSetupActivity.class);
                        intentMP.putExtra("Username", username);
                        intentMP.putExtra("Email", email);
                        intentMP.putExtra("Password", pwd);
                        intentMP.putExtra("Role", selection.toString());
                        startActivity(intentMP);
                        break;
                }
                finish();
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}