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

    @Query("SELECT * FROM food WHERE food_category LIKE :foodCategory ")
    LiveData<List<FoodEntity>> findSelectedCateFood(String foodCategory);

    @Query("SELECT * FROM food WHERE food_like LIKE :foodLike")
    LiveData<List<FoodEntity>> findFoodLikeList(int foodLike);

    @Query("SELECT * FROM food WHERE id =:foodId")
    FoodEntity findDetailFood(int foodId);

    @Query("SELECT * FROM food WHERE food_small_category LIKE :foodSmallCategory")
    LiveData<List<FoodEntity>> findFoodSmallCate(String foodSmallCategory);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<FoodEntity> foods);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(FoodEntity food);

    @Update
    void update(FoodEntity food);

    @Query("UPDATE food SET food_like =:like WHERE id =:id")
    void likeUpdate(int like, int id);

    @Delete
    void delete(FoodEntity food);

}
