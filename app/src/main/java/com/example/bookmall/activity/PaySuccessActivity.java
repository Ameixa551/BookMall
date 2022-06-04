package com.example.bookmall.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmall.R;

public class PaySuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);

        Button goBack = findViewById(R.id.pay_goBack_btn);
        goBack.setOnClickListener(view -> finish());
    }
}