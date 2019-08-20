package com.example.tourism.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.tourism.R;
import com.example.tourism.data.database.AppDatabase;
import com.example.tourism.databinding.FoodCategoryActivityBinding;
import com.example.tourism.view.adapter.FoodRecyclerAdapter;
import com.example.tourism.viewmodel.FoodViewModel;

public class FoodActivity extends AppCompatActivity {

    private FoodRecyclerAdapter foodRecyclerAdapter;
    private SnapHelper snapHelper;
    private FoodViewModel foodViewModel;
    private FoodRecyclerAdapter foodsAdpater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FoodCategoryActivityBinding binding = DataBindingUtil.setContentView(this,R.layout.food_category_activity);
        binding.setViewModel(new FoodViewModel(getApplication()));


        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        foodViewModel.getAllFoods().observe(this,foods -> foodRecyclerAdapter.setFood(foods));

        setupViews();
    }

    private void setupViews() {

        snapHelper = new LinearSnapHelper();
        RecyclerView recyclerView = findViewById(R.id.foodRecycler);
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        foodRecyclerAdapter = new FoodRecyclerAdapter(this);
        recyclerView.setAdapter(foodRecyclerAdapter);

    }
}
