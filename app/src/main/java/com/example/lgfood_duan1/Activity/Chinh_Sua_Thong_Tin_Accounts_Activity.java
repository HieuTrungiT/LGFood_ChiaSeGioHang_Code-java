package com.example.lgfood_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lgfood_duan1.Model.model_Account;
import com.example.lgfood_duan1.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.UUID;

public class Chinh_Sua_Thong_Tin_Accounts_Activity extends AppCompatActivity {


    private TextView
            SignUp_tv_btn_Login,
            SignUp_tv_btn_loginText,

    Login_tv_Phone,
            Login_tv_google;
    private EditText
            SignUp_edt_tenDangNhap,
            SignUp_edt_gmail,
            SignUp_edt_MatKhau,
            SignUp_edt_NhapLaiMatKhau,
            SignUp_edt_DiaChi,
            SignUp_edt_SDT;
    private LinearLayout
            SignUp_lv_btn_submid;
    private ImageView
            Login_tv_back,
            SingUp_Im_ViTri;
    //Login with google
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    //Login normal
    String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9])|(9[0-46-9]))(\\d)(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})?(\\d)$";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";    DatabaseReference node;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseStorage storage;
    private UploadTask uploadTask;
    private StorageReference mountainImagesRef, storageRef;
    private UUID uuid;
    private model_Account listAccount;
    private ArrayList<model_Account> arrListAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_thong_tin_account);
        anhXa();
        batSuKien();
    }

    private void batSuKien() {
//        back về 1 trang
        Login_tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chinh_Sua_Thong_Tin_Accounts_Activity.this, thongTinTaiKhoan_Activity.class);
                startActivity(intent);
            }
        });
    }


    private void firebaseData () {

        //         Gán giá trị trong firebase
        database = FirebaseDatabase.getInstance("https://duan1-lgfood-default-rtdb.asia-southeast1.firebasedatabase.app/");
        myRef = database.getReference().child("Account").child("");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrListAccount.clear();
                for (DataSnapshot child : snapshot.getChildren()) {
                    // gọi dữ liệu trên firebase về
                    listAccount = child.getValue(model_Account.class);

                    // add vào listItemSanpham
                    if (listAccount.getId() != null) {
                        arrListAccount.add(listAccount);

                    }
                }
//                checkTaiKhoanTrungLap();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

    }
    private void checkValidateSet() {

        String userName=SignUp_edt_tenDangNhap.getText().toString().trim();
        String email=SignUp_edt_gmail.getText().toString().trim();
        String password=SignUp_edt_MatKhau.getText().toString().trim();
        String repeatPassword=SignUp_edt_NhapLaiMatKhau.getText().toString().trim();
        String address=SignUp_edt_DiaChi.getText().toString().trim();
        String soDienThoai=SignUp_edt_SDT.getText().toString().trim();

        if (userName.length()<6 || userName.length()>50)
        {
            SignUp_edt_tenDangNhap.setError("Tên đăng nhập gồm 6 - 50 kí tự ");
        }else
        if (!email.matches(emailPattern))
        {
            SignUp_edt_gmail.setError("Sai định dạng Email");
        }else if (password.isEmpty() || password.length()<6)
        {
            SignUp_edt_MatKhau.setError("Mật khẩu đang trống hoặc bé hơn 6 kí tự");
        }else if (!password.equals(repeatPassword))
        {
            SignUp_edt_NhapLaiMatKhau.setError("Mật khẩu không khớp");
        }else if (!(soDienThoai.length() ==10 || !soDienThoai.matches(reg))){
            SignUp_edt_SDT.setError("Nhập sai định dạng số điện thoại");

        }else if (address.length()<6 || address.length()>150){
            SignUp_edt_DiaChi.setError("Địa Chỉ lớn hơn 6 và  không quá 100 kí tự");
        }

    }

    private boolean checkTaiKhoanTrungLap() {
        boolean check = false;

        for (int i = 0; i < arrListAccount.size(); i = i + 1) {
            String userNameFirebase = arrListAccount.get(i).getEmail();
            if (SignUp_edt_gmail.equals(userNameFirebase)) {
                check = true;
            }
//            if (SignUp_edt_gmail.equals(userNameShr)) {
//                check = false;
//            }
        }

        return check;

    }
    private void anhXa() {


//        TextView
        SignUp_tv_btn_Login= findViewById(R.id.signUp_tv_btn_Login);
        SignUp_tv_btn_loginText = findViewById(R.id.signUp_tv_btn_loginText);

        Login_tv_google = findViewById(R.id.login_tv_google);
        Login_tv_Phone= findViewById(R.id.login_tv_Phone);


//        EditText
        SignUp_edt_tenDangNhap = findViewById(R.id.signUp_edt_TenDangNhapKH);
        SignUp_edt_gmail = findViewById(R.id.signUp_edt_gmailKH);
        SignUp_edt_MatKhau = findViewById(R.id.signUp_edt_matKhauKH);
        SignUp_edt_NhapLaiMatKhau=  findViewById(R.id.signUp_edt_repeatMatKhau);
        SignUp_edt_DiaChi = findViewById(R.id.signUp_edt_DiaChiKhachHang);
        SignUp_edt_SDT = findViewById(R.id.signUp_edt_phoneNumber);

//        LinearLayout
        SignUp_lv_btn_submid= findViewById(R.id.signUp_lv_btn_submid);

//        Imgview
        Login_tv_back = findViewById(R.id.thongTinTaiKhoan_img_btn_thoat);
        SingUp_Im_ViTri = findViewById(R.id.singUp_Im_ViTri);
//        Firebase auth
        mAuth = FirebaseAuth.getInstance();
//        Firebase realtime
        database.getInstance("https://duan1-lgfood-default-rtdb.asia-southeast1.firebasedatabase.app/");
    }

}