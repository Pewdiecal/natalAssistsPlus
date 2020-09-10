package com.caltech.natalassistsplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
TextInputLayout usernameInputLayout;
TextInputLayout pwdInputLayout;
EditText usernameedt;
EditText pwdedt;
Button signinbtn;
Button registerbtn;
Button forgetbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInputLayout = findViewById(R.id.usernameInputLayout);
        pwdInputLayout = findViewById(R.id.pwdInputLayout);
        usernameedt = findViewById(R.id.usernameedt);
        pwdedt = findViewById(R.id.pwdedt);
        signinbtn = findViewById(R.id.signinbtn);
        registerbtn = findViewById(R.id.registerbtn);
        forgetbtn = findViewById(R.id.forgetbtn);
    }
}