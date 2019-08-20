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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<FoodEntity> foods);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(FoodEntity food);
//    @Query("SELECT * FROM food WHERE food_city LiKE :city AND food_category LIKE :category")
//    FoodEntity findByAll(String city,String category);

    @Update
    void update(FoodEntity food);

//    @Insert
//    void insertAll(FoodEntity... foods);

    @Delete
    void delete(FoodEntity food);
}
