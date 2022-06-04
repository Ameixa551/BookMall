package com.example.bookmall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookmall.activity.LoginActivity;
import com.example.bookmall.activity.MallActivity;
import com.example.bookmall.activity.RegisterActivity;
import com.example.bookmall.dao.UserMapper;
import com.example.bookmall.models.User;

public class MainActivity extends AppCompatActivity {
    private final UserMapper userMapper = new UserMapper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login = findViewById(R.id.main_login);
        Button register = findViewById(R.id.main_register);
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo",
                Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", null);
        String pwd = sharedPreferences.getString("pwd", null);

        SQLiteDatabase db = userMapper.getWritableDatabase();
        userMapper.onCreate(db);

        // 如果不为空，则直接验证登录
        if (name != null && pwd != null){
            User user = userMapper.isUserExist(db, name, pwd);
            if (user != null){
                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MallActivity.class);
                startActivity(intent);
                finish();
            }
        }

        register.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        login.setOnClickListener(view -> {
            Intent intent = new Intent (MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}