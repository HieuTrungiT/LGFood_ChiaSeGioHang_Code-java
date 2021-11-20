package com.example.lgfood_duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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

import com.example.lgfood_duan1.Model.model_SanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputLayout;
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
    int loaiSanPhamit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham_vao_kho_hang);

        anhXa();
        batSuKien();
        setSpinner();


    }
// sửa sản phẩm
    private void suaSanPhamKho() {
        if (!checkValidate()){
            return;
        }
        myRef = database.getReference("khoHang");
        int loaiSanPhams = Integer.parseInt(loaiSanPham);
        myRef.child(idSanPhamit).child("tenSanPham").setValue(tenSanPham);
        myRef.child(idSanPhamit).child("giaNhapSanPham").setValue(giaNhapSanPham);
        myRef.child(idSanPhamit).child("giaBanSanPham").setValue(giaBanSanPham);
        myRef.child(idSanPhamit).child("giamGiaSanPham").setValue(giamGiaSanPham);
        myRef.child(idSanPhamit).child("soLuongSanPham").setValue(soLuongSanPham);
        myRef.child(idSanPhamit).child("ngaySanXuat").setValue(ngaySanXuatSanPham);
        myRef.child(idSanPhamit).child("loaiSanPham").setValue(loaiSanPham);
        myRef.child(idSanPhamit).child("xuatXu").setValue(xuatXuSanPham);

        Intent intent = new Intent(Them_San_Pham_Vao_Kho_Hang_Activity.this, khoHang_Activity.class);
        startActivity(intent);

    }
// sửa sản phẩm
    private void openDialogUpdate() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_cuttom_capnhatsanpham);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
//        sử lý khi nhấn ra ngoài thì có thoát hay không
        dialog.setCancelable(false);
//        khai báo bắt sự kiện
        TextView Dialog_them_tvBtn_khong = dialog.findViewById(R.id.dialog_them_tvBtn_khong);
        TextView Dialog_them_tvBtn_dongY = dialog.findViewById(R.id.dialog_them_tvBtn_dongY);

//        tắt dialog đi
        Dialog_them_tvBtn_khong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        Dialog_them_tvBtn_dongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (!(full == null)) {
//                    nếu như có ảnh được chọn
//                    luuAnhUptate();
                } else {
//                    nếu như không có ảnh được chọn
                    suaSanPhamKho();
                }

            }
        });
        dialog.show();
    }
//**** xóa sản phẩm
    private void xoaSanPhamKho() {
        myRef = database.getReference("khoHang");
        myRef.child(idSanPhamit).removeValue();
        Intent intent = new Intent(Them_San_Pham_Vao_Kho_Hang_Activity.this, khoHang_Activity.class);
        startActivity(intent);
    }


    private void openDialogDelete() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_cuttom_xoasanpham);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
//        sử lý khi nhấn ra ngoài thì có thoát hay không
        dialog.setCancelable(false);
//        khai báo bắt sự kiện
        TextView Dialog_them_tvBtn_khong = dialog.findViewById(R.id.dialog_them_tvBtn_khong);
        TextView Dialog_them_tvBtn_dongY = dialog.findViewById(R.id.dialog_them_tvBtn_dongY);

