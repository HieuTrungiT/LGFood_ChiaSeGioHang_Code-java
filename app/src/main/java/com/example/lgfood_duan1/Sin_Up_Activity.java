package com.example.lgfood_duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lgfood_duan1.Model.model_Account;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.UUID;

public class Sin_Up_Activity extends AppCompatActivity {

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
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    model_Account model_account;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_up);

        anhXa();
        processrequest();
        batSuKien();
    }
//khai báo client của google
    private void processrequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }
//khi ấn nút login sẽ chạy vào hàm này
    private void processLogin() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
//mở dialog tài khoản gg
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
//lay dữ liệu tài khoản
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            firebaseAuthWithGoogle(account.getIdToken());

        } catch (ApiException e) {
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
        }
    }
//xử lí đăng nhập
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user!=null){
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Sin_Up_Activity.this, "fail", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
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
                processLogin();
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
                checkValidateSet();
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
        else{
            database = FirebaseDatabase.getInstance("https://duan1-lgfood-default-rtdb.asia-southeast1.firebasedatabase.app/");
            //    FirebaseStorage
            node=database.getReference("Accounts");
            UUID uuid=UUID.randomUUID();
            model_account=new model_Account(uuid.toString(),userName,password,address,email,soDienThoai,uuid.toString(),"");
            node.child(model_account.getId()).setValue(model_account);
            Intent intent=new Intent(Sin_Up_Activity.this,Login_Activity.class);
            startActivity(intent);
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }
    }

    private void normalLogin() {

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