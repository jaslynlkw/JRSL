package com.example.jrsl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.homeIcon);

        // Take instance of Action Bar
        // using getSupportActionBar and
        // if it is not Null
        // then call hide function
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

    }
    Home homeFragment = new Home();
    Search searchFragment = new Search();
    Saved savedFragment = new Saved();
    Profile profileFragment = new Profile();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.homeIcon:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
                return true;

            case R.id.searchIcon:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, searchFragment).addToBackStack(null).commit();
                return true;

            case R.id.savedIcon:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, savedFragment).commit();
                return true;

            case R.id.profileIcon:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, profileFragment).addToBackStack(null).commit();
                return true;
        }
        return false;
    }
}