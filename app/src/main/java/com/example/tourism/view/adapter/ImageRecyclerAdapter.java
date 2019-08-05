package com.example.tourism.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourism.R;
import com.example.tourism.contract.ImageRecyclerViewContract;
import com.example.tourism.databinding.ImageItemBinding;
import com.example.tourism.model.KakaoSearch;
import com.example.tourism.viewmodel.ImageItemViewModel;

import java.util.List;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder> {

    private final Context context;
    private final ImageRecyclerViewContract view;
    private List<KakaoSearch.ImageItems> items;

    public ImageRecyclerAdapter(Context context, ImageRecyclerViewContract view) {
        this.context =context;
        this.view = view;
    }

    public void setItemsAndRefresh(List<KakaoSearch.ImageItems> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public KakaoSearch.ImageItems getItemAt(int position) {
        return items.get(position);
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.image_item,parent,false);
        binding.setViewModel(new ImageItemViewModel(view));
        return new ImageViewHolder(binding.getRoot(),binding.getViewModel());
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        final KakaoSearch.ImageItems item = getItemAt(position);
        holder.loadItem(item);

    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private final ImageItemViewModel viewModel;

        public ImageViewHolder(@NonNull View itemView, ImageItemViewModel viewModel) {
            super(itemView);
            this.viewModel = viewModel;
        }

        public void loadItem(KakaoSearch.ImageItems item) {
            viewModel.loadItem(item);
        }
    }
}
