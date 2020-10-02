package com.caltech.natalassistsplus;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FeedbackFragment extends Fragment {

    TextInputLayout suggestionInputLayout;
    EditText suggestionEdt;
    RatingBar ratingBar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public FeedbackFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        suggestionInputLayout = view.findViewById(R.id.suggestionTxtInputLayout);
        suggestionEdt = view.findViewById(R.id.suggestionsEdt);
        ratingBar = view.findViewById(R.id.ratingBar);
        ratingBar.setNumStars(5);

        // Inflate the layout for this fragment
        return view;
    }

    private boolean inputIsCorrect(){
        if(suggestionEdt.getText().toString().isEmpty()){
            suggestionInputLayout.setError("Feedback cannot be empty");
        } else {
            suggestionInputLayout.setError(null);
        }

        if(ratingBar.getRating() == 0.0){
            Toast.makeText(getContext(), "Please enter your rating", Toast.LENGTH_LONG).show();
        }

        return suggestionInputLayout.getError() == null && ratingBar.getRating() > 0.0;
    }

    private void uploadData(){

        Map<String, Object> feedbackData = new HashMap<>();
        feedbackData.put("FeedbackMessage", suggestionEdt.getText().toString());
        feedbackData.put("Ratings", ratingBar.getRating());

        db.collection("Feedbacks")
                .add(feedbackData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(), "Feedback sent", Toast.LENGTH_SHORT).show();
                        suggestionEdt.setText("");
                        ratingBar.setRating(0);
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.actionFeedbackConfirm:
                if(inputIsCorrect()){
                    uploadData();
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}