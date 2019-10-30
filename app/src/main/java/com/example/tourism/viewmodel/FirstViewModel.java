package com.example.tourism.viewmodel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.example.tourism.R;
import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.model.GPSVO;
import com.example.tourism.model.WeatherVO;
import com.example.tourism.service.GPSService;
import com.example.tourism.service.WeatherService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class FirstViewModel implements BottomNavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = FirstViewModel.class.getSimpleName();
    private final GPSService gpsService;
    public final Observable<WeatherVO> weatherObservable;
    public final ObservableField<String> address = new ObservableField<>();
    public final ObservableField<String> temperature = new ObservableField<>();
    private WeatherService weatherService;
    private FirstViewContract firstViewContract;
    private double K, C;
    private final GPSVO gpsvo;

    public FirstViewModel(FirstViewContract firstViewContract,WeatherService weatherService, GPSService gpsService) {
        this.firstViewContract = firstViewContract;
        this.weatherService = weatherService;
        this.gpsService = gpsService;

        gpsvo = gpsService.getGPS();
        weatherService.setLatitude(gpsvo.getLatitude());
        weatherService.setLongitude(gpsvo.getLongitude());
        weatherObservable = weatherService.getData();
    }

    public ObservableField<String> getTemperature() {
        return temperature;
    }

    public ObservableField<String> getAddress() { return address; }


    @SuppressLint("CheckResult")
    public void loadWeathers() {
        weatherObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherVO -> {
                    K = Double.parseDouble(weatherVO.main.temp);
                    C = K - 273.15;
                    temperature.set((Math.round(C)) + "°C");
                    address.set(gpsvo.getAddress());
                    firstViewContract.showWeather(weatherVO);
                }, Throwable::printStackTrace);
    }

    public void onFoodClick(View view) { firstViewContract.onClick(view); }

    public void onTourClick(View view) {
        firstViewContract.onClick(view);
    }

    public void onClick(View view) {
        view.setOnClickListener(v -> Log.d(TAG, "onClick: " + view.getId()));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

//    @Override
//    public boolean onNavigationItemSelected(@androidx.annotation.NonNull MenuItem item) {
//        return firstViewContract.onNaviClick(item);
//    }


}
