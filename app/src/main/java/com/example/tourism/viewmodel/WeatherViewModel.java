package com.example.tourism.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.tourism.GPSTracker;
import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.model.WeatherService;
import com.example.tourism.model.WeatherVO;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WeatherViewModel {

    private final FirstViewContract firstViewContract;
    private final WeatherService weatherService;
    private Context context;

    private String App_Id = "dc30cb9f6d62581f6c4159dbdbc95bff";

    public WeatherViewModel(Context context,FirstViewContract firstViewContract, WeatherService weatherService) {
        this.context =context;
        this.firstViewContract = firstViewContract;
        this.weatherService = weatherService;
        loadWeather();
    }

    GPSTracker gpsTracker = new GPSTracker(context);

    @SuppressLint("CheckResult")
    private void loadWeather() {
        Observable<WeatherVO> observable = (Observable<WeatherVO>) weatherService.getCurrentWeatherData(gpsTracker.getLatitude() , gpsTracker.getLongitude(),App_Id);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherVO>() {
                    @Override
                    public void accept(WeatherVO weatherVO) throws Exception {
                        firstViewContract.shwoWeather(weatherVO);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        firstViewContract.showError(throwable);
                    }
                });
    }

}
