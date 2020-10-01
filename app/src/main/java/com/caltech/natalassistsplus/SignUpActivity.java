package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
ProgressBar progressBar;
FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameTxtInput = findViewById(R.id.newusernameInputLayout);
        usernameEdt = findViewById(R.id.newusernameedt);
        emailTxtInput = findViewById(R.id.emailInputLayout);
        emailEdt = findViewById(R.id.emailEdt);
        pwdInputLayout = findViewById(R.id.pwdInputLayout);
        pwdEdt = findViewById(R.id.pwdEdt);
        confirmPwdLayout = findViewById(R.id.confirmPwdInputLayout);
        confirmPwdEdt = findViewById(R.id.confirmPwdEdt);
        continueBtn = findViewById(R.id.continueBtn);
        loginExistingAccBtn = findViewById(R.id.loginExistsBtn);
        progressBar = findViewById(R.id.signUpProgressBar);

        continueBtn.setOnClickListener(v -> {

            if(inputIsCorrect()){
                continueBtn.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);
                verifyNUpdate(usernameEdt.getText().toString());
            }
        });

        loginExistingAccBtn.setOnClickListener(v -> finish());

    }

    private boolean inputIsCorrect(){
        if(usernameEdt.getText().toString().isEmpty()){
            usernameTxtInput.setError("Username cannot be empty");
        } else {
            usernameTxtInput.setError(null);
        }

        if(pwdEdt.getText().toString().isEmpty()){
            pwdInputLayout.setError("Password cannot be empty");
        } else{
            pwdInputLayout.setError(null);
        }

        if(confirmPwdEdt.getText().toString().isEmpty()){
            confirmPwdLayout.setError("Confirm password cannot be empty");
        } else {
            confirmPwdLayout.setError(null);
        }

        if(!emailEdt.getText().toString().contains("@")){
            emailTxtInput.setError("Email Incorrect");
        } else {
            emailTxtInput.setError(null);
        }

        if(confirmPwdEdt.getText().toString().equals(pwdEdt.getText().toString())){
            pwdInputLayout.setError(null);
            confirmPwdLayout.setError(null);
        } else{
            pwdInputLayout.setError("Not match");
            confirmPwdLayout.setError("Not match");
        }

        return usernameTxtInput.getError() == null && pwdInputLayout.getError() == null && confirmPwdLayout.getError() == null
                && emailTxtInput.getError() == null;
    }

    private void verifyNUpdate(String username){
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            continueBtn.setEnabled(true);
                            progressBar.setVisibility(View.GONE);
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Document data", document.getId() + " => " + document.getData());
                            }
                            if(task.getResult().size() == 0){
                                Intent intent = new Intent(SignUpActivity.this, RoleActivity.class);
                                intent.putExtra("Username", usernameEdt.getText().toString());
                                intent.putExtra("Email", emailEdt.getText().toString());
                                intent.putExtra("Password", pwdEdt.getText().toString());
                                startActivity(intent);
                            }

                            for(int i = 0; i < task.getResult().size(); i++){
                                if(task.getResult().getDocuments().get(i).getString("Username").equals(username)){
                                    usernameTxtInput.setError("Username already exists");
                                    break;
                                }

                                if(i == task.getResult().size() - 1){
                                    Intent intent = new Intent(SignUpActivity.this, RoleActivity.class);
                                    intent.putExtra("Username", usernameEdt.getText().toString());
                                    intent.putExtra("Email", emailEdt.getText().toString());
                                    intent.putExtra("Password", pwdEdt.getText().toString());
                                    startActivity(intent);
                                }
                            }
                        } else {
                            Log.w("Firebase Err", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}