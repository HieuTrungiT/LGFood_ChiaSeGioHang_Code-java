package com.example.lgfood_duan1.Activity;import androidx.appcompat.app.AppCompatActivity;import androidx.constraintlayout.widget.ConstraintLayout;import android.os.Bundle;import android.widget.ImageView;import android.widget.TextView;import com.example.lgfood_duan1.R;import de.hdodenhof.circleimageview.CircleImageView;public class thongTinTaiKhoan_Activity extends AppCompatActivity {    private TextView            ThongTinTaiKhoan_tv_tenTaiKhoan;    private ImageView            ThongTinTaiKhoan_img_btn_thoat,            ThongTinTaiKhoan_img_btn_chonAnh,            ThongTinTaiKhoan_img_btn_showThongTinTaiKhoan,            ThongTinTaiKhoan_img_btn_showThongKe,            ThongTinTaiKhoan_img_btn_showCaiDat;    private ConstraintLayout            ThongTinTaiKhoan_ctlou_btn_showThongTinTaiKhoan,            ThongTinTaiKhoan_ctlou_btn_showThongKe,            ThongTinTaiKhoan_ctlou_btn_showCaiDat;    private CircleImageView            ThongTinTaiKhoan_Crimg_avatar;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_thong_tin_tai_khoan);        anhXa();    }    private void anhXa() {//    CircleImageView        ThongTinTaiKhoan_Crimg_avatar.findViewById(R.id.thongTinTaiKhoan_Crimg_avatar);//    TextView        ThongTinTaiKhoan_tv_tenTaiKhoan.findViewById(R.id.thongTinTaiKhoan_tv_tenTaiKhoan);//    ImageView        ThongTinTaiKhoan_img_btn_thoat.findViewById(R.id.thongTinTaiKhoan_img_btn_thoat);        ThongTinTaiKhoan_img_btn_chonAnh.findViewById(R.id.thongTinTaiKhoan_img_btn_chonAnh);        ThongTinTaiKhoan_ctlou_btn_showThongTinTaiKhoan.findViewById(R.id.thongTinTaiKhoan_ctlou_btn_showThongTinTaiKhoan);        ThongTinTaiKhoan_ctlou_btn_showThongKe.findViewById(R.id.thongTinTaiKhoan_ctlou_btn_showThongKe);        ThongTinTaiKhoan_ctlou_btn_showCaiDat.findViewById(R.id.thongTinTaiKhoan_ctlou_btn_showCaiDat);    }}