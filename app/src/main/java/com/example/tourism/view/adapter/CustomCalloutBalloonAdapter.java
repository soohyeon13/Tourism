package com.example.tourism.view.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.tourism.R;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;

public class CustomCalloutBalloonAdapter implements CalloutBalloonAdapter {
    private final View mCalloutBallon;
    private final String name;

    public CustomCalloutBalloonAdapter(Activity activity, String name) {
        mCalloutBallon = activity.getLayoutInflater().inflate(R.layout.callout_balloon_item,null);
        this.name = name;
    }

    @Override
    public View getCalloutBalloon(MapPOIItem mapPOIItem) {
        ((TextView)mCalloutBallon.findViewById(R.id.name)).setText(name);

        return null;
    }

    @Override
    public View getPressedCalloutBalloon(MapPOIItem mapPOIItem) {
        return null;
    }
}
