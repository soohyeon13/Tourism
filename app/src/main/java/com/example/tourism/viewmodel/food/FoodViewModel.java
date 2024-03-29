package com.example.tourism.viewmodel.food;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tourism.contract.FoodViewContract;
import com.example.tourism.data.FoodEntity;
import com.example.tourism.data.dao.FoodDao;
import com.example.tourism.data.database.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FoodViewModel extends AndroidViewModel{

    private final FoodViewContract foodViewContract;
    private final Context context;
    private FoodDao foodDao;
    private ExecutorService executorService;

    public FoodViewModel(@NonNull Application application, FoodViewContract foodViewContract, Context context) {
        super(application);
        this.foodViewContract = foodViewContract;
        this.context = context;
        foodDao = AppDatabase.getInstance(application).foodDao();
        executorService = Executors.newSingleThreadExecutor();
    }
    public LiveData<List<FoodEntity>> getFoodSmallCateFood(String smallCate) {return foodDao.findFoodSmallCate(smallCate);}
    //    public LiveData<List<FoodEntity>> getAllFoods() { return foodDao.findAll(); }
    public LiveData<List<FoodEntity>> getSelectedCateFood(String location) {return foodDao.findSelectedCateFood(location);}
    public void saveFood(FoodEntity foodEntity) { executorService.execute(() -> foodDao.save(foodEntity)); }
    public void deleteFood(FoodEntity foodEntity) { executorService.execute(() -> foodDao.delete(foodEntity)); }

    public void btnClick(View v) {
        foodViewContract.btnClick(v);
    }
}
