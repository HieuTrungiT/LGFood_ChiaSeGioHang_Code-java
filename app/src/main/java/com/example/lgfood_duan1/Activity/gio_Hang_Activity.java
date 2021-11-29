package com.example.lgfood_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lgfood_duan1.Adapter.addToGioHangAdapter;
import com.example.lgfood_duan1.Model.model_Account;
import com.example.lgfood_duan1.Model.model_Cart;
import com.example.lgfood_duan1.Model.model_SanPham;
import com.example.lgfood_duan1.Model.model_addToCart;
import com.example.lgfood_duan1.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class gio_Hang_Activity extends AppCompatActivity {
    private ImageView
            GioHang_img_back;
    private TextView
            GioHang_tv_diaChi,
            GioHang_tv_name,
            GioHang_tv_tongTien;
    private Button
            GioHang_btn_datHang;
    private LinearLayout
            GioHang_llout_btn_shareCart;
    private RecyclerView
            GioHang_rv_showGioHang;
    //thai
    private ArrayList<model_Cart> modelCartArrayList ;
    DatabaseReference mData;
    FirebaseDatabase database;
    SharedPreferences sharedPreferences;
    private ArrayList<model_addToCart> cartArrayList;
    addToGioHangAdapter cartAdapter;
    model_addToCart modelAddToCart;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        anhXa();
        //thai
        itemAddToCart();
        loadItemAddToCart();
        layTuFirebase();
    }




//thai
    private void itemAddToCart() {

        mData= database
                .getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("GioHangs").child(sharedPreferences.getString("IDGIOHANG",""));

        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (modelCartArrayList!=null){
                    modelCartArrayList.clear();
                }
                for (DataSnapshot ds: snapshot.getChildren()){
                    model_Cart cart=ds.getValue(model_Cart.class);
                             modelCartArrayList.add(cart);
                }
                checkKhoHang();

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

    }
//thai
    private void checkKhoHang() {
        mData= database
                .getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("khoHang");
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()){
                    model_SanPham sanPham=ds.getValue(model_SanPham.class);
                    for (int i=0;i<modelCartArrayList.size();i++){
                        if (sanPham.getIdSanPham()==(modelCartArrayList.get(i).getIdSanPham())) {
                            mData= database
                                    .getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                                    .getReference("newCarts");
                            modelAddToCart=new model_addToCart(sanPham.getIdSanPham(),sanPham.getAnhSanPham(),sanPham.getTenSanPham(),sanPham.getXuatXuSanPham(),String.valueOf(sanPham.getGiaBanSanPham()),modelCartArrayList.get(i).getSoLuong());
                            mData.child(sharedPreferences.getString("IDGIOHANG","")).child(sanPham.getIdSanPham()).setValue(modelAddToCart);
                            Log.d("itemmmmm",sanPham.getTenSanPham());

                        }
                    }

                }

            }
                @Override
                public void onCancelled(@NonNull  DatabaseError error) {

                }

            });


    }
    //thai
    private void loadItemAddToCart() {
        GioHang_rv_showGioHang.setHasFixedSize(true);
        GioHang_rv_showGioHang.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter=new addToGioHangAdapter(this, cartArrayList, new addToGioHangAdapter.IClickListener() {
            @Override
            public void onCLickMinusItem(model_addToCart cart) {
                onClickMinusItemAddToCart(cart);
            }

            @Override
            public void onClickPlusItem(model_addToCart cart) {
                onClickPlusItemAddToCart(cart);

            }
        });


        GioHang_rv_showGioHang.setAdapter(cartAdapter);
    }
    //giam so luong san pham: thai
    private void onClickMinusItemAddToCart(model_addToCart cart) {
        mData= database
                .getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("newCarts");
        i= Integer.parseInt(cart.getSoLuong());
        i--;
        if (i <= 1) {
            i = 1;
            cart.setSoLuong(i+"");
            mData.child(sharedPreferences.getString("IDGIOHANG","")).child(cart.getIdSp()).setValue(cart);
            return;
        } else {
            cart.setSoLuong(i+"");
            mData.child(sharedPreferences.getString("IDGIOHANG","")).child(cart.getIdSp()).setValue(cart);
        }
    }
    //tang so luong san pham:thai
    private void onClickPlusItemAddToCart(model_addToCart cart) {
        i= Integer.parseInt(cart.getSoLuong());
        i++;
        cart.setSoLuong(i+"");
        mData.child(sharedPreferences.getString("IDGIOHANG","")).child(cart.getIdSp()).setValue(cart);
//        datNhanh_tv_SoLuongSanpham.setText(String.valueOf(i));
//        datNhanh_btn_themSanPhamVaoGioHang.setText(String.valueOf(i * sanPham.getGiaBanSanPham()));
    }

    //thai: lay du lieu tu firebase
    private void layTuFirebase() {
        mData= database
                .getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("newCarts").child(sharedPreferences.getString("IDGIOHANG",""));
        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                model_addToCart cart = snapshot.getValue(model_addToCart.class);

                if (cart != null) {
                    cartArrayList
                            .add(new model_addToCart(cart.getIdSp(),
                                    cart.getAnhSp()
                                    , cart.getTenSp()
                                    , cart.getXuatXu()
                                    , cart.getGiaSp()
                                    , cart.getSoLuong()));
                    cartAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                model_addToCart cart = snapshot.getValue(model_addToCart.class);
                if (cart==null || cartArrayList==null ||cartArrayList.isEmpty()){
                    return;
                }
                for (int i=0;i<cartArrayList.size();i++){
                    if (cart.getIdSp()==cartArrayList.get(i).getIdSp()){
                        cartArrayList.set(i,cart);
                    }
                }
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void batSuKien(){

    }
    //    Bảo Toàn: kiểm tra dữ liệu đầu vào
    private void checkValidateSet(){

    }

    //    Bắt sự kiện buttom



    //     Ánh xạ đây nè :D
    private void anhXa() {

//        TextView
        GioHang_tv_diaChi = findViewById(R.id.gioHang_tv_diaChi);
        GioHang_tv_name = findViewById(R.id.gioHang_tv_name);
        GioHang_tv_tongTien = findViewById(R.id.gioHang_tv_tongTien);


//      ImageView

        GioHang_img_back = findViewById(R.id.gioHang_img_back);

//      Button

        GioHang_btn_datHang = findViewById(R.id.gioHang_btn_datHang);

//      LinearLayout

        GioHang_llout_btn_shareCart = findViewById(R.id.gioHang_llout_btn_shareCart);

//        ListView

        GioHang_rv_showGioHang = findViewById(R.id.gioHang_rv_showGioHang);


        //thai
        sharedPreferences=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        cartArrayList=new ArrayList<>();
        modelCartArrayList=new ArrayList<>();
    }

}