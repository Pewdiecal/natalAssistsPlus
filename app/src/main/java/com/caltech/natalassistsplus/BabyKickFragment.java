package com.caltech.natalassistsplus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BabyKickFragment extends Fragment {

    RecyclerView babyKickRecyclerView;
    RecyclerView.LayoutManager babyKickLayoutManager;
    BabyKickRecyclerViewAdapter babyKickRecyclerViewAdapter;
    ArrayList<BabyKickCount> babyKickCounts = new ArrayList<>();

    Button kickStartedBtn;
    Button kickedBtn;
    TextView timerTxt;

    private int seconds = 0;
    private boolean running = false;
    private int kicks = 0;

    public BabyKickFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baby_kick, container, false);
        kickStartedBtn = view.findViewById(R.id.kickStartedBtn);
        kickedBtn = view.findViewById(R.id.kickedBtn);
        timerTxt = view.findViewById(R.id.babyKickTimerTxt);
        babyKickRecyclerView = view.findViewById(R.id.babyKickRV);
        kickedBtn.setEnabled(false);

        babyKickRecyclerViewAdapter = new BabyKickRecyclerViewAdapter(babyKickCounts);
        babyKickLayoutManager = new LinearLayoutManager(getContext());
        babyKickRecyclerView.setHasFixedSize(true);
        babyKickRecyclerView.setLayoutManager(babyKickLayoutManager);
        babyKickRecyclerView.setAdapter(babyKickRecyclerViewAdapter);
        runTimer();

        kickStartedBtn.setOnClickListener(v -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
            if(kickStartedBtn.getText().toString().equals("Kick Started")){
                kickedBtn.setEnabled(true);
                kickStartedBtn.setText("Kick Stopped");
                running = true;
                kicks++;
            } else {
                babyKickCounts.add(new BabyKickCount(simpleDateFormat.format(new Date()), timerTxt.getText().toString(), kicks));
                kickStartedBtn.setText("Kick Started");
                kickedBtn.setEnabled(false);
                timerTxt.setText("00:00:00");
                running = false;
                seconds = 0;
                kicks = 0;
                babyKickRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        kickedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kicks++;
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void runTimer()
    {

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
                timerTxt.setText(time);

                // If running is true, increment the
                // seconds variable.
                if (running) {
                    seconds++;
                }

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }
}