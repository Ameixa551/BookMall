package com.example.bookmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmall.databinding.ItemCategoryBinding;
import com.example.bookmall.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final List<Category> mCategoryList;
    private final OnItemClickListener<Category> listener;
    private final LayoutInflater layoutInflater;


    public CategoryAdapter(Context context, List<Category> categoryList, OnItemClickListener<Category> listener){
        this.layoutInflater = LayoutInflater.from(context);
        this.mCategoryList = categoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding binding = ItemCategoryBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Category category = mCategoryList.get(position);
        holder.binding.setCategory(category);
        holder.binding.setItemClickListener(click -> listener.onItemClick(category));
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemCategoryBinding binding;

        public ViewHolder(ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
