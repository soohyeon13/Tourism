package com.example.tourism.view.foodview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.tourism.R;
import com.example.tourism.contract.FoodViewContract;
import com.example.tourism.databinding.FoodCategoryActivityBinding;
import com.example.tourism.view.Clickable;
import com.example.tourism.view.adapter.FoodRecyclerAdapter;
import com.example.tourism.view.adapter.HashTagSuggestAdapter;
import com.example.tourism.viewmodel.food.FoodViewModel;
import com.volokh.danylo.hashtaghelper.HashTagHelper;

import java.util.Objects;
import java.util.Optional;

public class FoodActivity extends Fragment implements FoodViewContract, Clickable,HashTagHelper.OnHashTagClickListener{

    private HashTagHelper mTextHashTagHelper;
    private HashTagHelper mEditTextHashTagHelper;
    private TextView mHashTagText;

    private FoodRecyclerAdapter foodRecyclerAdapter;
    private SnapHelper snapHelper;
    private FoodViewModel foodViewModel;
    private RecyclerView recyclerView;

    private static final String[] WORDS = new String[] {"애월","제주시","평대"};
    private AutoCompleteTextView autoText;
    private View view;
    private NavController navController;
    private static final String TAG = FoodActivity.class.getSimpleName();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FoodCategoryActivityBinding binding = DataBindingUtil.inflate(inflater,R.layout.food_category_activity,container,false);

        binding.setViewModel(new FoodViewModel(getActivity().getApplication(),this,getContext()));
        view = binding.getRoot();

        foodViewModel = binding.getViewModel();

        setupViews();

        return view;
    }

    private void setupViews() {

        autoText = view.findViewById(R.id.auto_text_field);
        HashTagSuggestAdapter adapter = new HashTagSuggestAdapter(Objects.requireNonNull(getContext()),android.R.layout.simple_dropdown_item_1line,WORDS);
        adapter.setCursorPositionListener(() -> autoText.getSelectionStart());
        autoText.setAdapter(adapter);

        mHashTagText = view.findViewById(R.id.text_h);

        char[] additionalSymbols = new char[] { '_', '$' };

        mTextHashTagHelper = HashTagHelper.Creator.create(getResources().getColor(R.color.colorPrimary),this,additionalSymbols);
        mTextHashTagHelper.handle(mHashTagText);

        mEditTextHashTagHelper = HashTagHelper.Creator.create(getResources().getColor(R.color.colorPrimaryDark),null);
        mEditTextHashTagHelper.handle(autoText);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        snapHelper = new LinearSnapHelper();
        recyclerView = view.findViewById(R.id.foodRecycler);
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(gridLayoutManager);
        foodRecyclerAdapter = new FoodRecyclerAdapter(getContext(),this, this::clickItem);
        recyclerView.setAdapter(foodRecyclerAdapter);

        try {
            NavHostFragment host = Optional.ofNullable((NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.view_controller)).orElseThrow(Exception::new);
            navController = host.getNavController();
        } catch (Throwable ignored) {
            Log.d("FirstActivity", String.valueOf(ignored));
        }
    }

    @Override
    public void clickItem(int id, String name) {
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        bundle.putString("name",name);
        navController.navigate(R.id.action_foodActivity_to_foodDetailActivity,bundle);
    }

    @Override
    public void btnClick(View view) {
        mHashTagText.setText(autoText.getText());
        foodViewModel.getSelectedCateFood(autoText.getText().toString().trim()).observe(this,food ->foodRecyclerAdapter.setFood(food));
    }

    @Override
    public void onHashTagClicked(String hashTag) {
        //Todo click event
    }


}
