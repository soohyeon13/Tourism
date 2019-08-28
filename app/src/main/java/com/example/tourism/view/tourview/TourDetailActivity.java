package com.example.tourism.view.tourview;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tourism.R;
import com.example.tourism.databinding.ActivityDetailTourBinding;
import com.example.tourism.viewmodel.tour.TourDetailViewModel;

public class TourDetailActivity extends AppCompatActivity {
    private TourDetailViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailTourBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_tour);
        binding.setViewModel(new TourDetailViewModel(getApplication(),getIntent().getIntExtra("tour_id",1),getApplicationContext()));

        viewModel = binding.getViewModel();
        viewModel.loadDetail();

        Log.d("!!!!!", String.valueOf(getIntent().getIntExtra("tour_id",1)));
    }
}
