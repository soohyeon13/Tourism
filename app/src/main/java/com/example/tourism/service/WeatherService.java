package com.example.tourism.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.example.tourism.model.WeatherSearch;
import com.example.tourism.model.WeatherVO;
import io.reactivex.Observable;

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
