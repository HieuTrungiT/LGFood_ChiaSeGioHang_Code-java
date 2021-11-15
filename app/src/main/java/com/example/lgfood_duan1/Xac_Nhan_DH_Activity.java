package com.example.lgfood_duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.TextView;

public class Xac_Nhan_DH_Activity extends AppCompatActivity {
    private CardView
            xacNhan_cardView_backItem,
            xacNhan_cardView_btnThayDoiThongTin,
            xacNhan_cardView_btnXacNhan;
    private TextView
            xacNhan_txt_tenKhachHang,
            xacNhan_txt_diaChiKhachHang,
            xacNhan_txt_sdtKhachHang_xacNhan,
            xacNhan_txt_gmailKhachHang,
//            xacNhan_txt_tenKhachHang_item,
//            xacNhan_txt_dateKhachHang_item,
//            xacNhan_txt_tinhTrang_item,
//            xacNhan_gia_item,
//            xacNhan_txt_sdtKhachHang_item,
//            xacNhan_diaChiKhachHang_item,
            xacNhan_txt_tienDonGia,
            xacNhan_txt_tienGiamGia,
            xacNhan_txt_giaPhiVanChuyen,
            xacNhan_txt_giaDonHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_dh);

        anhXa();

    }

    private void anhXa() {
                xacNhan_cardView_backItem=findViewById(R.id.xacNhan_cardView_backItem);
                xacNhan_cardView_btnThayDoiThongTin=findViewById(R.id.xacNhan_cardView_btnThayDoiThongTin);
                xacNhan_cardView_btnXacNhan=findViewById(R.id.xacNhan_cardView_btnXacNhan);
                xacNhan_txt_tenKhachHang=findViewById(R.id.xacNhan_txt_tenKhachHang);
                xacNhan_txt_diaChiKhachHang=findViewById(R.id.xacNhan_txt_diaChiKhachHang);
                xacNhan_txt_sdtKhachHang_xacNhan=findViewById(R.id.xacNhan_txt_sdtKhachHang_xacNhan);
                xacNhan_txt_gmailKhachHang=findViewById(R.id.xacNhan_txt_gmailKhachHang);
//                xacNhan_txt_tenKhachHang_item=findViewById(R.id.xacNhan_txt_tenKhachHang_item);
//                xacNhan_txt_dateKhachHang_item=findViewById(R.id.xacNhan_txt_dateKhachHang_item);
//                xacNhan_txt_tinhTrang_item=findViewById(R.id.xacNhan_txt_tinhTrang_item);
//                xacNhan_gia_item=findViewById(R.id.xacNhan_gia_item);
//                xacNhan_txt_sdtKhachHang_item=findViewById(R.id.xacNhan_txt_sdtKhachHang_item);
//                xacNhan_diaChiKhachHang_item=findViewById(R.id.xacNhan_diaChiKhachHang_item);
                xacNhan_txt_tienDonGia=findViewById(R.id.xacNhan_txt_tienDonGia);
                xacNhan_txt_tienGiamGia=findViewById(R.id.xacNhan_txt_tienGiamGia);
                xacNhan_txt_giaPhiVanChuyen=findViewById(R.id.xacNhan_txt_giaPhiVanChuyen);
                xacNhan_txt_giaDonHang=findViewById(R.id.xacNhan_txt_giaDonHang);
    }
}