package com.example.tourism.view.bottomnavi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tourism.R;
import com.example.tourism.databinding.ActivityLikeListBinding;
import com.example.tourism.viewmodel.bottomnaviviewmodel.LikeListViewModel;

public class ActivityLikeList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLikeListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_like_list);
        binding.setViewModel(new LikeListViewModel());


    }
}
