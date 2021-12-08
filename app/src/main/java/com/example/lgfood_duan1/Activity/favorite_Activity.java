package com.example.lgfood_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.lgfood_duan1.Adapter.addToGioHangAdapter;
import com.example.lgfood_duan1.Adapter.favoriteAdapter;
import com.example.lgfood_duan1.Model.model_Cart;
import com.example.lgfood_duan1.Model.model_SanPham;
import com.example.lgfood_duan1.Model.model_addToCart;
import com.example.lgfood_duan1.Model.model_yeuThich;
import com.example.lgfood_duan1.Model.model_yeuThichShow;
import com.example.lgfood_duan1.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class favorite_Activity extends AppCompatActivity {
    RecyclerView favorite_rev_showItem;
    FirebaseDatabase database;
    DatabaseReference dataRef;
    SharedPreferences sharedPreferences;
    ArrayList<model_yeuThich> modelYeuThichArrayList;
    favoriteAdapter favoriteAdapter;
    ArrayList<model_yeuThichShow> showArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        anhXa();
        itemDanhSachYeuThich();
        loadData();
        getDataOnDatabase();
    }

    private void getDataOnDatabase() {
        dataRef = database.getReference("itemDanhSachYeuThich").child(sharedPreferences.getString("IDDANHSACHYEUTHICH", ""));
        dataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                model_yeuThichShow yeuThichShow = snapshot.getValue(model_yeuThichShow.class);

                if (yeuThichShow != null) {
                    showArrayList
                            .add(new model_yeuThichShow(yeuThichShow.getIdSp()
                                    ,yeuThichShow.getMoTaSp()
                                    ,yeuThichShow.getTenSp()
                                    ,yeuThichShow.getNgaySanXuatSp()
                                    ,yeuThichShow.getXuatXuSp()
                                    ,yeuThichShow.getLoaiSp()
                                    ,yeuThichShow.getTinhTrangSp()
                                    ,yeuThichShow.getAnhSp()
                                    ,yeuThichShow.getNgayNhapSp()
                                    ,yeuThichShow.getSoLuongTrongKho()
                                    ,yeuThichShow.getGiamGiaSp()
                                    , yeuThichShow.getGiaNhapSp()
                                    ,yeuThichShow.getGiaBanSp()));
                    favoriteAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                model_yeuThichShow yeuThichShow = snapshot.getValue(model_yeuThichShow.class);
                if (yeuThichShow == null || showArrayList == null || showArrayList.isEmpty()) {
                    return;
                }
                for (int i = 0; i < showArrayList.size(); i++) {
                    if (yeuThichShow.getIdSp() == showArrayList.get(i).getIdSp()) {
                        showArrayList.set(i, yeuThichShow);
                    }
                }
                favoriteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                model_yeuThichShow yeuThichShow = snapshot.getValue(model_yeuThichShow.class);
                if (yeuThichShow == null || showArrayList == null || showArrayList.isEmpty()) {
                    return;
                }
                for (int i = 0; i < showArrayList.size(); i++) {
                    if (yeuThichShow.getIdSp() == showArrayList.get(i).getIdSp()) {
                        showArrayList.remove(showArrayList.get(i));
                        break;
                    }
                }
                favoriteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadData() {
        favorite_rev_showItem.setHasFixedSize(true);
        favorite_rev_showItem.setLayoutManager(new LinearLayoutManager(this));
        favoriteAdapter = new favoriteAdapter(this, showArrayList, new favoriteAdapter.IClickListener() {
            @Override
            public void onCLickMinusItem(model_yeuThichShow yeuThichShow) {
                onClickMinusItemDanhSachYeuThich(yeuThichShow);
            }

            @Override
            public void onClickPlusItem(model_yeuThichShow yeuThichShow) {
                onClickPlusItemDanhSachYeuThich(yeuThichShow);

            }

            @Override
            public void onLongClickDelete(model_yeuThichShow yeuThichShow) {
                onLongClickDeleteItem(yeuThichShow);

            }

            @Override
            public void onClickShowItem(model_yeuThichShow yeuThichShow) {
                onClickShowItemChiTiet(yeuThichShow);

            }
        });


                favorite_rev_showItem.setAdapter(favoriteAdapter);
    }

    private void onClickShowItemChiTiet(model_yeuThichShow yeuThichShow) {
    }

    private void onLongClickDeleteItem(model_yeuThichShow yeuThichShow) {
    }

    private void onClickPlusItemDanhSachYeuThich(model_yeuThichShow yeuThichShow) {
    }

    private void onClickMinusItemDanhSachYeuThich(model_yeuThichShow yeuThichShow) {

    }

    //thai
    private void itemDanhSachYeuThich() {

        dataRef = database.getReference("danhSachSanPhamYeuThich").child(sharedPreferences.getString("IDDANHSACHYEUTHICH", ""));

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (modelYeuThichArrayList != null) {
                    modelYeuThichArrayList.clear();
                }
                for (DataSnapshot ds : snapshot.getChildren()) {
                    model_yeuThich yeuThich=ds.getValue(model_yeuThich.class);
                    modelYeuThichArrayList.add(yeuThich);
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
        dataRef = database.getReference("khoHang");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    model_SanPham sanPham = ds.getValue(model_SanPham.class);

                    for (int i = 0; i < modelYeuThichArrayList.size(); i++) {

                        if (modelYeuThichArrayList.get(i).getIdSanPham().equals(sanPham.getIdSanPham())) {
                            dataRef = database.getReference("itemDanhSachYeuThich");
                            model_yeuThichShow yeuThichShow = new model_yeuThichShow(sanPham.getIdSanPham(),sanPham.getMoTaSanPham(),sanPham.getTenSanPham(),sanPham.getNgaySanXuatSanPham(),sanPham.getXuatXuSanPham(),sanPham.getLoaiSanPham(),sanPham.getTinhTrangSanPham(),sanPham.getAnhSanPham(),sanPham.getNgayNhapSanPham(),sanPham.getSoLuongSanPham(),sanPham.getGiamGiaSanPham(),sanPham.getGiaNhapSanPham(),sanPham.getGiaBanSanPham());
                            dataRef.child(sharedPreferences.getString("IDDANHSACHYEUTHICH","")).child(sanPham.getIdSanPham()).setValue(yeuThichShow);
                        }
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    private void anhXa() {
        favorite_rev_showItem=findViewById(R.id.favorite_rev_showItem);
        database = FirebaseDatabase.getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/");

        sharedPreferences=getSharedPreferences("USER_FILE",MODE_PRIVATE);

        modelYeuThichArrayList=new ArrayList<>();
        showArrayList=new ArrayList<>();
    }
}