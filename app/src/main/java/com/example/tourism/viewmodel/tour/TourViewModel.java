package com.example.tourism.viewmodel.tour;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tourism.R;
import com.example.tourism.contract.TourViewContract;
import com.example.tourism.data.TourEntity;
import com.example.tourism.data.dao.TourDao;
import com.example.tourism.data.database.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TourViewModel extends AndroidViewModel {
    private final TourViewContract tourviewContract;
    private TourDao tourDao;
    private ExecutorService executorService;
    public TourViewModel(@NonNull Application application, TourViewContract tourViewContract) {
        super(application);
        this.tourviewContract = tourViewContract;
        tourDao = AppDatabase.getInstance(application).tourDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<TourEntity>> getTourSmallCate(String smallCate) {return tourDao.findTourSmallCate(smallCate);}
    public LiveData<List<TourEntity>> getAllTours(){return tourDao.findAll();}
    public LiveData<List<TourEntity>> getSelectedCateTour(String tourCategory){return tourDao.findSelectedCateTour(tourCategory);}

    public void saveTour(TourEntity tourEntity){
        executorService.execute(() -> tourDao.save(tourEntity));
    }

    public void deleteTour(TourEntity tourEntity) {
        executorService.execute(() -> tourDao.delete(tourEntity));
    }

    public void btnClick(View v) {
        tourviewContract.btnClick(v);
    }
}
