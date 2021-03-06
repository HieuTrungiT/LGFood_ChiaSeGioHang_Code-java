package com.example.lgfood_duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;

import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lgfood_duan1.Model.model_SanPham;
import com.example.lgfood_duan1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Them_San_Pham_Vao_Kho_Hang_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private ImageView
            ThemSuaXoaSanPham_img_showImgV,
            ThemSuaXoaSanPham_img_btn_back,
            ThemSuaXoaSanPham_img_btn_lich;

    private TextView
            ThemSuaXoaSanPham_tv_btn_moThuMucAnh;


    private LinearLayout
            ThemSuaXoaSanPham_llout_btn_themSanPham,
            ThemSuaXoaSanPham_llout_btn_xoaSanPham,
            ThemSuaXoaSanPham_llout_btn_suaSanPham;

    private EditText
            ThemSuaXoaSanPham_edt_moTaSanPham,
            ThemSuaXoaSanPham_edt_tenSanPham,
            ThemSuaXoaSanPham_edt_giaNhap,
            ThemSuaXoaSanPham_edt_giaBan,
            ThemSuaXoaSanPham_edt_giamGia,
            ThemSuaXoaSanPham_edt_soLuong,
            ThemSuaXoaSanPham_edt_NSX,
            ThemSuaXoaSanPham_edt_xuatSu;
    private Spinner
            ThemSuaXoaSanPham_spn_LoaiSP,
            ThemSuaXoaSanPham_spn_TinhTrang;
    private Spinner spinner;
    private List<String> listLSP, listTTSP;
    // Date
    private Date date;
    private DateFormat dateFormat;

    String moTaSanPham,
            tenSanPham,
            ngaySanXuatSanPham,
            xuatXuSanPham,
            loaiSanPham,
            tinhTrangSanPham;

    int soLuongSanPham = 0, giamGiaSanPham = 0;
    double giaNhapSanPham,
            giaBanSanPham;

    //    Firebase
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseStorage storage;
    private UploadTask uploadTask;
    private StorageReference mountainImagesRef, storageRef;
    private UUID uuid;

    //    Model
    private model_SanPham listSanPham;

    //    Img
    private static final int REQUEST_IMAGE_OPEN = 123;
    Uri full;
    String idSanPhamit, anhSanPhamit, tenSanPhamit, giaSanPhamit, xuatXuit, ngayNhapit;
    int
            loaiSanPhamit,
            BdSoLuongSanPham,
            BdGiamGiaSanPham;
    double BdGiaNhapSanPham, BdGiaBanSanPham;
    String BdIdsanPham, BdMoTaSanPham, BdTenSanPham, BdNgaySanXuatSanPham, BdXuatXuSanPham, BdLoaiSanPham, BdTinhTrangSanPham, BdAnhSanPham, BdNgayNhapSanPham;
    Dialog dialog;
    Dialog dialogLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham_vao_kho_hang);

        anhXa();
        batSuKien();
        setSpinner();
        nhanDuLieuIntent();
