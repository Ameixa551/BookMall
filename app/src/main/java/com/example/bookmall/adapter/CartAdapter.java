package com.example.bookmall.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmall.databinding.ItemCartBinding;
import com.example.bookmall.models.DisplayOrder;
import com.example.bookmall.utils.ImageBase64;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<DisplayOrder> orders;
    private final OnClickListener<DisplayOrder> deleteListener;
    private final OnClickListener<DisplayOrder> checkedListener;

    public CartAdapter(List<DisplayOrder> orders, OnClickListener<DisplayOrder> deleteListener, OnClickListener<DisplayOrder> checkedListener) {
        this.orders = orders;
        this.deleteListener = deleteListener;
        this.checkedListener = checkedListener;
    }

    public void setOrders(List<DisplayOrder> orders){
        this.orders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartBinding binding = ItemCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DisplayOrder order = orders.get(position);
        holder.binding.setOrder(order);
        holder.binding.ivShowPic.setImageBitmap(ImageBase64.base64ToBitmap(order.getCoverPic()));
        holder.binding.setDeleteClickListener(click -> deleteListener.onItemClick(order));
        holder.binding.setCheckedClickListener(click -> checkedListener.onItemClick(order));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final ItemCartBinding binding;

        public ViewHolder(ItemCartBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
