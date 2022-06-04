package com.example.bookmall.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmall.databinding.ItemHistoryOrderBinding;
import com.example.bookmall.models.DisplayOrder;
import com.example.bookmall.utils.ImageBase64;

import java.util.List;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderAdapter.ViewHolder> {

    private final List<DisplayOrder> orders;
    private final OnClickListener<DisplayOrder> listener;

    public HistoryOrderAdapter(List<DisplayOrder> orders, OnClickListener<DisplayOrder> listener) {
        this.orders = orders;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoryOrderBinding binding = ItemHistoryOrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DisplayOrder order = orders.get(position);
        holder.binding.setOrder(order);
        holder.binding.ivHistoryPic.setImageBitmap(ImageBase64.base64ToBitmap(order.getCoverPic()));
        holder.binding.setOnClickListener(click -> listener.onItemClick(order));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final ItemHistoryOrderBinding binding;

        public ViewHolder(ItemHistoryOrderBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
