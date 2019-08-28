package com.example.tourism.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tour")
public class TourEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "tour_city")
    private String tourCity;

    @ColumnInfo(name = "tour_category")
    private String tourCategory;

    @ColumnInfo(name = "tour_name")
    private String tourName;

    @ColumnInfo(name = "tour_location")
    private String tourLocation;

    @ColumnInfo(name = "tour_describe")
    private String tourDescribe;

    @ColumnInfo(name = "tour_picture")
    private String tourPicture;

    @ColumnInfo(name = "tour_time")
    private String tourTime;

    public TourEntity() {
    }

    @Ignore
    public TourEntity(int id, String tourCity, String tourCategory, String tourName, String tourLocation, String tourDescribe, String tourPicture, String tourTime) {
        this.id = id;
        this.tourCity = tourCity;
        this.tourCategory = tourCategory;
        this.tourName = tourName;
        this.tourLocation = tourLocation;
        this.tourDescribe = tourDescribe;
        this.tourPicture = tourPicture;
        this.tourTime = tourTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTourCity() {
        return tourCity;
    }

    public void setTourCity(String tourCity) {
        this.tourCity = tourCity;
    }

    public String getTourCategory() {
        return tourCategory;
    }

    public void setTourCategory(String tourCategory) {
        this.tourCategory = tourCategory;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourLocation() {
        return tourLocation;
    }

    public void setTourLocation(String tourLocation) {
        this.tourLocation = tourLocation;
    }

    public String getTourDescribe() {
        return tourDescribe;
    }

    public void setTourDescribe(String tourDescribe) {
        this.tourDescribe = tourDescribe;
    }

    public String getTourPicture() {
        return tourPicture;
    }

    public void setTourPicture(String tourPicture) {
        this.tourPicture = tourPicture;
    }

    public String getTourTime() {
        return tourTime;
    }

    public void setTourTime(String tourTime) {
        this.tourTime = tourTime;
    }
}
