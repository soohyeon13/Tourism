package com.example.tourism.viewmodel;

import android.view.View;

import androidx.databinding.ObservableField;

import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.model.ImageVO;

public class ImageItemViewModel {
    public ObservableField<String> repoImageUrl = new ObservableField<>();
    FirstViewContract view;
    public ImageItemViewModel(FirstViewContract view) {
        this.view = view;
    }

    public void loadItem(ImageVO.Document item) {
        repoImageUrl.set(item.imageUrl);
    }

    public void onItemClick(View itemView) {

    }
}
