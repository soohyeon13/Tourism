package com.example.tourism.service;

import android.annotation.SuppressLint;

import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.model.ImageVO;
import com.example.tourism.model.KakaoSearch;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ImageService implements RetrofitService {
    private final FirstViewContract firstViewContract;
    private final KakaoSearch image;

    public ImageService(FirstViewContract firstViewContract, KakaoSearch image) {
        this.firstViewContract = firstViewContract;
        this.image = image;
    }


    @SuppressLint("CheckResult")
    @Override
    public void getData() {
        Observable<ImageVO> observable = image.listImage("제주도 바다");
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(vo -> firstViewContract.showImages(vo.documents),
                        throwable -> firstViewContract.showError(throwable));

    }

}
