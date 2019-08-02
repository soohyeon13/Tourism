package com.example.tourism.view;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourism.R;
import com.example.tourism.contract.ImageRecyclerViewContract;
import com.example.tourism.databinding.ActivityFirstBinding;
import com.example.tourism.model.KakaoSearch;
import com.example.tourism.view.adapter.ImageRecyclerAdapter;
import com.example.tourism.viewmodel.ImageListViewModel;
import com.google.android.material.navigation.NavigationView;

public class FirstActivity extends AppCompatActivity implements ImageRecyclerViewContract {

    private KakaoSearch kakaoSearch;
    private ImageRecyclerAdapter imageRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFirstBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_first);
        binding.setViewModel(new ImageListViewModel((ImageRecyclerViewContract) this,kakaoSearch));

        setupViews();
    }

    private void setupViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imageRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        imageRecyclerAdapter = new ImageRecyclerAdapter((Context)this,(ImageRecyclerViewContract) this);
        recyclerView.setAdapter(imageRecyclerAdapter);


        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation);



    }


    @Override
    public void showImages(KakaoSearch.Images images) {
        imageRecyclerAdapter.setItemsAndRefresh(images.items);

    }

    @Override
    public void showError() {
        Toast.makeText(this,"읽을 수 없습니다",Toast.LENGTH_LONG).show();
    }
}
