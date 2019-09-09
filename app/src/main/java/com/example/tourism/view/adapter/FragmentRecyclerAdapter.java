package com.example.tourism.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tourism.R;
import com.example.tourism.data.FoodEntity;
import com.example.tourism.databinding.DataItemBinding;
import com.example.tourism.viewmodel.food.FoodItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentRecyclerAdapter extends RecyclerView.Adapter<FragmentRecyclerAdapter.ViewHolder> {

    private final Context context;
    private List<FoodEntity> mFood;

    public FragmentRecyclerAdapter(Context context) {
        this.mFood = new ArrayList<>();
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DataItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.data_item, parent, false);
        binding.setViewModel(new FoodItemViewModel());
        return new ViewHolder(binding.getRoot(), binding.getViewModel());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.loadFood(mFood.get(position));
    }

    public void setFood(List<FoodEntity> food) {
        if (mFood != null) {
            FragDiffCallback fragDiffCallback = new FragDiffCallback(mFood, food);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(fragDiffCallback);

            mFood.clear();
            mFood.addAll(food);
            diffResult.dispatchUpdatesTo(this);
        } else {
            mFood = food;
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final FoodItemViewModel viewModel;
        private final TextView textItemView;
        private final ImageView imgView;

        public ViewHolder(@NonNull View itemView, FoodItemViewModel viewModel) {
            super(itemView);
            this.viewModel = viewModel;
            textItemView = itemView.findViewById(R.id.foodName);
            imgView = itemView.findViewById(R.id.foodImg);
        }

        public void loadFood(final FoodEntity food) {
            textItemView.setText(food.getFoodName());
            Glide.with(context)
                    .load(food.getFoodPicture())
                    .override(200, 200)
                    .into(imgView);
        }
    }

    private class FragDiffCallback extends DiffUtil.Callback {

        private final List<FoodEntity> mFood, food;

        public FragDiffCallback(List<FoodEntity> mFood, List<FoodEntity> food) {
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
