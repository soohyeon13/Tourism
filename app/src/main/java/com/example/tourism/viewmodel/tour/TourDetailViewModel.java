package com.example.tourism.viewmodel.tour;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

import com.example.tourism.data.TourEntity;
import com.example.tourism.data.dao.TourDao;
import com.example.tourism.data.database.AppDatabase;
import com.example.tourism.service.GPSService;
import com.kakao.kakaonavi.KakaoNaviParams;
import com.kakao.kakaonavi.KakaoNaviService;
import com.kakao.kakaonavi.Location;
import com.kakao.kakaonavi.NaviOptions;
import com.kakao.kakaonavi.options.CoordType;
import com.kakao.kakaonavi.options.RpOption;
import com.kakao.kakaonavi.options.VehicleType;

public class TourDetailViewModel extends AndroidViewModel {
    public final ObservableField<String> tourName = new ObservableField<>();
    public final ObservableField<String> tourLocation = new ObservableField<>();
    public final ObservableField<String> tourTime = new ObservableField<>();
    public final ObservableField<String> tourDescribe = new ObservableField<>();

    private Context context;

    private int getId;
    private TourDao tourDao;

    public ObservableField<String> getTourName() { return tourName; }
    public ObservableField<String> getTourLocation() { return tourLocation; }
    public ObservableField<String> getTourTime() { return tourTime; }
    public ObservableField<String> getTourDescribe() { return tourDescribe; }

    public TourDetailViewModel(@NonNull Application application, int id, Context context) {
        super(application);
        this.getId = id;
        this.context = context;
        tourDao = AppDatabase.getInstance(application).tourDao();
    }

    public TourEntity getDetailTour() {
        return tourDao.findDetailTour(getId);
    }

    public void loadDetail() {
        tourName.set(getDetailTour().getTourName());
        tourLocation.set(getDetailTour().getTourLocation());
        tourTime.set(getDetailTour().getTourTime());
        tourDescribe.set(getDetailTour().getTourDescribe());
    }

    public void onKaKaoNavi(View v) {
        GPSService gpsService = new GPSService(context);

        double lo = gpsService.getPointFromGeoCoder(tourLocation.get()).x;
        double la = gpsService.getPointFromGeoCoder(tourLocation.get()).y;

        Location destination = Location.newBuilder(tourLocation.get(),lo,la).build();
        NaviOptions options = NaviOptions.newBuilder().setCoordType(CoordType.WGS84).setVehicleType(VehicleType.FIRST).setRpOption(RpOption.SHORTEST).build();
        KakaoNaviParams.Builder builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options);
        KakaoNaviParams params = builder.build();
        KakaoNaviService.navigate(context,builder.build());
    }
}
