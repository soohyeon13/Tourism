package com.example.tourism.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ImageViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourism.R;
import com.example.tourism.contract.ImageRecyclerViewContract;
import com.example.tourism.databinding.ActivityFirstBinding;
import com.example.tourism.model.ImageVO;
import com.example.tourism.model.KakaoSearch;
import com.example.tourism.view.adapter.ImageRecyclerAdapter;
import com.example.tourism.viewmodel.ImageListViewModel;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.List;

import retrofit2.HttpException;

public class FirstActivity extends AppCompatActivity implements ImageRecyclerViewContract {

    private ImageRecyclerAdapter imageRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFirstBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_first);
        final KakaoSearch kakaoSearch = ((KakaoSearchApplication)getApplication()).getKakaoSearch();
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

        ImageView imageView = (ImageView)findViewById(R.id.image_weather);



    }


    @Override
    public void showImages(List<ImageVO.Document> itmes) {
        imageRecyclerAdapter.setItemsAndRefresh(itmes);

    }

    @Override
    public void showError(Throwable e) {

        HttpException error = (HttpException)e;
        try {
            String errorBody = error.response().errorBody().string();
            Log.d("asd", errorBody);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
