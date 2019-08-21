package com.example.tourism.viewmodel.tour;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tourism.data.TourEntity;
import com.example.tourism.data.dao.TourDao;
import com.example.tourism.data.database.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TourViewModel extends AndroidViewModel {
    private TourDao tourDao;
    private ExecutorService executorService;
    public TourViewModel(@NonNull Application application) {
        super(application);
        tourDao = AppDatabase.getInstance(application).tourDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<TourEntity>> getAllTours(){return tourDao.findAll();}

    public void saveTour(TourEntity tourEntity){
        executorService.execute(() -> tourDao.save(tourEntity));
    }

    public void deleteTour(TourEntity tourEntity) {
        executorService.execute(() -> tourDao.delete(tourEntity));
    }
}
