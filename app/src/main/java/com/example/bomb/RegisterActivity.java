package com.example.bomb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import database.User;


public class RegisterActivity extends AppCompatActivity {

    EditText ed_phone;
    EditText ed_password;
    EditText ed_email;
    FloatingActionButton fl_submit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ed_email =findViewById(R.id.ed_email);
        ed_password=findViewById(R.id.ed_password);
        ed_phone=findViewById(R.id.phone);
        ed_phone = findViewById(R.id.phone);
        fl_submit = findViewById(R.id.sign_up);
        fl_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(v);
            }
        });
    }

    /**
     * 账号密码注册
     */
    private void signUp(final View view) {
        final User user = new User();
        user.setUsername(ed_phone.getText().toString().trim());  ///
        user.setPassword(ed_password.getText().toString().trim());
        user.setAge(18);
        user.setGender("Man");
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    Snackbar.make(view, "注册成功", Snackbar.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                } else {
                    Log.v("Tag","尚未失败：" + e.getMessage());
                    Snackbar.make(view, "尚未失败：" + e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

}
