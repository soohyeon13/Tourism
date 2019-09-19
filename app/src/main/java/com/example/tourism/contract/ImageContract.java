package com.example.tourism.contract;

import android.view.View;

import com.example.tourism.model.ImageVO;

import java.util.List;

public interface ImageContract {
    void showImages(List<ImageVO.Document> items);

    void onClick(double la,double lo,String name);
}
