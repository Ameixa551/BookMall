package com.example.bookmall.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.bookmall.R;

public class HistoryOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);

        //返回按钮
        ImageView goBack = findViewById(R.id.history_order_back);
        goBack.setOnClickListener(view -> finish());
    }
}