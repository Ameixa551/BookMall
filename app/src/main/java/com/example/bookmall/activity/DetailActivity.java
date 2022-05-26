package com.example.bookmall.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmall.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //返回按钮
        ImageView goBack = findViewById(R.id.detail_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //加入购物车按钮
        Button addCart = findViewById(R.id.commodity_detail_btn_buy);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //书籍大图
        ImageView bookImage = findViewById(R.id.detail_book_image);
        //书籍名称
        TextView bookName = findViewById(R.id.detail_name);
        //价格
        TextView bookPrice = findViewById(R.id.detail_price);
        //作者
        TextView bookWriter = findViewById(R.id.detail_writer);
        //书号
        TextView bookNumber = findViewById(R.id.detail_book_number);
        //书本简介
        TextView bookIntro = findViewById(R.id.detail_introduction);


    }
}