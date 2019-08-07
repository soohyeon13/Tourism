package com.example.tourism.service;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.tourism.GPSTracker;
import com.example.tourism.R;
import com.example.tourism.contract.FirstViewContract;
import com.example.tourism.model.WeatherSearch;
import com.example.tourism.model.WeatherVO;
import com.example.tourism.view.FirstActivity;
import com.highbryds.gpstracker.GPSService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherService extends Service implements RetrofitService {
    private final FirstViewContract firstViewContract;
    private final WeatherSearch weatherSearch;
//    private final GPSTracker gpsTracker;
    private String App_Id = "dc30cb9f6d62581f6c4159dbdbc95bff";

    public WeatherService(FirstViewContract firstViewContract, WeatherSearch weatherSearch, Context context) {
        this.firstViewContract = firstViewContract;
        this.weatherSearch = weatherSearch;
//        getGPS(context);
//        this.gpsTracker = new GPSTracker(context)
    }

    private void getGPS(Context context) {
        GPSService.LocationInterval = 20000;
        GPSService.LocationFastestInterval = 15000;
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, FirstActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        GPSService.contentIntent = contentIntent;
        GPSService.NotificationTitle = "your app is tracking you";
        GPSService.NotificationTxt = "Amazing Stuff";
        GPSService.drawable_small = R.drawable.ic_launcher_background;

        startForegroundService(new Intent(context, GPSService.class));

    }


    @SuppressLint("CheckResult")
    @Override
    public void getData() {
        Observable<WeatherVO> observable = weatherSearch.getCurrentWeatherData("35" , "139",App_Id);
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


    class GPSReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            double latitude = Double.valueOf(intent.getStringExtra("latitude"));
            double longitude = Double.valueOf(intent.getStringExtra("longitude"));

            double speed = Double.valueOf(intent.getStringExtra("speed"));
            double altitude = Double.valueOf(intent.getStringExtra("altitude"));

            System.out.println("broadcast latitude:" + latitude);
            System.out.println("broadcast speed:" + speed);
            System.out.println("broadcast altitude:" + altitude);
            //Set it to some model class then maintain it in List saved in  sharedprefences - this will help you call the SendtoInternet Method
            //less frequently - i mean based on the number of list items you can take decision that once the list contains 5 items- send it to //the server - Its totally upto you.
//        SendtoInternet(latitude,longitude,altitude,speed)
        }
    }
}
