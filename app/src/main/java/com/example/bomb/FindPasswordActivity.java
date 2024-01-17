package com.example.bomb;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import database.User;

public class FindPasswordActivity extends AppCompatActivity {
    EditText ed_email;
    EditText ed_password;
    Button bt_ok;
    List<User> mUserList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bmob.initialize(this, "6f8a7eed76aa4d0f8dfd83cd746ba8a0");
        setContentView(R.layout.activity_forget);
        ed_email = findViewById(R.id.forget1);
        ed_password = findViewById(R.id.forget2);
        bt_ok = findViewById(R.id.forget3);
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailVerify(v);
//                AlertDialog.Bulider alert = new AlertDialog.Builder(FindPasswordActivity.this);
                equal(v);
                if(mUserList.size() != 0){

                }
            }
        });
    }


    public void equal(final View view){
        BmobQuery<User> categoryBmobQuery = new BmobQuery<>();
        categoryBmobQuery.addWhereEqualTo("email", ed_email.getText().toString().trim());
        categoryBmobQuery.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> object, BmobException e) {
                if (e == null) {
                    mUserList=object;
                    Snackbar.make(view, "查询成功：" + object.size(), Snackbar.LENGTH_LONG).show();
                } else {
                    Log.e("BMOB", e.toString());
                    Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * 发送验证邮件
     */
    private void emailVerify(final View view) {
        //TODO 此处替换为你的邮箱
        BmobUser user=BmobUser.getCurrentUser();
        final String email = "email";
        BmobUser.requestEmailVerify(email, new UpdateListener() {

            @Override
            public void done(BmobException e) {
                View mIvAvatar = null;
                if (e == null) {
                    Snackbar.make(mIvAvatar, "请求验证邮件成功，请到" + email + "邮箱中进行激活账户。", Snackbar.LENGTH_LONG).show();
                } else {
                    Log.e("BMOB", e.toString());
                    Snackbar.make(mIvAvatar, e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}
