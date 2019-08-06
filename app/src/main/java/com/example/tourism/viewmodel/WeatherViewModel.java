package com.example.tourism.viewmodel;

import android.annotation.SuppressLint;

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

    public WeatherViewModel(FirstViewContract firstViewContract, WeatherService weatherService) {
        this.firstViewContract = firstViewContract;
        this.weatherService = weatherService;
        loadWeather();
    }

    @SuppressLint("CheckResult")
    private void loadWeather() {
        Observable<WeatherVO> observable = weatherService.getCurrentWeatherData("35" , " 149","ㅁㄴㅇ");
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherVO>() {
                    @Override
                    public void accept(WeatherVO weatherVO) throws Exception {
                        firstViewContract.shwoWeather(weatherVO.main);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        firstViewContract.showError(throwable);
                    }
                });
    }

}
