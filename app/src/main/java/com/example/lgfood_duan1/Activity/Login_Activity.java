package com.example.lgfood_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lgfood_duan1.Model.model_Account;
import com.example.lgfood_duan1.Model.model_SanPham;
import com.example.lgfood_duan1.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    private SharedPreferences shareAcout;

    String idSharePre,passSharePre,userSharePre,idShareGioHang,idGioHangTam;
    boolean rememberSharePre;


//thai: login
    DatabaseReference mData;
    FirebaseDatabase database;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhXa();
        checkSavePass();
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
                loginNormal();

            }
        });
    }


    private void checkSavePass(){
        shareAcout = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = shareAcout.edit();
        rememberSharePre = shareAcout.getBoolean("REMEMBER",false);
        if(rememberSharePre == true){
            Intent intent= new Intent(Login_Activity.this,trangChu_SanPham_Activity.class);
            startActivity(intent);

        }else{
            editor.clear();
            editor.commit();

        }

    }


    //thai sharePreference

    private void rememberUser(String idUser,String idGioHang,String user,String password,boolean status,String viTri,String idViTri,String idGioHangTam,String nameUser,String anhUser,String idDanhSachYeuThich){

        SharedPreferences pref=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        if (!status){
            editor.putString("USERNAME",user);
            editor.putString("PASSWORD",password);
            editor.putString("IDUSRE",idUser);
            editor.putString("IDGIOHANG",idGioHang);
            editor.putString("VITRI",viTri);
            editor.putString("IDVITRI",idViTri);
            editor.putString("IDGIOHANGTAM",idGioHangTam);
            editor.putString("NAMEUSER",nameUser);
            editor.putString("ANHUSER",anhUser);
            editor.putString("IDDANHSACHYEUTHICH",idDanhSachYeuThich);
        }else {
            editor.putString("USERNAME",user);
            editor.putString("PASSWORD",password);
            editor.putBoolean("REMEMBER",status);
            editor.putString("IDUSRE",idUser);
            editor.putString("IDGIOHANG",idGioHang);
            editor.putString("VITRI",viTri);
            editor.putString("IDVITRI",idViTri);
            editor.putString("IDGIOHANGTAM",idGioHangTam);
            editor.putString("NAMEUSER",nameUser);
            editor.putString("ANHUSER",anhUser);
            editor.putString("IDDANHSACHYEUTHICH",idDanhSachYeuThich);


        }
        editor.commit();
    }
//thai login
    private void loginNormal() {
        String userName=Login_edt_username.getText().toString().trim();
        String password=Login_edt_password.getText().toString().trim();
        if (userName.isEmpty()){
            Login_edt_username.setError("Tên đăng nhập trống!");
        }else if (password.isEmpty()) {
            Login_edt_password.setError("Mật khẩu đang trống!");
        }else{
            mData= database
                .getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Accounts");
            mData.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds: snapshot.getChildren()){
                        model_Account account=ds.getValue(model_Account.class);

                            if (userName.matches(account.getName()+"") && password.matches(account.getPassword()+"")){
                                Toast.makeText(Login_Activity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Login_Activity.this,trangChu_SanPham_Activity.class);
                                startActivity(intent);

                        
                                rememberUser(account.getId(),account.getIdGioHang(),userName,password,checkBox.isChecked(),account.getAddress(),account.getIdViTri(),account.getIdGioHangTam(),account.getRealName(),account.getAnhKhachHang(),account.getIdDanhSachYeuThich());

                                return;
                            }else{
                                Toast.makeText(Login_Activity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }
//                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
                            }

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
        checkBox=findViewById(R.id.login_checkBox);
//        LinearLayout
        Login_llout_btn_submid = findViewById(R.id.login_llout_btn_submid);
        //sharedPreference
        SharedPreferences pref=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        Login_edt_username.setText(pref.getString("USERNAME",""));
        Login_edt_password.setText(pref.getString("PASSWORD",""));
        checkBox.setChecked(pref.getBoolean("REMEMBER",false));
     }
}