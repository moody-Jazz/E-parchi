package com.example.e_parchi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    FragmentManager fragmentManager;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //below command forces the device to use light theme even if its in dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawerLayout, toolbar,
                R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);

        //setting color of hamburger icon to white
        toggle.getDrawerArrowDrawable().setColor(getColor(android.R.color.white));

        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);



        // Get a reference to the BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //shows original colors of icons
        bottomNavigationView.setItemIconTintList(null);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation item clicks here
                int itemId = item.getItemId();
                if(itemId == R.id.home){
                    openFragment(new HomeFragment());
                    return true;
                } else if (itemId == R.id.history) {
                    openFragment(new HistoryFragment());
                    return true;
                }else if (itemId == R.id.your_info) {
                    openFragment(new InfoFragment());
                    return true;
                }else if(itemId == R.id.add_record){
                    openFragment(new RecordsFragment());
                    return true;
                }
                return false;
            }
        });
        fragmentManager = getSupportFragmentManager();
        openFragment(new HomeFragment());
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       int itemId = item.getItemId();
       if(itemId == R.id.settings){
           openFragment(new SettingsFragment());
       }else if (itemId == R.id.log_out) {
           openFragment(new LogOutFragment());
       }else if (itemId == R.id.drawer_home) {
           openFragment(new HomeFragment());
       }else if(itemId == R.id.about){
           openFragment(new AboutFragment());
       }else if(itemId == R.id.share){
           Intent intent = new Intent(Intent.ACTION_SEND);
           intent.setType("text/plain");
           intent.putExtra(Intent.EXTRA_SUBJECT,"Check out this cool App");
           intent.putExtra(Intent.EXTRA_TEXT,"Application's link");
           startActivity(intent);
       }
       drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    public void openFragment(Fragment fragment){
       FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }
}