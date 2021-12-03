package com.example.lgfood_duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    private SupportMapFragment
            ChiaSeGioHang_google_map;
    private LinearLayout
            ChiaSeGioHang_llout_btn_ggmap_phatTinNhieu,
            ChiaSeGioHang_llout_btn_rscv_phatTinHieu;
    private RecyclerView
            ChiaSeGioHang_rscv_showDanhSach;
    private ImageView
            ChiaSeGioHang_img_btn_back,
            ChiaSeGioHang_img_btn_showTheo,
            ChiaSeGioHang_img_phongTo,
            ChiaSeGioHang_img_thuNho;
    private EditText
            ChiaSeGioHang_edt_timKiemKey;
    private CardView
            ChiaSeGioHang_crv_btn_showTheo,
            ChiaSeGioHang_crv_btn_viTri;
    private FrameLayout
            ChiaSeGioHang_frlout_showListGgMap,
            ChiaSeGioHang_frlout_showListRscv;
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
// value
    boolean checkOnclick = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chia_se_gio_hang);
        anhXa();
        client = LocationServices.getFusedLocationProviderClient(this);
        batSuKien();
        fetchLastLocation();
        arrayList.add(sydney);
        arrayList.add(TamMorth);
        arrayList.add(NewCastlte);
        arrayList.add(Dubbo);
    }

    private void batSuKien() {
//        thoát trang
        ChiaSeGioHang_img_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiaSeGioHang_Activity.this, gio_Hang_Activity.class);
                startActivity(intent);
            }
        });
//  show theo gg map/ list danh sach
        ChiaSeGioHang_crv_btn_showTheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkOnclick == true ){
                    ChiaSeGioHang_img_btn_showTheo.setImageResource(R.drawable.ic_col_sort_row);

                    checkOnclick = false;

                }else{
                    ChiaSeGioHang_img_btn_showTheo.setImageResource(R.drawable.ic_ggmap);

                    checkOnclick = true;
                }
            }
        });

    }

    // Trung xin quyền gg map
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


    //Trung show gg map
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

    // Trung bắt sự kiện xin quyền location
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

    // Trung ánh  xạ activity
    private void anhXa() {
//        GG map
        ChiaSeGioHang_google_map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.chiaSeGioHang_google_map);
//         LinearLayout
        ChiaSeGioHang_llout_btn_ggmap_phatTinNhieu = findViewById(R.id.chiaSeGioHang_llout_btn_ggmap_phatTinNhieu);
        ChiaSeGioHang_llout_btn_rscv_phatTinHieu = findViewById(R.id.chiaSeGioHang_llout_btn_rscv_phatTinHieu);
//         RecyclerView
        ChiaSeGioHang_rscv_showDanhSach = findViewById(R.id.chiaSeGioHang_rscv_showDanhSach);
//         ImageView
        ChiaSeGioHang_img_btn_back = findViewById(R.id.chiaSeGioHang_img_btn_back);
        ChiaSeGioHang_img_btn_showTheo = findViewById(R.id.chiaSeGioHang_img_btn_showTheo);
        ChiaSeGioHang_img_phongTo = findViewById(R.id.chiaSeGioHang_img_phongTo);
        ChiaSeGioHang_img_thuNho = findViewById(R.id.chiaSeGioHang_img_thuNho);
//         EditText
        ChiaSeGioHang_edt_timKiemKey = findViewById(R.id.chiaSeGioHang_edt_timKiemKey);
//         CardView
        ChiaSeGioHang_crv_btn_showTheo = findViewById(R.id.chiaSeGioHang_crv_btn_showTheo);
        ChiaSeGioHang_crv_btn_viTri = findViewById(R.id.chiaSeGioHang_crv_btn_viTri);
//         FrameLayout
        ChiaSeGioHang_frlout_showListGgMap = findViewById(R.id.chiaSeGioHang_frlout_showListGgMap);
        ChiaSeGioHang_frlout_showListRscv = findViewById(R.id.chiaSeGioHang_frlout_showListRscv);
    }

}