package com.example.tourism.view.kakaomapview;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.tourism.R;
import com.example.tourism.databinding.ActivityKakaoMapBinding;
import com.example.tourism.view.adapter.CustomCalloutBalloonAdapter;
import com.example.tourism.viewmodel.kakaomapviewmodel.KakaoMapViewModel;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.Objects;


public class KakaoMapActivity extends Fragment{

    private ActivityKakaoMapBinding binding;
    private View view;
    private double[] list = new double[2];
    private String name;
    private MapView mapView;
    private MapPoint point;
    private MapPOIItem marker;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_kakao_map,container,false);

        view = binding.getRoot();
        list = Objects.requireNonNull(getArguments()).getDoubleArray("location");
        name = getArguments().getString("name");
        binding.setViewModel(new KakaoMapViewModel(list,getContext(), name));
        setupViews();
        return view;
    }

    private void setupViews() {
        mapView = new MapView(getContext());
        ViewGroup mapViewContainer = (ViewGroup) view.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        point = MapPoint.mapPointWithGeoCoord(list[0], list[1]);
        mapView.setMapCenterPoint(point,true);
        mapView.zoomIn(true);
        mapView.zoomOut(true);

        marker = new MapPOIItem();
        marker.setItemName(name);
        marker.setTag(1);
        marker.setMapPoint(point);
        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        marker.setCustomImageResourceId(R.drawable.pin);
        marker.setCustomImageAutoscale(false);
        marker.setCustomImageAnchor(1.0f,1.0f);
        mapView.addPOIItem(marker);
        mapView.setCalloutBalloonAdapter(new CustomCalloutBalloonAdapter(getActivity(),name));

        //Todo 충분한 공부가 필요(더 많은 기능을 생각해봐야됨)
        //Todo ViewModel 로 분리 할 수 있는지를 검토
        MapView.POIItemEventListener poiItemEventListener = new MapView.POIItemEventListener() {
            @Override
            public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
                Toast.makeText(getContext(),"선택되었습니다",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

            }

            @Override
            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

            }

            @Override
            public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

            }
        };
        poiItemEventListener.onPOIItemSelected(mapView,marker);


    }
}


