package com.example.tourism.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourism.R;
import com.example.tourism.contract.ImageRecyclerViewContract;
import com.example.tourism.databinding.ActivityFirstBinding;
import com.example.tourism.viewmodel.ImageItemViewModel;

public class FirstActivity extends AppCompatActivity implements ImageRecyclerViewContract {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFirstBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_first);
        binding.setViewModel(new ImageItemViewModel((ImageRecyclerViewContract) this));

        setupViews();
    }

    private void setupViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imageRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void showImages() {

    }
}
