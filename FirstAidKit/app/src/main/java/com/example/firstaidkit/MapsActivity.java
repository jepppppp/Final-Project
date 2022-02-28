package com.example.firstaidkit;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Address> listGeoCoder;
    private static final int LOCATION_PERMISSION_CODE = 101;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        searchView = findViewById(R.id.location_search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if (location != null  || !location.equals("")){
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location,1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });




        if (isLocationPermissionGranted()) {

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);



            try {
                listGeoCoder = new Geocoder(this).getFromLocationName("Villasis Pangasinan Market", 1);
                listGeoCoder = new Geocoder(this).getFromLocationName("Rosales Pangasinan Public Market, General Luna Street, Rosales, Pangasinan", 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            double longitude = listGeoCoder.get(0).getLongitude();
            double latitude = listGeoCoder.get(0).getLatitude();

            Log.i("GOOGLE_MAP_TAG", "Address has Longitude" + ":::" + String.valueOf(longitude) + "Latitude" + String.valueOf(latitude));
        }
        else {
            requestLocationPermission();
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Urdaneta and move the camera
        LatLng urdaneta = new LatLng(15.981661777741152, 120.57070231615755);
        mMap.addMarker(new MarkerOptions().position(urdaneta).title("Urdaneta Sacred Heart Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(urdaneta));

        LatLng urdaneta1 = new LatLng(15.974506795449225, 120.57781239454255);
        mMap.addMarker(new MarkerOptions().position(urdaneta1).title("Urdaneta District Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(urdaneta1));

        LatLng urdaneta2 = new LatLng(15.958091232040834, 120.57550225441474);
        mMap.addMarker(new MarkerOptions().position(urdaneta2).title("Francisco General Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(urdaneta2));

        LatLng urdaneta4 = new LatLng(15.974606764553176, 120.57799792505655);
        mMap.addMarker(new MarkerOptions().position(urdaneta4).title("Don Amadeo Perez Memorial Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(urdaneta4));

        LatLng urdanet5 = new LatLng(15.978443732403425, 120.56387877606979);
        mMap.addMarker(new MarkerOptions().position(urdanet5).title("Divine Mercy Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(urdanet5));

        LatLng urdaneta6 = new LatLng(15.9751431193207, 120.57799792505655);
        mMap.addMarker(new MarkerOptions().position(urdaneta6).title("Urdaneta District Hospital - Emergency"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(urdaneta6));

        LatLng urdaneta7 = new LatLng(15.978856305210979, 120.57044482407882);
        mMap.addMarker(new MarkerOptions().position(urdaneta7).title("Francisco General Hospital & Trauma Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(urdaneta7));

        LatLng urdaneta8 = new LatLng(15.97605079334512, 120.57078814685053);
        mMap.addMarker(new MarkerOptions().position(urdaneta8).title("Aarogya Deep Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(urdaneta8));

        LatLng urdanet9 = new LatLng(15.97572073053982, 120.57855582456058);
        mMap.addMarker(new MarkerOptions().position(urdanet9).title("Administration Builiding"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(urdanet9));

        LatLng urdaneta10 = new LatLng(15.98157926440472, 120.57057357011817);
        mMap.addMarker(new MarkerOptions().position(urdaneta10).title("Urdaneta Sacred Heart Hospital, Pediatrics and Cardiology Clinic Information - Virginia C. Mappala,"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(urdaneta10));


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);



    }

    private boolean isLocationPermissionGranted() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else
        {
            return false;
        }
    }
    private void requestLocationPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_CODE);
    }

}