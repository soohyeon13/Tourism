package com.example.tourism.view.tourview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
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

public class TourActivity extends AppCompatActivity implements TourViewContract, Clickable, HashTagHelper.OnHashTagClickListener {

    private HashTagHelper mTextHashTagHelper;
    private HashTagHelper mEditTextHashTagHelper;
    private TextView mEditTextView, mHashTagText;

    private RecyclerView tourRecycler;
    private SnapHelper snapHelper;
    private TourViewModel tourViewModel;
    private TourRecyclerAdapter tourRecyclerAdapter;

    private static final String[] WORDS = new String[] {"애월","제주"};
    private AutoCompleteTextView autoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TourCategoryActivityBinding binding = DataBindingUtil.setContentView(this,R.layout.tour_category_activity);
        binding.setViewModel(new TourViewModel(getApplication(),(TourViewContract)this));

        tourViewModel = binding.getViewModel();

        setupViews();
    }

    private void setupViews() {
        autoText = findViewById(R.id.auto_text_field);
        HashTagSuggestAdapter adapter = new HashTagSuggestAdapter(this,android.R.layout.simple_dropdown_item_1line,WORDS);
        adapter.setCursorPositionListener(() -> autoText.getSelectionStart());
        autoText.setAdapter(adapter);


        mHashTagText = findViewById(R.id.text_h);

        char[] additionalSymbols = new char[] { '_', '$' };

        mTextHashTagHelper = HashTagHelper.Creator.create(getResources().getColor(R.color.colorPrimary),this,additionalSymbols);
        mTextHashTagHelper.handle(mHashTagText);

        mEditTextHashTagHelper = HashTagHelper.Creator.create(getResources().getColor(R.color.colorPrimaryDark),null);
        mEditTextHashTagHelper.handle(autoText);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        snapHelper = new LinearSnapHelper();
        tourRecycler = findViewById(R.id.tourRecycler);
        snapHelper.attachToRecyclerView(tourRecycler);
        tourRecycler.setLayoutManager(gridLayoutManager);
        tourRecyclerAdapter = new TourRecyclerAdapter(this,this,this::clickItem);
        tourRecycler.setAdapter(tourRecyclerAdapter);

    }

    @Override
    public void btnClick(View view) {
        mHashTagText.setText(autoText.getText());
        tourViewModel.getSelectedCateTour("제주시","동부").observe(this,tours -> tourRecyclerAdapter.setTour(tours));
    }

    @Override
    public void clickItem(int id) {
        Intent intent = new Intent(this,TourDetailActivity.class);
        intent.putExtra("tour_id",id);
        startActivity(intent);
    }

    @Override
    public void onHashTagClicked(String hashTag) {
        //Todo click event
    }
}
