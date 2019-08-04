package com.example.tourism.contract;

import com.example.tourism.model.KakaoSearch;

public interface ImageRecyclerViewContract {
    void showImages(KakaoSearch.Images images);

    void showError(Throwable e);
}
