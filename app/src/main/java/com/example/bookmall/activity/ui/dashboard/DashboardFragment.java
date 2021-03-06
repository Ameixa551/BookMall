package com.example.bookmall.activity.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookmall.activity.MallViewModel;
import com.example.bookmall.activity.PaySuccessActivity;
import com.example.bookmall.adapter.CartAdapter;
import com.example.bookmall.databinding.FragmentDashboardBinding;

import java.util.Objects;

public class DashboardFragment extends Fragment implements PayAttentionFragment.PayAttentionDialogListener{

    private FragmentDashboardBinding binding;
    private CartAdapter cartAdapter;
    private MallViewModel mallViewModel;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        mallViewModel = new ViewModelProvider(requireActivity()).get(MallViewModel.class);
        dashboardViewModel.addContext(getContext());
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        dashboardViewModel.addUid(Objects.requireNonNull(mallViewModel.getUserInfo().getValue()).getId());

        cartAdapter = new CartAdapter(dashboardViewModel.getOrderList().getValue(), item -> {
            // 删除
            dashboardViewModel.deleteOrder(item);
            Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
        }, item -> {
            // 多选
            dashboardViewModel.updateItemChecked(item);
        });
        binding.listShoppingCart.setAdapter(cartAdapter);
        binding.listShoppingCart.setLayoutManager(new LinearLayoutManager(getContext()));

        dashboardViewModel.getOrderList().observe(getViewLifecycleOwner(), displayOrders -> {
            cartAdapter.setOrders(displayOrders);
            cartAdapter.notifyDataSetChanged();
            dashboardViewModel.getTotalCount();
        });

        binding.setPayClickListener(view -> {
            if(dashboardViewModel.getSelectCount().getValue() != 0){
                PayAttentionFragment payAttentionFragment = new PayAttentionFragment(this);
                payAttentionFragment.show(this.getChildFragmentManager(), "dialog");
            }else {
                Toast.makeText(getContext(), "请先选择书籍", Toast.LENGTH_SHORT).show();
            }
        });

        dashboardViewModel.getDisplayOrderList();
        dashboardViewModel.getTotalCount();
        binding.setViewModel(dashboardViewModel);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        dashboardViewModel.destroy();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        dashboardViewModel.paySelected();
        Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
        //跳转界面
        Intent intent = new Intent(getContext(), PaySuccessActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(getContext(), "取消支付", Toast.LENGTH_SHORT).show();
    }
}