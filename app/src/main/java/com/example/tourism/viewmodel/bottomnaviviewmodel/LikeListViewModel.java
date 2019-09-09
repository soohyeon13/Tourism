package com.example.tourism.viewmodel.bottomnaviviewmodel;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.annotations.NonNull;
import com.example.tourism.contract.LikeListContract;
import com.example.tourism.data.FoodEntity;
import com.example.tourism.data.TourEntity;
import com.example.tourism.data.dao.FoodDao;
import com.example.tourism.data.dao.TourDao;
import com.example.tourism.data.database.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LikeListViewModel extends AndroidViewModel {
    private final LikeListContract likeListContract;
    private final FoodDao foodDao;
    private final TourDao tourDao;
    private final ExecutorService executorService;

    public LikeListViewModel(@NonNull Application application, LikeListContract likeListContract) {
        super(application);
        this.likeListContract = likeListContract;
        foodDao = AppDatabase.getInstance(application).foodDao();
        tourDao = AppDatabase.getInstance(application).tourDao();
        executorService = Executors.newSingleThreadExecutor();
    }
    public LiveData<List<FoodEntity>> getFoodLikeList(String city,String location){return foodDao.findSelectedCateFood(city,location);}
    public LiveData<List<TourEntity>> getTourLikeList(String city,String location){return tourDao.findSelectedCateTour(city,location);}

    public void onFoodListClick(View view) {
        likeListContract.listClick(view);
    }

    public void onTourListClick(View view) {
        likeListContract.listClick(view);
    }
}
