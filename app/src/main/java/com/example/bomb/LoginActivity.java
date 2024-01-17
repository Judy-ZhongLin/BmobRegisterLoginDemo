package com.example.bomb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import database.User;



public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText ed_account;
    EditText ed_password;
    Button bt_smsVerify;
    Button bt_signin;
    TextView text_signup;
    TextView forget;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_account = findViewById(R.id.ed_account);
        ed_password = findViewById(R.id.ed_password);
        bt_smsVerify = findViewById(R.id.bt_smsVerify);
        forget = findViewById(R.id.bt_forget);

        bt_signin = findViewById(R.id.bt_signin);
        text_signup = findViewById(R.id.text_signup);
        bt_smsVerify.setOnClickListener(this);
        bt_signin.setOnClickListener(this);
        text_signup.setOnClickListener(this);
    }

    //验证码登录
    private void getSms(){
        Log.d("sms", "笑死，好难过，好想/(ㄒoㄒ)/~~");
        BmobSMS.requestSMSCode(ed_account.getText().toString().trim(), "", new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId, BmobException e) {
                if (e == null) {
                    Log.d("sms", "发送验证码成功"+ e.getErrorCode());
                    Toast.makeText(LoginActivity.this,"发送验证码成功，短信ID：" + smsId,Toast.LENGTH_LONG).show();
                } else {
                    Log.d("sms", "发送验证码失败"+ e.getErrorCode());
                    Toast.makeText(LoginActivity.this, "发送验证码失败：" + e.getErrorCode() + "-" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    /**
     * TODO 此API需要在用户已经注册并验证的前提下才能使用
     */
    private void loginBySms(){
        BmobUser.loginBySMSCode(ed_account.getText().toString().trim(), ed_password.getText().toString().trim(), new LogInListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    Toast.makeText(LoginActivity.this,"短信登录成功：" + bmobUser.getObjectId() + "-" + bmobUser.getUsername(), Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,"短信登录失败：" + e.getErrorCode() + "-" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bt_signin://登录
                login(v);           //改：密码登录或者短信验证码登录
                break;
            case R.id.bt_smsVerify://获取验证码
//                getSms();
                break;
            case R.id.text_signup:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
            case R.id.bt_forget:
                startActivity(new Intent(LoginActivity.this, FindPasswordActivity.class));


        }
    }

    /**
     * 账号密码登录
     */
    private void login(final View view) {
        final User user = new User();
        user.setUsername(ed_account.getText().toString().trim());
        user.setPassword(ed_password.getText().toString().trim());
        user.login(new SaveListener<User>() {

            @Override
            public void done(User bmobuser, BmobException e) {
                if (e == null) {
                    User user = BmobUser.getCurrentUser(User.class);
                    Snackbar.make(view, "登录成功，"+user.getUsername() , Snackbar.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Snackbar.make(view, "登录失败：" + e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

}
