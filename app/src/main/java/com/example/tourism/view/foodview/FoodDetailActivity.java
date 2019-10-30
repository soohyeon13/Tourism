package com.example.tourism.view.foodview;

import android.content.Context;
import android.content.Intent;
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
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
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
import com.example.tourism.view.kakaomapview.KakaoMapActivity;
import com.example.tourism.viewmodel.food.FoodDetailViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class FoodDetailActivity extends Fragment implements ImageContract {
    private FoodDetailViewModel viewModel;
    private ImageRecyclerAdapter imageRecyclerAdapter;
    private View view;
    private NavController navController;
    private ActivityDetailFoodBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_detail_food,container,false);
        view = binding.getRoot();

        final KakaoSearch kakaoSearch = ((TourApplication) getActivity().getApplication())
                .getData(KakaoSearch.class, new HashMap<String, String>() {{
                    put("Authorization", "KakaoAK" + " " + getResources().getString(R.string.kakao_REST_API_key));
                }});
        binding.setViewModel(new FoodDetailViewModel(getActivity().getApplication(), Optional.ofNullable(getArguments().getInt("id")).orElse(1),getActivity().getApplicationContext(),new ImageService(kakaoSearch,getArguments().getString("name")),this));

        viewModel = binding.getViewModel();
        viewModel.loadDetail();
        viewModel.loadImages();

        try {
            NavHostFragment host = Optional.ofNullable((NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.view_controller)).orElseThrow(Exception::new);
            navController = host.getNavController();
        } catch (Throwable ignored) {
            Log.d("FirstActivity", String.valueOf(ignored));
        }

        setipView();
        return view;
    }

    private void setipView() {
//        binding.like.setOnClickListener(this::likeClick);
        SnapHelper snapHelper;
        snapHelper = new LinearSnapHelper();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.imageRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        snapHelper.attachToRecyclerView(recyclerView);
        imageRecyclerAdapter = new ImageRecyclerAdapter(getContext(), (ImageContract) this);
        recyclerView.setAdapter(imageRecyclerAdapter);
    }

    private void likeClick(View view) {
        view.setBackgroundResource(R.drawable.like_color);
    }

    @Override
    public void showImages(List<ImageVO.Document> items) {
        imageRecyclerAdapter.setItemsAndRefresh(items);
    }

    @Override
    public void onClick(double la, double lo,String name) {
        Bundle bundle = new Bundle();
        double[] list = new double[2];
        list[0] = la;
        list[1] = lo;
        bundle.putString("name",name);
        bundle.putDoubleArray("location",list);
        navController.navigate(R.id.action_foodDetailActivity_to_kakaoMapActivity2,bundle);
    }
}
