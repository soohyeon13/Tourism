package com.example.tourism.view.likelistfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.tourism.R;
import com.example.tourism.databinding.FragmentFoodBinding;
import com.example.tourism.view.Clickable;
import com.example.tourism.view.adapter.FoodRecyclerAdapter;
import com.example.tourism.view.foodview.FoodDetailActivity;
import com.example.tourism.viewmodel.fragment.FragmentFoodListViewModel;

import java.util.Objects;

public class FoodListFragment extends Fragment implements Clickable {

    private GridLayoutManager gridLayoutManager;
    private SnapHelper snapHelper;
    private RecyclerView recyclerView;
    private FoodRecyclerAdapter adapter;
//    private FragmentRecyclerAdapter adapter;
    private FragmentFoodBinding binding;
    private FragmentFoodListViewModel viewModel;

    public static FoodListFragment newInstance() {
        return new FoodListFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_food,container,false);
        binding.setViewModel(new FragmentFoodListViewModel(Objects.requireNonNull(getActivity()).getApplication()));
        viewModel = binding.getViewModel();
        viewModel.getFoodLikeList("제주시","동부").observe(this,like ->adapter.setFood(like));

        gridLayoutManager = new GridLayoutManager(getActivity(),2);
        snapHelper = new LinearSnapHelper();
        recyclerView = binding.foodListRecycler;
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(gridLayoutManager);
//        adapter = new FragmentRecyclerAdapter(getActivity());
        adapter = new FoodRecyclerAdapter(getActivity(),this::clickItem);
        recyclerView.setAdapter(adapter);


        return binding.getRoot();
    }

    @Override
    public void clickItem(int id) {
        Intent intent = new Intent(getActivity(), FoodDetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
