package com.example.lgfood_duan1.Activity;import androidx.annotation.NonNull;import androidx.appcompat.app.AppCompatActivity;import androidx.recyclerview.widget.DefaultItemAnimator;import androidx.recyclerview.widget.GridLayoutManager;import androidx.recyclerview.widget.RecyclerView;import android.app.Dialog;import android.content.Intent;import android.graphics.Color;import android.graphics.drawable.ColorDrawable;import android.os.Bundle;import android.view.Gravity;import android.view.KeyEvent;import android.view.View;import android.view.ViewGroup;import android.view.Window;import android.view.WindowManager;import android.view.animation.Animation;import android.view.animation.AnimationUtils;import android.widget.AdapterView;import android.widget.ArrayAdapter;import android.widget.EditText;import android.widget.ImageView;import android.widget.LinearLayout;import android.widget.Spinner;import android.widget.TextView;import android.widget.Toast;import com.example.lgfood_duan1.Adapter.adapter_SanPham_Kho;import com.example.lgfood_duan1.Model.model_SanPham;import com.example.lgfood_duan1.R;import com.google.android.material.floatingactionbutton.FloatingActionButton;import com.google.firebase.database.DataSnapshot;import com.google.firebase.database.DatabaseError;import com.google.firebase.database.DatabaseReference;import com.google.firebase.database.FirebaseDatabase;import com.google.firebase.database.ValueEventListener;import java.util.ArrayList;import java.util.Collection;import java.util.Collections;import java.util.List;public class khoHang_Activity extends AppCompatActivity {    private static boolean xetList;    private Spinner            KhoHang_spn_chonLoai;    private RecyclerView            KhoHang_rscV_showThongTinDanhSachSanPham;    private LinearLayout            KhoHang_llout_btn_backItem,            KhoHang_llout_showPhanLoai,            KhoHang_llout_btn_showPhanLoai;    private ImageView            ThemSuaXoaSanPham_img_showImgV,            TrenXuong,            DuoiLen,            GiaCaoDenThap,            GiaThapLenCao,            DeleteSort;    private ImageView            XuLi_txt_Button_Sua;    private FloatingActionButton KhoHang_fl_btn_showThemSanPham;    adapter_SanPham_Kho Adapter_SanPham_Kho;    //Firebase    private DatabaseReference dataSanPhamRef;    private FirebaseDatabase dataSanPham;    //    Model    ArrayList<model_SanPham> arrListSanPham;    model_SanPham arrSanPham;    private List<String> listLSP;    boolean check = false;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_kho_hang);        anhXa();        getDataFirebase();        batSuKien();        setSpinner();    }    //  Trung Kiểm tra loại sản phẩm    private ArrayList<model_SanPham> kiemTraLoaiSanPham(String loaiSP, ArrayList<model_SanPham> arrListSP) {        ArrayList<model_SanPham> arrListLoaiSanPham = new ArrayList<>();        for (int i = 0; i < arrListSP.size(); i = i + 1) {            if (loaiSP.equals(arrListSP.get(i).getLoaiSanPham())) {                arrListLoaiSanPham.add(arrListSP.get(i));            }        }        return arrListLoaiSanPham;    }    // tìm kiếm theo loại spinner    private void timKiemTheoLoai() {        KhoHang_spn_chonLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {            @Override            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {                String loaiSanPham = KhoHang_spn_chonLoai.getSelectedItem().toString();                Toast.makeText(khoHang_Activity.this, loaiSanPham + "", Toast.LENGTH_SHORT).show();                if (!loaiSanPham.equals("All")) {                    showListProduc_Vartical(kiemTraLoaiSanPham(loaiSanPham, arrListSanPham));                } else {                    showListProduc_Vartical(arrListSanPham);                }            }            @Override            public void onNothingSelected(AdapterView<?> adapterView) {            }        });//        .setOnItemClickListener(new AdapterView.OnItemClickListener() {//            @Override//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {//                Toast.makeText(khoHang_Activity.this, i+"", Toast.LENGTH_SHORT).show();//            }//        });    }    private void batSuKien() {        timKiemTheoLoai();//        show phân loại        KhoHang_llout_btn_showPhanLoai.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {//set kích thước ẩn hiện                ViewGroup.LayoutParams params = KhoHang_llout_showPhanLoai.getLayoutParams();                if (check == true) {                    params.height = 0;                    params.width = LinearLayout.LayoutParams.MATCH_PARENT;                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_bottom);                    KhoHang_llout_showPhanLoai.setAnimation(animation);                    KhoHang_llout_showPhanLoai.setLayoutParams(params);                    check = false;                } else {                    params.height = LinearLayout.LayoutParams.MATCH_PARENT;                    params.width = LinearLayout.LayoutParams.MATCH_PARENT;                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom);                    KhoHang_llout_showPhanLoai.setAnimation(animation);                    KhoHang_llout_showPhanLoai.setLayoutParams(params);                    check = true;                }            }        });//        Trung Mở trang thêm sản phẩm        KhoHang_fl_btn_showThemSanPham.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                Intent intent = new Intent(khoHang_Activity.this, Them_San_Pham_Vao_Kho_Hang_Activity.class);                startActivity(intent);            }        });//    Trung thoát trang        KhoHang_llout_btn_backItem.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                Intent intent = new Intent(khoHang_Activity.this, thongTinTaiKhoan_Activity.class);                startActivity(intent);            }        });// cún bắt sự kiện sort        TrenXuong.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                Collections.sort(arrListSanPham, model_SanPham.sapXepTheoTenAZ);                Adapter_SanPham_Kho.notifyDataSetChanged();            }        });        DuoiLen.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                Collections.sort(arrListSanPham, model_SanPham.sapXepTheoTenZA);                Adapter_SanPham_Kho.notifyDataSetChanged();            }        });        GiaCaoDenThap.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                Collections.sort(arrListSanPham, model_SanPham.sapXepTheoGiaThapLenCao);                Adapter_SanPham_Kho.notifyDataSetChanged();            }        });        GiaThapLenCao.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                Collections.sort(arrListSanPham, model_SanPham.sapXepTheoGiaCaoxuongThap);                Adapter_SanPham_Kho.notifyDataSetChanged();            }        });        DeleteSort.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                showListProduc_Vartical(arrListSanPham);            }        });    }    // Trung: hiện tùy chọn loại spinner    private void setSpinner() {//        set giá trị loại sản phẩm spinner        listLSP = new ArrayList<>();        listLSP.add("All");        listLSP.add("Trà túi lọc");        listLSP.add("Cà phê");        listLSP.add("Trái cây xấy dẻo");        listLSP.add("Hạt đặc sản");        listLSP.add("Thảo mộc");        ArrayAdapter loaiSanPhamSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listLSP);        KhoHang_spn_chonLoai.setAdapter(loaiSanPhamSpinnerAdapter);    }    public void setXoaItem_gioHang(String idSanPham, int viTri) {        final Dialog dialog = new Dialog(this);        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);        dialog.setContentView(R.layout.dialog_cuttom_xoasanpham);        Window window = dialog.getWindow();        if (window == null) {            return;        }        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));        WindowManager.LayoutParams windowAttributes = window.getAttributes();        windowAttributes.gravity = Gravity.CENTER;        window.setAttributes(windowAttributes);//        sử lý khi nhấn ra ngoài thì có thoát hay không        dialog.setCancelable(false);//        khai báo bắt sự kiện        TextView Dialog_them_tvBtn_khong = dialog.findViewById(R.id.dialog_them_tvBtn_khong);        TextView Dialog_them_tvBtn_dongY = dialog.findViewById(R.id.dialog_them_tvBtn_dongY);//        tắt dialog đi        Dialog_them_tvBtn_khong.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                dialog.dismiss();            }        });        Dialog_them_tvBtn_dongY.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                dialog.dismiss();                dataSanPhamRef = dataSanPham.getReference().child("khoHang");                dataSanPhamRef.child(idSanPham).removeValue();                Adapter_SanPham_Kho.notifyItemChanged(viTri);            }        });        dialog.show();    }    //Trung: lấy dữ liệu sản phẩm trên firebase về    private void getDataFirebase() {        dataSanPhamRef = dataSanPham.getReference().child("khoHang");        dataSanPhamRef.addValueEventListener(new ValueEventListener() {            @Override            public void onDataChange(@NonNull DataSnapshot snapshot) {                arrListSanPham.clear();//                arrListSanPham.clear();//                Chạy vòng lặp kiểm tra dữ liệu                for (DataSnapshot child : snapshot.getChildren()) {                    arrSanPham = child.getValue(model_SanPham.class);                    arrListSanPham.add(arrSanPham);                }                Toast.makeText(khoHang_Activity.this, arrListSanPham.size() + "", Toast.LENGTH_SHORT).show();                showListProduc_Vartical(arrListSanPham);            }            @Override            public void onCancelled(@NonNull DatabaseError error) {            }        });    }    /********************Show thông tin ra kiểu dọc**********************/    private void showListProduc_Vartical(ArrayList<model_SanPham> arrListSp) {        KhoHang_rscV_showThongTinDanhSachSanPham.setLayoutManager(new GridLayoutManager(this, 1));        KhoHang_rscV_showThongTinDanhSachSanPham.setItemAnimator(new DefaultItemAnimator());        //        Initilize        Adapter_SanPham_Kho = new adapter_SanPham_Kho(arrListSp, khoHang_Activity.this, new adapter_SanPham_Kho.IClickLinstenr() {            @Override            public void onClickCHinhSuaItem(model_SanPham sanPham) {                onClickCHinhSuaSanPham(sanPham);            }        });        KhoHang_rscV_showThongTinDanhSachSanPham.setAdapter(Adapter_SanPham_Kho);    }    private void onClickCHinhSuaSanPham(model_SanPham sanPham) {// gửi dữ liệu quá trang trỉnh sửa sản phẩm        Intent intent = new Intent(khoHang_Activity.this, Them_San_Pham_Vao_Kho_Hang_Activity.class);        Bundle bundle = new Bundle();        bundle.putString("bd_idSanPham", sanPham.getIdSanPham());        bundle.putString("bd_moTaSanPham", sanPham.getMoTaSanPham());        bundle.putString("bd_tenSanPham", sanPham.getTenSanPham());        bundle.putString("bd_ngaySanXuatSanPham", sanPham.getNgaySanXuatSanPham());        bundle.putString("bd_xuatXuSanPham", sanPham.getXuatXuSanPham());        bundle.putString("bd_loaiSanPham", sanPham.getLoaiSanPham());        bundle.putString("bd_tinhTrangSanPham", sanPham.getTinhTrangSanPham());        bundle.putString("bd_anhSanPham", sanPham.getAnhSanPham());        bundle.putString("bd_ngayNhapSanPham", sanPham.getNgayNhapSanPham());        bundle.putInt("bd_soLuongSanPham", sanPham.getSoLuongSanPham());        bundle.putInt("bd_giamGiaSanPham", sanPham.getGiamGiaSanPham());        bundle.putDouble("bd_giaNhapSanPham", sanPham.getGiaNhapSanPham());        bundle.putDouble("bd_giaBanSanPham", sanPham.getGiaBanSanPham());        intent.putExtras(bundle);        startActivity(intent);    }    private void anhXa() {//View        //        model        arrListSanPham = new ArrayList<model_SanPham>();        //Firebase        dataSanPham = FirebaseDatabase.getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/");//        Spinner        KhoHang_spn_chonLoai = findViewById(R.id.khoHang_spn_chonLoai);//       RecyclerView        KhoHang_rscV_showThongTinDanhSachSanPham = findViewById(R.id.khoHang_rscV_showThongTinDanhSachSanPham);//ImageView        TrenXuong = findViewById(R.id.trenXuong);        DuoiLen = findViewById(R.id.duoiLen);        GiaCaoDenThap = findViewById(R.id.giaCaoDenThap);        GiaThapLenCao = findViewById(R.id.giaThapLenCao);        DeleteSort = findViewById(R.id.deleteSort);//        FloatingActionButton        KhoHang_fl_btn_showThemSanPham = findViewById(R.id.khoHang_fl_btn_showThemSanPham);//        LinearLayout        KhoHang_llout_btn_backItem = findViewById(R.id.khoHang_llout_btn_backItem);        KhoHang_llout_showPhanLoai = findViewById(R.id.khoHang_llout_showPhanLoai);        KhoHang_llout_btn_showPhanLoai = findViewById(R.id.khoHang_llout_btn_showPhanLoai);    }}