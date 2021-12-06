package com.example.lgfood_duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lgfood_duan1.R;

public class Xac_Nhan_DH_Activity extends AppCompatActivity {
    private CardView
            XacNhan_cardView_backItem,
            XacNhan_cardView_btnThayDoiThongTin,
            XacNhan_cardView_btnXacNhan;
    private TextView
            XacNhan_txt_tenKhachHang,
            XacNhan_txt_diaChiKhachHang,
            XacNhan_txt_sdtKhachHang_xacNhan,
            XacNhan_txt_gmailKhachHang,
            XacNhan_txt_tienDonGia,
            XacNhan_txt_tienGiamGia,
            XacNhan_txt_giaPhiVanChuyen,
            XacNhan_txt_giaDonHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_dh);
        anhXa();
        batSuKien();
    }

    private void batSuKien() {
//    bắt sự kiện quay lại trang trước
        XacNhan_cardView_backItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Xac_Nhan_DH_Activity.this,gio_Hang_Activity.class);
                startActivity(intent);
            }
        });
//        bắt sự kiện chuyển trang đến sửa thông tin tài khoản
        XacNhan_cardView_btnThayDoiThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Xac_Nhan_DH_Activity.this,thongTinTaiKhoan_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void anhXa() {
//        CartView
        XacNhan_cardView_backItem = findViewById(R.id.xacNhan_cardView_backItem);
        XacNhan_cardView_btnThayDoiThongTin = findViewById(R.id.xacNhan_cardView_btnThayDoiThongTin);
        XacNhan_cardView_btnXacNhan = findViewById(R.id.xacNhan_cardView_btnXacNhan);
//        TextView
        XacNhan_txt_tenKhachHang = findViewById(R.id.xacNhan_txt_tenKhachHang);
        XacNhan_txt_diaChiKhachHang = findViewById(R.id.xacNhan_txt_diaChiKhachHang);
        XacNhan_txt_sdtKhachHang_xacNhan = findViewById(R.id.xacNhan_txt_sdtKhachHang_xacNhan);
        XacNhan_txt_gmailKhachHang = findViewById(R.id.xacNhan_txt_gmailKhachHang);
        XacNhan_txt_tienDonGia = findViewById(R.id.xacNhan_txt_tienDonGia);
        XacNhan_txt_tienGiamGia = findViewById(R.id.xacNhan_txt_tienGiamGia);
        XacNhan_txt_giaPhiVanChuyen = findViewById(R.id.xacNhan_txt_giaPhiVanChuyen);
        XacNhan_txt_giaDonHang = findViewById(R.id.xacNhan_txt_giaDonHang);
    }
}