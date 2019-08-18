package com.example.tourism.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourism.R;
import com.example.tourism.databinding.FoodItemBinding;
import com.example.tourism.viewmodel.FoodItemViewModel;
import com.example.tourism.viewmodel.FoodViewModel;

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.FoodViewHolder> {
    private Context context;

    public FoodRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FoodItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.food_item,parent,false);
        binding.setViewModel(new FoodItemViewModel());
        return new FoodViewHolder(binding.getRoot(),binding.getViewModel());
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {

        private final FoodItemViewModel viewModel;

        public FoodViewHolder(@NonNull View itemView, FoodItemViewModel viewModel) {
            super(itemView);
            this.viewModel = viewModel;
        }

    }
}
