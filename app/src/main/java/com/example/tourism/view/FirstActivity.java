package com.example.tourism.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourism.R;
import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.databinding.ActivityFirstBinding;
import com.example.tourism.model.ImageVO;
import com.example.tourism.model.KakaoSearch;
import com.example.tourism.model.WeatherSearch;
import com.example.tourism.model.WeatherVO;
import com.example.tourism.service.GPSService;
import com.example.tourism.service.ImageService;
import com.example.tourism.service.WeatherService;
import com.example.tourism.view.adapter.ImageRecyclerAdapter;
import com.example.tourism.viewmodel.FirstViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FirstActivity extends AppCompatActivity implements FirstViewContract {

    private ImageRecyclerAdapter imageRecyclerAdapter;
//    TextView weatherIcon, country, temp;

//    private Typeface weatherFont;
//    private final static String PATH_TO_WEATHER_FONT = "fonts/weather.ttf";

//    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
//    private static final int PERMISSIONS_REQUEST_CODE = 100;
//    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private FirstViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFirstBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_first);

        final KakaoSearch kakaoSearch = ((TourApplication) getApplication())
                .getData(KakaoSearch.class, new HashMap<String, String>() {{
                    put("Authorization", "KakaoAK" + " " + getResources().getString(R.string.kakao_REST_API_key));
                }});

        final WeatherSearch weatherSearch = ((TourApplication) getApplication()).getData(WeatherSearch.class);

        binding.setViewModel(new FirstViewModel(this, new ImageService(kakaoSearch), new WeatherService(weatherSearch), new GPSService(this)));

        viewModel = binding.getViewModel();
        viewModel.loadImages();
        viewModel.loadWeathers();

        setupViews();

    }

    private void setupViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.imageRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        imageRecyclerAdapter = new ImageRecyclerAdapter((Context) this, (FirstViewContract) this);
        recyclerView.setAdapter(imageRecyclerAdapter);


        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);

//        weatherIcon = (TextView) findViewById(R.id.weather_icon_text);
//        country = (TextView) findViewById(R.id.country_text);
//        temp = (TextView) findViewById(R.id.temp_text);
//        weatherFont = Typeface.createFromAsset(getAssets(), PATH_TO_WEATHER_FONT);
//        weatherIcon.setTypeface(weatherFont);
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case GPS_ENABLE_REQUEST_CODE:
//                if (checkLocationServicesStatus()) {
//                    Log.d("LogLocation", "GPS활성");
//                    checkRunTimePermission();
//                }
//                break;
//        }
//    }
//
//    private void checkRunTimePermission() {
//        int hasFineLocationPermission = ContextCompat.checkSelfPermission(FirstActivity.this,
//                Manifest.permission.ACCESS_FINE_LOCATION);
//        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(FirstActivity.this,
//                Manifest.permission.ACCESS_COARSE_LOCATION);
//
//
//        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
//                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
//
//        } else {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(FirstActivity.this, REQUIRED_PERMISSIONS[0])) {
//                Toast.makeText(FirstActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
//                ActivityCompat.requestPermissions(FirstActivity.this, REQUIRED_PERMISSIONS,
//                        PERMISSIONS_REQUEST_CODE);
//            } else {
//                ActivityCompat.requestPermissions(FirstActivity.this, REQUIRED_PERMISSIONS,
//                        PERMISSIONS_REQUEST_CODE);
//            }
//        }
//    }
//
//    private boolean checkLocationServicesStatus() {
//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
//                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//    }

//    @SuppressLint("SetTextI18n")
//    @Override
//    public void showWeather(WeatherVO weather) {


//        double K = Double.parseDouble(weather.main.temp);
//        double C = K - 273.15;
//        String name = weather.name;
//        temp.setText((Math.round(C)) + "°C");
//
//        switch (weather.weather.get(0).icon) {
//            case "01d":
//                weatherIcon.setText(R.string.wi_day_sunny);
//                break;
//            case "02d":
//                weatherIcon.setText(R.string.wi_cloudy_gusts);
//                break;
//            case "03d":
//                weatherIcon.setText(R.string.wi_cloud_down);
//                break;
//            case "04d":
//                weatherIcon.setText(R.string.wi_cloudy);
//                break;
//            case "04n":
//                weatherIcon.setText(R.string.wi_night_cloudy);
//                break;
//            case "10d":
//                weatherIcon.setText(R.string.wi_day_rain_mix);
//                break;
//            case "11d":
//                weatherIcon.setText(R.string.wi_day_thunderstorm);
//                break;
//            case "13d":
//                weatherIcon.setText(R.string.wi_day_snow);
//                break;
//            case "01n":
//                weatherIcon.setText(R.string.wi_night_clear);
//                break;
//            case "02n":
//                weatherIcon.setText(R.string.wi_night_cloudy);
//                break;
//            case "03n":
//                weatherIcon.setText(R.string.wi_night_cloudy_gusts);
//                break;
//            case "10n":
//                weatherIcon.setText(R.string.wi_night_cloudy_gusts);
//                break;
//            case "11n":
//                weatherIcon.setText(R.string.wi_night_rain);
//                break;
//            case "13n":
//                weatherIcon.setText(R.string.wi_night_snow);
//                break;
//        }

//    }

//    @Override
//    public void showImages(List<ImageVO.Document> itmes) {
//        imageRecyclerAdapter.setItemsAndRefresh(itmes);
//    }
//
//    @Override
//    public void showError(Throwable e) {
//        e.printStackTrace();
//    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(FirstActivity.this, FoodActivity.class);
        startActivity(intent);
    }
}
