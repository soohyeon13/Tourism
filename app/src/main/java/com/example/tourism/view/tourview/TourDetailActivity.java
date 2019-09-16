package com.example.tourism.view.tourview;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
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
import java.util.Optional;

public class TourDetailActivity extends Fragment implements ImageContract{
    private TourDetailViewModel viewModel;
    private ImageRecyclerAdapter imageRecyclerAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ActivityDetailTourBinding binding = DataBindingUtil.inflate(inflater,R.layout.activity_detail_tour,container,false);
        view = binding.getRoot();
        final KakaoSearch kakaoSearch = ((TourApplication) getActivity().getApplication())
                .getData(KakaoSearch.class, new HashMap<String, String>() {{
                    put("Authorization", "KakaoAK" + " " + getResources().getString(R.string.kakao_REST_API_key));
                }});

        binding.setViewModel(new TourDetailViewModel(getActivity().getApplication(), Optional.ofNullable(getArguments().getInt("id")).orElse(1),getActivity().getApplicationContext(),new ImageService(kakaoSearch),this));

        viewModel = binding.getViewModel();
        viewModel.loadDetail();
        viewModel.loadImages();

        setupViews();
        return view;
    }

    private void setupViews() {
        SnapHelper snapHelper;
        snapHelper = new LinearSnapHelper();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.imageRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        snapHelper.attachToRecyclerView(recyclerView);
        imageRecyclerAdapter = new ImageRecyclerAdapter(getContext(), (ImageContract) this);
        recyclerView.setAdapter(imageRecyclerAdapter);
    }

    @Override
    public void showImages(List<ImageVO.Document> items) {
        imageRecyclerAdapter.setItemsAndRefresh(items);
    }
}
