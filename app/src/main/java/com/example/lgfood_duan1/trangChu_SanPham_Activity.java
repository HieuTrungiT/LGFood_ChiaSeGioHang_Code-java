package com.example.lgfood_duan1;import androidx.annotation.NonNull;import androidx.appcompat.app.ActionBarDrawerToggle;import androidx.appcompat.app.AppCompatActivity;import androidx.core.view.GravityCompat;import androidx.drawerlayout.widget.DrawerLayout;import androidx.recyclerview.widget.LinearLayoutManager;import androidx.recyclerview.widget.RecyclerView;import android.app.Activity;import android.content.Intent;import android.os.Bundle;import android.view.Menu;import android.view.MenuItem;import android.view.View;import android.widget.Button;import android.widget.FrameLayout;import android.widget.ImageView;import android.widget.TextView;import android.widget.Toast;import android.widget.Toolbar;import com.example.lgfood_duan1.Model.model_SanPham;import com.google.android.material.bottomnavigation.BottomNavigationView;import com.google.android.material.navigation.NavigationView;import java.util.ArrayList;import java.util.List;public class trangChu_SanPham_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {    private DrawerLayout            TrangChuSanPham_drawerllout_main;    private NavigationView            TrangChuSanPham_nav_drawer;    private ImageView            TrangChuSanPham_img_showMenu,            TrangChuSanPham_img_btn_thongBao,            TrangChuSanPham_img_btn_kieuLoaiSanPham,            TrangChuSanPham_img_showLoaiCoffee,            TrangChuSanPham_img_showLoaiThaoDuoc,            TrangChuSanPham_img_showLoaiHat,            TrangChuSanPham_img_showLoaiMuc,            TrangChuSanPham_img_showLoaiTra,            DatNhanh_img_showAnhSanPham,            DatNhanh_img_btn_giamSoLuongSanPham,            DatNhanh_img_btn_tangSoLuongSanPham;    private TextView            TrangChuSanPham_tv_soLuongThongBao,            TrangChuSanPham_tv_showTatCaLoaiSanpham,            TrangChuSanPham_tv_showLoai,            DatNhanh_tv_xuatXuSanPham,            DatNhanh_tv_showTenSanPham,            DatNhanh_tv_giaSanPham,            DatNhanh_tv_giamGiaSanPham,            DatNhanh_tv_ngaySanXuat,            DatNhanh_tv_soLuongSanPhamTrongKho,            DatNhanh_tv_moTaSanPham,            DatNhanh_tv_SoLuongSanpham,            DatNhanh_tv_soLuongSanPhamYeuThichDaMua;    private RecyclerView            TrangChuSanPham_rscV_showSanPhamNgang,            TrangChuSanPham_rscV_showSanPhamDoc;    private FrameLayout            DatNhanh_FmLt_showChiTietSanPham;    private Button            DatNhanh_btn_themSanPhamVaoGioHang;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_trang_chu_san_pham);        anhXa();        NavigationDrawer();        BottomNavigationView navigationView = findViewById(R.id.trangChuSanPham_bottomNavigation);        navigationView.setSelectedItemId(R.id.cart);        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {            @Override            public void onNavigationItemReselected(@NonNull MenuItem item) {                switch (item.getItemId()){                    case R.id.cart:                        startActivity(new Intent(getApplicationContext(),                                gio_Hang_Activity.class));                        overridePendingTransition(0,0);                        return;                    case R.id.Like:                        return;                    case R.id.Home:                        return;                    case R.id.Paid:                        startActivity(new Intent(getApplicationContext(),                                xac_Nhan_Don_hang_Activity.class));                        overridePendingTransition(0,0);                        return;                    case R.id.Use:                        startActivity(new Intent(getApplicationContext(),                                Chinh_Sua_Thong_Tin_Accounts_Activity.class));                        overridePendingTransition(0,0);                        return;                }                return ;            }        });    }    private void NavigationDrawer() {        /*           NavigationView Drawer Menu           */        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, TrangChuSanPham_drawerllout_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close);        TrangChuSanPham_drawerllout_main.addDrawerListener(toggle);        toggle.syncState();        TrangChuSanPham_nav_drawer.setNavigationItemSelectedListener(this);        TrangChuSanPham_nav_drawer.bringToFront();        TrangChuSanPham_nav_drawer.setCheckedItem(R.id.drawer_nav_login);        /*           Phần quyền đăng nhập           *///        Menu menu = TrangChuSanPham_nav_drawer.getMenu();//        menu.findItem(R.id.drawer_nav_logout).setVisible(false);//        menu.findItem(R.id.drawer_nav_profile).setVisible(false);        TrangChuSanPham_img_showMenu.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                if (TrangChuSanPham_drawerllout_main.isDrawerVisible(GravityCompat.START))                    TrangChuSanPham_drawerllout_main.closeDrawer(GravityCompat.START);                else TrangChuSanPham_drawerllout_main.openDrawer(GravityCompat.START);            }        });    }    @Override    public void onBackPressed() {        if (TrangChuSanPham_drawerllout_main.isDrawerVisible(GravityCompat.START)) {            TrangChuSanPham_drawerllout_main.closeDrawer(GravityCompat.START);        } else {            super.onBackPressed();        }    }    //    Bảo Toàn: kiểm tra dữ liệu đầu vào    private void checkValidateSet() {    }    //    Bắt sự kiện buttom    private void batSuKien() {    }    //     Ánh xạ đây nè :D    private void anhXa() {        //      ImageView        TrangChuSanPham_img_showMenu = findViewById(R.id.trangChuSanPham_img_showMenu);        TrangChuSanPham_img_btn_thongBao = findViewById(R.id.trangChuSanPham_img_btn_thongBao);        TrangChuSanPham_img_btn_kieuLoaiSanPham = findViewById(R.id.trangChuSanPham_img_btn_kieuLoaiSanPham);        TrangChuSanPham_img_showLoaiCoffee = findViewById(R.id.trangChuSanPham_img_showLoaiCoffee);        TrangChuSanPham_img_showLoaiThaoDuoc = findViewById(R.id.trangChuSanPham_img_showLoaiThaoDuoc);        TrangChuSanPham_img_showLoaiHat = findViewById(R.id.trangChuSanPham_img_showLoaiHat);        TrangChuSanPham_img_showLoaiMuc = findViewById(R.id.trangChuSanPham_img_showLoaiMuc);        TrangChuSanPham_img_showLoaiTra = findViewById(R.id.trangChuSanPham_img_showLoaiTra);        //      TextView        TrangChuSanPham_tv_soLuongThongBao = findViewById(R.id.trangChuSanPham_tv_soLuongThongBao);        TrangChuSanPham_tv_showTatCaLoaiSanpham = findViewById(R.id.trangChuSanPham_tv_showTatCaLoaiSanpham);        TrangChuSanPham_tv_showLoai = findViewById(R.id.trangChuSanPham_tv_showLoai);        //      ecyclerView        TrangChuSanPham_rscV_showSanPhamNgang = findViewById(R.id.trangChuSanPham_rscV_showSanPhamNgang);        TrangChuSanPham_rscV_showSanPhamDoc = findViewById(R.id.trangChuSanPham_rscV_showSanPhamDoc);        //        DrawerLayout        TrangChuSanPham_drawerllout_main = findViewById(R.id.trangChuSanPham_drawerllout_main);        //        NavigationView        TrangChuSanPham_nav_drawer = findViewById(R.id.trangChuSanPham_nav_drawer);        //       layout datnhanh        //        ImageView        DatNhanh_img_showAnhSanPham = findViewById(R.id.datNhanh_img_showAnhSanPham);        DatNhanh_img_btn_giamSoLuongSanPham = findViewById(R.id.datNhanh_img_btn_giamSoLuongSanPham);        DatNhanh_img_btn_tangSoLuongSanPham = findViewById(R.id.datNhanh_img_btn_tangSoLuongSanPham);        //        TextView        DatNhanh_tv_showTenSanPham = findViewById(R.id.datNhanh_tv_showTenSanPham);        DatNhanh_tv_giaSanPham = findViewById(R.id.datNhanh_tv_giaSanPham);        DatNhanh_tv_giamGiaSanPham = findViewById(R.id.datNhanh_tv_giamGiaSanPham);        DatNhanh_tv_ngaySanXuat = findViewById(R.id.datNhanh_tv_ngaySanXuat);        DatNhanh_tv_soLuongSanPhamTrongKho = findViewById(R.id.datNhanh_tv_soLuongSanPhamTrongKho);        DatNhanh_tv_moTaSanPham = findViewById(R.id.datNhanh_tv_moTaSanPham);        DatNhanh_tv_SoLuongSanpham = findViewById(R.id.datNhanh_tv_SoLuongSanpham);        DatNhanh_tv_soLuongSanPhamYeuThichDaMua = findViewById(R.id.datNhanh_tv_soLuongSanPhamYeuThichDaMua);//        FrameLayout        DatNhanh_FmLt_showChiTietSanPham = findViewById(R.id.datNhanh_FmLt_showChiTietSanPham);//        Button        DatNhanh_btn_themSanPhamVaoGioHang = findViewById(R.id.datNhanh_btn_themSanPhamVaoGioHang);    }    @Override    public boolean onNavigationItemSelected(@NonNull MenuItem item) {        switch (item.getItemId()) {            case R.id.drawer_nav_login:                break;            case R.id.drawer_nav_logout:                break;            case R.id.drawer_nav_profile:                break;            case R.id.drawer_nav_rate:                break;            case R.id.drawer_nav_share:                break;        }        TrangChuSanPham_drawerllout_main.closeDrawer(GravityCompat.START);        return true;    }}