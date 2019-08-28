package com.example.tourism.viewmodel.food;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tourism.R;
import com.example.tourism.contract.FoodViewContract;
import com.example.tourism.data.FoodEntity;
import com.example.tourism.data.dao.FoodDao;
import com.example.tourism.data.database.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FoodViewModel extends AndroidViewModel{

    private final FoodViewContract foodViewContract;
    private String city,location;
    private FoodDao foodDao;
    private ExecutorService executorService;

    public FoodViewModel(@NonNull Application application, FoodViewContract foodViewContract) {
        super(application);
        this.foodViewContract = foodViewContract;
        foodDao = AppDatabase.getInstance(application).foodDao();
        executorService = Executors.newSingleThreadExecutor();
    }
    //    public LiveData<List<FoodEntity>> getAllFoods() { return foodDao.findAll(); }
    public LiveData<List<FoodEntity>> getSelectedCateFood() {return foodDao.findSelectedCateFood(city,location);}
    public void saveFood(FoodEntity foodEntity) {
        executorService.execute(() -> foodDao.save(foodEntity));
    }

    public void deleteFood(FoodEntity foodEntity) {
        executorService.execute(() -> foodDao.delete(foodEntity));
    }

    public void btnClick(View v) { foodViewContract.btnClick(v); }


    public void onCheckedChanged(View v) {
        switch (v.getId()) {
            case R.id.check_jeju:
                city = "제주시";
                break;
            case R.id.check_seogwipo:
                city = "서귀포";
                break;
            case R.id.check_east:
                location = "동부";
                break;
            case R.id.check_jeju_city:
                location = "제주시";
                break;
            case R.id.check_west:
                location = "서부";
                break;
            case R.id.check_seogwi_city:
                location = "서귀포시";
                break;
        }
    }

}
