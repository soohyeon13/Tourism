package com.example.tourism.viewmodel;

import androidx.databinding.ObservableField;

import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.model.WeatherVO;

public class WeatherItemViewModel{
    private FirstViewContract view;
    public ObservableField<String> weatherIcon = new ObservableField<>();
    public ObservableField<String> country = new ObservableField<>();
    public ObservableField<String> temp = new ObservableField<>();

    public WeatherItemViewModel(FirstViewContract view) {
        this.view = view;
    }

    public void loadWeather(WeatherVO item) {
        weatherIcon.set(item.weather.get(0).icon);
        country.set(item.sys.country);
        temp.set(item.main.temp);
    }
}
