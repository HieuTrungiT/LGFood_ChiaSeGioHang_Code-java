package com.example.lgfood_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lgfood_duan1.Adapter.donHangChiTietUser_Adapter;
import com.example.lgfood_duan1.Adapter.donHangUser_Adapter;
import com.example.lgfood_duan1.Adapter.xacNhanDonHangAdmin_Adapter;
import com.example.lgfood_duan1.Model.model_Account;
import com.example.lgfood_duan1.Model.model_SanPham;
import com.example.lgfood_duan1.Model.model_chiTietSanPhamHoaDon;
import com.example.lgfood_duan1.Model.model_hoaDon;
import com.example.lgfood_duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class chiTietDonhangUser_Activity extends AppCompatActivity {
    TextView DonHangChiTiet_txt_tinhTrang_itemDangXuLy,
            DonHangChiTiet_txt_tinhTrang_itemChuaXacNhan,
            DonHangChiTiet_txt_tinhTrang_itemDaXuLy;
    ImageView
            DonHangChiTiet_img_btnThoat;
    RecyclerView
            DonHangChiTiet_rscv_showDanhSachDonHang;
    FloatingActionButton
            DonHangChiTiet_floatBtn_moGioHang;
    SharedPreferences sharedPreferences;
    // model
    FirebaseDatabase database;
    DatabaseReference dataRef, dataSanPhamRef, dataCTSPHoaDonRef,dataRefHoaDon;
    private ArrayList<model_hoaDon> arrListHoaDon;
    private model_Account arrAcout;
    donHangChiTietUser_Adapter AdapterDonHangChiTiet;
    private model_chiTietSanPhamHoaDon arrCTSPHoaDon;
    private ArrayList<model_chiTietSanPhamHoaDon> arrListCTSPHoaDon;

    private model_SanPham arrSanPham;
    private ArrayList<model_SanPham> arrListSanPham;
    //Value
    String idUserIt, idHoaDonIt, idChiTietSanPhamHoaDonIt, idDanhSachChiTietDonHang;
    double tongGiaIt;
    int tinhTrangHoaDonIt;
    Button donHangChiTiet_btn_huyDonHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_donhang_user);
        anhXa();
        getDataIntent();
        setTinhTrang();
        getDataDTSPHoaDon();
        batSuKien();
    }

    private void batSuKien() {
        DonHangChiTiet_img_btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chiTietDonhangUser_Activity.this, donHangUser_Activity.class);
                startActivity(intent);
            }
        });
        DonHangChiTiet_floatBtn_moGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chiTietDonhangUser_Activity.this, gio_Hang_Activity.class);
                startActivity(intent);
            }
        });
        donHangChiTiet_btn_huyDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huyDonHangFuntion();
            }
        });
    }

    private void huyDonHangFuntion() {
        getDataIntent();
        Dialog dialogLoading = new Dialog(chiTietDonhangUser_Activity.this);
        dialogLoading.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogLoading.setContentView(R.layout.item_dialog_chucnang_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialogLoading.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        dialogLoading.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogLoading.setCancelable(false); //Optional
        dialogLoading.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        ImageView item_dialog_chucNang_img_imgErro=dialogLoading.findViewById(R.id.item_dialog_chucNang_img_imgErro);
        TextView item_dialog_chucNang_txt_nameErro=dialogLoading.findViewById(R.id.item_dialog_chucNang_txt_nameErro);
        Button Okay = dialogLoading.findViewById(R.id.btn_okay);
        Button Cancel = dialogLoading.findViewById(R.id.btn_cancel);
        //setText Item
        Okay.setText("Đồng ý");
        item_dialog_chucNang_img_imgErro.setImageResource(R.drawable.question);
        item_dialog_chucNang_txt_nameErro.setText("Bạn có muốn xóa đơn hàng này?");
        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog diaLog = new Dialog(chiTietDonhangUser_Activity.this);
                diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                diaLog.setContentView(R.layout.item_login);
                diaLog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataRefHoaDon=database.getReference("HoaDon").child(sharedPreferences.getString("IDDANHSACHDONHANG",""));
                        dataRefHoaDon.child(idHoaDonIt).removeValue();
                        startActivity(new Intent(chiTietDonhangUser_Activity.this,donHangUser_Activity.class));

                        diaLog.dismiss();
                    }
                },1000);
                diaLog.show();
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLoading.dismiss();
            }
        });
        dialogLoading.show();

    }

    //     lấy giá trị từ Danh Sách chi tiết hóa đơn Firebase
    private void getDataDTSPHoaDon() {
        dataCTSPHoaDonRef = database.getReference("ChiTietHoaDon").child(idChiTietSanPhamHoaDonIt);
        dataCTSPHoaDonRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    arrCTSPHoaDon = child.getValue(model_chiTietSanPhamHoaDon.class);
                    arrListCTSPHoaDon.add(arrCTSPHoaDon);
                }
                getDataThongTinSanPhamTrongKho(arrListCTSPHoaDon);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    //    lấy thoogn tin chi tiết từng sản phẩm
    private void getDataThongTinSanPhamTrongKho(ArrayList<model_chiTietSanPhamHoaDon> arrListCTSPHoaDons) {

        for (int i = 0; i < arrListCTSPHoaDons.size(); i++) {

            dataSanPhamRef = database.getReference("khoHang").child(arrListCTSPHoaDons.get(i).getIdSanPham());
            int finalI = i;
            dataSanPhamRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    arrSanPham = snapshot.getValue(model_SanPham.class);
                    arrListSanPham.add(arrSanPham);
                    if (arrListCTSPHoaDons.size() == arrListSanPham.size()) {
                        getDataFirebaseUsser(arrListCTSPHoaDons, arrListSanPham);
                    }


                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
        }


    }

    // lấy thông tin user
    private void getDataFirebaseUsser(ArrayList<model_chiTietSanPhamHoaDon> arrListCTSPHoaDons, ArrayList<model_SanPham> arrListSanPham) {
        dataRef = database.getReference("Accounts").child(sharedPreferences.getString("IDUSRE", ""));
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                arrAcout = snapshot.getValue(model_Account.class);
                showListDanhSach(arrListCTSPHoaDons, arrListSanPham, arrAcout);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void setTinhTrang() {
        if (tinhTrangHoaDonIt == 0){
            DonHangChiTiet_txt_tinhTrang_itemChuaXacNhan.setVisibility(View.VISIBLE);
            DonHangChiTiet_txt_tinhTrang_itemDangXuLy.setVisibility(View.INVISIBLE);
            DonHangChiTiet_txt_tinhTrang_itemDaXuLy .setVisibility(View.INVISIBLE);
            donHangChiTiet_btn_huyDonHang.setVisibility(View.VISIBLE);
        }else if (tinhTrangHoaDonIt == 1){
            DonHangChiTiet_txt_tinhTrang_itemChuaXacNhan.setVisibility(View.INVISIBLE);
            DonHangChiTiet_txt_tinhTrang_itemDangXuLy.setVisibility(View.VISIBLE);
            DonHangChiTiet_txt_tinhTrang_itemDaXuLy .setVisibility(View.INVISIBLE);
            donHangChiTiet_btn_huyDonHang.setVisibility(View.INVISIBLE);
        }else{
            DonHangChiTiet_txt_tinhTrang_itemChuaXacNhan.setVisibility(View.INVISIBLE);
            DonHangChiTiet_txt_tinhTrang_itemDangXuLy.setVisibility(View.INVISIBLE);
            DonHangChiTiet_txt_tinhTrang_itemDaXuLy .setVisibility(View.VISIBLE);
            donHangChiTiet_btn_huyDonHang.setVisibility(View.INVISIBLE);
        }
    }

    // gán giá trị tình trạng
    // show ra danh sách chi tiết sản phẩm hóa đơn
    private void showListDanhSach(ArrayList<model_chiTietSanPhamHoaDon> arrListCTSPHoaDon, ArrayList<model_SanPham> arrListSanPham, model_Account arrAcout) {
        DonHangChiTiet_rscv_showDanhSachDonHang.setHasFixedSize(true);
        DonHangChiTiet_rscv_showDanhSachDonHang.setLayoutManager(new LinearLayoutManager(this));
        AdapterDonHangChiTiet = new donHangChiTietUser_Adapter(chiTietDonhangUser_Activity.this, arrListSanPham, arrListCTSPHoaDon, arrAcout);
        DonHangChiTiet_rscv_showDanhSachDonHang.setAdapter(AdapterDonHangChiTiet);
        AdapterDonHangChiTiet.notifyDataSetChanged();
    }

    // lấy dữ liệu intent
    private void getDataIntent() {
        Bundle bundle = getIntent().getExtras();
        idUserIt = bundle.getString("iT_idUser", "");
        idHoaDonIt = bundle.getString("iT_idHoaDon", "");
        idDanhSachChiTietDonHang = bundle.getString("iT_idDanhSachChiTietDoHang", "");

        idChiTietSanPhamHoaDonIt = bundle.getString("iT_idChiTietSanPhamHoaDon", "");
        tongGiaIt = bundle.getDouble("iT_tongGia", 0);
        tinhTrangHoaDonIt = bundle.getInt("iT_tinhTrang", 0);
//        set giá trị
    }

    private void anhXa() {
        arrListCTSPHoaDon = new ArrayList<>();
        arrListSanPham = new ArrayList<>();


        sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        database = FirebaseDatabase.getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/");
//        TextView
        DonHangChiTiet_txt_tinhTrang_itemDangXuLy = findViewById(R.id.donHangChiTiet_txt_tinhTrang_itemDangXuLy);
        DonHangChiTiet_txt_tinhTrang_itemChuaXacNhan = findViewById(R.id.donHangChiTiet_txt_tinhTrang_itemChuaXacNhan);
        DonHangChiTiet_txt_tinhTrang_itemDaXuLy = findViewById(R.id.donHangChiTiet_txt_tinhTrang_itemDaXuLy);
//        ImageView
        DonHangChiTiet_img_btnThoat = findViewById(R.id.donHangChiTiet_img_btnThoat);
//        RecyclerView
        DonHangChiTiet_rscv_showDanhSachDonHang = findViewById(R.id.donHangChiTiet_rscv_showDanhSachDonHang);
//        FloatingActionButton
        DonHangChiTiet_floatBtn_moGioHang = findViewById(R.id.donHangChiTiet_floatBtn_moGioHang);
        donHangChiTiet_btn_huyDonHang=findViewById(R.id.donHangChiTiet_btn_huyDonHang);

    }
}