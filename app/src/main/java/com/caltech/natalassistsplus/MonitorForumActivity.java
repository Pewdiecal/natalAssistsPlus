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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MonitorForumActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ForumRecyclerViewAdapter forumRecyclerViewAdapter;
    ArrayList<ForumPost> forumPosts = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_forum);

        toolbar = findViewById(R.id.monitorForumToolbar);
        recyclerView = findViewById(R.id.monitorForumRV);
        toolbar.setTitle("Monitor forum");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layoutManager = new LinearLayoutManager(this);
        forumRecyclerViewAdapter = new ForumRecyclerViewAdapter(forumPosts, true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(forumRecyclerViewAdapter);

        fetchPostData();

    }

    private void fetchPostData(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());

        db.collection("forumPosts")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        boolean isExists;
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            isExists = false;
                            for(ForumPost forumPost:forumPosts){
                                if(forumPost.getPostDesc().equals(document.getString("PostDesc")) &&
                                        forumPost.getUsername().equals(document.getString("Username"))){
                                    isExists = true;
                                    break;
                                }
                            }

                            if(!isExists){
                                forumPosts.add(new ForumPost(document.getString("Username"), R.drawable.ic_baseline_supervised_user_circle_24,
                                        simpleDateFormat.format(new Date()), document.getString("PostDesc"), 0, document.getId()));
                            }
                        }
                        forumRecyclerViewAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}