package com.example.bookmall.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.bookmall.R;
import com.example.bookmall.dao.OrderMapper;
import com.example.bookmall.databinding.ActivityDetailBinding;
import com.example.bookmall.models.Book;
import com.example.bookmall.models.Order;
import com.example.bookmall.utils.ImageBase64;

import java.util.Calendar;

public class DetailActivity extends AppCompatActivity {

    private final OrderMapper orderMapper = new OrderMapper(DetailActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        SQLiteDatabase db = orderMapper.getWritableDatabase();
        orderMapper.onCreate(db);

        Book book = (Book)getIntent().getSerializableExtra("book");
        binding.setBook(book);
        binding.detailBookImage.setImageBitmap(ImageBase64.base64ToBitmap(book.getCoverPic()));

        //返回按钮
        ImageView goBack = findViewById(R.id.detail_back);
        goBack.setOnClickListener(view -> finish());

        //加入购物车按钮
        Button addCart = findViewById(R.id.commodity_detail_btn_buy);
        addCart.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences("userInfo",
                    Context.MODE_PRIVATE);
            int uid = sharedPreferences.getInt("uid", -1);
            if(uid == -1){
                Toast.makeText(this, "请登录", Toast.LENGTH_SHORT).show();
            }else{
                Order existOrder = orderMapper.selectByBookId(db, uid, book.getId());
                // 购物车中已经存在该书
                if(existOrder != null){
                    existOrder.setBookNum(existOrder.getBookNum()+1);
                    existOrder.setSumPrice(existOrder.getBookNum() * book.getPrice());
                    orderMapper.updateBookNum(db, existOrder);
                } else {
                    // 购物车中不存在该书
                    existOrder = new Order(0, Calendar.getInstance().getTimeInMillis(),
                            0, false, book.getPrice(), book.getId(), 1, uid);
                    orderMapper.addOrder(db, existOrder);
                }
                Toast.makeText(this, "已加入购物车", Toast.LENGTH_SHORT).show();
            }
        });
    }
}