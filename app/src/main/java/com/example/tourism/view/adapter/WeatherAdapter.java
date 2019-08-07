package com.example.tourism.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.tourism.R;
import com.example.tourism.contract.FirstViewContract;

public class WeatherAdapter extends BaseAdapter {
    private final FirstViewContract view;
    private final String icon;
    private final String temp;
    private final String country;

    private static LayoutInflater inflater = null;
    private final Context context;
    private final static String PATH_TO_WEATHER_FONT = "fonts/weather.ttf";
    Typeface weather_font;

    public WeatherAdapter(Context context, FirstViewContract view, String icon, String temp, String country ) {
        this.context = context;
        this.view = view;
        this.icon = icon;
        this.temp = temp;
        this.country = country;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.current_weather,null);
        holder.weatherIcon = (TextView)rowView.findViewById(R.id.weather_icon_text);
        holder.country=(TextView)rowView.findViewById(R.id.country_text);
        holder.temp = (TextView)rowView.findViewById(R.id.temp_text);

        holder.weatherIcon.setTypeface(weather_font);

        switch (icon) {
            case "01d":
                holder.weatherIcon.setText(R.string.wi_day_sunny);
                break;
            case "02d":
                holder.weatherIcon.setText(R.string.wi_cloudy_gusts);
                break;
            case "03d":
                holder.weatherIcon.setText(R.string.wi_cloud_down);
                break;
            case "10d":
                holder.weatherIcon.setText(R.string.wi_day_rain_mix);
                break;
            case "11d":
                holder.weatherIcon.setText(R.string.wi_day_thunderstorm);
                break;
            case "13d":
                holder.weatherIcon.setText(R.string.wi_day_snow);
                break;
            case "01n":
                holder.weatherIcon.setText(R.string.wi_night_clear);
                break;
            case "04d":
                holder.weatherIcon.setText(R.string.wi_cloudy);
                break;
            case "04n":
                holder.weatherIcon.setText(R.string.wi_night_cloudy);
                break;
            case "02n":
                holder.weatherIcon.setText(R.string.wi_night_cloudy);
                break;
            case "03n":
                holder.weatherIcon.setText(R.string.wi_night_cloudy_gusts);
                break;
            case "10n":
                holder.weatherIcon.setText(R.string.wi_night_cloudy_gusts);
                break;
            case "11n":
                holder.weatherIcon.setText(R.string.wi_night_rain);
                break;
            case "13n":
                holder.weatherIcon.setText(R.string.wi_night_snow);
                break;
        }
        holder.temp.setText(temp);
        holder.country.setText(country);


        return rowView;
    }

    public class Holder {
        TextView weatherIcon;
        TextView country;
        TextView temp;

    }
}
