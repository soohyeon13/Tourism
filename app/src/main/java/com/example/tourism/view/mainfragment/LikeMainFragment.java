package com.example.tourism.view.mainfragment;

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

import com.example.tourism.R;
import com.example.tourism.contract.LikeListContract;
import com.example.tourism.databinding.ActivityLikeListBinding;
import com.example.tourism.viewmodel.bottomnaviviewmodel.LikeListViewModel;

import java.util.Objects;

public class LikeMainFragment extends Fragment implements LikeListContract{
    private ActivityLikeListBinding binding;
    private NavController navController;
    private View view;
    private LikeListViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_like_list, container, false);
        binding.setViewModel(new LikeListViewModel(getActivity().getApplication(),this));
        view = binding.getRoot();
        setUpFragment();
        return view;
    }

    private void setUpFragment() {
        NavHostFragment host = (NavHostFragment) Objects.requireNonNull(getChildFragmentManager().findFragmentById(R.id.list_fragment_controller));
        navController = host.getNavController();
        binding.foodList.setOnClickListener(this::listClick);
        binding.tourList.setOnClickListener(this::listClick);

    }

    @Override
    public void listClick(View view) {
        switch (view.getId()) {
            case R.id.foodList:
                navController.navigate(R.id.foodListFragment);
                break;
            case R.id.tourList:
                navController.navigate(R.id.tourListFragment);
                break;
        }
    }
}
