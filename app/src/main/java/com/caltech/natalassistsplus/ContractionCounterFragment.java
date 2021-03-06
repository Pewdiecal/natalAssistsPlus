package com.caltech.natalassistsplus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ContractionCounterFragment extends Fragment {

    TextView timer;
    Button contractionStartedBtn;
    RecyclerView contractionRecyclerView;
    RecyclerView.LayoutManager contractionLayoutManager;
    ContractionRecyclerViewAdapter contractionRecyclerViewAdapter;
    ArrayList<ContractionCount> contractionCounts = new ArrayList<>();
    private int seconds = 0;
    private boolean running = false;

    public ContractionCounterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contraction_counter, container, false);
        timer = view.findViewById(R.id.timerContractionTxt);
        contractionStartedBtn = view.findViewById(R.id.contractionBtn);
        runTimer();
        contractionStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
                if(contractionStartedBtn.getText().toString().equals("Contraction Started")){
                    contractionStartedBtn.setText("Contraction Stop");
                    running = true;
                } else {
                    contractionStartedBtn.setText("Contraction Started");
                    running = false;
                    seconds = 0;
                    contractionCounts.add(new ContractionCount(simpleDateFormat.format(new Date()), timer.getText().toString()));
                    contractionRecyclerViewAdapter.updateData();
                }

            }
        });

        contractionRecyclerView = view.findViewById(R.id.contractionRV);
        contractionLayoutManager = new LinearLayoutManager(getContext());
        contractionRecyclerViewAdapter = new ContractionRecyclerViewAdapter(contractionCounts);
        contractionRecyclerView.setLayoutManager(contractionLayoutManager);
        contractionRecyclerView.setAdapter(contractionRecyclerViewAdapter);

        return view;
    }

    private void runTimer()
    {
        // Creates a new Handler
        final Handler handler
                = new Handler(Looper.getMainLooper());

        handler.post(new Runnable() {

            @Override
            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // Format the seconds into hours, minutes,
                // and seconds.
                String time
                        = String
                        .format(Locale.getDefault(),
                                "%02d:%02d:%02d", hours,
                                minutes, secs);

                // Set the text view text.


                // If running is true, increment the
                // seconds variable.
                if (running) {
                    timer.setText(time);
                    seconds++;
                }

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }
}