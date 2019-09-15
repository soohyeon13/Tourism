package com.example.tourism.view;

import android.os.Bundle;
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
//        ActivityFirstBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_first);

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
//            NavigationUI.setupWithNavController(bottomNavigationView, navController);
            bottomNavigationView.setOnNavigationItemSelectedListener(this::onNaviClick);
        } catch (Throwable ignored) {
        }
//        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
//        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);


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
//        return false;
//        switch (item.getItemId()) {
//            case R.id.return_home_menu:
//                Log.d("####1",String.valueOf(item.getItemId()));
//                return true;
//            case R.id.like_list_menu:
//                Log.d("####3",String.valueOf(item.getItemId()));
//                Intent intent1 = new Intent(FirstActivity.this, ActivityLikeList.class);
//                startActivity(intent1);
//                return true;
//            case R.id.search_bus_menu:
//                String url = "daummaps://roadView?p=37.537229,127.005515";
//                String download = "download path";
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                startActivity(intent);
////                intent.addCategory(Intent.CATEGORY_BROWSABLE);
////                List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
////                if (list == null || list.isEmpty()) {
////                    this.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(download)));
////                }else {
////                    this.startActivity(intent);
////                }
//                Log.d("####4",String.valueOf(item.getItemId()));
//                return true;
//        }
        return false;
    }
}
