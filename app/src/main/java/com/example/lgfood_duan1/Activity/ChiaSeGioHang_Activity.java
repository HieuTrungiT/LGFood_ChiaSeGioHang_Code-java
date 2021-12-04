package com.example.lgfood_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lgfood_duan1.Adapter.chiaSeGioHang_showDoc_adapter;
import com.example.lgfood_duan1.Model.model_Cart;
import com.example.lgfood_duan1.Model.model_viTri;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class ChiaSeGioHang_Activity extends AppCompatActivity implements OnMapReadyCallback {

    private SupportMapFragment
            ChiaSeGioHang_google_map;
    private LinearLayout
            ChiaSeGioHang_llout_btn_ggmap_phatTinNhieu,
            ChiaSeGioHang_llout_btn_rscv_phatTinHieu,
            ChiaSeGioHang_llout_FormTop,
            ChiaSeGioHang_llout_btn_showFormTop;
    private RecyclerView
            ChiaSeGioHang_rscv_showDanhSach;
    private ImageView
            ChiaSeGioHang_img_btn_back,
            ChiaSeGioHang_img_btn_showTheo,
            ChiaSeGioHang_img_phongTo,
            ChiaSeGioHang_img_thuNho,
            ChiaSeGioHang_img_btn_timKiemKey;
    private EditText
            ChiaSeGioHang_edt_timKiemKey;
    private CardView
            ChiaSeGioHang_crv_btn_showTheo,
            ChiaSeGioHang_crv_btn_viTri;
    private FrameLayout
            ChiaSeGioHang_frlout_showListGgMap,
            ChiaSeGioHang_frlout_showListRscv;
    //    ggmap
    private Location currentLocation;
    private FusedLocationProviderClient client;
    private static final int REQUEST_CODE = 101;
    //    model
    private model_viTri arrViTri;
    private model_Cart arrCart, arrCartTam;
    private ArrayList<model_viTri> arrListViTri;
    private ArrayList<model_Cart> arrListCart, arrayListCartTam;

    private SharedPreferences shareAcout;
    //    mảng
    private ArrayList<LatLng> arrayList;


    // value
    boolean checkOnclick = true, checkShowForm = true;
    String idGioHang, idViTri;
    //Firebase
    private DatabaseReference dataRef, dataAccoutRef;
    private FirebaseDatabase database;
    // adapter
    chiaSeGioHang_showDoc_adapter seGioHang_showDoc_adapter;

    @Override
    protected void onStart() {
        dataRef = database.getReference("location").child(idViTri);
        dataRef.child("tinhTrang").setValue(false);
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chia_se_gio_hang);
        anhXa();
        batSuKien();
        getDataFirebaseViTri();

//        chia sẻ giỏ hàng
        client = LocationServices.getFusedLocationProviderClient(ChiaSeGioHang_Activity.this);
        fetchLastLocation();
        getSharedPre();
        getDataGioHang();
        ChiaSeGioHang_img_btn_timKiemKey.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String valueKey = ChiaSeGioHang_edt_timKiemKey.getText().toString();
                if (valueKey.equals("")) {
                    getDataFirebaseViTri();
                }
                return false;
            }
        });

    }

    //   Trung lưu vị trí lên mảng
    private void setArrViTri(ArrayList<model_viTri> arrListViTris) {
        for (int i = 0; i < arrListViTris.size(); i++) {

            arrayList.add(new LatLng(arrListViTris.get(i).getLatitude(), arrListViTris.get(i).getLongitude()));
        }
    }

    /******************** Trung Show thông tin ra kiểu dọc**********************/
    private void showListProduc_Vartical(ArrayList<model_viTri> arrListViTri) {
        ChiaSeGioHang_rscv_showDanhSach.setLayoutManager(new GridLayoutManager(this, 2));
        ChiaSeGioHang_rscv_showDanhSach.setItemAnimator(new DefaultItemAnimator());
        //        Initilize
        seGioHang_showDoc_adapter = new chiaSeGioHang_showDoc_adapter(arrListViTri, ChiaSeGioHang_Activity.this);

        ChiaSeGioHang_rscv_showDanhSach.setAdapter(seGioHang_showDoc_adapter);
    }

    //Trung: lấy dữ liệu sản phẩm trên firebase về
    private void getDataFirebaseViTri() {

        dataRef = database.getReference("location");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                arrListViTri.clear();
//                Chạy vòng lặp kiểm tra dữ liệu
                for (DataSnapshot child : snapshot.getChildren()) {
                    arrViTri = child.getValue(model_viTri.class);
                    if (arrViTri.isTinhTrang() == true) {
                        arrListViTri.add(arrViTri);
                    }
                }
                setArrViTri(arrListViTri);
                showListProduc_Vartical(arrListViTri);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void batSuKien() {

//      Tìm kiếm vị trí user bằng key
        ChiaSeGioHang_img_btn_timKiemKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                check validate
                String valueKey = ChiaSeGioHang_edt_timKiemKey.getText().toString();
                if (!valueKey.equals("")) {

                    dataRef = database.getReference("location");
                    dataRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            arrListViTri.clear();
//                Chạy vòng lặp kiểm tra dữ liệu
                            for (DataSnapshot child : snapshot.getChildren()) {
                                arrViTri = child.getValue(model_viTri.class);
                                if (arrViTri.getKey().contains(valueKey)) {
                                    arrListViTri.add(arrViTri);
                                }
                            }
                            showListProduc_Vartical(arrListViTri);
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });
                }else{
                    getDataFirebaseViTri();
                }
            }
        });


