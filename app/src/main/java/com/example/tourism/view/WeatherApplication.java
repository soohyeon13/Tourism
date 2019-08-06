package com.example.tourism.view;

import android.app.Application;

import com.example.tourism.model.WeatherService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApplication extends Application {
    private String BaseUrl = "http://api.openweathermap.org/";
    private WeatherService weatherService;

    @Override
    public void onCreate() {
        super.onCreate();
        setupAPIClient();
    }

    private void setupAPIClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherService = retrofit.create(WeatherService.class);

    }

    public WeatherService getWeatherService() {return weatherService;}
}
