package com.example.tourism.view.adapter;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.view.FirstActivity;

public class WeatherAdapter extends BaseAdapter {
    private final static String PATH_TO_WEATHER_FONT = "fonts/weather.ttf";
    private final String[] humidity;
    private final FirstViewContract context;
    private final String[] rain;
    private final String[] icon;
    private final String[] time;
    private final Typeface weather_font;

    public WeatherAdapter(FirstViewContract view, String[] humidity, String[] rain, String[] icon, String[] time, Typeface weather_font) {
        this.humidity=humidity;
        context = view;
        this.rain=rain;
        this.icon=icon;
        this.time=time;
        this.weather_font =weather_font;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
