package com.example.bookmall.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmall.databinding.ItemCartBinding;
import com.example.bookmall.models.Book;
import com.example.bookmall.models.Order;
import com.example.bookmall.utils.ImageBase64;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<Order> orders;
    private List<Book> books;
    private final OnClickListener<Order> listener;

    public CartAdapter(List<Order> orders, List<Book> books, OnClickListener<Order> listener) {
        this.orders = orders;
        this.books = books;
        this.listener = listener;
    }

    public void setOrders(List<Order> orders){
        this.orders = orders;
    }

    public void setBooks(List<Book> books){
        this.books = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartBinding binding = ItemCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Order order = orders.get(position);
        final Book book = books.get(position);
        holder.binding.setBook(book);
        holder.binding.setOrder(order);
        holder.binding.ivShowPic.setImageBitmap(ImageBase64.base64ToBitmap(book.getCoverPic()));
        holder.binding.setDeleteClickListener(click -> listener.onItemClick(order));
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
