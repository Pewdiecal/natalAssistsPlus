package com.caltech.natalassistsplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class AccountRecoveryActivity extends AppCompatActivity {
TextInputLayout usernamerecoverInputLayout;
EditText usernamerecoverEdt;
Button confirmbtn;
Button backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_recovery);

        usernamerecoverInputLayout = findViewById(R.id.usernamerecoverInputLayout);
        usernamerecoverEdt = findViewById(R.id.usernamerecoverEdt);
        confirmbtn = findViewById(R.id.confirmbtn);
        backbtn = findViewById(R.id.backbtn);
    }
}