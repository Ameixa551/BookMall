package com.example.bookmall.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmall.R;
import com.example.bookmall.dao.UserMapper;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText userPassword;
    private final UserMapper userMapper = new UserMapper(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.login_login_btn);
        TextView goRegister = findViewById(R.id.login_linkregister_txtview);
        username = findViewById(R.id.login_username_txt);
        userPassword = findViewById(R.id.login_password_txt);
        SQLiteDatabase db = userMapper.getReadableDatabase();
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        login.setOnClickListener(view -> {
            String name = username.getText().toString().trim();
            String password = userPassword.getText().toString().trim();
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                if (userMapper.isUserExist(db, name, password)) {
                    // 将用户信息添加至SharedPreference
                    editor.putString("name", name);
                    editor.putString("pwd", password);
                    editor.apply();

                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MallActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败，请重试", Toast.LENGTH_SHORT).show();
                }

            }
        });

        goRegister.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }
}