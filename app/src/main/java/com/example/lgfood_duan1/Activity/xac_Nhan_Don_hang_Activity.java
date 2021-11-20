package com.example.lgfood_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lgfood_duan1.R;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class xac_Nhan_Don_hang_Activity extends AppCompatActivity {

    private TextView
            XacNhanDonHang_tv_name,
            XacNhanDonHang_tv_donChuaXacNhan,
            XacNhanDonHang_tv_donHang;
    private Button
            XacNhanDonHang_btn_donDaXacNhan;
    private ImageView
            XacNhanDonHang_img_back;
    private ListView
            XacNhanDonHang_lv_hienDanhSachDonHangAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_don_hang);
        anhXa();

        BottomNavigationView navigationView = findViewById(R.id.trangChuSanPham_bottomNavigation);
        navigationView.setSelectedItemId(R.id.cart);
        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(),
                                gio_Hang_Activity.class));
                        overridePendingTransition(0,0);
                        return;
                    case R.id.Like:
                        return;
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(),
                                trangChu_SanPham_Activity.class));
                        overridePendingTransition(0,0);
                        return;
                    case R.id.Paid:

                    case R.id.Use:
                        startActivity(new Intent(getApplicationContext(),
                                Chinh_Sua_Thong_Tin_Accounts_Activity.class));
                        overridePendingTransition(0,0);
                        return;
                }
                return ;
            }
        });

    }

//    Bảo Toàn: kiểm tra dữ liệu đầu vào
    private void checkValidateSet(){

    }

//    Bắt sự kiện buttom
    private void batSuKien(){

    }


//     Ánh xạ đây nè :D
    private void anhXa() {

//       TextView
        XacNhanDonHang_tv_name = findViewById(R.id.xacNhanDonHang_tv_name);
        XacNhanDonHang_tv_donChuaXacNhan = findViewById(R.id.xacNhanDonHang_tv_donChuaXacNhan);
        XacNhanDonHang_tv_donHang = findViewById(R.id.xacNhanDonHang_tv_donHang);

//      ImageView
        XacNhanDonHang_img_back = findViewById(R.id.xacNhanDonHang_img_back);

//        Button
        XacNhanDonHang_btn_donDaXacNhan = findViewById(R.id.xacNhanDonHang_btn_donDaXacNhan);
//        ListView
        XacNhanDonHang_lv_hienDanhSachDonHangAdmin = findViewById(R.id.xacNhanDonHang_lv_hienDanhSachDonHangAdmin);


    }
}