package com.example.tourism.view.tourview;

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
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.tourism.R;
import com.example.tourism.contract.TourViewContract;
import com.example.tourism.databinding.TourCategoryActivityBinding;
import com.example.tourism.view.Clickable;
import com.example.tourism.view.adapter.HashTagSuggestAdapter;
import com.example.tourism.view.adapter.TourRecyclerAdapter;
import com.example.tourism.viewmodel.tour.TourViewModel;
import com.volokh.danylo.hashtaghelper.HashTagHelper;

import java.util.Optional;

public class TourActivity extends Fragment implements TourViewContract, Clickable, HashTagHelper.OnHashTagClickListener {

    private HashTagHelper mTextHashTagHelper;
    private HashTagHelper mEditTextHashTagHelper;
    private TextView mEditTextView, mHashTagText;

    private RecyclerView tourRecycler;
    private SnapHelper snapHelper;
    private TourViewModel tourViewModel;
    private TourRecyclerAdapter tourRecyclerAdapter;

    private static final String[] WORDS = new String[] {"조천","제주시"};
    private AutoCompleteTextView autoText;
    private View view;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TourCategoryActivityBinding binding = DataBindingUtil.inflate(inflater,R.layout.tour_category_activity,container,false);
        view = binding.getRoot();
        binding.setViewModel(new TourViewModel(getActivity().getApplication(),(TourViewContract)this));

        tourViewModel = binding.getViewModel();
        setupViews();

        return view;
    }

    private void setupViews() {
        autoText = view.findViewById(R.id.auto_text_field);
        HashTagSuggestAdapter adapter = new HashTagSuggestAdapter(getContext(),android.R.layout.simple_dropdown_item_1line,WORDS);
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
        tourRecycler = view.findViewById(R.id.tourRecycler);
        snapHelper.attachToRecyclerView(tourRecycler);
        tourRecycler.setLayoutManager(gridLayoutManager);
        tourRecyclerAdapter = new TourRecyclerAdapter(getContext(),this,this::clickItem);
        tourRecycler.setAdapter(tourRecyclerAdapter);

        try {
            NavHostFragment host = Optional.ofNullable((NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.view_controller)).orElseThrow(Exception::new);
            navController = host.getNavController();
        } catch (Throwable ignored) {
            Log.d("FirstActivity", String.valueOf(ignored));
        }

    }

    @Override
    public void btnClick(View view) {
        mHashTagText.setText(autoText.getText());
        tourViewModel.getSelectedCateTour(autoText.getText().toString().trim()).observe(this,tours -> tourRecyclerAdapter.setTour(tours));
    }


    @Override
    public void onHashTagClicked(String hashTag) {
        //Todo click event
    }

    @Override
    public void clickItem(int id, String name) {
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        bundle.putString("name",name);
        navController.navigate(R.id.action_tourActivity_to_tourDetailActivity,bundle);
    }
}
