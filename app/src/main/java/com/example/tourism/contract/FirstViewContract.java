package com.example.tourism.contract;

import android.view.MenuItem;
import android.view.View;

import com.example.tourism.model.ImageVO;
import com.example.tourism.model.WeatherVO;

import java.util.List;

public interface FirstViewContract {
    void showWeather(WeatherVO weather );

//    void showImages(List<ImageVO.Document> items);

    void onClick(View view);

    boolean onNaviClick(MenuItem item);

}
