package com.example.bomb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bomb.R;
import com.example.bomb.RegisterActivity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MainActivity extends AppCompatActivity {
    Button bt_login;
    Button bt_Lost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bmob.initialize(this, "6f8a7eed76aa4d0f8dfd83cd746ba8a0");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bt_login = findViewById(R.id.bt_login);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });


        bt_Lost = findViewById(R.id.bt_lost);
        bt_Lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }
}