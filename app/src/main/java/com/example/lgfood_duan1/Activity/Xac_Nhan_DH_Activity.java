package com.example.lgfood_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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

import com.example.lgfood_duan1.Adapter.xacNhanDonHangUser_Adapter;
import com.example.lgfood_duan1.Model.model_Account;
import com.example.lgfood_duan1.Model.model_Cart;
import com.example.lgfood_duan1.Model.model_addToCart;
import com.example.lgfood_duan1.Model.model_chiTietSanPhamHoaDon;
import com.example.lgfood_duan1.Model.model_hoaDon;
import com.example.lgfood_duan1.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Xac_Nhan_DH_Activity extends AppCompatActivity {
    private CardView

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
    private RecyclerView
            XacNhan_rscv_showItemSanPham;
    private ImageView XacNhan_cardView_backItem;
    //Value
    double tongTien;
    String idDanhSachDonHangUser, idUserSharePre;
    //    SharePre
    SharedPreferences sharePreAcout;
    //    Firebase\
    private DatabaseReference dataTaiKhoanRef, dataGioHangRef, dataHoaDonRef, dataCTSPHoaDonRef;
    private FirebaseDatabase database;
    //Model
    private ArrayList<model_Cart> arrListGioHangs;
    private ArrayList<model_addToCart> arrListNewCarts;
    private model_Account listAccount;
    private model_addToCart arrNewCart;
    private model_Cart arrGioHang;
    private model_hoaDon arrHoaDon;
    private model_chiTietSanPhamHoaDon arrCTSPHoaDon;

    //adapter
    xacNhanDonHangUser_Adapter adapterXacNhanDonHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_dh);
        anhXa();
        batSuKien();
        getValueIntent();
        getValueSharePre();
        getDataFirebase();
        showListGioHang();
    }

    //show danh s??ch s???n ph???m
    private void showListGioHang() {
        XacNhan_rscv_showItemSanPham.setHasFixedSize(true);
        XacNhan_rscv_showItemSanPham.setLayoutManager(new LinearLayoutManager(this));
        adapterXacNhanDonHang = new xacNhanDonHangUser_Adapter(this, arrListNewCarts, arrListGioHangs);
        XacNhan_rscv_showItemSanPham.setAdapter(adapterXacNhanDonHang);
    }

    //l???y id t??? SharePre
    private void getValueSharePre() {
        sharePreAcout = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        idDanhSachDonHangUser = sharePreAcout.getString("IDDANHSACHDONHANG", "");
        idUserSharePre = sharePreAcout.getString("IDUSRE", "");
//
    }

    //    l???y thoogn tin user t??? firebase
    private void getDataFirebase() {
        dataTaiKhoanRef = database.getReference().child("Accounts").child(idUserSharePre);
        dataTaiKhoanRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//            ki???m tra d??? li???u
                listAccount = snapshot.getValue(model_Account.class);
                setData(listAccount);
                getDataGioHang(listAccount);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    // Trung l???y id gi??? h??ng ????? l???y s??? l?????ng s???n ph???m
    private void getDataGioHang(model_Account listAccount) {
//        l???y d??? li???u t??? firebase gioHang

        dataGioHangRef = database.getReference("GioHangs").child(listAccount.getIdGioHang());
        dataGioHangRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (arrListGioHangs != null) {
                    arrListGioHangs.clear();
                }
                for (DataSnapshot ds : snapshot.getChildren()) {
                    arrGioHang = ds.getValue(model_Cart.class);
                    arrListGioHangs.add(arrGioHang);
                }
                getDataNewCart();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }


    //Trung: L???y th??ng tin s???n ph???m t???n newCart firebase ????? show l??n Adapter
    private void getDataNewCart() {
//        l???y s???n ph???m t??? gi??? h??ng
        dataGioHangRef = database.getReference("newCarts").child(listAccount.getIdGioHang());
        dataGioHangRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                arrListNewCarts.clear();
                for (DataSnapshot child : snapshot.getChildren()) {
                    arrNewCart = child.getValue(model_addToCart.class);
                    arrListNewCarts.add(arrNewCart);
                }
                adapterXacNhanDonHang.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    //Trung g??n gi?? tr??? l??n TextView th??ng tin user
    private void setData(model_Account listAccount) {
        XacNhan_txt_tenKhachHang.setText(listAccount.getRealName());
        XacNhan_txt_diaChiKhachHang.setText(listAccount.getAddress());
        XacNhan_txt_sdtKhachHang_xacNhan.setText(listAccount.getPhoneNumber());
        XacNhan_txt_gmailKhachHang.setText(listAccount.getEmail());
    }

    //trung l???y gi?? tr??? intent t??? trang gi??? h??ng qua g??n v??o gi?? ti???n x??c nh???n
    private void getValueIntent() {
        Bundle bundle = getIntent().getExtras();
        tongTien = bundle.getDouble("iT_tongGiaTien", 0);
        XacNhan_txt_tienDonGia.setText(tongTien + "00vn??");
        XacNhan_txt_giaDonHang.setText(tongTien + 30.000 + "00vn??");
    }

    // Trung l??u thong tin h??a ????n khi x??c nh???n
    private void luuThongTinHoaDon() {

        Dialog dialog = new Dialog(Xac_Nhan_DH_Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_dialog_chucnang_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        ImageView item_dialog_chucNang_img_imgErro = dialog.findViewById(R.id.item_dialog_chucNang_img_imgErro);
        TextView item_dialog_chucNang_txt_nameErro = dialog.findViewById(R.id.item_dialog_chucNang_txt_nameErro);
        Button Okay = dialog.findViewById(R.id.btn_okay);
        Button Cancel = dialog.findViewById(R.id.btn_cancel);
        //setText Item
        Okay.setText("Order");
        item_dialog_chucNang_img_imgErro.setImageResource(R.drawable.question);
        item_dialog_chucNang_txt_nameErro.setText("Would you want to order the products?");
        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog diaLogDoc = new Dialog(Xac_Nhan_DH_Activity.this);
                diaLogDoc.requestWindowFeature(Window.FEATURE_NO_TITLE);
                diaLogDoc.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                diaLogDoc.setCancelable(false); //Optional
                diaLogDoc.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog
                diaLogDoc.setContentView(R.layout.activity_add_to_cart_anim);
//        arrListHoaDon;
                dataHoaDonRef = database.getReference("HoaDon");
                // datetime hi???n t???i
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'l??c' HH:mm:ss z");
                Date reaDate = new Date(System.currentTimeMillis());
                arrHoaDon = new model_hoaDon(UUID.randomUUID().toString(), UUID.randomUUID().toString(), idUserSharePre, tongTien + 30.000, 0, formatter.format(reaDate).toString());
                luuThongTinChiTietSanPhamHoaDon(arrHoaDon);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        diaLogDoc.dismiss();
                        startActivity(new Intent(Xac_Nhan_DH_Activity.this, gio_Hang_Activity.class));
                    }
                }, 2300);
                diaLogDoc.show();
                dialog.dismiss();
                dataHoaDonRef.child(listAccount.getIdDanhSachDonHang()).child(arrHoaDon.getIdHoaDon()).setValue(arrHoaDon);
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //Trung l??u danh s??ch s???n ph???m h??a ????n
    private void luuThongTinChiTietSanPhamHoaDon(model_hoaDon arrHoaDon) {
        //        arrListHoaDon
        dataCTSPHoaDonRef = database.getReference("ChiTietHoaDon");
        for (int i = 0; i < arrListGioHangs.size(); i++) {
            for (int j = 0; j < arrListNewCarts.size(); j++) {
                if (arrListGioHangs.get(i).getIdSanPham().equals(arrListNewCarts.get(j).getIdSp())) {
                    arrCTSPHoaDon = new model_chiTietSanPhamHoaDon(UUID.randomUUID().toString(), arrListGioHangs.get(i).getIdSanPham(), arrListGioHangs.get(i).getSoLuong(), arrListNewCarts.get(j).getGiaBanSp());
                    dataCTSPHoaDonRef.child(arrHoaDon.getIdChiTietDonHang()).child(arrCTSPHoaDon.getIdChiTietHoaDon()).setValue(arrCTSPHoaDon);
                }
            }
        }
        Toast.makeText(this, "l??u h??a ????n th??nh c??ng", Toast.LENGTH_SHORT).show();
    }


    private void batSuKien() {
//        b???t s??? ki???n x??c nh???n mua
        XacNhan_cardView_btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luuThongTinHoaDon();
            }
        });
//    b???t s??? ki???n quay l???i trang tr?????c
        XacNhan_cardView_backItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Xac_Nhan_DH_Activity.this, gio_Hang_Activity.class);
                startActivity(intent);
            }
        });
//        b???t s??? ki???n chuy???n trang ?????n s???a th??ng tin t??i kho???n
        XacNhan_cardView_btnThayDoiThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Xac_Nhan_DH_Activity.this, thongTinTaiKhoan_Activity.class);
                startActivity(intent);
            }
        });
    }


    private void anhXa() {
//        Model arrlist
        arrListGioHangs = new ArrayList<>();
        arrListNewCarts = new ArrayList<>();
        //    Firebase
        database = FirebaseDatabase.getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/");
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
//        RecyclerView
        XacNhan_rscv_showItemSanPham = findViewById(R.id.xacNhan_rscv_showItemSanPham);
    }

}