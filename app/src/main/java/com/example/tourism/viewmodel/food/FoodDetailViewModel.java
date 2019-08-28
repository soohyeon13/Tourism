package com.example.tourism.viewmodel.food;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

import com.example.tourism.data.FoodEntity;
import com.example.tourism.data.dao.FoodDao;
import com.example.tourism.data.database.AppDatabase;
import com.example.tourism.view.FoodDetailActivity;
import com.kakao.kakaonavi.KakaoNaviParams;
import com.kakao.kakaonavi.KakaoNaviService;
import com.kakao.kakaonavi.Location;
import com.kakao.kakaonavi.NaviOptions;
import com.kakao.kakaonavi.options.CoordType;
import com.kakao.kakaonavi.options.RpOption;
import com.kakao.kakaonavi.options.VehicleType;

public class FoodDetailViewModel extends AndroidViewModel {
    public final ObservableField<String> foodName = new ObservableField<>();
    public final ObservableField<String> foodCall = new ObservableField<>();
    public final ObservableField<String> foodLocation = new ObservableField<>();
    public final ObservableField<String> foodTime = new ObservableField<>();
    public final ObservableField<String> foodMenu = new ObservableField<>();
    public final ObservableField<String> foodImg = new ObservableField<>();
    private final int id;
    private Context context;
    private FoodDao foodDao;

    public FoodDetailViewModel(@NonNull Application application , int id, Context context) {
        super(application);
        foodDao = AppDatabase.getInstance(application).foodDao();
        this.id = id;
        this.context = context;
    }

    public ObservableField<String> getFoodName() { return foodName; }
    public ObservableField<String> getFoodCall() { return foodCall; }
    public ObservableField<String> getFoodLocation() { return foodLocation; }
    public ObservableField<String> getFoodTime() { return foodTime; }
    public ObservableField<String> getFoodMenu() { return foodMenu; }
    public ObservableField<String> getFoodImg() { return foodImg; }

    public FoodEntity getDetailFood() { return foodDao.findDetailFood(id);}

    public void loadDetail() {
        foodName.set(getDetailFood().getFoodName());
        foodCall.set(getDetailFood().getFoodCallNum());
        foodLocation.set(getDetailFood().getFoodLocation());
        foodTime.set(getDetailFood().getFoodDate());
    }

    public void onKaKaoNavi(View v) {
        Location destination = Location.newBuilder("카카오 판교 오피스",127.10821222694533,37.40205604363057).build();
        NaviOptions options = NaviOptions.newBuilder().setCoordType(CoordType.WGS84).setVehicleType(VehicleType.FIRST).setRpOption(RpOption.SHORTEST).build();
        KakaoNaviParams.Builder builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options);
        KakaoNaviParams params = builder.build();
        KakaoNaviService.navigate(context,builder.build());
    }
}
