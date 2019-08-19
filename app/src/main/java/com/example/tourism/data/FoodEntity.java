package com.example.tourism.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
public class FoodEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "food_city")
    private String foodCity;

    @ColumnInfo(name = "food_category")
    private String foodCategory;

    @ColumnInfo(name = "food_name")
    private String foodName;

    @ColumnInfo(name = "food_call_num")
    private int foodCallNum;

    @ColumnInfo(name = "food_date")
    private String foodDate;

    @ColumnInfo(name = "food_location")
    private String foodLocation;

}
