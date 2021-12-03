package com.example.lgfood_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lgfood_duan1.Adapter.trangChu_showDoc_adapter;
import com.example.lgfood_duan1.Model.adapter_DanhSachGioHang;
import com.example.lgfood_duan1.Model.adapter_SanPham_Kho;
import com.example.lgfood_duan1.Model.model_Account;
import com.example.lgfood_duan1.Model.model_Cart;
import com.example.lgfood_duan1.Model.model_SanPham;
import com.example.lgfood_duan1.Model.model_addToCart;
import com.example.lgfood_duan1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Xac_Nhan_DH_Activity extends AppCompatActivity {
    private CardView
            xacNhan_cardView_backItem,
            XacNhan_cardView_btnThayDoiThongTin,
            xacNhan_cardView_btnXacNhan;

    private adapter_DanhSachGioHang
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

    String idKhachHang;
    String idGioHang;

    adapter_SanPham_Kho adapter_sanPham_kho;
    adapter_DanhSachGioHang adapter_danhSachGioHang;
    ArrayList<model_Cart> arrListgioHang;
    ArrayList<model_addToCart>arrayListaddToCart;
    model_Cart arrgiohang;
    model_addToCart model_addToCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_dh);

        anhXa();
        batsukien();
        showsanpham();
//        tongtien();
        LuuHoaDon();


    }

    private void showsanpham() {
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
    private void getSharedPre() {
        sharedPreferences = getSharedPreferences("idSanPham", MODE_PRIVATE);

        idKhachHang = sharedPreferences.getString("idUse", "");
        idGioHang = sharedPreferences.getString("idGioHang", "");
    }


private void LuuHoaDon(){
    getSharedPre();

    //    hóa đơn:  id hóa đơn"Stirng ", id chi tiết hóa đơn,  tình trạng hóa đơn, id use, ngày tạo
    //    chi tiết hóa đơn:  id sản phẩm, lấy giá sản phẩm, số lượng sản phẩm, id chi tiết hóa đơn
// lấy id hóa đơn của use chill("hoadon").chill(idhoadon).setvalue(arrhoadon);
    dataSanPhamRef= dataSanPham.getReference("newCarts").child(sharedPreferences.getString("IDGIOHANG", ""));

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





//    private void tongtien() {
//        double phiVanCHuyen = 30.000;
//        double giamGia = 10;
//
//        tien = Math.round((adapter_sanPham_kho.getTotalFee() * phiVanCHuyen) * 100.0) / 100.0;
//        double total = Math.round((adapter_sanPham_kho.getTotalFee() + tien + giamGia) * 100.0) / 100.0;
//        double itemTotal = Math.round(adapter_sanPham_kho.getTotalFee() * 100.0) / 100.0;
//
//        xacNhan_txt_tienDonGia.setText("$" + itemTotal);
//        xacNhan_txt_giaPhiVanChuyen.setText("$" + tien);
//        xacNhan_txt_tienGiamGia.setText("$" + giamGia);
//        xacNhan_txt_giaDonHang.setText("$" + total);
//    }


    /********************Show thông tin ra kiểu dọc**********************/


    private void showsanpham(ArrayList<model_SanPham> arrListSp) {
        XacNhan_rscV_danhsachSanPham.setLayoutManager(new GridLayoutManager(this, 2));
        XacNhan_rscV_danhsachSanPham.setItemAnimator(new DefaultItemAnimator());
        //        Initilize
        XacNhan_rscV_danhsachSanPham = new adapter_DanhSachGioHang(arrListSp, Xac_Nhan_DH_Activity.this, new adapter_DanhSachGioHang.IClickListener() {
            @Override
            public void onClickShowItem(model_SanPham sanPham) {
                showItemChiTietSanPham(sanPham);
            }

        });
        XacNhan_rscV_danhsachSanPham.setAdapter(adapter_danhSachGioHang);
    }

    private void showItemChiTietSanPham(model_SanPham sanPham) {
        anhXa();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_custom3);

        //thong tin san pham
        ImageView datNhanh_img_showAnhSanPham = dialog.findViewById(R.id.datNhanh_img_showAnhSanPham);
        TextView datNhanh_tv_xuatXuSanPham = dialog.findViewById(R.id.datNhanh_tv_xuatXuSanPham);
        TextView datNhanh_tv_showTenSanPham = dialog.findViewById(R.id.datNhanh_tv_showTenSanPham);
        TextView datNhanh_tv_giaSanPham = dialog.findViewById(R.id.datNhanh_tv_giaSanPham);

        Glide.with(Xac_Nhan_DH_Activity.this)
                .load(sanPham.getAnhSanPham())
                .into(datNhanh_img_showAnhSanPham);
        datNhanh_tv_xuatXuSanPham.setText(sanPham.getXuatXuSanPham());
        datNhanh_tv_showTenSanPham.setText(sanPham.getTenSanPham());
        datNhanh_tv_giaSanPham.setText(sanPham.getGiaBanSanPham() + "00đ");

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();

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