package com.example.tourism.viewmodel;

import android.app.Application;
import android.databinding.tool.util.L;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tourism.data.FoodEntity;
import com.example.tourism.data.dao.FoodDao;
import com.example.tourism.data.database.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FoodViewModel extends AndroidViewModel{

    private FoodDao foodDao;
    private ExecutorService executorService;

    public FoodViewModel(@NonNull Application application) {
        super(application);
        foodDao = AppDatabase.getInstance(application).foodDao();
        executorService = Executors.newSingleThreadExecutor();
    }
    public LiveData<List<FoodEntity>> getAllFoods() {
        return foodDao.findAll();
    }
    public void saveFood(FoodEntity foodEntity) {
        executorService.execute(() -> foodDao.save(foodEntity));
    }

    public void deleteFood(FoodEntity foodEntity) {
        executorService.execute(() -> foodDao.delete(foodEntity));
    }
}
