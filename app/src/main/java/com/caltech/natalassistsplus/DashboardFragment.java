package com.caltech.natalassistsplus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    RecyclerView menuRecyclerView;
    MenuRecyclerViewAdapter menuRecyclerViewAdapter;
    RecyclerView scheduleRecyclerView;
    ScheduleRecyclerViewAdapter scheduleRecyclerViewAdapter;
    RecyclerView infoRecyclerView;
    InfoRecyclerViewAdapter infoRecyclerViewAdapter;
    RecyclerView.LayoutManager menuLayoutManager;
    RecyclerView.LayoutManager scheduleLayoutManager;
    RecyclerView.LayoutManager infoLayoutManager;
    ArrayList<FoodMenu> foodMenus = new ArrayList<>();
    ArrayList<Schedule> schedules = new ArrayList<>();
    ArrayList<Information> information = new ArrayList<>();

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        foodMenus.add(new FoodMenu("Chili Chicken", R.drawable.chilli_chicken,
                "", "Spicy and tasty"));
        foodMenus.add(new FoodMenu("Butter Chicken", R.drawable.butter_chicken,
                "", "Creamy tasty and easy to make."));
        foodMenus.add(new FoodMenu("Chicken Rice", R.drawable.chicken_rice,
                "", "Authentic traditional taste"));
        foodMenus.add(new FoodMenu("Mac and cheese", R.drawable.mac_cheese,
                "", "Cheesy, creamy and tasty"));
        foodMenus.add(new FoodMenu("Nasi Lemak", R.drawable.nasi_lemak_ayam_goreng,
                "", "Malaysian's favourite breakfast"));
        foodMenus.add(new FoodMenu("Rendang Ayam", R.drawable.rendang_ayam,
                "", "Malay authentic dish"));
        foodMenus.add(new FoodMenu("Scramble egg rice", R.drawable.scramble_egg_rice,
                "", "Protein rich and tasty"));
        foodMenus.add(new FoodMenu("NY steak", R.drawable.steak,
                "", "Tasty western NY steak"));
        foodMenus.add(new FoodMenu("Thai green curry", R.drawable.thai_green_curry,
                "", "Thai's favourite"));

        schedules.add(new Schedule("Ultrasonic checkup", "30/9/2020", "Hospital Putrajaya",
                "12pm", R.drawable.ic_ultrasound));
        schedules.add(new Schedule("Medical checkup", "10/11/2020", "Hospital Putrajaya",
                "9am", R.drawable.ic_heart));
        schedules.add(new Schedule("Clinical checkup", "12/12/2020", "Klinik Putrajaya",
                "10am", R.drawable.ic_hospital));

        information.add(new Information(R.drawable.fitness_bg, R.drawable.ic_gym, "Women fitness",
                "Fitness", "", "Tips to stay fit while pregnant"));
        information.add(new Information(R.drawable.weight, R.drawable.ic_weight_scale, "Weight management",
                "Health", "", "Manage your weight to stay healthy"));
        information.add(new Information(R.drawable.sleep, R.drawable.ic_sleep, "How to get more sleep",
                "Lifestyle", "", "Tips to get more rest"));
        information.add(new Information(R.drawable.love, R.drawable.ic_love, "Importance of having healthy relationship",
                "Daily life", "", "Rules to maintain good relationship with partner"));


        menuRecyclerViewAdapter = new MenuRecyclerViewAdapter(foodMenus);
        scheduleRecyclerViewAdapter = new ScheduleRecyclerViewAdapter(schedules);
        infoRecyclerViewAdapter = new InfoRecyclerViewAdapter(information);

        menuLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);

        infoLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);

        scheduleLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);

        menuRecyclerView = view.findViewById(R.id.menurecyclerView);
        scheduleRecyclerView = view.findViewById(R.id.schedulerecyclerView);
        infoRecyclerView = view.findViewById(R.id.inforecyclerView);

        menuRecyclerView.setHasFixedSize(true);
        scheduleRecyclerView.setHasFixedSize(true);
        infoRecyclerView.setHasFixedSize(true);

        menuRecyclerView.setLayoutManager(menuLayoutManager);
        scheduleRecyclerView.setLayoutManager(scheduleLayoutManager);
        infoRecyclerView.setLayoutManager(infoLayoutManager);

        menuRecyclerView.setAdapter(menuRecyclerViewAdapter);
        scheduleRecyclerView.setAdapter(scheduleRecyclerViewAdapter);
        infoRecyclerView.setAdapter(infoRecyclerViewAdapter);

        return view;
    }
}