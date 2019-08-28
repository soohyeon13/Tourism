package com.example.tourism.view.foodview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tourism.R;
import com.example.tourism.databinding.ActivityDetailFoodBinding;
import com.example.tourism.viewmodel.food.FoodDetailViewModel;

public class FoodDetailActivity extends AppCompatActivity {
    private FoodDetailViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailFoodBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_food);
        binding.setViewModel(new FoodDetailViewModel(getApplication(),getIntent().getIntExtra("id",1),getApplicationContext()));


        viewModel = binding.getViewModel();
        viewModel.loadDetail();

        setipView();
    }

    private void setipView() {

    }
}
