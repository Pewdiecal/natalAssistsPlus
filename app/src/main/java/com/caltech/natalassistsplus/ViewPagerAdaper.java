package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdaper extends FragmentStateAdapter {
    Fragment[] fragments = new Fragment[12];
    public ViewPagerAdaper(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment dashboard = new DashboardFragment();
        Fragment confinementCenter = new ConfinementFragment();
        Fragment costPlanner = new CostPlannerFragment();
        Fragment forum = new ForumFragment();
        Fragment infomationCenter = new InfomationCenterFragment();
        Fragment contractionCounter = new ContractionCounterFragment();
        Fragment babyKick = new BabyKickFragment();
        Fragment sleepAlarm = new SleepAlarmFragment();
        Fragment chatroom = new ChatroomFragment();
        Fragment medicalMatters = new MedicalMattersFragment();
        Fragment relaxationMusic = new RelaxationMusicFragment();
        Fragment feedback = new FeedbackFragment();

        fragments[0] = dashboard;
        fragments[1] = confinementCenter;
        fragments[2] = costPlanner;
        fragments[3] = forum;
        fragments[4] = infomationCenter;
        fragments[5] = contractionCounter;
        fragments[6] = babyKick;
        fragments[7] = sleepAlarm;
        fragments[8] = chatroom;
        fragments[9] = medicalMatters;
        fragments[10] = relaxationMusic;
        fragments[11] = feedback;


        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}
