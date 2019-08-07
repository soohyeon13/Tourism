package com.example.tourism.model;

import com.google.gson.annotations.SerializedName;


public class WeatherVO {
    @SerializedName("coord")
    public Coord coord;

    @SerializedName("weather")
    public Weather weather;

    @SerializedName("main")
    public Main main;

    @SerializedName("wind")
    public Wind wind;

    @SerializedName("clouds")
    public Clouds clouds;

    @SerializedName("rain")
    public Rain rain;

    @SerializedName("sys")
    public Sys sys;


    @SerializedName("dt")
    public float dt;
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("cod")
    public float cod;



    public class Coord {
        @SerializedName("lon")
        Float lon;
        @SerializedName("lat")
        Float lat;

        private Float getLon() {return lon;}
        private Float getLat() {return lat;}
    }

    public class Weather {
        @SerializedName("id")
        Integer id;
        @SerializedName("main")
        String main;
        @SerializedName("description")
        String description;
        @SerializedName("icon")
        public String icon;

        private Integer getId(){return id; }
        private String getMain() {return main;}
        private String getDescription() {return description;}
        public String getIcon() {return icon;}
    }

    public class Main {
        @SerializedName("temp")
        public String temp;
        @SerializedName("humidity")
        Float humidity;
        @SerializedName("pressure")
        Float pressure;
        @SerializedName("temp_min")
        Float temp_min;
        @SerializedName("temp_max")
        Float temp_max;


        public String getTemp() { return temp; }
        private Float getHumidity() { return humidity; }
        private Float getPressure() { return pressure; }
        private Float getTemp_min() { return temp_min; }
        private Float getTemp_max() { return temp_max; }


    }

    public class Wind {
        @SerializedName("speed")
        Float speed;
        @SerializedName("deg")
        Float deg;

        private Float getSpeed() {return speed;}
        private Float getDeg() {return deg;}
    }

    public class Clouds {
        @SerializedName("all")
        Float all;

        private Float getAll() {return all;}
    }

    public class Sys {
        @SerializedName("country")
        public String country;
        @SerializedName("sunrise")
        Long sunrise;
        @SerializedName("sunset")
        Long sunset;

        private String getCountry() {return country;}
        private Long getSunrise() {return sunrise;}
        private Long getSunset() {return sunset;}


    }

    public class Rain {
        @SerializedName("3h")
        Float h3;

        private Float getH3() {return h3;}
    }
}
