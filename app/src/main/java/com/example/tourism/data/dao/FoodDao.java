package com.example.tourism.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tourism.data.FoodEntity;

import java.util.List;

@Dao
public interface FoodDao {

    @Query("SELECT * FROM food")
    List<FoodEntity> getAll();

    @Query("SELECT * FROM food WHERE food_city LiKE :city AND food_category LIKE :category")
    FoodEntity findByAll(String city,String category);

    @Insert
    void insertAll(FoodEntity...foods);

    @Delete
    void delete(FoodEntity food);
}
