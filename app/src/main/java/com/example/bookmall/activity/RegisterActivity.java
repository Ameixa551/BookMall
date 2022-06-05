package com.example.bookmall.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmall.MainActivity;
import com.example.bookmall.R;
import com.example.bookmall.dao.UserMapper;

public class RegisterActivity extends AppCompatActivity {
    private UserMapper userMapper = new UserMapper(RegisterActivity.this);
    private EditText username;
    private EditText userPassword;
    private Button register;
    private Button goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SQLiteDatabase db = userMapper.getWritableDatabase();
        register = findViewById(R.id.register_register_btn);
        goBack = findViewById(R.id.register_goBack_btn);
        username = findViewById(R.id.register_username_txt);
        userPassword = findViewById(R.id.register_password_txt);

        goBack.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        register.setOnClickListener(view -> {
            String name = username.getText().toString().trim();
            String password = userPassword.getText().toString().trim();
            if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
                userMapper.addUser(db,name,password);
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(RegisterActivity.this,"注册成功，请登录",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
            }
        });

    }
}