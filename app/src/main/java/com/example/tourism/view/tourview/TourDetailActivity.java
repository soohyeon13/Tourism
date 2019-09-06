package com.example.tourism.view.tourview;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.tourism.R;
import com.example.tourism.contract.ImageContract;
import com.example.tourism.databinding.ActivityDetailTourBinding;
import com.example.tourism.model.ImageVO;
import com.example.tourism.model.KakaoSearch;
import com.example.tourism.service.ImageService;
import com.example.tourism.view.TourApplication;
import com.example.tourism.view.adapter.ImageRecyclerAdapter;
import com.example.tourism.viewmodel.tour.TourDetailViewModel;

import java.util.HashMap;
import java.util.List;

public class TourDetailActivity extends AppCompatActivity implements ImageContract{
    private TourDetailViewModel viewModel;
    private ImageRecyclerAdapter imageRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailTourBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_tour);

        final KakaoSearch kakaoSearch = ((TourApplication) getApplication())
                .getData(KakaoSearch.class, new HashMap<String, String>() {{
                    put("Authorization", "KakaoAK" + " " + getResources().getString(R.string.kakao_REST_API_key));
                }});

        binding.setViewModel(new TourDetailViewModel(getApplication(),getIntent().getIntExtra("tour_id",1),getApplicationContext(),new ImageService(kakaoSearch),this));

        viewModel = binding.getViewModel();
        viewModel.loadDetail();
        viewModel.loadImages();

        setupViews();
    }

    private void setupViews() {
        SnapHelper snapHelper;
        snapHelper = new LinearSnapHelper();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.imageRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        snapHelper.attachToRecyclerView(recyclerView);
        imageRecyclerAdapter = new ImageRecyclerAdapter((Context) this, (ImageContract) this);
        recyclerView.setAdapter(imageRecyclerAdapter);
    }

    @Override
    public void showImages(List<ImageVO.Document> items) {
        imageRecyclerAdapter.setItemsAndRefresh(items);
    }
}
