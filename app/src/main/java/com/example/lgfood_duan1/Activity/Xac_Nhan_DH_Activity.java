package com.example.lgfood_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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


    adapter_DanhSachGioHang adapter_danhSachGioHang;
    ArrayList<model_SanPham> arrListSanPham;
    model_SanPham arrSanPham;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_dh);

        anhXa();
        batsukien();
//        getDataFirebase();
//        XacNhan_rscV_danhsachSanPham = findViewById(R.id.xacNhan_rscV_danhsachSanPham);
//        arrListSanPham = new ArrayList<>();
//        adapter_danhSachGioHang= new adapter_DanhSachGioHang(Xac_Nhan_DH_Activity.this,R.layout.item_custom3,arrListSanPham);
//
//        XacNhan_rscV_danhsachSanPham.setAdapter(adapter_danhSachGioHang);

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


//    private void getDataFirebase() {
//
//
//        dataSanPhamRef = dataSanPham.getReference().child("khoHang");
//        dataSanPhamRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                arrListSanPham.clear();
////                arrListSanPham.clear();
////                Chạy vòng lặp kiểm tra dữ liệu
//                for (DataSnapshot child : snapshot.getChildren()) {
//                    arrSanPham = child.getValue(model_SanPham.class);
//                    arrListSanPham.add(arrSanPham);
//                }
//                Toast.makeText(Xac_Nhan_DH_Activity.this, arrListSanPham.size() + "", Toast.LENGTH_SHORT).show();
//                showListProduc_Vartical(arrListSanPham);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//
//        });
//    }



//    public void setTongGiaSanPham(double tongTien) {
//        String stGia = String.format(tongTien + "");
//
//        if (stGia.length() > 6) {
//
//            xacNhan_txt_giaDonHang.setText(stGia.substring(0, 6) + "00vnđ");
//        } else if (tongTien == 0) {
//            xacNhan_txt_giaDonHang.setText(tongTien + "vnđ");
//
//        } else {
//            xacNhan_txt_giaDonHang.setText(tongTien + "00vnđ");
//        }
//
//
//    }


    /********************Show thông tin ra kiểu dọc**********************/
//    private void showListProduc_Vartical(ArrayList<model_SanPham> arrListSp) {
//        XacNhan_rscV_danhsachSanPham.setLayoutManager(new GridLayoutManager(this, 1));
//        XacNhan_rscV_danhsachSanPham.setItemAnimator(new DefaultItemAnimator());
//        //        Initilize
////        Adapter_SanPham_Kho = new adapter_SanPham_Kho(arrListSp, khoHang_Activity.this,new adapter_SanPham_Kho.IClickLinstenr());
//
//
//        XacNhan_rscV_danhsachSanPham.setAdapter(adapter_danhSachGioHang);
//    }





    private void anhXa() {

        arrListSanPham = new ArrayList<model_SanPham>();

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