package com.example.tourism.viewmodel.tour;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

import com.example.tourism.R;
import com.example.tourism.contract.ImageContract;
import com.example.tourism.data.TourEntity;
import com.example.tourism.data.dao.TourDao;
import com.example.tourism.data.database.AppDatabase;
import com.example.tourism.model.ImageVO;
import com.example.tourism.service.GPSService;
import com.example.tourism.service.ImageService;
import com.kakao.kakaonavi.KakaoNaviParams;
import com.kakao.kakaonavi.KakaoNaviService;
import com.kakao.kakaonavi.Location;
import com.kakao.kakaonavi.NaviOptions;
import com.kakao.kakaonavi.options.CoordType;
import com.kakao.kakaonavi.options.RpOption;
import com.kakao.kakaonavi.options.VehicleType;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TourDetailViewModel extends AndroidViewModel {
    public final ObservableField<String> tourName = new ObservableField<>();
    public final ObservableField<String> tourLocation = new ObservableField<>();
    public final ObservableField<String> tourTime = new ObservableField<>();
    public final ObservableField<String> tourDescribe = new ObservableField<>();
    public final ObservableField<Integer> tourImg = new ObservableField<>();
    public final Observable<ImageVO> imageVOObservable;
    private final ImageService imageService;
    private final ImageContract imageContract;

    private Context context;

    private int getId;
    private TourDao tourDao;

    public ObservableField<String> getTourName() { return tourName; }
    public ObservableField<String> getTourLocation() { return tourLocation; }
    public ObservableField<String> getTourTime() { return tourTime; }
    public ObservableField<String> getTourDescribe() { return tourDescribe; }
    public ObservableField<Integer> getTourImg() {return  tourImg;}

    public TourDetailViewModel(@NonNull Application application, int id, Context context, ImageService imageService, ImageContract imageContract) {
        super(application);
        this.getId = id;
        this.context = context;
        this.imageService = imageService;
        this.imageContract = imageContract;
        tourDao = AppDatabase.getInstance(application).tourDao();

        imageVOObservable = imageService.getData();
    }

    public TourEntity getDetailTour() { return tourDao.findDetailTour(getId); }
    public void updateTourLike(int like) {tourDao.likeTourUpdate(like,getId);}

    public void loadDetail() {
        if (getDetailTour().getTourLike() == 1) tourImg.set(R.drawable.like_color);
        else if (getDetailTour().getTourLike() == 0) tourImg.set(R.drawable.like);
        tourName.set(getDetailTour().getTourName());
        tourLocation.set(getDetailTour().getTourLocation());
        tourTime.set(getDetailTour().getTourTime());
        tourDescribe.set(getDetailTour().getTourDescribe());
    }

    public void likeClick(View view) {
        if (getDetailTour().getTourLike() == 1) {
            updateTourLike(0);
            tourImg.set(R.drawable.like);
        }else if (getDetailTour().getTourLike() == 0) {
            updateTourLike(1);
            tourImg.set(R.drawable.like_color);
        }
    }

    public void startKakaoMapView(View v) {
        GPSService gpsService = new GPSService(context);

        double lo = gpsService.getPointFromGeoCoder(tourLocation.get()).x;
        double la = gpsService.getPointFromGeoCoder(tourLocation.get()).y;
        imageContract.onClick(la,lo,tourName.get());
    }

    @SuppressLint("CheckResult")
    public void loadImages() {
        imageVOObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(vo -> {
                    imageContract.showImages(vo.documents);
                });
    }
}
