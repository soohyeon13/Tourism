package com.example.tourism.view.foodview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.tourism.R;
import com.example.tourism.contract.FoodViewContract;
import com.example.tourism.databinding.FoodCategoryActivityBinding;
import com.example.tourism.view.Clickable;
import com.example.tourism.view.adapter.FoodRecyclerAdapter;
import com.example.tourism.viewmodel.food.FoodViewModel;
import com.volokh.danylo.hashtaghelper.HashTagHelper;

public class FoodActivity extends AppCompatActivity implements FoodViewContract, Clickable,HashTagHelper.OnHashTagClickListener{

    private HashTagHelper mTextHashTagHelper;
    private HashTagHelper mEditTextHashTagHelper;
    private TextView mEditTextView, mHashTagText;

    private FoodRecyclerAdapter foodRecyclerAdapter;
    private SnapHelper snapHelper;
    private FoodViewModel foodViewModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FoodCategoryActivityBinding binding = DataBindingUtil.setContentView(this,R.layout.food_category_activity);
        binding.setViewModel(new FoodViewModel(getApplication(),this,this));

        foodViewModel = binding.getViewModel();

        setupViews();
    }

    private void setupViews() {

        mEditTextView = findViewById(R.id.edit_text_field);
        mHashTagText = findViewById(R.id.text_h);

        char[] additionalSymbols = new char[] { '_', '$' };

        mTextHashTagHelper = HashTagHelper.Creator.create(getResources().getColor(R.color.colorPrimary),this,additionalSymbols);
        mTextHashTagHelper.handle(mHashTagText);

        mEditTextHashTagHelper = HashTagHelper.Creator.create(getResources().getColor(R.color.colorPrimaryDark),null);
        mEditTextHashTagHelper.handle(mEditTextView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        snapHelper = new LinearSnapHelper();
        recyclerView = findViewById(R.id.foodRecycler);
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(gridLayoutManager);
        foodRecyclerAdapter = new FoodRecyclerAdapter((Context)this,this, this::clickItem);
        recyclerView.setAdapter(foodRecyclerAdapter);


    }

    @Override
    public void clickItem(int id) {
        Intent intent = new Intent(this, FoodDetailActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }


    @Override
    public void btnClick(View view) {
        mHashTagText.setText(mEditTextView.getText());
        foodViewModel.getSelectedCateFood("제주시","동부").observe(this,food ->foodRecyclerAdapter.setFood(food));
    }

    @Override
    public void onHashTagClicked(String hashTag) {
        //Todo click event
    }
}
