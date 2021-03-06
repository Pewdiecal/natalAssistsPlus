package com.caltech.natalassistsplus;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    private TextView usernameNavView;
    private TextView roleNavView;
    private ImageView profileImg;
    String username;
    String docID;
    String role;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);

        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        username = getIntent().getStringExtra("Username");
        docID = getIntent().getStringExtra("DocID");
        role = getIntent().getStringExtra("Role");

        ViewPagerAdaper viewPagerAdaper = new ViewPagerAdaper(this);
        mDrawer = findViewById(R.id.drawer_layout);
        nvDrawer = findViewById(R.id.nav_view);

        View headerView =  nvDrawer.getHeaderView(0);
        usernameNavView = headerView.findViewById(R.id.usernameNavView);
        roleNavView = headerView.findViewById(R.id.roleNavView);
        profileImg = headerView.findViewById(R.id.profilePicNavView);
        usernameNavView.setText(username);
        roleNavView.setText(role);
        nvDrawer.getMenu().clear();
        if(role.equals("Mother")){
            profileImg.setImageResource(R.drawable.ic_mother);
            nvDrawer.inflateMenu(R.menu.drawer_view);
        } else {
            profileImg.setImageResource(R.drawable.ic_dad);
            nvDrawer.inflateMenu(R.menu.menu_drawer_view_father);
        }

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

                        case R.id.actionLogout:
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                            finish();
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
            case 4:
            case 5:
            case 6:
            case 9:
                getMenuInflater().inflate(R.menu.main_content, menu);
                break;
            case 3:
                getMenuInflater().inflate(R.menu.menu_forum_fragment, menu);
                break;
            case 7:
                getMenuInflater().inflate(R.menu.menu_alarm, menu);
                break;
            case 11:
                getMenuInflater().inflate(R.menu.menu_feedback, menu);
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

            case R.id.actionCreateForum:
                Intent intent = new Intent(this, CreateForumPostActivity.class);
                intent.putExtra("Username", username);
                startActivity(intent);
                break;

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