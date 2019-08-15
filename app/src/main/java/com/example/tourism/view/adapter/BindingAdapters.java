package com.example.tourism.view.adapter;


import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tourism.R;
import com.example.tourism.model.WeatherVO;

public class BindingAdapters {
    @androidx.databinding.BindingAdapter({"bind:imageUrl"})
    public static void loadImage(final ImageView imageView,final String imageUrl) {

        Glide.with(imageView.getContext())
                .load(imageUrl)
                .centerCrop()
                .into(imageView);
    }

//    @androidx.databinding.BindingAdapter({"bind:icon"})
//    public static void setTypeface(TextView textView, String icon) {
//        WeatherVO weatherVO = new WeatherVO();
//        icon = weatherVO.weather.get(0).icon;
//        Typeface tf = Typeface.createFromAsset(textView.getContext().getAssets(),"fonts/weather.ttf");
//        textView.setTypeface(tf);
//        switch (icon) {
//            case "01d":
//                textView.setText(R.string.wi_day_sunny);
//                break;
//            case "02d":
//                textView.setText(R.string.wi_cloudy_gusts);
//                break;
//            case "03d":
//                textView.setText(R.string.wi_cloud_down);
//                break;
//            case "04d":
//                textView.setText(R.string.wi_cloudy);
//                break;
//            case "04n":
//                textView.setText(R.string.wi_night_cloudy);
//                break;
//            case "10d":
//                textView.setText(R.string.wi_day_rain_mix);
//                break;
//            case "11d":
//                textView.setText(R.string.wi_day_thunderstorm);
//                break;
//            case "13d":
//                textView.setText(R.string.wi_day_snow);
//                break;
//            case "01n":
//                textView.setText(R.string.wi_night_clear);
//                break;
//            case "02n":
//                textView.setText(R.string.wi_night_cloudy);
//                break;
//            case "03n":
//                textView.setText(R.string.wi_night_cloudy_gusts);
//                break;
//            case "10n":
//                textView.setText(R.string.wi_night_cloudy_gusts);
//                break;
//            case "11n":
//                textView.setText(R.string.wi_night_rain);
//                break;
//            case "13n":
//                textView.setText(R.string.wi_night_snow);
//                break;
//        }
//    }
}
