package com.example.tourism.viewmodel;

import android.view.View;

import androidx.databinding.ObservableField;

import com.example.tourism.contract.ImageRecyclerViewContract;
import com.example.tourism.model.ImageVO;
import com.example.tourism.model.KakaoSearch;

public class ImageItemViewModel {
    public ObservableField<String> repoImageUrl = new ObservableField<>();
    ImageRecyclerViewContract view;
    public ImageItemViewModel(ImageRecyclerViewContract view) {
        this.view = view;
    }

    public void loadItem(ImageVO.Document item) {
        repoImageUrl.set(item.imageUrl);
    }

    public void onItemClick(View itemView) {

    }
}
