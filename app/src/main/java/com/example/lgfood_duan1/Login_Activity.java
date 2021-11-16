package com.example.lgfood_duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Login_Activity extends AppCompatActivity {

    private TextView
            Login_tv_btn_SignUp,
            Login_tv_btn_SignUpText,
            Login_tv_username,
            Login_tv_password,
            login_tv_Phone,
            login_tv_google;
    private EditText
            Login_edt_username,
            Login_edt_password;
    private LinearLayout
            Login_llout_btn_submid;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhXa();

        batSuKien();
    }




    private void batSuKien() {
//        chuyển sang trang đăng kí tài khoản
        Login_tv_btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, Sin_Up_Activity.class);
                startActivity(intent);
            }
        });
        Login_tv_btn_SignUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, Sin_Up_Activity.class);

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


        //        check tài khoản
        Login_llout_btn_submid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //      gọi hàm check tài khoản
//                checkValidateSet();
            }
        });
    }

    /********************Kiểm tra tài khoản
     * @return**********************/


    private void anhXa() {


//        TextView
        Login_tv_btn_SignUp = findViewById(R.id.login_tv_btn_SignUp);
        Login_tv_btn_SignUpText = findViewById(R.id.login_tv_btn_SignUpText);
        Login_tv_username = findViewById(R.id.login_tv_username);
        Login_tv_password = findViewById(R.id.login_tv_password);
        login_tv_google = findViewById(R.id.login_tv_google);
        login_tv_Phone= findViewById(R.id.login_tv_Phone);


//        EditText
        Login_edt_username = findViewById(R.id.login_edt_username);
        Login_edt_password = findViewById(R.id.login_edt_password);

//        LinearLayout
        Login_llout_btn_submid = findViewById(R.id.login_llout_btn_submid);
    }
}