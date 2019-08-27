package com.example.tourism.viewmodel.food;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

import com.example.tourism.data.FoodEntity;
import com.example.tourism.data.dao.FoodDao;
import com.example.tourism.data.database.AppDatabase;
import com.example.tourism.view.FoodDetailActivity;

import java.util.List;

public class FoodDetailViewModel extends AndroidViewModel {
    public final ObservableField<String> foodName = new ObservableField<>();
    public final ObservableField<String> foodCall = new ObservableField<>();
    public final ObservableField<String> foodLocation = new ObservableField<>();
    public final ObservableField<String> foodTime = new ObservableField<>();
    public final ObservableField<String> foodMenu = new ObservableField<>();
    public final ObservableField<String> foodImg = new ObservableField<>();
    private final int id;
    private FoodDao foodDao;

    public FoodDetailViewModel(@NonNull Application application ,int id) {
        super(application);
        foodDao = AppDatabase.getInstance(application).foodDao();
        this.id = id;
    }

    public ObservableField<String> getFoodName() { return foodName; }
    public ObservableField<String> getFoodCall() { return foodCall; }
    public ObservableField<String> getFoodLocation() { return foodLocation; }
    public ObservableField<String> getFoodTime() { return foodTime; }
    public ObservableField<String> getFoodMenu() { return foodMenu; }
    public ObservableField<String> getFoodImg() { return foodImg; }

    public FoodEntity getDetailFood() {
        return foodDao.findDetailFood(id);}

    public void loadDetail() {
        foodName.set(getDetailFood().getFoodName());
        foodCall.set(getDetailFood().getFoodCallNum());
        foodLocation.set(getDetailFood().getFoodLocation());
        foodTime.set(getDetailFood().getFoodDate());
    }
}
