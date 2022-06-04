package com.example.bookmall.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmall.databinding.ItemBookBinding;
import com.example.bookmall.models.Book;
import com.example.bookmall.utils.ImageBase64;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> bookList;
    private final OnClickListener<Book> listener;

    public BookAdapter(List<Book> bookList, OnClickListener<Book> listener){
        this.bookList = bookList;
        this.listener = listener;
    }

    public void setBookList(List<Book> books){
        this.bookList = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookBinding binding = ItemBookBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Book book = bookList.get(position);
        holder.binding.setBook(book);
        holder.binding.itemBookImageView.setImageBitmap(ImageBase64.base64ToBitmap(book.getCoverPic()));
        holder.binding.setItemClickListener(click -> listener.onItemClick(book));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final ItemBookBinding binding;

        public ViewHolder(ItemBookBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
