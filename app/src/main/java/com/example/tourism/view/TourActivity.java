package com.example.tourism.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tourism.R;
import com.example.tourism.databinding.TourCategoryActivityBinding;
import com.example.tourism.viewmodel.TourViewModel;

public class TourActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TourCategoryActivityBinding binding = DataBindingUtil.setContentView(this,R.layout.tour_category_activity);
        binding.setViewModel(new TourViewModel());
    }
}
