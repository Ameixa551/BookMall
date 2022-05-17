package com.example.bookmall.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmall.SQLiteDB.MyDataBaseHelper;
import com.example.bookmall.R;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private TextView goRegister;
    private EditText username;
    private EditText userpassword;
    private MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login_login_btn);
        goRegister = findViewById(R.id.login_linkregister_txtview);
        username = findViewById(R.id.login_username_txt);
        userpassword = findViewById(R.id.login_password_txt);
        SQLiteDatabase db = myDataBaseHelper.getWritableDatabase();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString().trim();
                String password = userpassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    if (myDataBaseHelper.isUserExist(db, name, password)) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,MallActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "登录失败，请重试", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}