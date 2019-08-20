package com.example.tourism.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tourism.data.TourEntity;

import java.util.List;

@Dao
public interface TourDao {
    @Query("SELECT * FROM tour")
    LiveData<List<TourEntity>> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(TourEntity tour);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<TourEntity> tour);

    @Update
    void update(TourEntity tour);

    @Delete
    void delete(TourEntity tour);

}
