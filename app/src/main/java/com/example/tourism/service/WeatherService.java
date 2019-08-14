package com.example.tourism.service;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.tourism.GPSTracker;
import com.example.tourism.R;
import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.model.WeatherSearch;
import com.example.tourism.model.WeatherVO;
import com.example.tourism.view.FirstActivity;
import com.highbryds.gpstracker.GPSService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherService extends Service implements RetrofitService<WeatherVO> {
    private final WeatherSearch weatherSearch;
    private String App_Id = "dc30cb9f6d62581f6c4159dbdbc95bff";
    private double latitude;
    private double longitude;

    public WeatherService(WeatherSearch weatherSearch) {
        this.weatherSearch = weatherSearch;
    }


    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public Observable<WeatherVO> getData() {
        return weatherSearch.getCurrentWeatherData(String.valueOf(latitude), String.valueOf(longitude), App_Id);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
