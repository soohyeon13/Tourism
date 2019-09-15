package com.example.tourism.view.mainfragment;

import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tourism.R;
import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.databinding.HomeFragmentBinding;
import com.example.tourism.model.WeatherSearch;
import com.example.tourism.model.WeatherVO;
import com.example.tourism.service.GPSService;
import com.example.tourism.service.WeatherService;
import com.example.tourism.view.TourApplication;
import com.example.tourism.viewmodel.FirstViewModel;

public class HomeFragment extends Fragment implements FirstViewContract {

    TextView weatherIcon;
    private Typeface weatherFont;
    private final static String PATH_TO_WEATHER_FONT = "fonts/weather.ttf";
    private FirstViewModel mViewModel;
    HomeFragmentBinding binding;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);

        final WeatherSearch weatherSearch = ((TourApplication) getActivity().getApplication()).getData(WeatherSearch.class);
        binding.setViewModel(new FirstViewModel(this, new WeatherService(weatherSearch), new GPSService(getContext())));

        mViewModel = binding.getViewModel();
        mViewModel.loadWeathers();

        setupViews();

        return binding.getRoot();
    }

    private void setupViews() {
        weatherIcon = binding.weatherIconText;
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), PATH_TO_WEATHER_FONT);
        weatherIcon.setTypeface(weatherFont);
    }

    @Override
    public void onClick(View view) {
//        if (view.getId() == R.id.foodLayout) {
//            Intent intent = new Intent(FirstActivity.this, FoodActivity.class);
//            startActivity(intent);
//        } else {
//            Intent intent1 = new Intent(FirstActivity.this, TourActivity.class);
//            startActivity(intent1);
//        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showWeather(WeatherVO weather) {
        switch (weather.weather.get(0).icon) {
            case "01d":
                weatherIcon.setText(R.string.wi_day_sunny);
                break;
            case "02d":
                weatherIcon.setText(R.string.wi_cloudy_gusts);
                break;
            case "03d":
                weatherIcon.setText(R.string.wi_cloud_down);
                break;
            case "04d":
                weatherIcon.setText(R.string.wi_cloudy);
                break;
            case "04n":
                weatherIcon.setText(R.string.wi_night_cloudy);
                break;
            case "10d":
                weatherIcon.setText(R.string.wi_day_rain_mix);
                break;
            case "11d":
                weatherIcon.setText(R.string.wi_day_thunderstorm);
                break;
            case "13d":
                weatherIcon.setText(R.string.wi_day_snow);
                break;
            case "01n":
                weatherIcon.setText(R.string.wi_night_clear);
                break;
            case "02n":
                weatherIcon.setText(R.string.wi_night_cloudy);
                break;
            case "03n":
                weatherIcon.setText(R.string.wi_night_cloudy_gusts);
                break;
            case "10n":
                weatherIcon.setText(R.string.wi_night_cloudy_gusts);
                break;
            case "11n":
                weatherIcon.setText(R.string.wi_night_rain);
                break;
            case "13n":
                weatherIcon.setText(R.string.wi_night_snow);
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel




    }

}
