package com.example.tourism.viewmodel.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tourism.data.FoodEntity;
import com.example.tourism.data.TourEntity;
import com.example.tourism.data.dao.FoodDao;
import com.example.tourism.data.dao.TourDao;
import com.example.tourism.data.database.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FragmentFoodListViewModel extends AndroidViewModel {
    private final FoodDao foodDao;
    private final TourDao tourDao;
    private final ExecutorService executorService;
    public FragmentFoodListViewModel(@NonNull Application application) {
        super(application);
        foodDao = AppDatabase.getInstance(application).foodDao();
        tourDao = AppDatabase.getInstance(application).tourDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<FoodEntity>> getFoodLikeList(String city, String location){return foodDao.findSelectedCateFood(city,location);}
    public LiveData<List<TourEntity>> getTourLikeList(String city, String location){return tourDao.findSelectedCateTour(city,location);}

}
