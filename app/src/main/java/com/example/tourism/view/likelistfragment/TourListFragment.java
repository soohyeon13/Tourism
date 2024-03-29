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
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.tourism.R;
import com.example.tourism.databinding.FragmentTourBinding;
import com.example.tourism.view.Clickable;
import com.example.tourism.view.adapter.TourRecyclerAdapter;
import com.example.tourism.view.tourview.TourDetailActivity;
import com.example.tourism.viewmodel.fragment.FragmentFoodListViewModel;

import java.util.Objects;

public class TourListFragment extends Fragment implements Clickable {

    private FragmentFoodListViewModel viewModel;
    private TourRecyclerAdapter adapter;
    private SnapHelper snapHelper;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private NavController navController;

    public static TourListFragment newInstance() {
        return new TourListFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentTourBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tour,container,false);
        binding.setViewModel(new FragmentFoodListViewModel(Objects.requireNonNull(getActivity()).getApplication()));
        viewModel = binding.getViewModel();

        viewModel.getTourLikeList(1).observe(this,like -> adapter.setTour(like));

        recyclerView = binding.fragmentTour;
        gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new TourRecyclerAdapter(getActivity(),this::clickItem);
        recyclerView.setAdapter(adapter);

        NavHostFragment host = (NavHostFragment) Objects.requireNonNull(getActivity().getSupportFragmentManager().findFragmentById(R.id.view_controller));
        navController = host.getNavController();

        return binding.getRoot();
    }

    @Override
    public void clickItem(int id, String name) {
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        bundle.putString("name",name);
        navController.navigate(R.id.action_likeMainFragment_to_tourDetailActivity,bundle);
    }
}
