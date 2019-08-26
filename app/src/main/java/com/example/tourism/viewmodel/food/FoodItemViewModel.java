package com.example.tourism.viewmodel.food;

import android.view.View;

import com.example.tourism.contract.FoodViewContract;

public class FoodItemViewModel {
    private final FoodViewContract foodViewContract;

    public FoodItemViewModel(FoodViewContract foodViewContract) {
        this.foodViewContract = foodViewContract;
    }

    public void imgClick(View view) {
        foodViewContract.imgClick(view);
    }
}
