package com.example.tantao.broadcastbestpractice;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tantao.broadcastbestpractice.base.BaseActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoactionMapActivity extends BaseActivity {

    @InjectView(R.id.locationMap)
    TextView locationMap;


    private static final int SHOWLOCATION=0;
    private LocationManager locationManager;
    private String provider;


    LocationListener locationListener=new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaction_map);
        ButterKnife.inject(this);
        //取得定位服务
        locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
        //获取所有位置提供器
        List<String> providerList=locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)){
            provider=LocationManager.GPS_PROVIDER;
            Log.d("GPS_PROVIDER",provider);
        }
        else if (providerList.contains(LocationManager.NETWORK_PROVIDER)){
            provider=LocationManager.NETWORK_PROVIDER;
            Log.d("NETWORK_PROVIDER",provider);
        }else {
            Toast.makeText(this,"No provider",Toast.LENGTH_SHORT).show();
            return;
        }


        Location location=locationManager.getLastKnownLocation(provider);
            if (location!=null){
                showLocation(location);
            }
        locationManager.requestLocationUpdates(provider,5000,1,locationListener);

    }

    private void showLocation(Location location){
        String currentPosition = "latitude is " + location.getLatitude() + "\n"
                + "longitude is " + location.getLongitude();
        locationMap.setText(currentPosition);




    }

}
