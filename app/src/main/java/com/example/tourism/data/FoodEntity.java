package com.example.tourism.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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
    private String foodCallNum;

    @ColumnInfo(name = "food_date")
    private String foodDate;

    @ColumnInfo(name = "food_location")
    private String foodLocation;

    @ColumnInfo(name = "food_price")
    private String foodPrice;

    @ColumnInfo(name = "food_picture_url")
    private String foodPicture;

    @ColumnInfo(name = "food_menu")
    private String foodMenu;

    public FoodEntity(){}

    @Ignore
    public FoodEntity(int id,String foodCity,String foodCategory,String foodName, String foodCallNum,String foodDate, String foodLocation,String foodPrice,String foodPicture,String foodMenu) {
        this.id = id;
        this.foodCity = foodCity;
        this.foodCategory = foodCategory;
        this.foodName = foodName;
        this.foodCallNum = foodCallNum;
        this.foodDate = foodDate;
        this.foodLocation = foodLocation;
        this.foodPrice = foodPrice;
        this.foodPicture = foodPicture;
        this.foodMenu = foodMenu;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFoodCity() { return foodCity; }
    public void setFoodCity(String foodCity) { this.foodCity = foodCity; }

    public String getFoodCategory() { return foodCategory; }
    public void setFoodCategory(String foodCategory) { this.foodCategory = foodCategory; }

    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }

    public String getFoodCallNum() { return foodCallNum; }
    public void setFoodCallNum(String foodCallNum) { this.foodCallNum = foodCallNum; }

    public String getFoodDate() { return foodDate; }
    public void setFoodDate(String foodDate) { this.foodDate = foodDate; }

    public String getFoodLocation() { return foodLocation; }
    public void setFoodLocation(String foodLocation) { this.foodLocation = foodLocation; }

    public String getFoodPrice() { return foodPrice; }
    public void setFoodPrice(String foodPrice) { this.foodPrice = foodPrice; }

    public String getFoodPicture() { return foodPicture; }
    public void setFoodPicture(String foodPicture) { this.foodPicture = foodPicture; }

    public String getFoodMenu() { return foodMenu; }
    public void setFoodMenu(String foodMenu) { this.foodMenu = foodMenu; }


}