//        Quay l???i trang tr?????c

        ThemSuaXoaSanPham_img_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Them_San_Pham_Vao_Kho_Hang_Activity.this, khoHang_Activity.class);
                startActivity(intent);
            }
        });
    }

    //    Trung nh???n d??? li???u
    private void nhanDuLieuIntent() {

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            ThemSuaXoaSanPham_llout_btn_themSanPham.setVisibility(View.INVISIBLE);
            ThemSuaXoaSanPham_llout_btn_suaSanPham.setVisibility(View.VISIBLE);
            ThemSuaXoaSanPham_llout_btn_xoaSanPham.setVisibility(View.VISIBLE);
            BdIdsanPham = bundle.getString("bd_idSanPham", "");
            BdMoTaSanPham = bundle.getString("bd_moTaSanPham", "");
            BdTenSanPham = bundle.getString("bd_tenSanPham", "");
            BdNgaySanXuatSanPham = bundle.getString("bd_ngaySanXuatSanPham", "");
            BdXuatXuSanPham = bundle.getString("bd_xuatXuSanPham", "");
            BdLoaiSanPham = bundle.getString("bd_loaiSanPham", "");
            BdTinhTrangSanPham = bundle.getString("bd_tinhTrangSanPham", "");
            BdAnhSanPham = bundle.getString("bd_anhSanPham", "");
            BdNgayNhapSanPham = bundle.getString("bd_ngayNhapSanPham", "");
            BdSoLuongSanPham = bundle.getInt("bd_soLuongSanPham", 0);
            BdGiamGiaSanPham = bundle.getInt("bd_giamGiaSanPham", 0);
            BdGiaNhapSanPham = bundle.getDouble("bd_giaNhapSanPham", 0);
            BdGiaBanSanPham = bundle.getDouble("bd_giaBanSanPham", 0);

//            gi??n g??i tr???
            ThemSuaXoaSanPham_edt_tenSanPham.setText(BdIdsanPham);
            Glide.with(Them_San_Pham_Vao_Kho_Hang_Activity.this)
                    .load(BdAnhSanPham)
                    .into(ThemSuaXoaSanPham_img_showImgV);
            ThemSuaXoaSanPham_edt_tenSanPham.setText(BdTenSanPham);
            ThemSuaXoaSanPham_edt_giaNhap.setText(BdGiaNhapSanPham + "00");
            ThemSuaXoaSanPham_edt_giaBan.setText(BdGiaBanSanPham + "00");
            ThemSuaXoaSanPham_edt_giamGia.setText(BdGiamGiaSanPham + "");
            ThemSuaXoaSanPham_edt_soLuong.setText(BdSoLuongSanPham + "");
            ThemSuaXoaSanPham_edt_NSX.setText(BdNgaySanXuatSanPham);
            ThemSuaXoaSanPham_edt_xuatSu.setText(BdXuatXuSanPham);
            ThemSuaXoaSanPham_edt_moTaSanPham.setText(BdMoTaSanPham);

            listLSP = new ArrayList<>();
            listLSP.add(BdLoaiSanPham);
            listLSP.add("Tr?? t??i l???c");
            listLSP.add("C?? ph??");
            listLSP.add("Tr??i c??y x???y d???o");
            listLSP.add("H???t ?????c s???n");
            listLSP.add("Th???o m???c");
            for (int i = 0; i < listLSP.size(); i = i + 1) {
                if (BdLoaiSanPham.equals(listLSP.get(i))) {
                    listLSP.remove(i);
                }
            }
            ArrayAdapter loaiSanPhamSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listLSP);
            ThemSuaXoaSanPham_spn_LoaiSP.setAdapter(loaiSanPhamSpinnerAdapter);
            listTTSP = new ArrayList<>();
            listTTSP.add(BdTinhTrangSanPham);
            listTTSP.add("0");
            listTTSP.add("1");
            listTTSP.add("2");
            for (int i = 0; i < listTTSP.size(); i = i + 1) {
                if (BdTinhTrangSanPham.equals(listTTSP.get(i))) {
                    listTTSP.remove(i);
                }
            }
            ArrayAdapter tinhTrangSanPhamSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listTTSP);
            ThemSuaXoaSanPham_spn_TinhTrang.setAdapter(tinhTrangSanPhamSpinnerAdapter);

        } else {
            ThemSuaXoaSanPham_llout_btn_themSanPham.setVisibility(View.VISIBLE);
        }
    }

    //Trungs???a s???n ph???m
    private void suaSanPhamKho() {
        getValue();
        if (!checkValidate()) {
            return;
        }
        myRef = database.getReference("khoHang");

        myRef.child(BdIdsanPham).child("moTaSanPham").setValue(moTaSanPham);
        myRef.child(BdIdsanPham).child("tenSanPham").setValue(tenSanPham);
        myRef.child(BdIdsanPham).child("xuatXuSanPham").setValue(xuatXuSanPham);
        myRef.child(BdIdsanPham).child("loaiSanPham").setValue(loaiSanPham);
        myRef.child(BdIdsanPham).child("tinhTrangSanPham").setValue(tinhTrangSanPham);
//        myRef.child(idSanPhamit).child("anhSanPham").setValue();

        myRef.child(BdIdsanPham).child("soLuongSanPham").setValue(soLuongSanPham);
        myRef.child(BdIdsanPham).child("giamGiaSanPham").setValue(giamGiaSanPham);
        myRef.child(BdIdsanPham).child("giaNhapSanPham").setValue(giaNhapSanPham);
        myRef.child(BdIdsanPham).child("giaBanSanPham").setValue(giaBanSanPham);

        Intent intent = new Intent(Them_San_Pham_Vao_Kho_Hang_Activity.this, khoHang_Activity.class);
        startActivity(intent);

    }

    //Trung dialog s???a s???n ph???m
    private void openDialogUpdate() {
        ImageView item_dialog_chucNang_img_imgErro=dialog.findViewById(R.id.item_dialog_chucNang_img_imgErro);
        TextView item_dialog_chucNang_txt_nameErro=dialog.findViewById(R.id.item_dialog_chucNang_txt_nameErro);
        Button Okay = dialog.findViewById(R.id.btn_okay);
        Button Cancel = dialog.findViewById(R.id.btn_cancel);
        //setText Item
        Okay.setText("?????ng ??");
        item_dialog_chucNang_img_imgErro.setImageResource(R.drawable.question);
        item_dialog_chucNang_txt_nameErro.setText("B???n c?? mu???n s???a s???n ph???m n??y kh??ng?");
        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!(full == null)) {
//                    n???u nh?? c?? ???nh ???????c ch???n
                            luuSanPham_AnhUptate();
                            finish();
                        } else {
//                    n???u nh?? kh??ng c?? ???nh ???????c ch???n
                            suaSanPhamKho();
                            finish();
                        }
                        dialogLoading.dismiss();
                    }
                },1000);
                dialogLoading.show();
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

    // Trung c???p nh???t s???n ph???m khi c?? ???nh
    private void luuSanPham_AnhUptate() {
        getValue();
        if (checkValidate() == true) {
            storageRef = storage.getReference();
            mountainImagesRef = storageRef.child("images/sanPham/" + new Date().getTime() + ".jpg");
            uploadTask = mountainImagesRef.putFile(full);
            //  ki???m tra
            //  n???u l???i file ch???y v??o ????y
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(Them_San_Pham_Vao_Kho_Hang_Activity.this, "L??u ???nh th???t b???i", Toast.LENGTH_SHORT).show();
                }
                //     add file th??nh c??ng
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    //    kh???i t???o link url
                    mountainImagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            // datetime hi???n t???i


                            myRef = database.getReference("khoHang");

                            myRef.child(BdIdsanPham).child("moTaSanPham").setValue(moTaSanPham);
                            myRef.child(BdIdsanPham).child("tenSanPham").setValue(tenSanPham);
                            myRef.child(BdIdsanPham).child("xuatXuSanPham").setValue(xuatXuSanPham);
                            myRef.child(BdIdsanPham).child("loaiSanPham").setValue(loaiSanPham);
                            myRef.child(BdIdsanPham).child("tinhTrangSanPham").setValue(tinhTrangSanPham);
                            myRef.child(BdIdsanPham).child("anhSanPham").setValue(uri.toString());

                            myRef.child(BdIdsanPham).child("soLuongSanPham").setValue(soLuongSanPham);
                            myRef.child(BdIdsanPham).child("giamGiaSanPham").setValue(giamGiaSanPham);
                            myRef.child(BdIdsanPham).child("giaNhapSanPham").setValue(giaNhapSanPham);
                            myRef.child(BdIdsanPham).child("giaBanSanPham").setValue(giaBanSanPham);

                            Intent intent = new Intent(Them_San_Pham_Vao_Kho_Hang_Activity.this, khoHang_Activity.class);
                            startActivity(intent);

                        }
                    });
                }
            });
        }
    }

    //**** x??a s???n ph???m
    private void xoaSanPhamKho() {
        myRef = database.getReference("khoHang");
        Toast.makeText(this, BdIdsanPham+"", Toast.LENGTH_SHORT).show();
        myRef.child(BdIdsanPham).removeValue();
        Intent intent = new Intent(Them_San_Pham_Vao_Kho_Hang_Activity.this, khoHang_Activity.class);
        startActivity(intent);
    }


    private void openDialogDelete() {
        ImageView item_dialog_chucNang_img_imgErro=dialog.findViewById(R.id.item_dialog_chucNang_img_imgErro);
        TextView item_dialog_chucNang_txt_nameErro=dialog.findViewById(R.id.item_dialog_chucNang_txt_nameErro);
        Button Okay = dialog.findViewById(R.id.btn_okay);
        Button Cancel = dialog.findViewById(R.id.btn_cancel);
        //setText Item
        Okay.setText("?????ng ??");
        item_dialog_chucNang_img_imgErro.setImageResource(R.drawable.question);
        item_dialog_chucNang_txt_nameErro.setText("B???n c?? mu???n x??a s???n ph???m n??y kh??ng?");
        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xoaSanPhamKho();
                        finish();
                        dialogLoading.dismiss();
                    }
                },1000);
                dialogLoading.show();
                dialog.dismiss();
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

    // Trung: m??? th?? vi???n ???nh
    private void moFileThuMucAnh() {
        anhXa();
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_IMAGE_OPEN);
    }

    // Trung l???y ???nh trong th?? vi???n v?? show anh l??n imgview
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //  b???t s??? ki???n khi nh???n l??u ???nh th??? g??n v??o imgview
        if (requestCode == REQUEST_IMAGE_OPEN && resultCode == RESULT_OK && data != null && data.getData() != null) {
            full = data.getData();
            ThemSuaXoaSanPham_img_showImgV.setImageURI(full);
            ThemSuaXoaSanPham_img_showImgV.setVisibility(View.VISIBLE);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    //    Trung: l??u s???n ph???m l??n firebase
    private void themDuLieuVaoFirebase() {
        getValue();
        if (checkValidate() == true) {
            storageRef = storage.getReference();
            mountainImagesRef = storageRef.child("images/sanPham/" + new Date().getTime() + ".jpg");
            uploadTask = mountainImagesRef.putFile(full);

            //  ki???m tra
            //  n???u l???i file ch???y v??o ????y
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(Them_San_Pham_Vao_Kho_Hang_Activity.this, "L??u ???nh th???t b???i", Toast.LENGTH_SHORT).show();
                }
                //     add file th??nh c??ng
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    //    kh???i t???o link url
                    mountainImagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            // datetime hi???n t???i
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'l??c' HH:mm:ss z");
                            Date reaDate = new Date(System.currentTimeMillis());
//                        //      myRef ch??? con tr??? t???i v??? tr?? ""
                            myRef = database.getReference("khoHang");
//
//                        //      ki???m tra ??i???u ki???n n???u nh?? c?? ???nh tr??n storage th?? l??u c?? ?????nh d???ng kh??ng th?? r???ng

                            listSanPham = new model_SanPham(uuid.toString(), moTaSanPham, tenSanPham, ngaySanXuatSanPham, xuatXuSanPham, loaiSanPham, tinhTrangSanPham, uri.toString(), formatter.format(reaDate).toString(), soLuongSanPham, giamGiaSanPham, giaNhapSanPham, giaBanSanPham);

//                        //        add gi?? tr???
//

                            ImageView item_dialog_chucNang_img_imgErro=dialog.findViewById(R.id.item_dialog_chucNang_img_imgErro);
                            TextView item_dialog_chucNang_txt_nameErro=dialog.findViewById(R.id.item_dialog_chucNang_txt_nameErro);
                            Button Okay = dialog.findViewById(R.id.btn_okay);
                            Button Cancel = dialog.findViewById(R.id.btn_cancel);
                            Okay.setText("?????ng ??");
                            item_dialog_chucNang_img_imgErro.setImageResource(R.drawable.question);
                            item_dialog_chucNang_txt_nameErro.setText("B???n c?? mu???n th??m s???n ph???m n??y kh??ng?");
                            Okay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Handler handler=new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            myRef.child(listSanPham.getIdSanPham().toString()).setValue(listSanPham);
                                            finish();
                                            dialogLoading.dismiss();
                                        }
                                    },1000);
                                    dialogLoading.show();
                                    dialog.dismiss();
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
                    });
                }
            });

        }
    }

    // Trung: Ki???u tra validate ?????u v??o
    private boolean checkValidate() {
//        ???nh s???n ph???m
        if (!(full == null) || !(BdAnhSanPham == null)) {
            ThemSuaXoaSanPham_img_showImgV.setBackgroundResource(R.drawable.broder_stroke_cam1);
//        t??n s???n ph???m
            if (!tenSanPham.equals("")) {
                setNullBackgourd(ThemSuaXoaSanPham_edt_tenSanPham);
                if (!(tenSanPham.length() < 5 || tenSanPham.length() > 50)) {
                    setNullBackgourd(ThemSuaXoaSanPham_edt_tenSanPham);
//                gi?? nh???p s???n ph???m
                    if (!(giaNhapSanPham < 1.000)) {
                        if (giaNhapSanPham == 1 || giaNhapSanPham == 2 || giaNhapSanPham == 3 || giaNhapSanPham == 4 || giaNhapSanPham == 5 || giaNhapSanPham == 6 || giaNhapSanPham == 7 || giaNhapSanPham == 8 || giaNhapSanPham == 8 || giaNhapSanPham == 9) {
                            ThemSuaXoaSanPham_edt_giaNhap.setText(giaNhapSanPham + "00");
                        }
                        ThemSuaXoaSanPham_edt_giaNhap.setText(giaNhapSanPham + "00");
                        setNullBackgourd(ThemSuaXoaSanPham_edt_giaNhap);
                        if (!(giaNhapSanPham >= 999.999)) {
//                        gi?? b??n
                            if (!(giaBanSanPham <= giaNhapSanPham)) {
                                if (!(giaBanSanPham < 1.000)) {
                                    if (giaBanSanPham == 1 || giaBanSanPham == 2 || giaBanSanPham == 3 || giaBanSanPham == 4 || giaBanSanPham == 5 || giaBanSanPham == 6 || giaBanSanPham == 7 || giaBanSanPham == 8 || giaBanSanPham == 8 || giaBanSanPham == 9) {
                                        ThemSuaXoaSanPham_edt_giaBan.setText(giaBanSanPham + "00");
                                    }
                                    ThemSuaXoaSanPham_edt_giaBan.setText(giaBanSanPham + "00");
                                    setNullBackgourd(ThemSuaXoaSanPham_edt_giaBan);
                                    if (!(giaBanSanPham >= 999.999)) {
                                        if (!(giamGiaSanPham > 90)) {
                                            setNullBackgourd(ThemSuaXoaSanPham_edt_giamGia);
                                            if (!(soLuongSanPham < 10)) {
                                                if (!(soLuongSanPham >= 10000)) {
                                                    setNullBackgourd(ThemSuaXoaSanPham_edt_soLuong);
                                                    if (!xuatXuSanPham.equals("")) {
                                                        setNullBackgourd(ThemSuaXoaSanPham_edt_xuatSu);
                                                        if (!loaiSanPham.equals("null")) {

                                                            ThemSuaXoaSanPham_spn_LoaiSP.setBackgroundResource(R.drawable.broder_stroke_black);
                                                            if (!moTaSanPham.equals("")) {

                                                                return true;
                                                            } else {
                                                                ThemSuaXoaSanPham_edt_moTaSanPham.setError("M?? t??? s???n ph???m kh??ng ???????c ????? tr???ng!!");
                                                                ThemSuaXoaSanPham_edt_moTaSanPham.requestFocus();
                                                            }
                                                        } else {
                                                            ThemSuaXoaSanPham_spn_LoaiSP.setBackgroundResource(R.drawable.broder_stroke_red_error);
                                                        }
                                                    } else {
                                                        showError(ThemSuaXoaSanPham_edt_xuatSu, "Kh??ng ???????c ????? tr???ng xu???t x??? s???n ph???m!!!");
                                                    }
                                                } else {
                                                    showError(ThemSuaXoaSanPham_edt_soLuong, "S??? l?????ng nh???p v??? kh??ng ???????c qu?? 10000SP");
                                                }
                                            } else {
                                                showError(ThemSuaXoaSanPham_edt_soLuong, "S??? l?????ng nh???p v??? kh??ng ???????c nh??? h??n 10SP");
                                            }
                                        } else {
                                            showError(ThemSuaXoaSanPham_edt_giamGia, "Gi???m gi?? kh??ng ???????c l???n h??n 90% gi?? tr???");
                                        }
                                    } else {
                                        showError(ThemSuaXoaSanPham_edt_giaBan, "Gi?? b??n s???n ph???m kh??ng ???????c l???n h??n 999.999??");
                                    }
                                } else {
                                    showError(ThemSuaXoaSanPham_edt_giaBan, "Gi?? b??n kh??ng ???????c b??? tr???ng ho???c nh??? h??n 1.000?? !!!");
                                }
                            } else {
                                showError(ThemSuaXoaSanPham_edt_giaBan, "Gi?? b??n kh??ng nh??? h??n ho???c b???ng gi?? nh???p !!!");
                            }
                        } else {
                            showError(ThemSuaXoaSanPham_edt_giaNhap, "Gi?? Nh???p s???n ph???m kh??ng ???????c l???n h??n 999.999??");
                        }
                    } else {
                        showError(ThemSuaXoaSanPham_edt_giaNhap, "Gi?? nh???p kh??ng ???????c b??? tr???ng ho???c nh??? h??n 1.000?? !!!");
                    }
                } else {
                    showError(ThemSuaXoaSanPham_edt_tenSanPham, "T??n s???n ph???m kh??ng ???????c < 5 v?? > 50 k?? t???!!!");
                }
            } else {
                showError(ThemSuaXoaSanPham_edt_tenSanPham, "T??n s???n ph???m kh??ng ???????c ????? tr???ng!!!");
            }
        } else {
            ThemSuaXoaSanPham_img_showImgV.setBackgroundResource(R.drawable.broder_stroke_red_error);
            Toast.makeText(this, "Ch??a c?? ???nh s???n ph???m!!!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    //    Trung: t???t l???i
    private void setNullBackgourd(EditText edt) {
        edt.setBackgroundResource(R.drawable.broder_stroke_black);
    }

    //    Trung: show l???i
    public void showError(EditText filed, String text) {
        filed.setError(text);
        filed.requestFocus();
        filed.setBackgroundResource(R.drawable.broder_stroke_red_error);
    }

    // Trung: hi???n dialog date
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    // Trung: hi???n t??y ch???n lo???i spinner
    private void setSpinner() {
//        set gi?? tr??? lo???i s???n ph???m spinner
        listLSP = new ArrayList<>();
        listLSP.add("null");
        listLSP.add("Tr?? t??i l???c");
        listLSP.add("C?? ph??");
        listLSP.add("Tr??i c??y x???y d???o");
        listLSP.add("H???t ?????c s???n");
        listLSP.add("Th???o m???c");
        ArrayAdapter loaiSanPhamSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listLSP);
        ThemSuaXoaSanPham_spn_LoaiSP.setAdapter(loaiSanPhamSpinnerAdapter);

//        set gi?? tr??? t??nh tr???ng lo???i s???n ph???m spinner
        listTTSP = new ArrayList<>();
        listTTSP.add("0");
        listTTSP.add("1");
        listTTSP.add("2");
        ArrayAdapter tinhTrangSanPhamSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listTTSP);
        ThemSuaXoaSanPham_spn_TinhTrang.setAdapter(tinhTrangSanPhamSpinnerAdapter);
    }


    private void batSuKien() {

// b???t s??? ki???n xoa
        ThemSuaXoaSanPham_llout_btn_xoaSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogDelete();
            }
        });

        ThemSuaXoaSanPham_llout_btn_suaSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogUpdate();
            }
        });
        //  back v??? login
        ThemSuaXoaSanPham_img_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // th??m s???n ph???m v??o firebase
        ThemSuaXoaSanPham_llout_btn_themSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themDuLieuVaoFirebase();
            }
        });

        // m??? dialog date
        ThemSuaXoaSanPham_img_btn_lich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

