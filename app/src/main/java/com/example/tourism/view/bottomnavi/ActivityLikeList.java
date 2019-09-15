package com.example.tourism.view.bottomnavi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tourism.viewmodel.bottomnaviviewmodel.LikeListViewModel;

public class ActivityLikeList extends AppCompatActivity {
    private LikeListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActivityLikeListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_like_list);
//        binding.setViewModel(new LikeListViewModel(getApplication(),this));
//
//        viewModel = binding.getViewModel();
//        setUpFragment();
    }

//    private void setUpFragment() {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.fragment_container, FoodListFragment.newInstance()).commit();
//    }
//
//    @Override
//    public void listClick(View view) {
//        if (view.getId() == R.id.foodList) {
//            replaceFragment(FoodListFragment.newInstance());
//        }else {
//            replaceFragment(TourListFragment.newInstance());
//        }
//    }
//
//    private void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container,fragment).commit();
//}
}
