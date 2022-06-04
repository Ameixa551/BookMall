package com.example.bookmall.activity.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookmall.adapter.CartAdapter;
import com.example.bookmall.dao.BookMapper;
import com.example.bookmall.dao.OrderMapper;
import com.example.bookmall.databinding.FragmentDashboardBinding;
import com.example.bookmall.models.Book;
import com.example.bookmall.models.Order;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private CartAdapter cartAdapter;
    private OrderMapper orderMapper;
    private BookMapper bookMapper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        orderMapper = new OrderMapper(getContext());
        bookMapper = new BookMapper(getContext());
        SQLiteDatabase orderDB = orderMapper.getWritableDatabase();
        SQLiteDatabase bookDB = bookMapper.getReadableDatabase();
        SharedPreferences sharedPreferences = container.getContext().getSharedPreferences("userInfo",
                Context.MODE_PRIVATE);
        int uid = sharedPreferences.getInt("uid", -1);

        List<Order> orderList = orderMapper.selectCartOrder(orderDB, uid);
        List<Book> bookList = new ArrayList<>();
        for(Order o: orderList){
            bookList.add(bookMapper.selectById(bookDB, o.getBookId()));
        }
        cartAdapter = new CartAdapter(orderList, bookList, item -> {
            // 删除
            orderMapper.deleteOrder(orderDB, item);
            Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
        });
        binding.listShoppingCart.setAdapter(cartAdapter);
        binding.listShoppingCart.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}