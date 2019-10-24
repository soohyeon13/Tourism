package com.example.tourism.viewmodel.food;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

import com.example.tourism.contract.ImageContract;
import com.example.tourism.data.FoodEntity;
import com.example.tourism.data.dao.FoodDao;
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

public class FoodDetailViewModel extends AndroidViewModel {
    public final ObservableField<String> foodName = new ObservableField<>();
    public final ObservableField<String> foodCall = new ObservableField<>();
    public final ObservableField<String> foodLocation = new ObservableField<>();
    public final ObservableField<String> foodTime = new ObservableField<>();
    public final ObservableField<String> foodMenu = new ObservableField<>();
    public final ObservableField<String> foodImg = new ObservableField<>();
    private final int id;
    private final ImageContract imageContract;
    private Context context;
    private FoodDao foodDao;
    public final Observable<ImageVO> imageVOObservable;
    private ImageService imageService;

    public FoodDetailViewModel(@NonNull Application application , int id, Context context, ImageService imageService, ImageContract imageContract) {
        super(application);
        foodDao = AppDatabase.getInstance(application).foodDao();
        this.id = id;
        this.context = context;
        this.imageService = imageService;
        this.imageContract = imageContract;
        imageVOObservable = imageService.getData();
    }

    public ObservableField<String> getFoodName() { return foodName; }
    public ObservableField<String> getFoodCall() { return foodCall; }
    public ObservableField<String> getFoodLocation() { return foodLocation; }
    public ObservableField<String> getFoodTime() { return foodTime; }
    public ObservableField<String> getFoodMenu() { return foodMenu; }

    public FoodEntity getDetailFood() { return foodDao.findDetailFood(id);}

    public void loadDetail() {
        foodName.set(getDetailFood().getFoodName());
        foodCall.set(getDetailFood().getFoodCallNum());
        foodLocation.set(getDetailFood().getFoodLocation());
        foodTime.set(getDetailFood().getFoodDate());
        foodMenu.set(getDetailFood().getFoodMenu());
    }

    public void onKaKaoMap(View v) {


        GPSService gpsService = new GPSService(context);

        double la = gpsService.getPointFromGeoCoder(foodLocation.get()).y;
        double lo = gpsService.getPointFromGeoCoder(foodLocation.get()).x;

        imageContract.onClick(la,lo,foodName.get());
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
