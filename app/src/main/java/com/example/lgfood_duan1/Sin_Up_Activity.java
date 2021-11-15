package com.example.lgfood_duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Sin_Up_Activity extends AppCompatActivity {

    private TextView
            signUp_tv_btn_Login,
            signUp_tv_btn_loginText,
            Login_tv_username,
            Login_tv_password,
            login_tv_Phone,
            login_tv_google;
    private EditText
            Login_edt_username,
            Login_edt_password;
    private LinearLayout
            signUp_lv_btn_submid;
    private ImageView
            login_tv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_up);

        anhXa();

        batSuKien();
    }

    private void batSuKien() {
//        chuyển sang trang đăng kí tài khoản
        signUp_tv_btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sin_Up_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });
        signUp_tv_btn_loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sin_Up_Activity.this, Login_Activity.class);

                startActivity(intent);
            }
        });
        login_tv_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        login_tv_Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


//        back về login
        login_tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sin_Up_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });

        //        check tài khoản
        signUp_lv_btn_submid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //      gọi hàm check tài khoản
//                checkValidateSet();
            }
        });
    }

    private void anhXa() {


//        TextView
        signUp_tv_btn_Login= findViewById(R.id.signUp_tv_btn_Login);
        signUp_tv_btn_loginText = findViewById(R.id.signUp_tv_btn_loginText);
        Login_tv_username = findViewById(R.id.login_tv_username);
        Login_tv_password = findViewById(R.id.login_tv_password);
        login_tv_google = findViewById(R.id.login_tv_google);
        login_tv_Phone= findViewById(R.id.login_tv_Phone);


//        EditText
        Login_edt_username = findViewById(R.id.login_edt_username);
        Login_edt_password = findViewById(R.id.login_edt_password);

//        LinearLayout
        signUp_lv_btn_submid= findViewById(R.id.signUp_lv_btn_submid);

//        Imgview
        login_tv_back = findViewById(R.id.login_tv_back);
    }
}