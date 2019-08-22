package com.example.tourism.data.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.amitshekhar.DebugDB;
import com.example.tourism.data.FoodEntity;
import com.example.tourism.data.TourEntity;
import com.example.tourism.data.dao.FoodDao;
import com.example.tourism.data.dao.TourDao;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

@Database(version = 1, entities = {FoodEntity.class, TourEntity.class})
public abstract class AppDatabase extends RoomDatabase {


    private static String TAG = "AppDatabase";

    public abstract FoodDao foodDao();

    public abstract TourDao tourDao();

    private static AppDatabase INSTANCE;

    private final static List<FoodEntity> FOODS = Arrays.asList(
            new FoodEntity(1,"asd","asd","asd","asd","asd","asd"),
            new FoodEntity(2,"asd","asd","asd","asd","asd","asd"),
            new FoodEntity(3,"qwe","qwe","qwe","qwe","qwe","qwe")
    );

    private final static List<TourEntity> TOURS = Arrays.asList(
            new TourEntity(1,"qwe","qwe","qwe","qwe")
    );

    private static final Object sLock = new Object();

    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Executors.newSingleThreadExecutor().execute(
                                        () -> getInstance(context).foodDao().saveAll(FOODS));

                            }
                        })
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Executors.newSingleThreadExecutor().execute(
                                        () -> getInstance(context).tourDao().saveAll(TOURS)
                                );
                            }
                        })
                        .build();
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
