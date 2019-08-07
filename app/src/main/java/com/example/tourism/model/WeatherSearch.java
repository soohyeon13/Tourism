package com.example.tourism.model;

import io.reactivex.Observable;
import okhttp3.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherSearch {
    @GET("/data/2.5/weather")
    Observable<WeatherVO> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);
}
