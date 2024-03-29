package com.example.tourism.service;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import com.example.tourism.util.GPSTracker;
import com.example.tourism.model.GPSVO;
import com.example.tourism.model.PointVO;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GPSService {
    private final Context context;
    private Geocoder geocoder;

    public GPSService(Context context) {
        this.context =context;
    }

    public GPSVO getGPS() {
        GPSTracker gpsTracker = new GPSTracker(context);
        GPSVO gpsvo = new GPSVO();
        gpsvo.setLatitude(gpsTracker.getLatitude());
        gpsvo.setLongitude(gpsTracker.getLongitude());
        gpsvo.setAddress(getCurrentAddress(gpsvo,context));

        return gpsvo;
    }

    private String getCurrentAddress(GPSVO vo, Context context) {
        geocoder = new Geocoder(context, Locale.getDefault());

        List<Address> addresses = null;

        try {

            addresses = geocoder.getFromLocation(
                    vo.getLatitude(),
                    vo.getLongitude(),
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(context, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(context, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";
        }

        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(context, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";
        }

        Address address = addresses.get(0);
        String adminArea = address.getAdminArea();
        String countryName = address.getCountryName();
        String featureName = address.getFeatureName();
        String city = address.getAddressLine(0).replace(adminArea,"").replace(countryName,"").replace(featureName,"");

        return city.trim();
    }

    public PointVO getPointFromGeoCoder(String address) {
        PointVO pointVO = new PointVO();
        pointVO.address = address;

        geocoder = new Geocoder(context);
        List<Address> listAddress;
        address += "제주 ";
        try {
            listAddress = geocoder.getFromLocationName(address,1);
        } catch (IOException e) {
            e.printStackTrace();
            pointVO.havePoint = false;
            return pointVO;
        }
        if (listAddress.isEmpty()) {
            pointVO.havePoint =false;
            return pointVO;
        }

        pointVO.havePoint = true;
        pointVO.x = listAddress.get(0).getLongitude();
        pointVO.y = listAddress.get(0).getLatitude();
        return pointVO;
    }

}
