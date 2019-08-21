package com.example.tourism.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourism.R;
import com.example.tourism.data.FoodEntity;
import com.example.tourism.databinding.DataItemBinding;
import com.example.tourism.viewmodel.food.FoodItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.FoodViewHolder> {
    private Context context;
    private List<FoodEntity> mFood;

    public FoodRecyclerAdapter(Context context) {
        this.mFood = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DataItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.data_item,parent,false);
        binding.setViewModel(new FoodItemViewModel());
        return new FoodViewHolder(binding.getRoot(),binding.getViewModel());
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
       holder.bind(mFood.get(position));
    }

    public void setFood(List<FoodEntity> food) {
        if (mFood != null) {
            FoodDiffCallback foodDiffCallback = new FoodDiffCallback(mFood,food);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(foodDiffCallback);

            mFood.clear();
            mFood.addAll(food);
            diffResult.dispatchUpdatesTo(this);
        }else {
            mFood = food;
        }
    }

    @Override
    public int getItemCount() {
        return mFood.size();
     }

    public class FoodViewHolder extends RecyclerView.ViewHolder {

        private final FoodItemViewModel viewModel;
        private final TextView textItemView;

        public FoodViewHolder(@NonNull View itemView, FoodItemViewModel viewModel) {
            super(itemView);
            this.viewModel = viewModel;
            textItemView = itemView.findViewById(R.id.foodName);
        }

        void bind(final FoodEntity food) {
            textItemView.setText(food.getFoodName());
        }

    }

    private class FoodDiffCallback extends DiffUtil.Callback{

        private final List<FoodEntity> mFood, food;

        public FoodDiffCallback(List<FoodEntity> mFood, List<FoodEntity> food) {
            this.mFood = mFood;
            this.food = food;
        }

        @Override
        public int getOldListSize() {
            return mFood.size();
        }

        @Override
        public int getNewListSize() {
            return food.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mFood.get(oldItemPosition).getId() == food.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mFood.get(oldItemPosition).equals(food.get(newItemPosition));
        }
    }
}
