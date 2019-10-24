package com.example.tourism.service;

import android.annotation.SuppressLint;
import com.example.tourism.model.ImageVO;
import com.example.tourism.model.KakaoSearch;

import io.reactivex.Observable;

public class ImageService implements RetrofitService<ImageVO> {
    private final KakaoSearch image;
    private String name;
    public ImageService(KakaoSearch image,String name) {
        this.image = image;
        this.name = name;
    }


    @SuppressLint("CheckResult")
    @Override
    public Observable<ImageVO> getData() {
        return image.listImage(name);
    }
}
