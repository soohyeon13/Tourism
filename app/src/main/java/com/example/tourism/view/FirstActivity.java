package com.example.tourism.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FirstActivity extends AppCompatActivity implements FirstViewContract {

    private ImageRecyclerAdapter imageRecyclerAdapter;
    TextView weatherIcon;
    private Typeface weatherFont;
    private final static String PATH_TO_WEATHER_FONT = "fonts/weather.ttf";
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

        SnapHelper snapHelper;
        snapHelper = new LinearSnapHelper();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.imageRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        snapHelper.attachToRecyclerView(recyclerView);
        imageRecyclerAdapter = new ImageRecyclerAdapter((Context) this, (FirstViewContract) this);
        recyclerView.setAdapter(imageRecyclerAdapter);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);

        weatherIcon = (TextView) findViewById(R.id.weather_icon_text);
        weatherFont = Typeface.createFromAsset(getAssets(), PATH_TO_WEATHER_FONT);
        weatherIcon.setTypeface(weatherFont);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showWeather(WeatherVO weather) {
        switch (weather.weather.get(0).icon) {
            case "01d":
                weatherIcon.setText(R.string.wi_day_sunny);
                break;
            case "02d":
                weatherIcon.setText(R.string.wi_cloudy_gusts);
                break;
            case "03d":
                weatherIcon.setText(R.string.wi_cloud_down);
                break;
            case "04d":
                weatherIcon.setText(R.string.wi_cloudy);
                break;
            case "04n":
                weatherIcon.setText(R.string.wi_night_cloudy);
                break;
            case "10d":
                weatherIcon.setText(R.string.wi_day_rain_mix);
                break;
            case "11d":
                weatherIcon.setText(R.string.wi_day_thunderstorm);
                break;
            case "13d":
                weatherIcon.setText(R.string.wi_day_snow);
                break;
            case "01n":
                weatherIcon.setText(R.string.wi_night_clear);
                break;
            case "02n":
                weatherIcon.setText(R.string.wi_night_cloudy);
                break;
            case "03n":
                weatherIcon.setText(R.string.wi_night_cloudy_gusts);
                break;
            case "10n":
                weatherIcon.setText(R.string.wi_night_cloudy_gusts);
                break;
            case "11n":
                weatherIcon.setText(R.string.wi_night_rain);
                break;
            case "13n":
                weatherIcon.setText(R.string.wi_night_snow);
                break;
        }
    }

    @Override
    public void showImages(List<ImageVO.Document> itmes) {
        imageRecyclerAdapter.setItemsAndRefresh(itmes);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.foodLayout) {
            Intent intent = new Intent(FirstActivity.this, FoodActivity.class);
            startActivity(intent);
        }else {
            Intent intent1 = new Intent(FirstActivity.this,TourActivity.class);
            startActivity(intent1);
        }

    }
}
