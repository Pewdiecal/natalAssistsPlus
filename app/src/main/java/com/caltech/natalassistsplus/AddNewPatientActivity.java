package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class AddNewPatientActivity extends AppCompatActivity {

    TextInputLayout patientNameTxtInput;
    EditText patientNameEdt;
    Toolbar toolbar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_patient);
        username = getIntent().getStringExtra("DoctorUsername");

        patientNameTxtInput = findViewById(R.id.addNewPatientTxtInputLayout);
        patientNameEdt = findViewById(R.id.patientNameEdt);
        toolbar = findViewById(R.id.addNewPatientToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private boolean inputIsCorrect(){
        if(patientNameEdt.getText().toString().isEmpty()){
            patientNameTxtInput.setError("Field cannot be empty.");
        } else {
            patientNameTxtInput.setError(null);
        }

        return patientNameTxtInput.getError() == null;
    }

    private void searchPatient(){
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(int i = 0; i < task.getResult().size(); i++){
                                if(task.getResult().getDocuments().get(i).getString("Username").equals(patientNameEdt.getText().toString())){
                                    updateData(task.getResult().getDocuments().get(i).getId());
                                    break;
                                } else if(i == task.getResult().size()-1){
                                    Toast.makeText(AddNewPatientActivity.this, "No match username found", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                });
    }

    private void updateData(String docID){
        Map<String, Object> updateDoctorLink = new HashMap<>();
        updateDoctorLink.put("DoctorLink", username);

        db.collection("users").document(docID)
                .update(updateDoctorLink);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_patient, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.actionAddPatientConfirm:
                if(inputIsCorrect()){
                    searchPatient();
                }
                break;
            default:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}