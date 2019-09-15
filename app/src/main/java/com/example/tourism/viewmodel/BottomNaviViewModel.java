package com.example.tourism.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import android.view.MenuItem;

import com.example.tourism.R;
import com.example.tourism.contract.BottomNaviContract;
import com.example.tourism.view.FirstActivity;
import com.example.tourism.view.bottomnavi.ActivityLikeList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class BottomNaviViewModel implements BottomNavigationView.OnNavigationItemSelectedListener{
    private final Activity activity;
    private final Context context;

    public BottomNaviViewModel( Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }


    @Override
    public boolean onNavigationItemSelected(@androidx.annotation.NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.return_home_menu:
                Log.d("####1",String.valueOf(item.getItemId()));
                return true;
            case R.id.like_list_menu:
                Log.d("####3",String.valueOf(item.getItemId()));
                Intent intent1 = new Intent(context, ActivityLikeList.class);
                context.startActivity(intent1);
                return true;
            case R.id.search_bus_menu:
                String url = "daummaps://roadView?p=37.537229,127.005515";
                String download = "download path";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(intent);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                if (list == null || list.isEmpty()) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(download)));
                }else {
                    context.startActivity(intent);
                }
                Log.d("####4",String.valueOf(item.getItemId()));
                return true;
        }
        return false;
    }

}
