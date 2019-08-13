package com.example.tourism.viewmodel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.tourism.R;
import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.model.KakaoSearch;
import com.example.tourism.model.WeatherSearch;
import com.example.tourism.service.ImageService;
import com.example.tourism.service.WeatherService;
import com.example.tourism.view.FirstActivity;
import com.example.tourism.view.FoodActivity;

public class FirstViewModel{
    private ImageService imageService;
    private WeatherService weatherService;
    public FirstViewModel(FirstActivity firstActivity, KakaoSearch image, WeatherSearch weatherSearch) {
        this.imageService = new ImageService(firstActivity, image);
        this.weatherService = new WeatherService(firstActivity, weatherSearch, firstActivity);
    }


    @SuppressLint("CheckResult")
    public void loadImages() { imageService.getData(); }
    @SuppressLint("CheckResult")
    public void loadWeathers() { weatherService.getData(); }

}
