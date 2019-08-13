package com.example.tourism.view.adapter;


import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class BindingAdapters {
    @androidx.databinding.BindingAdapter({"imageUrl"})
    public static void loadImage(final ImageView imageView,final String imageUrl) {

        Glide.with(imageView.getContext())
                .load(imageUrl)
                .centerCrop()
                .into(imageView);
    }
}
