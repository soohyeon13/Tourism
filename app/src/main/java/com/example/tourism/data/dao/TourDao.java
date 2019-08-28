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

    @Query("SELECT * FROM tour WHERE tour_city LIKE :tourCity AND tour_category LIKE :tourCategory ")
    LiveData<List<TourEntity>> findSelectedCateTour(String tourCity, String tourCategory);

    @Query("SELECT * FROM tour WHERE id =:tourId")
    TourEntity findDetailTour(int tourId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(TourEntity tour);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<TourEntity> tour);

    @Update
    void update(TourEntity tour);

    @Delete
    void delete(TourEntity tour);

}
