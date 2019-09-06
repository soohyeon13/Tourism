package com.example.tourism.contract;

import com.example.tourism.model.ImageVO;

import java.util.List;

public interface ImageContract {
    void showImages(List<ImageVO.Document> items);
}
