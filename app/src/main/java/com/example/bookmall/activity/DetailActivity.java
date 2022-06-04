package com.example.bookmall.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.bookmall.R;
import com.example.bookmall.databinding.ActivityDetailBinding;
import com.example.bookmall.models.Book;
import com.example.bookmall.utils.ImageBase64;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        Book book = (Book)getIntent().getSerializableExtra("book");
        binding.setBook(book);
        binding.detailBookImage.setImageBitmap(ImageBase64.base64ToBitmap(book.getCoverPic()));

        //返回按钮
        ImageView goBack = findViewById(R.id.detail_back);
        goBack.setOnClickListener(view -> finish());

        //加入购物车按钮
        Button addCart = findViewById(R.id.commodity_detail_btn_buy);
        addCart.setOnClickListener(view -> {
            Toast.makeText(this, "加入购物车", Toast.LENGTH_SHORT).show();
        });
    }
}