//        tắt dialog đi
        Dialog_them_tvBtn_khong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        Dialog_them_tvBtn_dongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                xoaSanPhamKho();
            }
        });
        dialog.show();
    }

    // Trung: mở thư viện ảnh
    private void moFileThuMucAnh() {
        anhXa();
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_IMAGE_OPEN);
    }

    // lấy ảnh trong thư viện và show anh lên imgview
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //  bắt sự kiến khi nhấn lưu ảnh thỉ gán vào imgview
        if (requestCode == REQUEST_IMAGE_OPEN && resultCode == RESULT_OK && data != null && data.getData() != null) {
            full = data.getData();
            ThemSuaXoaSanPham_img_showImgV.setImageURI(full);
            ThemSuaXoaSanPham_img_showImgV.setVisibility(View.VISIBLE);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    //    Trung: lưu sản phẩm lên firebase
    private void themDuLieuVaoFirebase() {
        getValue();
        if (checkValidate() == true) {
            storageRef = storage.getReference();
            mountainImagesRef = storageRef.child("images/sanPham/" + new Date().getTime() + ".jpg");
            uploadTask = mountainImagesRef.putFile(full);

            //  kiếm tra
            //  nếu lỗi file chạy vào đây
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(Them_San_Pham_Vao_Kho_Hang_Activity.this, "Lưu ảnh thất bại", Toast.LENGTH_SHORT).show();
                }
                //     add file thành công
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    //    khởi tạo link url
                    mountainImagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            // datetime hiện tại
                            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy 'lúc' HH:mm:ss z");
                            Date reaDate = new Date(System.currentTimeMillis());
//                        //      myRef chỉ con trỏ tại vị trí ""
                            myRef = database.getReference("khoHang");
//
//                        //      kiểm tra điều kiện nếu như có ảnh trên storage thì lưu có định dạng không thì rỗng

                            listSanPham = new model_SanPham(uuid.toString(), moTaSanPham,tenSanPham,ngaySanXuatSanPham,xuatXuSanPham,loaiSanPham,tinhTrangSanPham,uri.toString(),formatter.format(reaDate).toString(),soLuongSanPham,giamGiaSanPham,giaNhapSanPham,giaBanSanPham);
//
//                        //        add giá trị
                            myRef.child(listSanPham.getIdSanPham().toString()).setValue(listSanPham);
                            Toast.makeText(Them_San_Pham_Vao_Kho_Hang_Activity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });

        }
    }

    // Trung: Kiểu tra validate đầu vào
    private boolean checkValidate() {
//        ảnh sản phẩm
        if (!(full == null)) {
            ThemSuaXoaSanPham_img_showImgV.setBackgroundResource(R.drawable.broder_stroke_cam1);
//        tên sản phẩm
            if (!tenSanPham.equals("")) {
                setNullBackgourd(ThemSuaXoaSanPham_edt_tenSanPham);
                if (!(tenSanPham.length() < 5 || tenSanPham.length() > 50)) {
                    setNullBackgourd(ThemSuaXoaSanPham_edt_tenSanPham);
//                giá nhập sản phẩm
                    if (!(giaNhapSanPham < 1.000)) {
                        if (giaNhapSanPham == 1 || giaNhapSanPham == 2 || giaNhapSanPham == 3 || giaNhapSanPham == 4 || giaNhapSanPham == 5 || giaNhapSanPham == 6 || giaNhapSanPham == 7 || giaNhapSanPham == 8 || giaNhapSanPham == 8 || giaNhapSanPham == 9) {
                            ThemSuaXoaSanPham_edt_giaNhap.setText(giaNhapSanPham + "00");
                        }
                        ThemSuaXoaSanPham_edt_giaNhap.setText(giaNhapSanPham + "00");
                        setNullBackgourd(ThemSuaXoaSanPham_edt_giaNhap);
                        if (!(giaNhapSanPham >= 999.999)) {
//                        giá bán
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
                                                                ThemSuaXoaSanPham_edt_moTaSanPham.setError("Mô tả sản phẩm không được để trống!!");
                                                                ThemSuaXoaSanPham_edt_moTaSanPham.requestFocus();
                                                            }
                                                        } else {
                                                            ThemSuaXoaSanPham_spn_LoaiSP.setBackgroundResource(R.drawable.broder_stroke_red_error);
                                                        }
                                                    } else {
                                                        showError(ThemSuaXoaSanPham_edt_xuatSu, "Không được để trống xuất xứ sản phẩm!!!");
                                                    }
                                                } else {
                                                    showError(ThemSuaXoaSanPham_edt_soLuong, "Số lượng nhập về không được quá 10000SP");
                                                }
                                            } else {
                                                showError(ThemSuaXoaSanPham_edt_soLuong, "Số lượng nhập về không được nhỏ hơn 10SP");
                                            }
                                        } else {
                                            showError(ThemSuaXoaSanPham_edt_giamGia, "Giảm giá không được lớn hơn 90% giá trị");
                                        }
                                    } else {
                                        showError(ThemSuaXoaSanPham_edt_giaBan, "Giá bán sản phẩm không được lớn hơn 999.999đ");
                                    }
                                } else {
                                    showError(ThemSuaXoaSanPham_edt_giaBan, "Giá bán không được bỏ trống hoặc nhỏ hơn 1.000đ !!!");
                                }
                            } else {
                                showError(ThemSuaXoaSanPham_edt_giaBan, "Giá bán không nhỏ hơn hoặc bằng giá nhập !!!");
                            }
                        } else {
                            showError(ThemSuaXoaSanPham_edt_giaNhap, "Giá Nhập sản phẩm không được lớn hơn 999.999đ");
                        }
                    } else {
                        showError(ThemSuaXoaSanPham_edt_giaNhap, "Giá nhập không được bỏ trống hoặc nhỏ hơn 1.000đ !!!");
                    }
                } else {
                    showError(ThemSuaXoaSanPham_edt_tenSanPham, "Tên sản phẩm không được < 5 và > 50 kí tự!!!");
                }
            } else {
                showError(ThemSuaXoaSanPham_edt_tenSanPham, "Tên sản phẩm không được để trống!!!");
            }
        } else {
            ThemSuaXoaSanPham_img_showImgV.setBackgroundResource(R.drawable.broder_stroke_red_error);
            Toast.makeText(this, "Chưa có ảnh sản phẩm!!!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    //    Trung: tắt lỗi
    private void setNullBackgourd(EditText edt) {
        edt.setBackgroundResource(R.drawable.broder_stroke_black);
    }

    //    Trung: show lỗi
    public void showError(EditText filed, String text) {
        filed.setError(text);
        filed.requestFocus();
        filed.setBackgroundResource(R.drawable.broder_stroke_red_error);
    }

    // Trung: hiện dialog date
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    // Trung: hiện tùy chọn loại spinner
    private void setSpinner() {
//        set giá trị loại sản phẩm spinner
        listLSP = new ArrayList<>();
        listLSP.add("null");
        listLSP.add("Trà túi lọc");
        listLSP.add("Cà phê");
        listLSP.add("Trái cây xấy dẻo");
        listLSP.add("Hạt đặc sản");
        listLSP.add("Thảo mộc");
        ArrayAdapter loaiSanPhamSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listLSP);
        ThemSuaXoaSanPham_spn_LoaiSP.setAdapter(loaiSanPhamSpinnerAdapter);

//        set giá trị tình trạng loại sản phẩm spinner
        listTTSP = new ArrayList<>();
        listTTSP.add("0");
        listTTSP.add("1");
        listTTSP.add("2");
        ArrayAdapter tinhTrangSanPhamSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listTTSP);
        ThemSuaXoaSanPham_spn_TinhTrang.setAdapter(tinhTrangSanPhamSpinnerAdapter);
    }


    private void batSuKien() {
// bắt sự kiện xoa
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
        //  back về login
        ThemSuaXoaSanPham_img_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // thêm sản phẩm vào firebase
        ThemSuaXoaSanPham_llout_btn_themSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themDuLieuVaoFirebase();
            }
        });

        // mở dialog date
        ThemSuaXoaSanPham_img_btn_lich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

