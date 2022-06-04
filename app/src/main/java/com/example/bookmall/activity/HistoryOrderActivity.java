package com.example.bookmall.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.bookmall.R;
import com.example.bookmall.databinding.ActivityHistoryOrderBinding;
import com.example.bookmall.models.DisplayOrder;

public class HistoryOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);
        ActivityHistoryOrderBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_history_order);
        //返回按钮
        ImageView goBack = findViewById(R.id.history_order_back);
        goBack.setOnClickListener(view -> finish());

        DisplayOrder order = (DisplayOrder) getIntent().getSerializableExtra("order");
        binding.setOrder(order);
    }
}