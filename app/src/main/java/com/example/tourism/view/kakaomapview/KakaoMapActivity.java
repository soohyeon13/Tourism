package com.example.tourism.view.kakaomapview;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.tourism.R;
import com.example.tourism.databinding.ActivityKakaoMapBinding;
import com.example.tourism.viewmodel.kakaomapviewmodel.KakaoMapViewModel;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.Objects;


public class KakaoMapActivity extends Fragment {

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
//        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(list[0],list[1]),true);
        point = MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633);
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
        marker.setCustomImageAnchor(0.5f,1.0f);
        mapView.addPOIItem(marker);



    }
}
