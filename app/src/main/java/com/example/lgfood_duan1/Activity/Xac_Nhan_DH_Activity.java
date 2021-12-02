package com.example.lgfood_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lgfood_duan1.Model.adapter_DanhSachGioHang;
import com.example.lgfood_duan1.Model.adapter_SanPham_Kho;
import com.example.lgfood_duan1.Model.model_Cart;
import com.example.lgfood_duan1.Model.model_SanPham;
import com.example.lgfood_duan1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Xac_Nhan_DH_Activity extends AppCompatActivity {
    private CardView
            xacNhan_cardView_backItem,
            XacNhan_cardView_btnThayDoiThongTin,
            xacNhan_cardView_btnXacNhan;

    private RecyclerView
            XacNhan_rscV_danhsachSanPham;
    private TextView
            XacNhan_txt_thayDoiThongTin,
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
    private DatabaseReference dataSanPhamRef;
    private FirebaseDatabase dataSanPham;
    private SharedPreferences sharedPreferences;

    double tien;


    adapter_SanPham_Kho adapter_sanPham_kho;
    adapter_DanhSachGioHang adapter_danhSachGioHang;
    ArrayList<model_Cart> arrListgioHang;
    model_SanPham arrSanPham;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_dh);

        anhXa();
        batsukien();
        showsanpham();
        tongtien();
        LuuHoaDon();


    }




    private void batsukien(){

//        cún: bắt sự kiện thay đổi thông tin
        XacNhan_cardView_btnThayDoiThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Xac_Nhan_DH_Activity.this, Chinh_Sua_Thong_Tin_Accounts_Activity.class);
                startActivity(intent);
            }
        });
    }


private void LuuHoaDon(){
    dataSanPhamRef= dataSanPham
            .getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference("GioHangs").child(sharedPreferences.getString("IDGIOHANG",""));

    dataSanPhamRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (arrListgioHang!=null){
                arrListgioHang.clear();
            }
            for (DataSnapshot ds: snapshot.getChildren()){
                model_Cart cart=ds.getValue(model_Cart.class);
                arrListgioHang.add(cart);
            }
            LuuHoaDon();

        }

        @Override
        public void onCancelled(@NonNull  DatabaseError error) {

        }
    });



}





    private void tongtien() {
        double phiVanCHuyen = 30.000;
        double giamGia = 10;

        tien = Math.round((adapter_sanPham_kho.getTotalFee() * phiVanCHuyen) * 100.0) / 100.0;
        double total = Math.round((adapter_sanPham_kho.getTotalFee() + tien + giamGia) * 100.0) / 100.0;
        double itemTotal = Math.round(adapter_sanPham_kho.getTotalFee() * 100.0) / 100.0;

        xacNhan_txt_tienDonGia.setText("$" + itemTotal);
        xacNhan_txt_giaPhiVanChuyen.setText("$" + tien);
        xacNhan_txt_tienGiamGia.setText("$" + giamGia);
        xacNhan_txt_giaDonHang.setText("$" + total);
    }


    /********************Show thông tin ra kiểu dọc**********************/
    private void showsanpham() {
        XacNhan_rscV_danhsachSanPham.setLayoutManager(new GridLayoutManager(this, 1));
        XacNhan_rscV_danhsachSanPham.setItemAnimator(new DefaultItemAnimator());
        adapter_danhSachGioHang = new adapter_DanhSachGioHang(arrListgioHang, Xac_Nhan_DH_Activity.this);

        XacNhan_rscV_danhsachSanPham.setAdapter(adapter_danhSachGioHang);


    }





    private void anhXa() {

        arrListgioHang = new ArrayList<model_Cart>();

        dataSanPham = FirebaseDatabase.getInstance("https://duan1lgfood-default-rtdb.asia-southeast1.firebasedatabase.app/");

        xacNhan_cardView_backItem=findViewById(R.id.xacNhan_cardView_backItem);
                XacNhan_cardView_btnThayDoiThongTin=findViewById(R.id.xacNhan_cardView_btnThayDoiThongTin);
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
                XacNhan_txt_thayDoiThongTin=findViewById(R.id.xacNhan_txt_thayDoiThongTin);
    }


}