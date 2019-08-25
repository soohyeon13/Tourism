package com.example.tourism.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tourism.data.FoodEntity;

import java.util.List;

@Dao
public interface FoodDao {

    @Query("SELECT * FROM food")
    LiveData<List<FoodEntity>> findAll();

    @Query("SELECT * FROM food WHERE food_city LIKE :foodCity AND food_category LIKE :foodCategory ")
    LiveData<List<FoodEntity>> findSelectedCateFood(String foodCity,String foodCategory);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<FoodEntity> foods);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(FoodEntity food);

    @Update
    void update(FoodEntity food);

    @Delete
    void delete(FoodEntity food);

}