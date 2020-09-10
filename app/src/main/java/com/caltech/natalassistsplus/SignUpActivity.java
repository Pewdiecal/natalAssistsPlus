package com.caltech.natalassistsplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {
TextInputLayout usernameTxtInput;
EditText usernameEdt;
TextInputLayout emailTxtInput;
EditText emailEdt;
TextInputLayout pwdInputLayout;
EditText pwdEdt;
TextInputLayout confirmPwdLayout;
EditText confirmPwdEdt;
Button continueBtn;
Button loginExistingAccBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameTxtInput = findViewById(R.id.usernameInputLayout);
        usernameEdt = findViewById(R.id.usernameedt);
        emailTxtInput = findViewById(R.id.emailInputLayout);
        emailEdt = findViewById(R.id.emailEdt);
        pwdInputLayout = findViewById(R.id.pwdInputLayout);
        pwdEdt = findViewById(R.id.pwdEdt);
        confirmPwdLayout = findViewById(R.id.confirmPwdInputLayout);
        confirmPwdEdt = findViewById(R.id.confirmPwdEdt);
        continueBtn = findViewById(R.id.continueBtn);
        loginExistingAccBtn = findViewById(R.id.loginExistsBtn);
    }
}