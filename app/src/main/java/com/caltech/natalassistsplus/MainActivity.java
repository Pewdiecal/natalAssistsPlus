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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
TextInputLayout usernameInputLayout;
TextInputLayout pwdInputLayout;
EditText usernameedt;
EditText pwdedt;
Button signinbtn;
Button registerbtn;
Button forgetbtn;
ProgressBar progressBar;
FirebaseFirestore db = FirebaseFirestore.getInstance();

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
        progressBar = findViewById(R.id.mainActivityProgressBar);

        signinbtn.setOnClickListener(v -> {
            String username = usernameedt.getText().toString();
            String pwd = pwdedt.getText().toString();
            signinbtn.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            if(username.isEmpty()){
                usernameInputLayout.setError("Username cannot be empty");
            } else {
                usernameInputLayout.setError(null);
            }

            if(pwd.isEmpty()){
                pwdInputLayout.setError("password cannot be empty");
            } else {
                pwdInputLayout.setError(null);
            }

            if(!pwd.isEmpty() && !username.isEmpty()){
                validateUser(username, pwd);
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        forgetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccountRecoveryActivity.class);
                startActivity(intent);
            }
        });

        uploadError("400", "Bad Request. The server cannot or will not process the request due to an apparent client error (e.g., malformed request syntax, " +
                "size too large, invalid request message framing, or deceptive request routing)", "Login screen.");
        uploadError("404", "Not found", "Dashboard screen.");
        uploadError("408", "Request Timeout", "Information center screen.");
        uploadError("413", "Payload Too Large", "Dashboard screen.");
        uploadError("417", "Expectation Failed", "Confinement Center screen");
    }

    private void validateUser(String username, String pwd){
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        signinbtn.setEnabled(true);
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("User", document.getId() + " => " + document.getData());

                            }
                            if(task.getResult().size() == 0){
                                Toast.makeText(MainActivity.this, "User credential incorrect.", Toast.LENGTH_LONG).show();
                            }

                            for(int i = 0; i < task.getResult().size(); i++){
                                if(task.getResult().getDocuments().get(i).getString("Username").equals(username) &&
                                        task.getResult().getDocuments().get(i).getString("Password").equals(pwd)){

                                    if(task.getResult().getDocuments().get(i).getString("Role").equals("Father") &&
                                    task.getResult().getDocuments().get(i).getString("MotherLink") != null){

                                        getMotherDocID(username, task.getResult().getDocuments().get(i).getString("MotherLink"),
                                                task.getResult().getDocuments().get(i).getString("Role"));

                                    } else {
                                        logInUser(username, task.getResult().getDocuments().get(i).getId(),
                                                task.getResult().getDocuments().get(i).getString("Role"));
                                    }

                                    break;
                                }
                                if(i == task.getResult().size() - 1){
                                    Toast.makeText(MainActivity.this, "User credential incorrect.", Toast.LENGTH_LONG).show();
                                }
                            }

                        } else {
                            Log.w("Error", "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    private void getMotherDocID(String username, String motherLink, String role){
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(int i = 0; i < task.getResult().size(); i++){
                            if(task.getResult().getDocuments().get(i).getString("Username").equals(motherLink)){
                                logInUser(username, task.getResult().getDocuments().get(i).getId(), role);
                                break;
                            }

                        }
                    }
                });
    }

    private void logInUser(String username, String docID, String role){
        Intent intent;
        if(role.equals("Admin") || role.equals("Medical Practitioner")){
            intent = new Intent(this, MedicAdminHomeActivity.class);
        } else {
            intent = new Intent(this, MainContentActivity.class);
        }
        intent.putExtra("Username", username);
        intent.putExtra("DocID", docID);
        intent.putExtra("Role", role);
        startActivity(intent);
        finish();
    }

    private void uploadError(String errCode, String errMsg, String errDetails){
        Map<String, Object> error = new HashMap<>();
        error.put("ErrorCode", errCode);
        error.put("ErrorMessage", errMsg);
        error.put("ErrorDetails", errDetails);

        db.collection("crashReport").add(error);
    }

}