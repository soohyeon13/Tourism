package com.example.tourism.model;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KakaoSearch {

    public static class Images {
        public final List<ImageItems> items;

        public Images(List<ImageItems> items) {
            this.items = items;
        }
    }

    public static class ImageItems {
        public final String image_url;

        public ImageItems(String image_url) {
            this.image_url = image_url;
        }
    }

    @GET("/v2/search/image")
    Observable<Images> listImage(@Query("query") String query);

}


