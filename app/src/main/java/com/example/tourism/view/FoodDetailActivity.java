package com.example.tourism.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tourism.R;
import com.example.tourism.contract.FoodDetailViewContract;
import com.example.tourism.databinding.ActivityDetailFoodBinding;
import com.example.tourism.viewmodel.food.FoodDetailViewModel;

public class FoodDetailActivity extends AppCompatActivity implements FoodDetailViewContract {
    private FoodDetailViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailFoodBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_food);
        binding.setViewModel(new FoodDetailViewModel(getApplication(),getIntent().getIntExtra("id",1)));


        viewModel = binding.getViewModel();
        viewModel.loadDetail();

        setipView();
    }

    private void setipView() {

    }
}
