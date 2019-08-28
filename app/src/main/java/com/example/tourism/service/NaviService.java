package com.example.tourism.service;

import android.content.Context;

import com.kakao.kakaonavi.KakaoNaviParams;
import com.kakao.kakaonavi.KakaoNaviService;
import com.kakao.kakaonavi.Location;
import com.kakao.kakaonavi.NaviOptions;
import com.kakao.kakaonavi.options.CoordType;
import com.kakao.kakaonavi.options.RpOption;
import com.kakao.kakaonavi.options.VehicleType;


//TODo 리펙토
public class NaviService {
    private Context context;

    public NaviService(Context context) {
        this.context = context;
    }

    public void onKakaoNavi() {
        Location destination = Location.newBuilder("카카오 판교 오피스",127.10821222694533,37.40205604363057).build();
        NaviOptions options = NaviOptions.newBuilder().setCoordType(CoordType.WGS84).setVehicleType(VehicleType.FIRST).setRpOption(RpOption.SHORTEST).build();
        KakaoNaviParams.Builder builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options);
        KakaoNaviService.navigate(context,builder.build());
    }
}
