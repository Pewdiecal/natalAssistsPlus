package com.caltech.natalassistsplus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SleepAlarmFragment extends Fragment {

    RecyclerView alarmRecyclerView;
    RecyclerView.LayoutManager alarmLayoutManager;
    AlarmRecyclerViewAdapter alarmRecyclerViewAdapter;
    ArrayList<AlarmInfo> alarmInfos = new ArrayList<>();

    public SleepAlarmFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sleep_alarm, container, false);
        alarmRecyclerView = view.findViewById(R.id.alarmRV);
        alarmLayoutManager = new LinearLayoutManager(getContext());
        alarmRecyclerViewAdapter = new AlarmRecyclerViewAdapter(alarmInfos);
        alarmRecyclerView.setLayoutManager(alarmLayoutManager);
        alarmRecyclerView.setAdapter(alarmRecyclerViewAdapter);
        alarmRecyclerView.setHasFixedSize(true);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        alarmRecyclerViewAdapter.notifyDataSetChanged();
    }
}