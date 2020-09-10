package com.caltech.natalassistsplus;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdaper extends FragmentStateAdapter {
    Fragment[] fragments = new Fragment[1];
    public ViewPagerAdaper(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment dashboard = new DashboardFragment();
        fragments[0] = dashboard;
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}
