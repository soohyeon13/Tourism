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
import com.example.tourism.data.TourEntity;
import com.example.tourism.databinding.TourItemBinding;
import com.example.tourism.viewmodel.tour.TourItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class TourRecyclerAdapter extends RecyclerView.Adapter<TourRecyclerAdapter.TourViewHolder> {
    private final Context context;
    private List<TourEntity> mTour;

    public TourRecyclerAdapter(Context context) {
        this.mTour = new ArrayList<>();
        this.context = context;
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
        private TextView textView;

        public TourViewHolder(@NonNull View itemView, TourItemViewModel viewModel) {
            super(itemView);
            this.viewModel = viewModel;
            textView = itemView.findViewById(R.id.tourName);
        }

        void bind(final TourEntity tour) {
            textView.setText(tour.getTourName());
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
