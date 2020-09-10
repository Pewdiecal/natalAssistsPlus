package com.caltech.natalassistsplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class MotherSetupActivity extends AppCompatActivity {

    TextInputLayout fatherUserNameInputLayout;
    TextInputLayout fatherEmailInputLayout;
    EditText fatherUserNameEdt;
    EditText fatherEmailEdt;
    Button finishbtn;
    Button skipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_setup);

        fatherEmailInputLayout = findViewById(R.id.fatherEmailInputLayout);
        fatherUserNameInputLayout = findViewById(R.id.fatherUserNameInputLayout);
        fatherUserNameEdt = findViewById(R.id.fatherUsernameEdt);
        fatherEmailEdt = findViewById(R.id.fatherEmailEdt);
        finishbtn = findViewById(R.id.finishBtn);
        skipBtn = findViewById(R.id.skipBtn);

    }
}