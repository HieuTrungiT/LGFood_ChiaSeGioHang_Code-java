package com.example.lgfood_duan1.Activity;import androidx.annotation.NonNull;import androidx.appcompat.app.AppCompatActivity;import androidx.recyclerview.widget.DefaultItemAnimator;import androidx.recyclerview.widget.GridLayoutManager;import androidx.recyclerview.widget.RecyclerView;import android.app.Dialog;import android.content.Intent;import android.graphics.Color;import android.graphics.drawable.ColorDrawable;import android.os.Bundle;import android.view.Gravity;import android.view.View;import android.view.ViewGroup;import android.view.Window;import android.view.WindowManager;import android.widget.EditText;import android.widget.ImageView;import android.widget.LinearLayout;import android.widget.Spinner;import android.widget.TextView;import android.widget.Toast;import com.example.lgfood_duan1.Model.adapter_SanPham_Kho;import com.example.lgfood_duan1.Model.model_SanPham;import com.example.lgfood_duan1.R;import com.google.firebase.database.DataSnapshot;import com.google.firebase.database.DatabaseError;import com.google.firebase.database.DatabaseReference;import com.google.firebase.database.FirebaseDatabase;import com.google.firebase.database.ValueEventListener;import java.util.ArrayList;public class khoHang_Activity extends AppCompatActivity {    private static boolean xetList;    private Spinner            KhoHang_spn_chonLoai;    private RecyclerView            KhoHang_rscV_showThongTinDanhSachSanPham;    private LinearLayout            KhoHang_llout_btn_backItem;    private EditText            ThemSuaXoaSanPham_edt_moTaSanPham,            ThemSuaXoaSanPham_edt_tenSanPham,            ThemSuaXoaSanPham_edt_giaNhap,            ThemSuaXoaSanPham_edt_giaBan,            ThemSuaXoaSanPham_edt_giamGia,            ThemSuaXoaSanPham_edt_soLuong,            ThemSuaXoaSanPham_edt_NSX,            ThemSuaXoaSanPham_edt_xuatSu;    private ImageView            ThemSuaXoaSanPham_img_showImgV;    private ImageView            XuLi_txt_Button_Sua;    private Spinner            ThemSuaXoaSanPham_spn_LoaiSP;    adapter_SanPham_Kho  Adapter_SanPham_Kho;    //Firebase    private DatabaseReference dataSanPhamRef;    private FirebaseDatabase dataSanPham;    //    Model    ArrayList<model_SanPham> arrListSanPham;    model_SanPham arrSanPham;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_kho_hang);        anhXa();        getDataFirebase();            }    public void setXoaItem_gioHang(String idSanPham, int viTri) {        final Dialog dialog = new Dialog(this);        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);        dialog.setContentView(R.layout.dialog_cuttom_xoasanpham);        Window window = dialog.getWindow();        if (window == null) {            return;        }        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));        WindowManager.LayoutParams windowAttributes = window.getAttributes();        windowAttributes.gravity = Gravity.CENTER;        window.setAttributes(windowAttributes);//        sử lý khi nhấn ra ngoài thì có thoát hay không        dialog.setCancelable(false);//        khai báo bắt sự kiện        TextView Dialog_them_tvBtn_khong = dialog.findViewById(R.id.dialog_them_tvBtn_khong);        TextView Dialog_them_tvBtn_dongY = dialog.findViewById(R.id.dialog_them_tvBtn_dongY);//        tắt dialog đi        Dialog_them_tvBtn_khong.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                dialog.dismiss();            }        });        Dialog_them_tvBtn_dongY.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                dialog.dismiss();                dataSanPhamRef = dataSanPham.getReference().child("khoHang");                dataSanPhamRef.child(idSanPham).removeValue();                Adapter_SanPham_Kho.notifyItemChanged(viTri);            }        });        dialog.show();    }        //Trung: lấy dữ liệu sản phẩm trên firebase về    private void getDataFirebase(){        dataSanPhamRef = dataSanPham.getReference().child("khoHang");        dataSanPhamRef.addValueEventListener(new ValueEventListener() {            @Override            public void onDataChange(@NonNull  DataSnapshot snapshot) {                arrListSanPham.clear();//                arrListSanPham.clear();//                Chạy vòng lặp kiểm tra dữ liệu                for (DataSnapshot child : snapshot.getChildren()) {                    arrSanPham = child.getValue(model_SanPham.class);                    arrListSanPham.add(arrSanPham);                }                Toast.makeText(khoHang_Activity.this, arrListSanPham.size() + "", Toast.LENGTH_SHORT).show();                showListProduc_Vartical(arrListSanPham);            }            @Override            public void onCancelled(@NonNull  DatabaseError error) {            }        });    }    public void setShowItem_gioHang(String idSanPham, String anhSanPham, String tenSanPham,                                    String giaSanPham, String giaNhapSanPham, String                                            soLuongSanPham ,String xuatXu, String ngayNhap, String giamGiaSanPham ,  String loaiSanPham) {        Intent intent = new Intent(khoHang_Activity.this, khoHang_Activity.class);        Bundle bundle = new Bundle();        bundle.putString("key_idSanPham", idSanPham);        bundle.putString("key_imgSanPham", anhSanPham);        bundle.putString("key_tenSanPham", tenSanPham);        bundle.putString("key_giaSanPham", giaNhapSanPham);        bundle.putString("key_giaSanPham", giaSanPham);        bundle.putString("key_giaSanPham", soLuongSanPham);        bundle.putString("key_giaSanPham", giamGiaSanPham);        bundle.putString("key_xuatXuSanPham", xuatXu);        bundle.putString("key_ngayNhapSanPham", ngayNhap);        bundle.putString("key_loaiSanPham", loaiSanPham);        intent.putExtras(bundle);        startActivity(intent);    }    /********************Show thông tin ra kiểu dọc**********************/    private void showListProduc_Vartical(ArrayList<model_SanPham> arrListSp) {        KhoHang_rscV_showThongTinDanhSachSanPham.setLayoutManager(new GridLayoutManager(this, 1));        KhoHang_rscV_showThongTinDanhSachSanPham.setItemAnimator(new DefaultItemAnimator());        //        Initilize//        Adapter_SanPham_Kho = new adapter_SanPham_Kho(arrListSp, khoHang_Activity.this,new adapter_SanPham_Kho.IClickLinstenr());Adapter_SanPham_Kho = new adapter_SanPham_Kho(arrListSp, khoHang_Activity.this, new adapter_SanPham_Kho.IClickLinstenr() {    @Override    public void onClickCHinhSuaItem(model_SanPham sanPham) {        onClickCHinhSuaSanPham(sanPham);    }})    ;        KhoHang_rscV_showThongTinDanhSachSanPham.setAdapter(Adapter_SanPham_Kho);    }    private void onClickCHinhSuaSanPham(model_SanPham sanPham) {        final Dialog dialog = new Dialog(this);        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);        dialog.setContentView(R.layout.activity_them_san_pham_vao_kho_hang);        ThemSuaXoaSanPham_img_showImgV = dialog.findViewById(R.id.themSuaXoaSanPham_img_showImgV);        ThemSuaXoaSanPham_edt_tenSanPham = dialog.findViewById(R.id.themSuaXoaSanPham_edt_tenSanPham);        ThemSuaXoaSanPham_edt_giaNhap = dialog.findViewById(R.id.themSuaXoaSanPham_edt_giaNhap);        ThemSuaXoaSanPham_edt_giaBan = dialog.findViewById(R.id.themSuaXoaSanPham_edt_giaBan);        ThemSuaXoaSanPham_edt_giamGia = dialog.findViewById(R.id.themSuaXoaSanPham_edt_giamGia);        ThemSuaXoaSanPham_edt_soLuong = dialog.findViewById(R.id.themSuaXoaSanPham_edt_soLuong);        ThemSuaXoaSanPham_edt_NSX = dialog.findViewById(R.id.themSuaXoaSanPham_edt_NSX);        ThemSuaXoaSanPham_edt_xuatSu = dialog.findViewById(R.id.themSuaXoaSanPham_edt_xuatSu);        ThemSuaXoaSanPham_spn_LoaiSP = dialog.findViewById(R.id.themSuaXoaSanPham_spn_LoaiSP);        ThemSuaXoaSanPham_edt_moTaSanPham= dialog.findViewById(R.id.themSuaXoaSanPham_edt_moTaSanPham);//        set text//        ThemSuaXoaSanPham_img_showImgV.setImageResource(sanPham.getAnhSanPham());        ThemSuaXoaSanPham_edt_tenSanPham.setText(sanPham.getTenSanPham());        ThemSuaXoaSanPham_edt_giaNhap.setText(sanPham.getGiaNhapSanPham() + "00");        ThemSuaXoaSanPham_edt_giaBan.setText(sanPham.getGiaBanSanPham() + "00");        ThemSuaXoaSanPham_edt_giamGia.setText(sanPham.getGiamGiaSanPham() + "00");        ThemSuaXoaSanPham_edt_soLuong.setText(sanPham.getSoLuongSanPham() + "00");        ThemSuaXoaSanPham_edt_NSX.setText(sanPham.getNgaySanXuatSanPham());        ThemSuaXoaSanPham_edt_xuatSu.setText(sanPham.getXuatXuSanPham());        ThemSuaXoaSanPham_edt_moTaSanPham.setText(sanPham.getMoTaSanPham());//       ThemSuaXoaSanPham_spn_LoaiSP.setTag(sanPham.getLoaiSanPham());        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;        dialog.getWindow().setGravity(Gravity.BOTTOM);        dialog.show();    }    private void anhXa() {        //        model        arrListSanPham = new ArrayList<model_SanPham>();        //Firebase        dataSanPham = FirebaseDatabase.getInstance("https://duan1lgfood-default-rtdb.asia-southeast1.firebasedatabase.app/");//        Spinner        KhoHang_spn_chonLoai = findViewById(R.id.khoHang_spn_chonLoai);//       RecyclerView        KhoHang_rscV_showThongTinDanhSachSanPham = findViewById(R.id.khoHang_rscV_showThongTinDanhSachSanPham);//ImageView//        LinearLayout         KhoHang_llout_btn_backItem = findViewById(R.id.khoHang_llout_btn_backItem);    }}