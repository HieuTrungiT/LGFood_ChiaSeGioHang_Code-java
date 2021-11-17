package com.example.lgfood_duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lgfood_duan1.R;

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