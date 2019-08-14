package com.example.tourism.service;

import android.annotation.SuppressLint;

import com.example.tourism.model.ImageVO;

import io.reactivex.Observable;

public interface RetrofitService<E> {
    @SuppressLint("CheckResult")
    Observable<E> getData();
}
