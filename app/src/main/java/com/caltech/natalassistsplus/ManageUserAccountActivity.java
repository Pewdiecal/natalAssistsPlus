package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ManageUserAccountActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ManageUserAccountRecyclerViewAdapter manageUserAccountRecyclerViewAdapter;
    ArrayList<UserAccount> userAccounts = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user_account);

        toolbar = findViewById(R.id.manageUserAccToolbar);
        recyclerView = findViewById(R.id.manageUserAccRV);

        toolbar.setTitle("Manage user account");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layoutManager = new LinearLayoutManager(this);
        manageUserAccountRecyclerViewAdapter = new ManageUserAccountRecyclerViewAdapter(userAccounts);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(manageUserAccountRecyclerViewAdapter);

        fetchData();

    }

    private void fetchData() {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("User", document.getId() + " => " + document.getData());
                            userAccounts.add(new UserAccount(document.getString("Username"), document.getString("RecoveryStatus"),
                                    document.getId()));
                        }
                        manageUserAccountRecyclerViewAdapter.notifyDataSetChanged();
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}