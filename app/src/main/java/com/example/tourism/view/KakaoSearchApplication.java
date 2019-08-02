package com.example.tourism.view;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.example.tourism.R;
import com.example.tourism.model.KakaoSearch;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class KakaoSearchApplication extends Application {
    private Retrofit retrofit;
    private KakaoSearch kakaoSearch;
    @Override
    public void onCreate() {
        super.onCreate();
        setupAPIClient();
    }

    private void setupAPIClient() {
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                Log.d("API LOG",message);
//            }
//        });
//        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().addHeader("Authorization: ","KakaoAK"+ " " + getResources().getString(R.string.kakao_REST_API_key)).build());
            }
        }).build();

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://apis.daum.net")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        kakaoSearch = retrofit.create(KakaoSearch.class);

    }

    public KakaoSearch kakaoSearch() {
        return kakaoSearch;
    }
}