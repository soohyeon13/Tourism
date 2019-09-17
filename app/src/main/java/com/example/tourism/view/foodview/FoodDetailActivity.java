package com.example.tourism.view.foodview;

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
import com.example.tourism.databinding.ActivityDetailFoodBinding;
import com.example.tourism.model.ImageVO;
import com.example.tourism.model.KakaoSearch;
import com.example.tourism.service.ImageService;
import com.example.tourism.view.TourApplication;
import com.example.tourism.view.adapter.ImageRecyclerAdapter;
import com.example.tourism.viewmodel.food.FoodDetailViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class FoodDetailActivity extends Fragment implements ImageContract {
    private FoodDetailViewModel viewModel;
    private ImageRecyclerAdapter imageRecyclerAdapter;
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ActivityDetailFoodBinding binding = DataBindingUtil.inflate(inflater,R.layout.activity_detail_food,container,false);
        view = binding.getRoot();

        final KakaoSearch kakaoSearch = ((TourApplication) getActivity().getApplication())
                .getData(KakaoSearch.class, new HashMap<String, String>() {{
                    put("Authorization", "KakaoAK" + " " + getResources().getString(R.string.kakao_REST_API_key));
                }});
        binding.setViewModel(new FoodDetailViewModel(getActivity().getApplication(), Optional.ofNullable(getArguments().getInt("id")).orElse(1),getActivity().getApplicationContext(),new ImageService(kakaoSearch),this));

        viewModel = binding.getViewModel();
        viewModel.loadDetail();
        viewModel.loadImages();

        setipView();
        return view;
    }

    private void setipView() {
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
