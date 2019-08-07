package com.example.tourism.view;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourism.GPSTracker;
import com.example.tourism.R;
import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.databinding.ActivityFirstBinding;
import com.example.tourism.model.ImageVO;
import com.example.tourism.model.KakaoSearch;
import com.example.tourism.model.WeatherService;
import com.example.tourism.model.WeatherVO;
import com.example.tourism.view.adapter.ImageRecyclerAdapter;
import com.example.tourism.view.adapter.WeatherAdapter;
import com.example.tourism.viewmodel.ImageListViewModel;
import com.example.tourism.viewmodel.WeatherViewModel;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.List;

import retrofit2.HttpException;

public class FirstActivity extends AppCompatActivity implements FirstViewContract {

    private ImageRecyclerAdapter imageRecyclerAdapter;
    TextView weatherIcon, country, temp;
    private Typeface weatherFont;
    private final static String PATH_TO_WEATHER_FONT = "fonts/weather.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFirstBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_first);
        final KakaoSearch kakaoSearch = ((KakaoSearchApplication)getApplication()).getKakaoSearch();
        binding.setViewModel(new ImageListViewModel((FirstViewContract) this,kakaoSearch));
        setupViews();
    }

    private void setupViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imageRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        imageRecyclerAdapter = new ImageRecyclerAdapter((Context)this,(FirstViewContract) this);
        recyclerView.setAdapter(imageRecyclerAdapter);


        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation);

        weatherIcon = (TextView)findViewById(R.id.weather_icon_text);
        country = (TextView)findViewById(R.id.country_text);
        temp = (TextView)findViewById(R.id.temp_text);

        weatherFont = Typeface.createFromAsset(getAssets(),PATH_TO_WEATHER_FONT);
        weatherIcon.setTypeface(weatherFont);


    }


    @Override
    public void shwoWeather(WeatherVO weather) {

    }

    @Override
    public void showImages(List<ImageVO.Document> itmes) {
        imageRecyclerAdapter.setItemsAndRefresh(itmes);

    }

    @Override
    public void showError(Throwable e) {

        HttpException error = (HttpException)e;
        try {
            String errorBody = error.response().errorBody().string();
            Log.d("asd", errorBody);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
