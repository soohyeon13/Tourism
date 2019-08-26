package com.example.tourism.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.tourism.R;
import com.example.tourism.contract.TourViewContract;
import com.example.tourism.databinding.TourCategoryActivityBinding;
import com.example.tourism.view.adapter.TourRecyclerAdapter;
import com.example.tourism.viewmodel.tour.TourViewModel;

public class TourActivity extends AppCompatActivity implements TourViewContract {
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
        tourRecyclerAdapter = new TourRecyclerAdapter(this);
        tourRecycler.setAdapter(tourRecyclerAdapter);

    }

    @Override
    public void btnClick(View view) {
        tourViewModel.getSelectedCateTour().observe(this,tours -> tourRecyclerAdapter.setTour(tours));
    }
}
