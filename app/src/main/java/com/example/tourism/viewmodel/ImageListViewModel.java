package com.example.tourism.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.tourism.contract.ImageRecyclerViewContract;
import com.example.tourism.model.ImageVO;
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
                for (ImageVO.Document item :
                        vo.getDocuments()) {

                }

                imageRecyclerViewContract.showImages(vo.documents);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                imageRecyclerViewContract.showError(throwable);
            }
        });
    }

}
