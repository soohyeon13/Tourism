package com.example.tourism.model;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KakaoSearch {
    @GET("/v2/search/image")
    Observable<ResultVO> listImage(@Query("query") String query);
}


