package com.wherecycle.smartrecycle.model;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wherecycle.smartrecycle.Manifest;
import com.wherecycle.smartrecycle.R;

public class MapActivityShowAll extends AppCompatActivity implements OnMapReadyCallback {
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready ");
        mMap = googleMap;
    }

    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQ_CODE = 1234;

    private Boolean mLocationPermissionGranted = false;
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);

        getLocationPermission();
    }
    

    private void initMap() {
        Log.d(TAG, "init map: initialising map ");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapActivityShowAll.this);
    }


    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQ_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQ_CODE);
        }
    }
        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions,
         int[] grantResults){
            Log.d(TAG, "onRequestPermissionResult: called. ");
            mLocationPermissionGranted = false;

            switch (requestCode) {
                case LOCATION_PERMISSION_REQ_CODE: {
                    if (grantResults.length > 0) {
                        for (int i = 0; i < grantResults.length; i++) {
                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                mLocationPermissionGranted = false;
                                Log.d(TAG, "onRequestPermissionResult: failed.");
                                return;
                            }
                        }
                        Log.d(TAG, "onRequestPermissionResult: granted.");
                        mLocationPermissionGranted = true;
                        //initialise map
                        initMap();
                    }
                }
            }
        }
    }


