package com.example.tourism.service;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.tourism.GPSTracker;
import com.example.tourism.R;
import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.model.WeatherSearch;
import com.example.tourism.model.WeatherVO;
import com.example.tourism.view.FirstActivity;
import com.highbryds.gpstracker.GPSService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherService extends Service implements RetrofitService {
    private final FirstViewContract firstViewContract;
    private final WeatherSearch weatherSearch;
    private String App_Id = "dc30cb9f6d62581f6c4159dbdbc95bff";
    private Context context;
    private double latitude;
    private double longitude;
    public static String address;


    public WeatherService(FirstViewContract firstViewContract, WeatherSearch weatherSearch, Context context) {
        this.firstViewContract = firstViewContract;
        this.weatherSearch = weatherSearch;
        this.context = context;

    }


    public void getGPS() {
        GPSTracker gpsTracker = new GPSTracker(context);

        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();

        address = getCurrentAddress(latitude, longitude,context);
    }

    public String getCurrentAddress(double latitude, double longitude,Context context) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        }

        Address address = addresses.get(0);
        String adminArea = address.getAdminArea();
        String countryName = address.getCountryName();
        String featureName = address.getFeatureName();
        String city = address.getAddressLine(0).replace(adminArea,"").replace(countryName,"").replace(featureName,"");
        city = city.trim();
        return city;

    }

    @SuppressLint("CheckResult")
    @Override
    public void getData() {
        getGPS();
        Observable<WeatherVO> observable = weatherSearch.getCurrentWeatherData(String.valueOf(latitude), String.valueOf(longitude), App_Id);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(firstViewContract::showWeather, firstViewContract::showError);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
