package com.wherecycle.smartrecycle.model;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wherecycle.smartrecycle.MainActivity;
import com.wherecycle.smartrecycle.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MapActivityShowAll extends AppCompatActivity implements OnMapReadyCallback {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //this method adds a back button.
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActionBar ab = getSupportActionBar();
        if(ab!=null){
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        Log.d(TAG, "onMapReady: map is ready ");
        mMap = googleMap;

        final SharedPreferences sp;
        final String fileName = "myFile";

        final int recyc;
        sp = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        recyc = sp.getInt("nameKey", -1);                                                      //recyc is assigned the the shared preference value from the RecyclerView



        DatabaseReference myDB;                                                                     //declare the database
        myDB = FirebaseDatabase.getInstance().getReference();                                       //and instantiate it
        myDB.child("Recycling Centres").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();                       //iterable gives us the ability to traverse the database
                for (DataSnapshot child : children) {                                               //here we traverse the iterable
                    Locations locations = child.getValue(Locations.class);                          //we make a new location equal to the location the iterable is currently looking at
                    if (recyc == 0) {                                                               //here we check what recyc is equal to , to discover which Recycler item was clicked
                        if (locations.isAlumin()) {                                                 //then the appropriate boolean is checked. This filters out all the Locations that do not recycle the desired material
                            LatLng newLocation = new LatLng(locations.getLat(), locations.getLng());//we create a LatLng data type here, calling the getter methods of the Location
                            mMap.addMarker(new MarkerOptions().position(newLocation).title(locations.getName()).snippet("Phone: "+locations.getPhone()));   //here we use our LatLng to make a marker and to add a title and snippet from th eLOcation information
                        }
                    } else if (recyc == 7) {
                        if (locations.isBatteries()) {
                            LatLng newLocation = new LatLng(locations.getLat(), locations.getLng());
                            mMap.addMarker(new MarkerOptions().position(newLocation).title(locations.getName()).snippet("Phone: "+locations.getPhone()));
                        }
                    } else if (recyc == 6) {
                        if (locations.isCardboard()) {
                            LatLng newLocation = new LatLng(locations.getLat(), locations.getLng());
                            mMap.addMarker(new MarkerOptions().position(newLocation).title(locations.getName()).snippet("Phone: "+locations.getPhone()));
                        }
                    } else if (recyc == 5) {
                        if (locations.isElectronics()) {
                            LatLng newLocation = new LatLng(locations.getLat(), locations.getLng());
                            mMap.addMarker(new MarkerOptions().position(newLocation).title(locations.getName()).snippet("Phone: "+locations.getPhone()));
                        }
                    } else if (recyc == 4) {
                        if (locations.isFurniture()) {
                            LatLng newLocation = new LatLng(locations.getLat(), locations.getLng());
                            mMap.addMarker(new MarkerOptions().position(newLocation).title(locations.getName()).snippet("Phone: "+locations.getPhone()));
                        }
                    } else if (recyc == 8) {
                        if (locations.isGlass()) {
                            LatLng newLocation = new LatLng(locations.getLat(), locations.getLng());
                            mMap.addMarker(new MarkerOptions().position(newLocation).title(locations.getName()).snippet("Phone: "+locations.getPhone()));
                        }
                    } else if (recyc == 2) {
                        if (locations.isMetal()) {
                            LatLng newLocation = new LatLng(locations.getLat(), locations.getLng());
                            mMap.addMarker(new MarkerOptions().position(newLocation).title(locations.getName()).snippet("Phone: "+locations.getPhone()));
                        }
                    } else if (recyc == 3) {
                        if (locations.isPlastics()) {
                            LatLng newLocation = new LatLng(locations.getLat(), locations.getLng());
                            mMap.addMarker(new MarkerOptions().position(newLocation).title(locations.getName()).snippet("Phone: "+locations.getPhone()));
                        }
                    } else if (recyc == 1) {
                        if (locations.isTextiles()){
                            LatLng newLocation = new LatLng(locations.getLat(), locations.getLng());
                            mMap.addMarker(new MarkerOptions().position(newLocation).title(locations.getName()).snippet("Phone: "+locations.getPhone()));
                        }
                    } else{
                        LatLng newLocation = new LatLng(locations.getLat(), locations.getLng());
                        mMap.addMarker(new MarkerOptions().position(newLocation).title(locations.getName()).snippet("Phone: "+locations.getPhone()));
                    }
                }
                sp.edit().clear().commit();//resets the map to show all markers
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MapActivityShowAll.this, "Database Read Failed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MapActivityShowAll.this, MainActivity.class);
                startActivity(intent);
            }
        });

        if (mLocationPermissionGranted) { //if location permissions are accepted
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            //gets rid of location centering button because the search bar covers it anyway.

        }
    }

    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQ_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15;

    //widgets
    private EditText mSearchText;
    private ImageView mGps;

    //variables
    private Boolean mLocationPermissionGranted = false;
    private GoogleMap mMap;
    private com.google.android.gms.location.FusedLocationProviderClient mFusedLocationClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        mSearchText = (EditText) findViewById(R.id.input_search);
        mGps = (ImageView) findViewById(R.id.ic_gps);




        getLocationPermission();

        init();
    }

    private void init(){
        Log.d(TAG, "init: initialising");

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == keyEvent.ACTION_DOWN
                        || keyEvent.getAction() == keyEvent.KEYCODE_ENTER) {

                    //execute method for searching
                    geoLocate();
                    mSearchText.setText(null);  // clears field when the search is finished.

                }
                return false;
            }

        });

        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked gps icon");
                getDeviceLocation();
            }
        });

        hideSoftKeyboard();
    }
    private void geoLocate(){ //this method is used to find a location through search.
        Log.d(TAG, "geoLocate: geolocating");

        String searchString = mSearchText.getText().toString();

        Geocoder geocoder = new Geocoder(MapActivityShowAll.this);
        List<Address> list = new ArrayList<>();
        try{
            list= geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Log.e(TAG, "geolocate: IOException" + e.getMessage() );

        }
        if(list.size()>0){
            Address address = list.get(0);

            moveCamera(new LatLng(address.getLatitude(),address.getLongitude()), 12.0f,
                    address.getAddressLine(0));

        }
    }


    //The below method finds the location of the device which will act as a starting location for
    // the map when it is initialised.
    private void getDeviceLocation(){
        Log.d(TAG,"getDeviceLocation: current location");

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionGranted){
                final Task <Location> location = mFusedLocationClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 9.0f,
                                    "My Location");

                        }else{
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(MapActivityShowAll.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

        }catch(SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

    // this method moves the camera to a specific location that is searched. It also adds a marker.
    private void moveCamera(LatLng latLng, float zoom, String title ){
        Log.d(TAG, "moveCamera: moving the camera to lat" + latLng.latitude + ", lng " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        if(!title.equals("My Location")){
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);
        }

        hideSoftKeyboard();

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
                mLocationPermissionGranted = true; //both location permissions granted
                initMap();
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQ_CODE);
                        //asks for permission for Coarse Location
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
            mLocationPermissionGranted = false; //initially set to false

            switch (requestCode) {
                case LOCATION_PERMISSION_REQ_CODE: {
                    if (grantResults.length > 0) {
                        for (int i = 0; i < grantResults.length; i++) {
                            //loops though all the grant results as the could be more than one.
                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                mLocationPermissionGranted = false;
                                Log.d(TAG, "onRequestPermissionResult: failed.");
                                return;
                            }
                        }
                        Log.d(TAG, "onRequestPermissionResult: granted.");
                        mLocationPermissionGranted = true;
                        //then initialise map because permissions have been granted
                        initMap();
                    }
                }
            }
    }

    private void hideSoftKeyboard(){
        Log.d(TAG, "hideSoftKeyboard: hiding.");
         this.getWindow().setSoftInputMode((WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN));
    }

}


