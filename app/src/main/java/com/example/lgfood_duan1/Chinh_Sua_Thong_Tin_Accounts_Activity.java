package com.example.lgfood_duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Chinh_Sua_Thong_Tin_Accounts_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_thong_tin_account);

        BottomNavigationView navigationView = findViewById(R.id.trangChuSanPham_bottomNavigation);
        navigationView.setSelectedItemId(R.id.Use);
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
                        startActivity(new Intent(getApplicationContext(),
                                xac_Nhan_Don_hang_Activity.class));
                        overridePendingTransition(0,0);
                        return;
                    case R.id.Use:
                        return;
                }
                return;
            }
        });
    }
}