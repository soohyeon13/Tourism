package com.example.tourism.view.bottomnavi;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tourism.R;
import com.example.tourism.contract.LikeListContract;
import com.example.tourism.databinding.ActivityLikeListBinding;
import com.example.tourism.view.fragment.FoodListFragment;
import com.example.tourism.view.fragment.TourListFragment;
import com.example.tourism.viewmodel.bottomnaviviewmodel.LikeListViewModel;

public class ActivityLikeList extends AppCompatActivity implements LikeListContract {
    private LikeListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLikeListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_like_list);
        binding.setViewModel(new LikeListViewModel(getApplication(),this));

        viewModel = binding.getViewModel();
        setUpFragment();
    }

    private void setUpFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, FoodListFragment.newInstance()).commit();
    }

    @Override
    public void listClick(View view) {
        if (view.getId() == R.id.foodList) {
            replaceFragment(FoodListFragment.newInstance());
        }else {
            replaceFragment(TourListFragment.newInstance());
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment).commit();
    }
}
