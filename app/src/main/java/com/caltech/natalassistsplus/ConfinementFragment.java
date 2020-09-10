package com.caltech.natalassistsplus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ConfinementFragment extends Fragment {

    RecyclerView confinementRecyclerView;
    RecyclerView.LayoutManager confinementLayoutManager;
    ConfinementRecyclerViewAdapter confinementRecyclerViewAdapter;
    ArrayList<ConfinementCenter> confinementCenters = new ArrayList<>();

    public ConfinementFragment() {
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
        View view = inflater.inflate(R.layout.fragment_confinement, container, false);
        // Inflate the layout for this fragment
        confinementRecyclerView = view.findViewById(R.id.confinementRV);
        confinementLayoutManager = new LinearLayoutManager(getContext());

        confinementCenters.add(new ConfinementCenter("The Senses Confinement Centre",
                "75, Jalan SS7/2, Ss 7, 47300 Petaling Jaya, Selangor, Malaysia",
                "https://www.thesenses.com.my/", R.drawable.confiment1));
        confinementCenters.add(new ConfinementCenter("享悦产后护理中心",
                "No.1, Jalan Anggerik Vanilla 31/94, Kota Komuning Seksyen 31, 40460 Shah Alam, Selangor, Malaysia",
                "http://www.bestmonth.com.my/", R.drawable.confinement2));
        confinementCenters.add(new ConfinementCenter("12dynasty confinement centre",
                "residence, No. 33, jalan timur 1, D'island, 47130 Puchong, Selangor, Malaysia",
                "https://www.facebook.com/12dynastyconfinement/", R.drawable.confinement3));
        confinementCenters.add(new ConfinementCenter("Lovely Confinement Centre",
                "133, Jalan SS 2/72, SS 2, 47300 Petaling Jaya, Selangor, Malaysia",
                "https://www.facebook.com/LovelyConfinement/", R.drawable.confinement4));
        confinementCenters.add(new ConfinementCenter("BondaCare Confinement centre",
                "No 17, Jln Tanjung Rhu 30/40A, Kota Kemuning, 40460 Shah Alam, Selangor, Malaysia",
                "https://www.facebook.com/bondacareconfinement/?referrer=whatsapp", R.drawable.confinement5));
        confinementCenters.add(new ConfinementCenter("IJ Confinement Centre Puchong",
                "Residence, No.3, Jalan Timur 1, D'island, 47130 Puchong, Selangor, Malaysia",
                "https://www.facebook.com/IJConfinementPuchong/", R.drawable.confinement6));

        confinementRecyclerViewAdapter = new ConfinementRecyclerViewAdapter(confinementCenters);
        confinementRecyclerView.setHasFixedSize(true);
        confinementRecyclerView.setLayoutManager(confinementLayoutManager);
        confinementRecyclerView.setAdapter(confinementRecyclerViewAdapter);

        return view;
    }
}