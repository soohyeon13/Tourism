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
    private String tourCity,tourCategory;
    public TourViewModel(@NonNull Application application, TourViewContract tourViewContract) {
        super(application);
        this.tourviewContract = tourViewContract;
        tourDao = AppDatabase.getInstance(application).tourDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<TourEntity>> getAllTours(){return tourDao.findAll();}
    public LiveData<List<TourEntity>> getSelectedCateTour(){return tourDao.findSelectedCateTour(tourCity,tourCategory);}

    public void saveTour(TourEntity tourEntity){
        executorService.execute(() -> tourDao.save(tourEntity));
    }

    public void deleteTour(TourEntity tourEntity) {
        executorService.execute(() -> tourDao.delete(tourEntity));
    }

    public void btnClick(View v) {
        tourviewContract.btnClick(v);
    }
    public void onCheckedChanged(View v) {
        switch (v.getId()) {
            case R.id.check_jeju:
                tourCity = "제주시";
                break;
            case R.id.check_seogwipo:
                tourCity = "서귀포";
                break;
            case R.id.check_east:
                tourCategory = "동부";
                break;
            case R.id.check_jeju_city:
                tourCategory = "제주시";
                break;
            case R.id.check_west:
                tourCategory = "서부";
                break;
            case R.id.check_seogwi_city:
                tourCategory = "서귀포시";
                break;
        }
    }
}
