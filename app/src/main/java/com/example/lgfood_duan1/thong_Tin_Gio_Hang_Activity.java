package com.example.lgfood_duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class thong_Tin_Gio_Hang_Activity extends AppCompatActivity {
    private ImageView
            ThongTinGioHang_img_back;
    private TextView
            ThongTinGioHang_tv_name,
            ThongTinGioHang_tv_ten,
            ThongTinGioHang_tv_diaChi,
            ThongTinGioHang_tv_phone,
            ThongTinGioHang_tv_gmail,
            ThongTinGioHang_tv_tienDonHang,
            ThongTinGioHang_tv_tienGiamGia,
            ThongTinGioHang_tv_tongTien,
            ThongTinGioHang_tv_tienVanChuyen;
    private Button
            ThongTinGioHang_btn_thayDoiThongTin,
            ThongTinGioHang_btn_xacNhan;
    private ListView
            ThongTinGioHang_lv_xacNhanDonHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_gio_hang);
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
        ThongTinGioHang_tv_name = findViewById(R.id.thongTinGioHang_tv_name);
        ThongTinGioHang_tv_ten = findViewById(R.id.thongTinGioHang_tv_ten);
        ThongTinGioHang_tv_diaChi = findViewById(R.id.thongTinGioHang_tv_diaChi);
        ThongTinGioHang_tv_phone = findViewById(R.id.thongTinGioHang_tv_phone);
        ThongTinGioHang_tv_gmail = findViewById(R.id.thongTinGioHang_tv_gmail);
        ThongTinGioHang_tv_tienDonHang = findViewById(R.id.thongTinGioHang_tv_tienDonHang);
        ThongTinGioHang_tv_tienGiamGia = findViewById(R.id.thongTinGioHang_tv_tienGiamGia);
        ThongTinGioHang_tv_tongTien = findViewById(R.id.thongTinGioHang_tv_tongTien);
        ThongTinGioHang_tv_tienVanChuyen = findViewById(R.id.thongTinGioHang_tv_tienVanChuyen);

//      ImageView

        ThongTinGioHang_img_back = findViewById(R.id.thongTinGioHang_img_back);

//      Button

        ThongTinGioHang_btn_thayDoiThongTin = findViewById(R.id.thongTinGioHang_btn_thayDoiThongTin);
        ThongTinGioHang_btn_xacNhan = findViewById(R.id.thongTinGioHang_btn_thayDoiThongTin);

//        ListView
        ThongTinGioHang_lv_xacNhanDonHang = findViewById(R.id.thongTinGioHang_lv_xacNhanDonHang);

    }
}