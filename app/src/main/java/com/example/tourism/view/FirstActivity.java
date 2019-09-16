package com.example.tourism.view;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tourism.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;
import java.util.Optional;

public class FirstActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        setupViews();

    }

    private void setupViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            NavHostFragment host = Optional.ofNullable((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.view_controller)).orElseThrow(Exception::new);
            navController = host.getNavController();
            bottomNavigationView.setOnNavigationItemSelectedListener(this::onNaviClick);
        } catch (Throwable ignored) {
            Log.d("FirstActivity", String.valueOf(ignored));
        }
    }

    public boolean onNaviClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.return_home_menu:
                navController.navigate(R.id.homeFragment);
                return true;
            case R.id.like_list_menu:
                navController.navigate(R.id.likeMainFragment);
                return true;
            case R.id.search_bus_menu:
                navController.navigate(R.id.busSearchFragment);
                return true;

        }
        return false;
    }
}
