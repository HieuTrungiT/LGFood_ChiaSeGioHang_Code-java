package com.example.lgfood_duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.lgfood_duan1.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class ChiaSeGioHang_Activity extends AppCompatActivity implements OnMapReadyCallback {
    private Button
            ChiaSeGioHang_btn_chiaSe,
            ChiaSeGioHang_btn_nhan;
    private SupportMapFragment
            ChiaSeGioHang_google_map;
    //    ggmap
    Location currentLocation;
    FusedLocationProviderClient client;
    private static final int REQUEST_CODE = 101;
    //    mảng
    ArrayList<LatLng> arrayList = new ArrayList<LatLng>();
    LatLng sydney = new LatLng(-34, 15);
    LatLng TamMorth = new LatLng(-32.083332, 150.916672);
    LatLng NewCastlte = new LatLng(-27.470125, 153.021072);
    LatLng Dubbo = new LatLng(-32.256943, 148.601105);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chia_se_gio_hang);
        anhXa();
        client = LocationServices.getFusedLocationProviderClient(this);
        batSuKien();
        arrayList.add(sydney);
        arrayList.add(TamMorth);
        arrayList.add(NewCastlte);
        arrayList.add(Dubbo);
    }

    private void batSuKien() {
        fetchLastLocation();

    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    Toast.makeText(ChiaSeGioHang_Activity.this, currentLocation.getLatitude()
                            + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();


                    ChiaSeGioHang_google_map.getMapAsync(ChiaSeGioHang_Activity.this);

                }
            }
        });
    }

    // ánh  xạ activity
    private void anhXa() {
//        Button
        ChiaSeGioHang_btn_chiaSe = findViewById(R.id.chiaSeGioHang_btn_chiaSe);
        ChiaSeGioHang_btn_nhan = findViewById(R.id.chiaSeGioHang_btn_nhan);

//        GG map
        ChiaSeGioHang_google_map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.chiaSeGioHang_google_map);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        for (int i = 0; i < arrayList.size(); i++) {
            LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());

            MarkerOptions markerOptions = new MarkerOptions().position(arrayList.get(i))
                    .title(arrayList.get(i) + "");
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(arrayList.get(i), 20));

            googleMap.addMarker(markerOptions);
//                mMap = googleMap;
//
//            mMap.addMarker(new MarkerOptions().position().title("Marker"));
//            mMap
//
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLastLocation();
                }
                break;
        }
    }


}