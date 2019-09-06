package com.example.tourism.viewmodel;

import android.view.View;

import androidx.databinding.ObservableField;

import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.contract.ImageContract;
import com.example.tourism.model.ImageVO;

public class ImageItemViewModel {
    public ObservableField<String> repoImageUrl = new ObservableField<>();
    ImageContract view;
    public ImageItemViewModel(ImageContract view) {
        this.view = view;
    }

    public void loadItem(ImageVO.Document item) {
        repoImageUrl.set(item.imageUrl);
    }

    public void onItemClick(View itemView) {

    }
}
