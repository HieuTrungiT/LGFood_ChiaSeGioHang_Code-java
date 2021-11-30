package com.example.lgfood_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lgfood_duan1.Adapter.addToGioHangAdapter;
import com.example.lgfood_duan1.Model.model_Account;
import com.example.lgfood_duan1.Model.model_Cart;
import com.example.lgfood_duan1.Model.model_SanPham;
import com.example.lgfood_duan1.Model.model_addToCart;
import com.example.lgfood_duan1.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    private ArrayList<model_Cart> modelCartArrayList;
    DatabaseReference mData;
    FirebaseDatabase database;
    SharedPreferences sharedPreferences;
    private ArrayList<model_addToCart> cartArrayList;
    addToGioHangAdapter cartAdapter;
    model_addToCart modelAddToCart;
    FusedLocationProviderClient fusedLocationProviderClient;
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
        getLocation();
        batSuKien();
    }


    //  Trung lấy vị trí
    private void getLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(gio_Hang_Activity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//             getLocation();
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete( Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {

                        try {
                            Geocoder geocoder = new Geocoder(
                                    gio_Hang_Activity.this, Locale.getDefault());

                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            GioHang_tv_diaChi.setText(Html.fromHtml("" + addresses.get(0).getAddressLine(0)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            ActivityCompat.requestPermissions(gio_Hang_Activity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

        }

    }
    //thai
    private void itemAddToCart() {

        mData = database
                .getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("GioHangs").child(sharedPreferences.getString("IDGIOHANG", ""));

        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (modelCartArrayList != null) {
                    modelCartArrayList.clear();
                }
                for (DataSnapshot ds : snapshot.getChildren()) {
                    model_Cart cart = ds.getValue(model_Cart.class);
                    modelCartArrayList.add(cart);
                }
                checkKhoHang();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    //thai
    private void checkKhoHang() {
        mData = database
                .getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("khoHang");
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    model_SanPham sanPham = ds.getValue(model_SanPham.class);
                    for (int i = 0; i < modelCartArrayList.size(); i++) {
                        Toast.makeText(gio_Hang_Activity.this, modelCartArrayList.size()+"", Toast.LENGTH_SHORT).show();
                        if ((sanPham.getIdSanPham()) == (modelCartArrayList.get(i).getIdSanPham())) {
                            mData = database
                                    .getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                                    .getReference("newCarts");
//                            modelAddToCart=new model_addToCart(sanPham.getIdSanPham(),sanPham.getMoTaSanPham(),sanPham.getTenSanPham(),sanPham.getNgaySanXuatSanPham(),sanPham.getXuatXuSanPham(),sanPham.getLoaiSanPham(),sanPham.getTinhTrangSanPham(),sanPham.getAnhSanPham(),sanPham.getNgayNhapSanPham(),Integer.parseInt(modelCartArrayList.get(i).getSoLuong()),sanPham.getGiamGiaSanPham() ,sanPham.getGiaNhapSanPham(),sanPham.getGiaBanSanPham());
//                            mData.child(sharedPreferences.getString("IDGIOHANG","")).child(sanPham.getIdSanPham()).setValue(modelAddToCart);
//                            Log.d("itemmmmm",modelCartArrayList.get(i).getSoLuong());
                        }
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


    }

    //thai
    private void loadItemAddToCart() {
        GioHang_rv_showGioHang.setHasFixedSize(true);
        GioHang_rv_showGioHang.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new addToGioHangAdapter(this, cartArrayList, new addToGioHangAdapter.IClickListener() {
            @Override
            public void onCLickMinusItem(model_addToCart cart) {
                onClickMinusItemAddToCart(cart);
            }

            @Override
            public void onClickPlusItem(model_addToCart cart) {
                onClickPlusItemAddToCart(cart);

            }

            @Override
            public void onLongClickDelete(model_addToCart cart) {
                onLongClickDeleteItem(cart);
            }

            @Override
            public void onClickShowItem(model_addToCart cart) {
                onClickShowItemChiTiet(cart);
            }
        });


        GioHang_rv_showGioHang.setAdapter(cartAdapter);
    }

    private void onClickShowItemChiTiet(model_addToCart cart) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dat_nhanh);

        LinearLayout datNhanh_linear_turnOffDiaLog = dialog.findViewById(R.id.datNhanh_linear_turnOffDiaLog);
        //thong tin san pham
        ImageView datNhanh_img_showAnhSanPham = dialog.findViewById(R.id.datNhanh_img_showAnhSanPham);
        TextView datNhanh_tv_xuatXuSanPham = dialog.findViewById(R.id.datNhanh_tv_xuatXuSanPham);
        TextView datNhanh_tv_showTenSanPham = dialog.findViewById(R.id.datNhanh_tv_showTenSanPham);
        TextView datNhanh_tv_giaSanPham = dialog.findViewById(R.id.datNhanh_tv_giaSanPham);
        TextView datNhanh_tv_giamGiaSanPham = dialog.findViewById(R.id.datNhanh_tv_giamGiaSanPham);
        TextView datNhanh_tv_ngaySanXuat = dialog.findViewById(R.id.datNhanh_tv_ngaySanXuat);
        TextView datNhanh_tv_soLuongSanPhamTrongKho = dialog.findViewById(R.id.datNhanh_tv_soLuongSanPhamTrongKho);
        TextView datNhanh_tv_soLuongSanPhamYeuThichDaMua = dialog.findViewById(R.id.datNhanh_tv_soLuongSanPhamYeuThichDaMua);
        TextView datNhanh_tv_moTaSanPham = dialog.findViewById(R.id.datNhanh_tv_moTaSanPham);

        //tang giam so luong sp
        ImageView datNhanh_img_btn_giamSoLuongSanPham = dialog.findViewById(R.id.datNhanh_img_btn_giamSoLuongSanPham);
        TextView datNhanh_tv_SoLuongSanpham = dialog.findViewById(R.id.datNhanh_tv_SoLuongSanpham);
        ImageView datNhanh_img_btn_tangSoLuongSanPham = dialog.findViewById(R.id.datNhanh_img_btn_tangSoLuongSanPham);

        Button datNhanh_btn_themSanPhamVaoGioHang = dialog.findViewById(R.id.datNhanh_btn_themSanPhamVaoGioHang);

        Glide.with(gio_Hang_Activity.this)
                .load(cart.getAnhSp())
                .into(datNhanh_img_showAnhSanPham);
        datNhanh_tv_xuatXuSanPham.setText(cart.getXuatXuSp());
        datNhanh_tv_showTenSanPham.setText(cart.getTenSp());
        datNhanh_tv_giaSanPham.setText(cart.getGiaBanSp() + "00đ");
        datNhanh_tv_giamGiaSanPham.setText("   -" + cart.getGiamGiaSp());
        datNhanh_tv_ngaySanXuat.setText(cart.getNgaySanXuatSp());
        datNhanh_tv_soLuongSanPhamTrongKho.setText(String.valueOf(cart.getSoLuongSp()));
//        datNhanh_tv_soLuongSanPhamYeuThichDaMua.setText(Integer.valueOf(sanPham.getAnhSanPham()));
        datNhanh_tv_moTaSanPham.setText(cart.getMoTaSp());

        //giam so luong san pham
        datNhanh_img_btn_giamSoLuongSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i--;
                if (i <= 1) {
                    i = 1;
                    datNhanh_tv_SoLuongSanpham.setText(String.valueOf(i));
                    datNhanh_btn_themSanPhamVaoGioHang.setText(String.valueOf(i * cart.getGiaBanSp()));
                    return;
                } else {
                    datNhanh_tv_SoLuongSanpham.setText(String.valueOf(i));
                    datNhanh_btn_themSanPhamVaoGioHang.setText(String.valueOf(i * cart.getGiaBanSp()));
                }


            }
        });
        //tang so luong san pham
        datNhanh_img_btn_tangSoLuongSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                datNhanh_tv_SoLuongSanpham.setText(String.valueOf(i));
                datNhanh_btn_themSanPhamVaoGioHang.setText(String.valueOf(i * cart.getGiaBanSp()));
            }
        });
        //turn off dialog
        datNhanh_linear_turnOffDiaLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                i = 1;
            }
        });


