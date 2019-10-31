package com.example.tourism.viewmodel.catefragment;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tourism.contract.FoodSmallCateContract;
import com.example.tourism.data.FoodEntity;
import com.example.tourism.data.dao.FoodDao;
import com.example.tourism.data.database.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FoodCateFragmentViewModel extends AndroidViewModel {
    private static final String TAG = FoodCateFragmentViewModel.class.getSimpleName();
    private final FoodSmallCateContract contract;

    public FoodCateFragmentViewModel(@NonNull Application application, FoodSmallCateContract contract) {
        super(application);
        this.contract =contract;


    }
    public void onClick(View view) {
        contract.btnClick(view);
    }
}
