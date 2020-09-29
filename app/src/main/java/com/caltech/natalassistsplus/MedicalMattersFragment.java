package com.caltech.natalassistsplus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MedicalMattersFragment extends Fragment {

    RecyclerView medicalMenuRecyclerView;
    RecyclerView.LayoutManager medicalLayoutManager;
    MedicalRecyclerViewAdapter medicalRecyclerViewAdapter;

    public MedicalMattersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medical_matters, container, false);
        medicalMenuRecyclerView = view.findViewById(R.id.medicalMenuRV);
        medicalLayoutManager = new LinearLayoutManager(getContext());
        medicalRecyclerViewAdapter = new MedicalRecyclerViewAdapter();
        medicalMenuRecyclerView.setHasFixedSize(true);
        medicalMenuRecyclerView.setAdapter(medicalRecyclerViewAdapter);

        return view;
    }
}