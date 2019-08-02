package com.example.tourism.viewmodel;

import android.annotation.SuppressLint;

import com.example.tourism.contract.ImageRecyclerViewContract;
import com.example.tourism.model.KakaoSearch;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.Observable;

import io.reactivex.schedulers.Schedulers;

public class ImageListViewModel {
    private final ImageRecyclerViewContract imageRecyclerViewContract;
    private final KakaoSearch image;

    public ImageListViewModel(ImageRecyclerViewContract imageRecyclerViewContract, KakaoSearch image) {
        this.imageRecyclerViewContract = imageRecyclerViewContract;
        this.image = image;
    }


    @SuppressLint("CheckResult")
    private void loadImages() {
        Observable observable = image.listImage("제주인기관광지");
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<KakaoSearch.Images>() {

            @Override
            public void accept(KakaoSearch.Images images) throws Exception {
                imageRecyclerViewContract.showImages(images);
            }

        });
    }
}