//        mở thư viện ảnh
        ThemSuaXoaSanPham_tv_btn_moThuMucAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moFileThuMucAnh();
            }
        });
    }

//  Trung: lấy giá  trị nhập vào
    private void getValue() {
//        random id
        uuid = UUID.randomUUID();
        moTaSanPham = ThemSuaXoaSanPham_edt_moTaSanPham.getText().toString();
        tenSanPham = ThemSuaXoaSanPham_edt_tenSanPham.getText().toString();
        try {
            giaNhapSanPham = Double.parseDouble(ThemSuaXoaSanPham_edt_giaNhap.getText().toString().trim());
            setNullBackgourd(ThemSuaXoaSanPham_edt_giaNhap);
        } catch (Exception e) {
            showError(ThemSuaXoaSanPham_edt_giaNhap, "Vui lòng kiểm tra lại định dạng 000.000đ");
            ThemSuaXoaSanPham_edt_giaNhap.setText("0");
        }
        try {
            giaBanSanPham = Double.parseDouble(ThemSuaXoaSanPham_edt_giaBan.getText().toString().trim());
            setNullBackgourd(ThemSuaXoaSanPham_edt_giaBan);
        } catch (Exception e) {
            showError(ThemSuaXoaSanPham_edt_giaBan, "Vui lòng kiểm tra lại định dạng 000.000đ");
            ThemSuaXoaSanPham_edt_giaBan.setText("0");
        }
        try {
            giamGiaSanPham = Integer.parseInt(ThemSuaXoaSanPham_edt_giamGia.getText().toString().trim());
            setNullBackgourd(ThemSuaXoaSanPham_edt_giamGia);
        } catch (Exception e) {
            showError(ThemSuaXoaSanPham_edt_giamGia, "không được bỏ trống ");
            ThemSuaXoaSanPham_edt_giamGia.setText("0");
        }
        try {
            soLuongSanPham = Integer.parseInt(ThemSuaXoaSanPham_edt_soLuong.getText().toString().trim());
            setNullBackgourd(ThemSuaXoaSanPham_edt_soLuong);
        } catch (Exception e) {
            showError(ThemSuaXoaSanPham_edt_soLuong, "Số lượng không được bỏ trống!!!");
            ThemSuaXoaSanPham_edt_soLuong.setText("0");
        }
        ngaySanXuatSanPham = ThemSuaXoaSanPham_edt_NSX.getText().toString().trim();
        xuatXuSanPham = ThemSuaXoaSanPham_edt_xuatSu.getText().toString().trim();
        loaiSanPham = ThemSuaXoaSanPham_spn_LoaiSP.getSelectedItem().toString();
        tinhTrangSanPham = ThemSuaXoaSanPham_spn_TinhTrang.getSelectedItem().toString();
    }

    private void anhXa() {
        //    Firebase
        database = FirebaseDatabase.getInstance("https://duan1-lgfood-default-rtdb.asia-southeast1.firebasedatabase.app/");
        //    FirebaseStorage
        storage = FirebaseStorage.getInstance("gs://duan1-lgfood.appspot.com");
        //    Date hiện tại
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = new Date();
//        ImageView
        ThemSuaXoaSanPham_img_btn_back = findViewById(R.id.themSuaXoaSanPham_img_btn_back);
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
        // Trung: set date cho editText
        ThemSuaXoaSanPham_edt_NSX.setText(date);
    }
}