// chuyển đến trang chia sẻ kết nối "Phát tín hiệu"
        ChiaSeGioHang_llout_btn_ggmap_phatTinNhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiaSeGioHang_Activity.this, chiaSeKetNoiGioHang_Activity.class);
                startActivity(intent);
            }
        });
        ChiaSeGioHang_llout_btn_rscv_phatTinHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiaSeGioHang_Activity.this, chiaSeKetNoiGioHang_Activity.class);
                startActivity(intent);
            }
        });
//        show form tìm kiếm và chức năng
        ChiaSeGioHang_llout_btn_showFormTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup.LayoutParams params = ChiaSeGioHang_llout_FormTop.getLayoutParams();

                if (checkShowForm == false) {

                    params.height = 0;
                    params.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_bottom);
                    ChiaSeGioHang_llout_FormTop.setAnimation(animation);
                    ChiaSeGioHang_llout_FormTop.setLayoutParams(params);

                    checkShowForm = true;
                } else {
                    params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    params.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom);
                    ChiaSeGioHang_llout_FormTop.setAnimation(animation);
                    ChiaSeGioHang_llout_FormTop.setLayoutParams(params);
                    checkShowForm = false;

                }
            }
        });
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
                if (checkOnclick == true) {
                    ChiaSeGioHang_img_btn_showTheo.setImageResource(R.drawable.ic_col_sort_row);
                    ChiaSeGioHang_frlout_showListGgMap.setVisibility(View.VISIBLE);
                    ChiaSeGioHang_frlout_showListRscv.setVisibility(View.INVISIBLE);

                    checkOnclick = false;

                } else {
                    ChiaSeGioHang_img_btn_showTheo.setImageResource(R.drawable.ic_ggmap);
                    ChiaSeGioHang_frlout_showListGgMap.setVisibility(View.INVISIBLE);
                    ChiaSeGioHang_frlout_showListRscv.setVisibility(View.VISIBLE);
                    checkOnclick = true;
                }
            }
        });

    }


    // harePre
    private void getSharedPre() {
        shareAcout = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        idViTri = shareAcout.getString("IDVITRI", "");
        idGioHang = shareAcout.getString("IDGIOHANG", "");


    }

    //Trung nhận dữ liệu từ trang Adapter
    public void nhanDuLieuAdapterItem(String idGioHangTamB) {

        setDataChiaSeGioHang(idGioHangTamB);
    }

    // lấy xong dữ liệu giỏ hàng a thì bắt đầu chuyển chia sẻ qua giỏ hàng tạm b
    private void setDataChiaSeGioHang(String idGioHangTamB) {
        dataRef = database.getReference("gioHangTam");
        for (int i = 0; i < arrListCart.size(); i++) {
            arrCartTam = new model_Cart(UUID.randomUUID().toString(), arrListCart.get(i).getIdSanPham(), arrListCart.get(i).getSoLuong());
            dataRef.child(idGioHangTamB).child(arrCartTam.getIdGioHang()).setValue(arrCartTam);
        }
    }

    // lấy dữ liệu từ giỏ hàng
    private void getDataGioHang() {
        dataRef = database.getReference("GioHangs").child(idGioHang);
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                arrListCart.clear();
//                Chạy vòng lặp kiểm tra dữ liệu
                for (DataSnapshot child : snapshot.getChildren()) {
                    arrCart = child.getValue(model_Cart.class);
                    arrListCart.add(arrCart);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

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
            Toast.makeText(this, arrayList.get(i) + "", Toast.LENGTH_SHORT).show();

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
//        Model
        arrayList = new ArrayList<LatLng>();
        arrListViTri = new ArrayList<>();
        arrListCart = new ArrayList<>();
        arrayListCartTam = new ArrayList<>();
//        firebase
        database = FirebaseDatabase.getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/");

//        GG map
        ChiaSeGioHang_google_map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.chiaSeGioHang_google_map);
//         LinearLayout
        ChiaSeGioHang_llout_btn_ggmap_phatTinNhieu = findViewById(R.id.chiaSeGioHang_llout_btn_ggmap_phatTinNhieu);
        ChiaSeGioHang_llout_btn_rscv_phatTinHieu = findViewById(R.id.chiaSeGioHang_llout_btn_rscv_phatTinHieu);
        ChiaSeGioHang_llout_btn_showFormTop = findViewById(R.id.chiaSeGioHang_llout_btn_showFormTop);
//         RecyclerView
        ChiaSeGioHang_rscv_showDanhSach = findViewById(R.id.chiaSeGioHang_rscv_showDanhSach);
//         ImageView
        ChiaSeGioHang_img_btn_back = findViewById(R.id.chiaSeGioHang_img_btn_back);
        ChiaSeGioHang_img_btn_showTheo = findViewById(R.id.chiaSeGioHang_img_btn_showTheo);
        ChiaSeGioHang_img_phongTo = findViewById(R.id.chiaSeGioHang_img_phongTo);
        ChiaSeGioHang_img_thuNho = findViewById(R.id.chiaSeGioHang_img_thuNho);
        ChiaSeGioHang_img_btn_timKiemKey = findViewById(R.id.chiaSeGioHang_img_btn_timKiemKey);
//         EditText
        ChiaSeGioHang_edt_timKiemKey = findViewById(R.id.chiaSeGioHang_edt_timKiemKey);
//         CardView
        ChiaSeGioHang_crv_btn_showTheo = findViewById(R.id.chiaSeGioHang_crv_btn_showTheo);
        ChiaSeGioHang_crv_btn_viTri = findViewById(R.id.chiaSeGioHang_crv_btn_viTri);
//         FrameLayout
        ChiaSeGioHang_frlout_showListGgMap = findViewById(R.id.chiaSeGioHang_frlout_showListGgMap);
        ChiaSeGioHang_frlout_showListRscv = findViewById(R.id.chiaSeGioHang_frlout_showListRscv);
        ChiaSeGioHang_llout_FormTop = findViewById(R.id.chiaSeGioHang_llout_FormTop);
    }

}