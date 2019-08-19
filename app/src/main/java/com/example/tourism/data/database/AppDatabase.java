package com.example.tourism.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tourism.data.FoodEntity;
import com.example.tourism.data.dao.FoodDao;

@Database(version = 1,entities = {FoodEntity.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract FoodDao foodDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(),AppDatabase.class,"app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