//        //add to cart
//        datNhanh_btn_themSanPhamVaoGioHang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String idProduct = sanPham.getIdSanPham();
//                dataRef = database.getReference("Accounts");
//                dataRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                            model_Account account = dataSnapshot.getValue(model_Account.class);
//                            idGioHang = String.valueOf(account.getIdGioHang());
//                        }
//                        UUID uuid = UUID.randomUUID();
//                        String idChiTietSanPham = String.valueOf(uuid);
//                        model_Cart cart = new model_Cart(idChiTietSanPham, idProduct, i + "");
//                        dataRef = database.getReference("GioHangs");
//                        dataRef.child(idGioHang).child(idChiTietSanPham).setValue(cart);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//        });

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();


    }

    private void onLongClickDeleteItem(model_addToCart cart) {

        new AlertDialog.Builder(gio_Hang_Activity.this)
                .setTitle(getString(R.string.app_name))
                .setMessage("Bạn chắc chắn muốn xóa item này không?")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database = FirebaseDatabase
                                .getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/");
                        mData = database.getReference("newCarts");

                        mData.child(sharedPreferences.getString("IDGIOHANG", "")).child(cart.getIdSp()).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError error, DatabaseReference ref) {

                                Toast.makeText(gio_Hang_Activity.this, "Delete item success", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                })

                .setNegativeButton("Cancel", null)
                .show();
    }

    //giam so luong san pham: thai
    private void onClickMinusItemAddToCart(model_addToCart cart) {
        mData = database
                .getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("newCarts");
        i = cart.getSoLuongSp();
        i--;
        if (i <= 1) {
            i = 1;
            cart.setSoLuongSp(i);
            mData.child(sharedPreferences.getString("IDGIOHANG", "")).child(cart.getIdSp()).setValue(cart);
            return;
        } else {
            cart.setSoLuongSp(i);
            mData.child(sharedPreferences.getString("IDGIOHANG", "")).child(cart.getIdSp()).setValue(cart);
        }
    }

    //tang so luong san pham:thai
    private void onClickPlusItemAddToCart(model_addToCart cart) {
        i = cart.getSoLuongSp();
        i++;
        cart.setSoLuongSp(i);
        mData.child(sharedPreferences.getString("IDGIOHANG", "")).child(cart.getIdSp()).setValue(cart);
//        datNhanh_tv_SoLuongSanpham.setText(String.valueOf(i));
//        datNhanh_btn_themSanPhamVaoGioHang.setText(String.valueOf(i * sanPham.getGiaBanSanPham()));
    }

    //thai: lay du lieu tu firebase
    private void layTuFirebase() {
        mData = database
                .getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("newCarts").child(sharedPreferences.getString("IDGIOHANG", ""));
        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                model_addToCart cart = snapshot.getValue(model_addToCart.class);

                if (cart != null) {
                    cartArrayList
                            .add(new model_addToCart(cart.getIdSp(),
                                    cart.getMoTaSp(),
                                    cart.getTenSp(),
                                    cart.getNgaySanXuatSp(),
                                    cart.getXuatXuSp(),
                                    cart.getLoaiSp(),
                                    cart.getTinhTrangSp(),
                                    cart.getAnhSp(),
                                    cart.getNgayNhapSp(),
                                    cart.getSoLuongSp(),
                                    cart.getGiamGiaSp(),
                                    cart.getGiaNhapSp(),
                                    cart.getGiaBanSp()));
                    cartAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                model_addToCart cart = snapshot.getValue(model_addToCart.class);
                if (cart == null || cartArrayList == null || cartArrayList.isEmpty()) {
                    return;
                }
                for (int i = 0; i < cartArrayList.size(); i++) {
                    if (cart.getIdSp() == cartArrayList.get(i).getIdSp()) {
                        cartArrayList.set(i, cart);
                    }
                }
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                model_addToCart cart = snapshot.getValue(model_addToCart.class);
                if (cart == null || cartArrayList == null || cartArrayList.isEmpty()) {
                    return;
                }
                for (int i = 0; i < cartArrayList.size(); i++) {
                    if (cart.getIdSp() == cartArrayList.get(i).getIdSp()) {
                        cartArrayList.remove(cartArrayList.get(i));
                        break;
                    }
                }
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void batSuKien() {
        GioHang_img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(gio_Hang_Activity.this,trangChu_SanPham_Activity.class);
                startActivity(intent);
            }
        });
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
        sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        cartArrayList = new ArrayList<>();
        modelCartArrayList = new ArrayList<>();
    }

}