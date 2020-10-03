package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MedicAdminHomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MedicAdminHomeRecyclerViewAdapter medicAdminHomeRecyclerViewAdapter;
    AdminRecyclerViewAdapter adminRecyclerViewAdapter;
    String username;
    String docID;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic_admin_home);

        username = getIntent().getStringExtra("Username");
        docID = getIntent().getStringExtra("DocID");
        role = getIntent().getStringExtra("Role");

        recyclerView = findViewById(R.id.medicAdminMenuRV);
        toolbar = findViewById(R.id.MedicAdminToolbar);
        setSupportActionBar(toolbar);

        layoutManager = new LinearLayoutManager(this);
        medicAdminHomeRecyclerViewAdapter = new MedicAdminHomeRecyclerViewAdapter(username, docID);
        adminRecyclerViewAdapter = new AdminRecyclerViewAdapter();
        recyclerView.setLayoutManager(layoutManager);

        if(role.equals("Admin")){
            recyclerView.setAdapter(adminRecyclerViewAdapter);
        } else {
            recyclerView.setAdapter(medicAdminHomeRecyclerViewAdapter);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_medic_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }
}