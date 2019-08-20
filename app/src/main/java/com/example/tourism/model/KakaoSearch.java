package com.example.tourism.model;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KakaoSearch {
    @GET("/v2/search/image")
    Observable<ImageVO> listImage(@Query("query") String query);
}


