package com.example.bookmall.activity.ui.notifications;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookmall.activity.DetailActivity;
import com.example.bookmall.activity.MallViewModel;
import com.example.bookmall.adapter.HistoryOrderAdapter;
import com.example.bookmall.dao.BookMapper;
import com.example.bookmall.dao.OrderMapper;
import com.example.bookmall.databinding.FragmentNotificationsBinding;
import com.example.bookmall.models.DisplayOrder;
import com.example.bookmall.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private OrderMapper orderMapper;
    private BookMapper bookMapper;
    private MallViewModel mallViewModel;
    private HistoryOrderAdapter historyOrderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        mallViewModel = new ViewModelProvider(requireActivity()).get(MallViewModel.class);
        orderMapper = new OrderMapper(getContext());
        bookMapper = new BookMapper(getContext());
        SQLiteDatabase orderDB = orderMapper.getReadableDatabase();
        SQLiteDatabase bookDB = bookMapper.getReadableDatabase();

        List<Order> orderList = orderMapper.selectPaidOrder(orderDB, Objects.requireNonNull(mallViewModel.getUserInfo().getValue()).getId());
        List<DisplayOrder> displayOrders = new ArrayList<>();
        for(Order o: orderList){
            displayOrders.add(new DisplayOrder(bookMapper.selectById(bookDB, o.getBookId()), o));
        }

        historyOrderAdapter = new HistoryOrderAdapter(displayOrders, item -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("book", item.getBook());
            startActivity(intent);
        });
        binding.listShoppingCart.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.listShoppingCart.setAdapter(historyOrderAdapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}