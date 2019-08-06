package com.example.tourism.contract;

import com.example.tourism.model.ImageVO;
import com.example.tourism.model.KakaoSearch;
import com.example.tourism.model.WeatherVO;

import java.util.List;

public interface FirstViewContract {
    void shwoWeather(WeatherVO.Main weather );

    void showImages(List<ImageVO.Document> items);

    void showError(Throwable e);
}
