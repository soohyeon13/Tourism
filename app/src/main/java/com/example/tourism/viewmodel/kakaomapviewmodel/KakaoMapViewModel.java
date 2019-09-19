package com.example.tourism.viewmodel.kakaomapviewmodel;

import android.content.Context;
import android.view.View;

import com.kakao.kakaonavi.KakaoNaviParams;
import com.kakao.kakaonavi.KakaoNaviService;
import com.kakao.kakaonavi.Location;
import com.kakao.kakaonavi.NaviOptions;
import com.kakao.kakaonavi.options.CoordType;
import com.kakao.kakaonavi.options.RpOption;
import com.kakao.kakaonavi.options.VehicleType;

public class KakaoMapViewModel {
    private final Context context;
    private final double[] list;
    private final String name;

    public KakaoMapViewModel(double[] list, Context context,String name) {
        this.context = context;
        this.list = list;
        this.name = name;
    }

    public void startKakaoNavi(View view) {
        Location destination = Location.newBuilder(name,list[1],list[0]).build();
        NaviOptions options = NaviOptions.newBuilder().setCoordType(CoordType.WGS84).setVehicleType(VehicleType.FIRST).setRpOption(RpOption.SHORTEST).build();
        KakaoNaviParams.Builder builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options);
        KakaoNaviParams params = builder.build();
        KakaoNaviService.navigate(context,builder.build());
    }
}
