package com.example.lgfood_duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Xu_Li_Don_Hang_Activity extends AppCompatActivity {
    private CardView
            xuLi_cardView_backItem,
            xuLi_cardView_formItem1,
            xuLi_cardView_formItem2,
            xuLi_cardView_formXacNhan,
            xuLi_cardView_minus,
            xuLi_cardView_plus,
            xuLi_cardView_heartItem,
            xuLi_cardView_message,
            xuLi_cardView_btnXacNhan;
    private TextView
            xuLi_txt_tenKhachHang,
            xuLi_txt_sdtKhachHang,
            xuLi_txt_gmailKhachHang,
            xuLi_txt_diaChiKhachHang,
            xuLi_txt_tenItem,
            xuLi_txt_xuatXuItem,
            xuLi_txt_giaItem,
            xuLi_txt_tenItem2,
            xuLi_txt_xuatXuItem2,
            xuLi_txt_giaItem2,
            xuLi_txt_tongTienFomXacNhan;
    private ImageView
            xuLi_img_anhItem,
            xuLi_img_anhItem2;
    private Button
            XuLi_btn_btnXacNhan;
    private LinearLayout XuLi_llout_btn_chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xu_li_don_hang);

        anhXa();
    }

    private void anhXa() {
        xuLi_cardView_backItem = findViewById(R.id.xuLi_cardView_backItem);
        xuLi_cardView_formItem1 = findViewById(R.id.xuLi_cardView_formItem1);
        xuLi_cardView_formItem2 = findViewById(R.id.xuLi_cardView_formItem2);
        xuLi_cardView_formXacNhan = findViewById(R.id.xuLi_cardView_formXacNhan);
        xuLi_cardView_minus = findViewById(R.id.xuLi_cardView_minus);
        xuLi_cardView_plus = findViewById(R.id.xuLi_cardView_plus);
        xuLi_cardView_heartItem = findViewById(R.id.xuLi_cardView_heartItem);
        XuLi_llout_btn_chat = findViewById(R.id.xuLi_llout_btn_chat);
        XuLi_btn_btnXacNhan = findViewById(R.id.xuLi_btn_btnXacNhan);
        xuLi_txt_tenKhachHang = findViewById(R.id.xuLi_txt_tenKhachHang);
        xuLi_txt_sdtKhachHang = findViewById(R.id.xacNhan_txt_sdtKhachHang_xacNhan);
        xuLi_txt_gmailKhachHang = findViewById(R.id.xacNhan_txt_gmailKhachHang);
        xuLi_txt_diaChiKhachHang = findViewById(R.id.xuLi_txt_diaChiKhachHang);
        xuLi_txt_tenItem = findViewById(R.id.xuLi_txt_tenItem);
        xuLi_txt_xuatXuItem = findViewById(R.id.xuLi_txt_xuatXuItem);
        xuLi_txt_giaItem = findViewById(R.id.xuLi_txt_giaItem);
        xuLi_txt_tenItem2 = findViewById(R.id.xuLi_txt_tenItem2);
        xuLi_txt_xuatXuItem2 = findViewById(R.id.xuLi_txt_xuatXuItem2);
        xuLi_txt_giaItem2 = findViewById(R.id.xuLi_txt_giaItem2);
        xuLi_txt_tongTienFomXacNhan = findViewById(R.id.xuLi_txt_tongTienFomXacNhan);
        xuLi_img_anhItem = findViewById(R.id.xuLi_img_anhItem);
        xuLi_img_anhItem2 = findViewById(R.id.xuLi_img_anhItem2);

        xuLi_cardView_backItem = findViewById(R.id.xuLi_cardView_backItem);


        xuLi_cardView_formXacNhan = findViewById(R.id.xuLi_cardView_formXacNhan);

        xuLi_txt_tongTienFomXacNhan = findViewById(R.id.xuLi_txt_tongTienFomXacNhan);


    }
}