package com.example.tourism.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.tourism.R;
import com.example.tourism.databinding.FoodCatagoryActivityBinding;
import com.example.tourism.view.adapter.FoodRecyclerAdapter;
import com.example.tourism.viewmodel.FoodViewModel;

public class FoodActivity extends AppCompatActivity {

    private FoodRecyclerAdapter foodRecyclerAdapter;
    private SnapHelper snapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FoodCatagoryActivityBinding binding = DataBindingUtil.setContentView(this,R.layout.food_catagory_activity);
        binding.setViewModel(new FoodViewModel());

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
