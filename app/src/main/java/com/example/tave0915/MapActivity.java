package com.example.tave0915;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FragmentManager fragmentManager;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        fragmentManager = getFragmentManager();
        mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        LatLng location = new LatLng(37.513055, 127.059765);
        MarkerOptions makerOptions = new MarkerOptions();
        makerOptions.title("코엑스");
        makerOptions.position(location);
        googleMap.addMarker(makerOptions);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16));

        // 버튼 6개중 하나 선택시 보여지는 위치
        findViewById(R.id.map_button_1).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        LatLng location = new LatLng(37.5162958434477, 127.05196608896408);
                        MarkerOptions makerOptions = new MarkerOptions();
                        makerOptions.title("강남구노인복지회관");
                        makerOptions.snippet("복지회관");
                        makerOptions.position(location);
                        googleMap.addMarker(makerOptions);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16));

                    }
                }
        );
        findViewById(R.id.map_button_2).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        LatLng location = new LatLng(37.517040387414724, 127.04190521599091);
                        MarkerOptions makerOptions = new MarkerOptions();
                        makerOptions.title("강남구보건소");
                        makerOptions.snippet("보건소");
                        makerOptions.position(location);
                        googleMap.addMarker(makerOptions);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16));

                    }
                }
        );
        findViewById(R.id.map_button_3).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        LatLng location = new LatLng(37.510557754240494, 127.05192267858015);
                        MarkerOptions makerOptions = new MarkerOptions();
                        makerOptions.title("봉전경로당");
                        makerOptions.snippet("경로당");
                        makerOptions.position(location);
                        googleMap.addMarker(makerOptions);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16));
                    }
                }
        );
        findViewById(R.id.map_button_4).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        LatLng location = new LatLng(37.51488034442946, 127.06286608908715);
                        MarkerOptions makerOptions = new MarkerOptions();
                        makerOptions.title("삼성1동주민센터");
                        makerOptions.snippet("주민센터");
                        makerOptions.position(location);
                        googleMap.addMarker(makerOptions);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16));
                    }
                }
        );
        findViewById(R.id.map_button_5).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        LatLng location = new LatLng(37.51860243936226, 127.04699425501457);
                        MarkerOptions makerOptions = new MarkerOptions();
                        makerOptions.title("강남구청");
                        makerOptions.snippet("구청");
                        makerOptions.position(location);
                        googleMap.addMarker(makerOptions);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16));

                    }
                }
        );
        findViewById(R.id.map_button_6).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        LatLng location = new LatLng(37.51974664860443, 127.04977690600703);
                        MarkerOptions makerOptions = new MarkerOptions();
                        makerOptions.title("우리들병원");
                        makerOptions.snippet("병원");
                        makerOptions.position(location);
                        googleMap.addMarker(makerOptions);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16));
                    }
                }
        );

        findViewById(R.id.map_button_7).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        LatLng location = new LatLng(37.513055, 127.059765);
                        MarkerOptions makerOptions = new MarkerOptions();
                        makerOptions.snippet("현재 위치");
                        makerOptions.position(location);
                        googleMap.addMarker(makerOptions);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,14));
                    }
                }
        );
    }
}