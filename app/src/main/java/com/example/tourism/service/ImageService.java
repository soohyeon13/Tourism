package com.example.tourism.service;

import android.annotation.SuppressLint;
import com.example.tourism.model.ImageVO;
import com.example.tourism.model.KakaoSearch;

import io.reactivex.Observable;

public class ImageService implements RetrofitService<ImageVO> {
    private final KakaoSearch image;

    public ImageService(KakaoSearch image) {
        this.image = image;
    }


    @SuppressLint("CheckResult")
    @Override
    public Observable<ImageVO> getData() {
        return image.listImage("제주도 바다");
    }
}
