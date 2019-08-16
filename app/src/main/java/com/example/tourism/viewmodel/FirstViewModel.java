package com.example.tourism.viewmodel;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.databinding.ObservableField;

import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.model.GPSVO;
import com.example.tourism.model.ImageVO;
import com.example.tourism.model.WeatherVO;
import com.example.tourism.service.GPSService;
import com.example.tourism.service.ImageService;
import com.example.tourism.service.WeatherService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class FirstViewModel {
    private final GPSService gpsService;
    public final Observable<WeatherVO> weatherObservable;
    public final ObservableField<String> address = new ObservableField<>();
    public final ObservableField<String> temperature = new ObservableField<>();
    public final Observable<ImageVO> imageVOObservable;
    private ImageService imageService;
    private WeatherService weatherService;
    private FirstViewContract firstViewContract;
    private double K, C;
    private final GPSVO gpsvo;

    public FirstViewModel(FirstViewContract firstViewContract, ImageService imageService, WeatherService weatherService, GPSService gpsService) {
        this.firstViewContract = firstViewContract;
        this.imageService = imageService;
        this.weatherService = weatherService;
        this.gpsService = gpsService;

        gpsvo = gpsService.getGPS();
        weatherService.setLatitude(gpsvo.getLatitude());
        weatherService.setLongitude(gpsvo.getLongitude());
        weatherObservable = weatherService.getData();
        imageVOObservable = imageService.getData();
    }

    public ObservableField<String> getTemperature() {
        return temperature;
    }

    public ObservableField<String> getAddress() { return address; }

    @SuppressLint("CheckResult")
    public void loadImages() {
        imageVOObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(vo -> {
                    firstViewContract.showImages(vo.documents);
                });
    }

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

    public void onFoodClick(View view) {
        firstViewContract.onClick(view);
    }

    public void onTourClick(View view) {
        firstViewContract.onClick(view);
    }

}
