package com.example.lgfood_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lgfood_duan1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class gio_Hang_Activity extends AppCompatActivity {
    private ImageView
            GioHang_img_back;
    private TextView
            GioHang_tv_diaChi,
            GioHang_tv_name,
            GioHang_tv_tongTien;
    private Button
            GioHang_btn_datHang;
    private LinearLayout
            GioHang_llout_btn_shareCart;
    private ListView
            GioHang_lv_showGioHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        anhXa();
    }

    //    Bảo Toàn: kiểm tra dữ liệu đầu vào
    private void checkValidateSet(){

    }

    //    Bắt sự kiện buttom
    private void batSuKien(){

    }


    //     Ánh xạ đây nè :D
    private void anhXa() {

//        TextView
        GioHang_tv_diaChi = findViewById(R.id.gioHang_tv_diaChi);
        GioHang_tv_name = findViewById(R.id.gioHang_tv_name);
        GioHang_tv_tongTien = findViewById(R.id.gioHang_tv_tongTien);


//      ImageView

        GioHang_img_back = findViewById(R.id.gioHang_img_back);

//      Button

        GioHang_btn_datHang = findViewById(R.id.gioHang_btn_datHang);

//      LinearLayout

        GioHang_llout_btn_shareCart = findViewById(R.id.gioHang_llout_btn_shareCart);

//        ListView

        GioHang_lv_showGioHang = findViewById(R.id.gioHang_lv_showGioHang);


        BottomNavigationView bottomNavigationView = findViewById(R.id.trangChuSanPham_bottomNavigation);

        bottomNavigationView.setSelectedItemId(R.id.cart);


        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.cart:

                        return;
                    case R.id.Like:
//                        startActivity(new Intent(getApplicationContext(),trangChu_SanPham_Activity.class));
//                        overridePendingTransition(0, 0);
                        return;
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(),trangChu_SanPham_Activity.class));
                        overridePendingTransition(0, 0);
                        return;
                    case R.id.Paid:
//                        startActivity(new Intent(getApplicationContext(),trangChu_SanPham_Activity.class));
//                        overridePendingTransition(0, 0);
                        return;
                    case R.id.Use:
                        startActivity(new Intent(getApplicationContext(),Chinh_Sua_Thong_Tin_Accounts_Activity.class));
                        overridePendingTransition(0, 0);
                        return;
                }
            }
        });


    }

}