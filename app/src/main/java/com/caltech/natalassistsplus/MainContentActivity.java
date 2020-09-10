package com.caltech.natalassistsplus;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainContentActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ViewPager2 viewPager2;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);

        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ViewPagerAdaper viewPagerAdaper = new ViewPagerAdaper(this);
        mDrawer = findViewById(R.id.drawer_layout);
        nvDrawer = findViewById(R.id.nav_view);
        viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setAdapter(viewPagerAdaper);
        viewPager2.setUserInputEnabled(false);
        nvDrawer.getMenu().getItem(0).setChecked(true);
        drawerToggle = setupDrawerToggle();

        // Setup toggle to display hamburger icon with nice animation
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);
        nvDrawer.setNavigationItemSelectedListener(
                menuItem -> {
                    switch (menuItem.getItemId()){
                        case R.id.dashboard_menu:
                            viewPager2.setCurrentItem(0, false);
                            break;

                        case R.id.confinement_menu:
                            viewPager2.setCurrentItem(1, false);
                            break;

                        case R.id.cost_planner_menu:
                            viewPager2.setCurrentItem(2, false);
                            break;

                        case R.id.forum_menu:
                            viewPager2.setCurrentItem(3,false);
                            break;

                        case R.id.info_menu:
                            viewPager2.setCurrentItem(4, false);
                            break;

                        case R.id.contraction_menu:
                            viewPager2.setCurrentItem(5, false);
                            break;

                        case R.id.kick_menu:
                            viewPager2.setCurrentItem(6, false);
                            break;

                        case R.id.alarm_menu:
                            viewPager2.setCurrentItem(7, false);
                            break;

                        case R.id.chatroom_menu:
                            viewPager2.setCurrentItem(8, false);
                            break;

                        case R.id.medical_menu:
                            viewPager2.setCurrentItem(9, false);
                            break;

                        case R.id.music_menu:
                            viewPager2.setCurrentItem(10, false);
                            break;

                        case R.id.feedback_menu:
                            viewPager2.setCurrentItem(11, false);
                            break;
                    }
                    menuItem.setChecked(true);
                    // Set action bar title
                    setTitle(menuItem.getTitle());
                    // Close the navigation drawer
                    mDrawer.closeDrawers();
                    return true;
                });

    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        switch (viewPager2.getCurrentItem()){
            case 1:
                getMenuInflater().inflate(R.menu.main_content, menu);
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }
}