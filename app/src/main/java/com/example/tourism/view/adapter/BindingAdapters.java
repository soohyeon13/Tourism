package com.example.tourism.view.adapter;


import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

public class BindingAdapters {
    @androidx.databinding.BindingAdapter({"imageUrl"})
    public static void loadImage(final ImageView imageView,final String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView));
    }
}
