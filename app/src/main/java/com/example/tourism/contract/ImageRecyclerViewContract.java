package com.example.tourism.contract;

import com.example.tourism.model.ImageVO;
import com.example.tourism.model.KakaoSearch;

import java.util.List;

public interface ImageRecyclerViewContract {


    void showImages(List<ImageVO.Document> items);

    void showError(Throwable e);
}