//        m??? th?? vi???n ???nh
        ThemSuaXoaSanPham_tv_btn_moThuMucAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moFileThuMucAnh();
            }
        });
    }

    //  Trung: l???y gi??  tr??? nh???p v??o
    private void getValue() {
//        random id
        uuid = UUID.randomUUID();
        moTaSanPham = ThemSuaXoaSanPham_edt_moTaSanPham.getText().toString();
        tenSanPham = ThemSuaXoaSanPham_edt_tenSanPham.getText().toString();
        try {
            giaNhapSanPham = Double.parseDouble(ThemSuaXoaSanPham_edt_giaNhap.getText().toString().trim());
            setNullBackgourd(ThemSuaXoaSanPham_edt_giaNhap);
        } catch (Exception e) {
            showError(ThemSuaXoaSanPham_edt_giaNhap, "Vui l??ng ki???m tra l???i ?????nh d???ng 000.000??");
            ThemSuaXoaSanPham_edt_giaNhap.setText("0");
        }
        try {
            giaBanSanPham = Double.parseDouble(ThemSuaXoaSanPham_edt_giaBan.getText().toString().trim());
            setNullBackgourd(ThemSuaXoaSanPham_edt_giaBan);
        } catch (Exception e) {
            showError(ThemSuaXoaSanPham_edt_giaBan, "Vui l??ng ki???m tra l???i ?????nh d???ng 000.000??");
            ThemSuaXoaSanPham_edt_giaBan.setText("0");
        }
        try {
            giamGiaSanPham = Integer.parseInt(ThemSuaXoaSanPham_edt_giamGia.getText().toString().trim());
            setNullBackgourd(ThemSuaXoaSanPham_edt_giamGia);
        } catch (Exception e) {
            showError(ThemSuaXoaSanPham_edt_giamGia, "kh??ng ???????c b??? tr???ng ");
            ThemSuaXoaSanPham_edt_giamGia.setText("0");
        }
        try {
            soLuongSanPham = Integer.parseInt(ThemSuaXoaSanPham_edt_soLuong.getText().toString().trim());
            setNullBackgourd(ThemSuaXoaSanPham_edt_soLuong);
        } catch (Exception e) {
            showError(ThemSuaXoaSanPham_edt_soLuong, "S??? l?????ng kh??ng ???????c b??? tr???ng!!!");
            ThemSuaXoaSanPham_edt_soLuong.setText("0");
        }
        ngaySanXuatSanPham = ThemSuaXoaSanPham_edt_NSX.getText().toString().trim();
        xuatXuSanPham = ThemSuaXoaSanPham_edt_xuatSu.getText().toString().trim();
        loaiSanPham = ThemSuaXoaSanPham_spn_LoaiSP.getSelectedItem().toString();
        tinhTrangSanPham = ThemSuaXoaSanPham_spn_TinhTrang.getSelectedItem().toString();
    }

    private void anhXa() {
        //thai: diaLog
        dialog=new Dialog(Them_San_Pham_Vao_Kho_Hang_Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_dialog_chucnang_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        dialogLoading = new Dialog(Them_San_Pham_Vao_Kho_Hang_Activity.this);
        dialogLoading.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogLoading.setContentView(R.layout.item_login);
        dialogLoading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //    Firebase
        database = FirebaseDatabase.getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        //    FirebaseStorage

        storage = FirebaseStorage.getInstance("gs://duan-lgfood1.appspot.com");
        //    Date hi???n t???i
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = new Date();
//        ImageView
        ThemSuaXoaSanPham_img_btn_back = findViewById(R.id.themSuaXoaSanPham_img_btn_backs);
        ThemSuaXoaSanPham_img_showImgV = findViewById(R.id.themSuaXoaSanPham_img_showImgV);
        ThemSuaXoaSanPham_img_btn_lich = findViewById(R.id.themSuaXoaSanPham_img_btn_lich);
//        TextView
        ThemSuaXoaSanPham_tv_btn_moThuMucAnh = findViewById(R.id.themSuaXoaSanPham_tv_btn_moThuMucAnh);

//      LinearLayout
        ThemSuaXoaSanPham_llout_btn_themSanPham = findViewById(R.id.themSuaXoaSanPham_llout_btn_themSanPham);
        ThemSuaXoaSanPham_llout_btn_xoaSanPham = findViewById(R.id.themSuaXoaSanPham_llout_btn_xoaSanPham);
        ThemSuaXoaSanPham_llout_btn_suaSanPham = findViewById(R.id.themSuaXoaSanPham_llout_btn_suaSanPham);


//         TextInputLayout
        ThemSuaXoaSanPham_edt_tenSanPham = findViewById(R.id.themSuaXoaSanPham_edt_tenSanPham);
        ThemSuaXoaSanPham_edt_giaNhap = findViewById(R.id.themSuaXoaSanPham_edt_giaNhap);
        ThemSuaXoaSanPham_edt_giaBan = findViewById(R.id.themSuaXoaSanPham_edt_giaBan);
        ThemSuaXoaSanPham_edt_giamGia = findViewById(R.id.themSuaXoaSanPham_edt_giamGia);
        ThemSuaXoaSanPham_edt_soLuong = findViewById(R.id.themSuaXoaSanPham_edt_soLuong);
        ThemSuaXoaSanPham_edt_NSX = findViewById(R.id.themSuaXoaSanPham_edt_NSX);
        ThemSuaXoaSanPham_edt_xuatSu = findViewById(R.id.themSuaXoaSanPham_edt_xuatSu);
//       Spinner
        ThemSuaXoaSanPham_spn_LoaiSP = findViewById(R.id.themSuaXoaSanPham_spn_LoaiSP);
        ThemSuaXoaSanPham_spn_TinhTrang = findViewById(R.id.themSuaXoaSanPham_spn_TinhTrang);
//        EditText
        ThemSuaXoaSanPham_edt_moTaSanPham = findViewById(R.id.themSuaXoaSanPham_edt_moTaSanPham);
//        setDate
        ThemSuaXoaSanPham_edt_NSX.setText(dateFormat.format(date).toString());

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = month + "/" + dayOfMonth + "/" + year;

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            // Trung: set date cho editText
            ThemSuaXoaSanPham_edt_NSX.setText(date);
        }}
}