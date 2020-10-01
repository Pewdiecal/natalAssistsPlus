package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateForumPostActivity extends AppCompatActivity {

    TextInputLayout postDescInputLayout;
    EditText postDescEdt;
    Toolbar toolbar;
    ProgressBar progressBar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String username;
    String postDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_forum_post);

        username = getIntent().getStringExtra("Username");

        postDescInputLayout = findViewById(R.id.createForumTxtInputLayout);
        postDescEdt = findViewById(R.id.createForumPostEdt);
        toolbar = findViewById(R.id.createForumPostToolbar);
        progressBar = findViewById(R.id.createPostProgressBar);

        toolbar.setTitle("Create new post");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_create_forum, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.actionConfirmPost) {
            if (inputIsCorrect()) {
                progressBar.setVisibility(View.VISIBLE);
                uploadData();
            }
        } else {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void uploadData(){
        Map<String, Object> postsData = new HashMap<>();
        postsData.put("Username", username);
        postsData.put("PostDesc", postDesc);

        db.collection("forumPosts")
                .add(postsData)
                .addOnCompleteListener(task -> {Toast.makeText(CreateForumPostActivity.this, "Post created successfully.", Toast.LENGTH_LONG).show();
                    finish();

                })
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateForumPostActivity.this, "Post cannot be created.", Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean inputIsCorrect(){
        if(postDescEdt.getText().toString().isEmpty()){
            postDescInputLayout.setError("Post description cannot be empty");
        } else {
            postDescInputLayout.setError(null);
            postDesc = postDescEdt.getText().toString();
        }
        return postDescInputLayout.getError() == null;
    }
}