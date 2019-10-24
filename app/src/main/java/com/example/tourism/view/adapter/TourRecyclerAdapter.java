package com.example.tourism.view.adapter;

import android.content.Context;
import android.util.Log;
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
import com.example.tourism.contract.TourViewContract;
import com.example.tourism.data.TourEntity;
import com.example.tourism.databinding.TourItemBinding;
import com.example.tourism.viewmodel.tour.TourItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class TourRecyclerAdapter extends RecyclerView.Adapter<TourRecyclerAdapter.TourViewHolder> {
    private Context context;
    private TourViewContract tourViewContract;
    private Callback callback;
    private List<TourEntity> mTour;
    public interface Callback {
        void callback(int id,String name);
    }

    public TourRecyclerAdapter(Context context, TourViewContract tourViewContract,Callback callBack) {
        this.mTour = new ArrayList<>();
        this.context = context;
        this.tourViewContract = tourViewContract;
        this.callback = callBack;
    }

    public TourRecyclerAdapter(Context context,Callback callback) {
        this.mTour = new ArrayList<>();
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TourItemBinding bind = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.tour_item, parent, false);
        bind.setViewModel(new TourItemViewModel());
        return new TourViewHolder(bind.getRoot(), bind.getViewModel());

    }

    public void setTour(List<TourEntity> tour) {
        if (mTour != null) {
            TourDiffCallback tourDiffCallback = new TourDiffCallback(mTour, tour);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(tourDiffCallback);

            mTour.clear();
            mTour.addAll(tour);
            diffResult.dispatchUpdatesTo(this);
        } else {
            mTour = tour;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull TourRecyclerAdapter.TourViewHolder holder, int position) {
        holder.bind(mTour.get(position));
    }

    @Override
    public int getItemCount() {
        return mTour.size();
    }

    public class TourViewHolder extends RecyclerView.ViewHolder {
        private final TourItemViewModel viewModel;
        private final TextView textView;
        private final ImageView imageview;

        public TourViewHolder(@NonNull View itemView, TourItemViewModel viewModel) {
            super(itemView);
            this.viewModel = viewModel;
            textView = itemView.findViewById(R.id.tourName);
            imageview = itemView.findViewById(R.id.tourImg);
        }

        public void bind(final TourEntity tour) {
            textView.setText(tour.getTourName());
            Glide.with(context)
                    .load(tour.getTourPicture())
                    .override(200,200)
                    .into(imageview);
            imageview.setOnClickListener(v -> callback.callback(tour.getId(),tour.getTourName()));
        }
    }

    private class TourDiffCallback extends DiffUtil.Callback {
        private final List<TourEntity> mTour, tour;

        public TourDiffCallback(List<TourEntity> mTour, List<TourEntity> tour) {
            this.mTour = mTour;
            this.tour = tour;
        }

        @Override
        public int getOldListSize() {
            return mTour.size();
        }

        @Override
        public int getNewListSize() {
            return tour.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mTour.get(oldItemPosition).getId() == tour.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mTour.get(oldItemPosition).equals(tour.get(newItemPosition));
        }
    }
}
