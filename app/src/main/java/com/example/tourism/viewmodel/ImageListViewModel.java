package com.example.tourism.viewmodel;

import android.annotation.SuppressLint;

import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.model.ImageVO;
import com.example.tourism.model.KakaoSearch;
import com.example.tourism.model.WeatherService;
import com.example.tourism.model.WeatherVO;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.Observable;

import io.reactivex.schedulers.Schedulers;

public class ImageListViewModel {
    private final FirstViewContract firstViewContract;
    private final KakaoSearch image;

    public ImageListViewModel(FirstViewContract firstViewContract, KakaoSearch image) {
        this.firstViewContract = firstViewContract;
        this.image = image;
        loadImages();
    }


    @SuppressLint("CheckResult")
    private void loadImages() {
        Observable<ImageVO> observable = image.listImage("제주인기관광지");
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ImageVO>() {

            @Override
            public void accept(ImageVO vo) throws Exception {
                firstViewContract.showImages(vo.documents);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                firstViewContract.showError(throwable);
            }
        });

    }

}
