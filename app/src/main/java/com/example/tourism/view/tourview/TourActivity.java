package com.example.tourism.view.tourview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.tourism.R;
import com.example.tourism.contract.TourViewContract;
import com.example.tourism.databinding.TourCategoryActivityBinding;
import com.example.tourism.view.Clickable;
import com.example.tourism.view.adapter.TourRecyclerAdapter;
import com.example.tourism.viewmodel.tour.TourViewModel;

public class TourActivity extends AppCompatActivity implements TourViewContract, Clickable {
    private RecyclerView tourRecycler;
    private SnapHelper snapHelper;
    private TourViewModel tourViewModel;
    private TourRecyclerAdapter tourRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TourCategoryActivityBinding binding = DataBindingUtil.setContentView(this,R.layout.tour_category_activity);
        binding.setViewModel(new TourViewModel(getApplication(),(TourViewContract)this));

        tourViewModel = binding.getViewModel();
//        tourViewModel = ViewModelProviders.of(this).get(TourViewModel.class);
//        tourViewModel.getAllTours().observe(this,tours -> tourRecyclerAdapter.setTour(tours));

        setupViews();
    }

    private void setupViews() {

        snapHelper = new LinearSnapHelper();
        tourRecycler = findViewById(R.id.tourRecycler);
        snapHelper.attachToRecyclerView(tourRecycler);
        tourRecycler.setLayoutManager(new LinearLayoutManager(this));
        tourRecyclerAdapter = new TourRecyclerAdapter(this,this,this::clickItem);
        tourRecycler.setAdapter(tourRecyclerAdapter);

    }

    @Override
    public void btnClick(View view) {
        tourViewModel.getSelectedCateTour().observe(this,tours -> tourRecyclerAdapter.setTour(tours));
    }

    @Override
    public void clickItem(int id) {
        Intent intent = new Intent(this,TourDetailActivity.class);
        intent.putExtra("tour_id",id);
        startActivity(intent);
    }
}
