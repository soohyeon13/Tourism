package com.example.tourism.view;

import android.app.Application;

import com.example.tourism.R;
import com.example.tourism.model.KakaoSearch;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
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

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().addHeader("Authorization: ","KakaoAK"+ " " + getResources().getString(R.string.kakao_REST_API_key)).build());
            }
        }).build();

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://dapi.kakao.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        kakaoSearch = retrofit.create(KakaoSearch.class);

    }

    public KakaoSearch getKakaoSearch() {
        return kakaoSearch;
    }
}