package com.example.lgfood_duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Sin_Up_Activity extends AppCompatActivity {

    private TextView
            SignUp_tv_btn_Login,
            SignUp_tv_btn_loginText,

            Login_tv_Phone,
            Login_tv_google;
    private TextInputLayout
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_up);

        anhXa();

        batSuKien();
    }

    private void batSuKien() {
//        chuyển sang trang đăng kí tài khoản
        SignUp_tv_btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sin_Up_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });
        SignUp_tv_btn_loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sin_Up_Activity.this, Login_Activity.class);

                startActivity(intent);
            }
        });
        Login_tv_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Login_tv_Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


//        back về login
        Login_tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sin_Up_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });

        //        check tài khoản
        SignUp_lv_btn_submid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //      gọi hàm check tài khoản
//                checkValidateSet();
            }
        });
    }

    private void anhXa() {


//        TextView
        SignUp_tv_btn_Login= findViewById(R.id.signUp_tv_btn_Login);
        SignUp_tv_btn_loginText = findViewById(R.id.signUp_tv_btn_loginText);

        Login_tv_google = findViewById(R.id.login_tv_google);
        Login_tv_Phone= findViewById(R.id.login_tv_Phone);


//        EditText
        SignUp_edt_tenDangNhap = findViewById(R.id.signUp_edt_tenDangNhap);
        SignUp_edt_gmail = findViewById(R.id.signUp_edt_gmail);
        SignUp_edt_MatKhau = findViewById(R.id.signUp_edt_MatKhau);
        SignUp_edt_NhapLaiMatKhau=  findViewById(R.id.signUp_edt_NhapLaiMatKhau);
        SignUp_edt_DiaChi = findViewById(R.id.signUp_edt_DiaChi);
        SignUp_edt_SDT = findViewById(R.id.signUp_edt_SDT);

//        LinearLayout
        SignUp_lv_btn_submid= findViewById(R.id.signUp_lv_btn_submid);

//        Imgview
        Login_tv_back = findViewById(R.id.login_tv_back);
        SingUp_Im_ViTri = findViewById(R.id.singUp_Im_ViTri);
    }
}