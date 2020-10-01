package com.caltech.natalassistsplus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfomationCenterFragment extends Fragment {

    RecyclerView informationRecyclerView;
    RecyclerView.LayoutManager informationLayoutManager;
    GridLayoutManager layoutManager;
    InformationRecyclerViewAdapter informationRecyclerViewAdapter;
    ArrayList<Information> informations = new ArrayList<>();

    public InfomationCenterFragment() {
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
        View view = inflater.inflate(R.layout.fragment_infomation_center, container, false);
        informationRecyclerView = view.findViewById(R.id.informationCenterRV);

        informations.add(new Information(R.drawable.pic1, R.drawable.ic_carrot, "",
                "Healthy Recipies", "","Tips to eat healthy \n" +
                "during pregnancy\n" +
                "to stay fit."));

        informations.add(new Information(R.drawable.pic2, R.drawable.ic_baby, "",
                "Baby care", "","How to take care\n" +
                "of new born \n" +
                "baby easily."));

        informations.add(new Information(R.drawable.pic3, R.drawable.ic_mother, "",
                "Women Fashions", "","20 Tips For \n" +
                "Maternity Fashion For \n" +
                "Every Mom-To-Be."));

        informations.add(new Information(R.drawable.pic4, R.drawable.ic_flat, "",
                "Women care", "","A few easy message\n" +
                "tips that husband\n" +
                "can learn at home."));

        layoutManager = new GridLayoutManager(getActivity(), 2);
        informationRecyclerViewAdapter = new InformationRecyclerViewAdapter(informations);
        informationRecyclerView.setHasFixedSize(true);
        informationLayoutManager = new LinearLayoutManager(getContext());
        informationRecyclerView.setLayoutManager(layoutManager);
        informationRecyclerView.setAdapter(informationRecyclerViewAdapter);

        return view;
    }
}