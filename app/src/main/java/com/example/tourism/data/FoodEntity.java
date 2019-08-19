package com.example.tourism.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food")
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


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFoodCity() { return foodCity; }
    public void setFoodCity(String foodCity) { this.foodCity = foodCity; }

    public String getFoodCategory() { return foodCategory; }
    public void setFoodCategory(String foodCategory) { this.foodCategory = foodCategory; }

    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }

    public int getFoodCallNum() { return foodCallNum; }
    public void setFoodCallNum(int foodCallNum) { this.foodCallNum = foodCallNum; }

    public String getFoodDate() { return foodDate; }
    public void setFoodDate(String foodDate) { this.foodDate = foodDate; }

    public String getFoodLocation() { return foodLocation; }
    public void setFoodLocation(String foodLocation) { this.foodLocation = foodLocation; }


}
