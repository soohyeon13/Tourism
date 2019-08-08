package com.example.tourism.view;

import android.app.Application;
import android.util.Log;

import com.example.tourism.R;
import com.example.tourism.model.WeatherSearch;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TourApplication extends Application {
    private String WeatherBaseUrl = "http://api.openweathermap.org/";
    private String ImageBaseUrl = "https://dapi.kakao.com";

    @Override
    public void onCreate() {
        super.onCreate();
    }



    public <T> T getData(Class<T> service) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(s -> Log.d("API LOG", s));
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
        return getRetrofitObject(service, client,WeatherBaseUrl);

    }


    public <T> T getData(Class<T> service, Map<String, String> headerParams) {
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                for(String key : headerParams.keySet()) {
                    builder.addHeader(key, headerParams.get(key));
                }

                return chain.proceed(builder.build());
            }
        }).build();
        return getRetrofitObject(service, client,ImageBaseUrl);
    }

    private <T> T getRetrofitObject(Class<T> service, OkHttpClient client, String url) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(service);
    }
}
