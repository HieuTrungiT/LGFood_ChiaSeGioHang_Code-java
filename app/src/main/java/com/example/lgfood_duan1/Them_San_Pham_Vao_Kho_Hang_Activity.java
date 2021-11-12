package com.example.lgfood_duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputLayout;

public class Them_San_Pham_Vao_Kho_Hang_Activity extends AppCompatActivity {
    private ImageView
            ThemSuaXoaSanPham_img_showImgV,
            ThemSuaXoaSanPham_img_btn_back;

    private TextView
            ThemSuaXoaSanPham_tv_btn_moThuMucAnh;

    private LinearLayout
            ThemSuaXoaSanPham_llout_btn_themSanPham;


    private TextInputLayout
            ThemSuaXoaSanPham_edt_tenSanPham,
            ThemSuaXoaSanPham_edt_giaNhap,
            ThemSuaXoaSanPham_edt_giaBan,
            ThemSuaXoaSanPham_edt_giamGia,
            ThemSuaXoaSanPham_edt_soLuong,
            ThemSuaXoaSanPham_edt_NSX,
            ThemSuaXoaSanPham_edt_xuatSu;
    private Spinner
            ThemSuaXoaSanPham_spn_LoaiSP,
            ThemSuaXoaSanPham_spn_TinhTrang;

    //Tạo một mảng dữ liệu giả
    String arr[]={
            "Hàng điện tử",
            "Hàng hóa chất",
            "Hàng gia dụng"};
    TextView selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham_vao_kho_hang);

        anhXa();
        batSuKien();

    }

    private void batSuKien(){
        //        back về login
        ThemSuaXoaSanPham_img_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void anhXa() {
//        ImageView
        ThemSuaXoaSanPham_img_btn_back = findViewById(R.id.themSuaXoaSanPham_img_btn_back);
        ThemSuaXoaSanPham_img_showImgV = findViewById(R.id.themSuaXoaSanPham_img_showImgV);
//        TextView
        ThemSuaXoaSanPham_tv_btn_moThuMucAnh = findViewById(R.id.themSuaXoaSanPham_tv_btn_moThuMucAnh);

//      LinearLayout
        ThemSuaXoaSanPham_llout_btn_themSanPham = findViewById(R.id.themSuaXoaSanPham_llout_btn_themSanPham);


//         TextInputLayout
        ThemSuaXoaSanPham_edt_tenSanPham = findViewById(R.id.themSuaXoaSanPham_edt_tenSanPham);
        ThemSuaXoaSanPham_edt_giaNhap = findViewById(R.id.themSuaXoaSanPham_edt_giaNhap);
        ThemSuaXoaSanPham_edt_giaBan = findViewById(R.id.themSuaXoaSanPham_edt_giaBan);
        ThemSuaXoaSanPham_edt_giamGia = findViewById(R.id.themSuaXoaSanPham_edt_giamGia);
        ThemSuaXoaSanPham_edt_soLuong =findViewById(R.id.themSuaXoaSanPham_edt_soLuong);
        ThemSuaXoaSanPham_edt_NSX = findViewById(R.id.themSuaXoaSanPham_edt_NSX);
        ThemSuaXoaSanPham_edt_xuatSu = findViewById(R.id.themSuaXoaSanPham_edt_xuatSu);
//       Spinner
        ThemSuaXoaSanPham_spn_LoaiSP  = findViewById(R.id.themSuaXoaSanPham_spn_LoaiSP);
        ThemSuaXoaSanPham_spn_TinhTrang = findViewById(R.id.themSuaXoaSanPham_spn_TinhTrang);

    }
}