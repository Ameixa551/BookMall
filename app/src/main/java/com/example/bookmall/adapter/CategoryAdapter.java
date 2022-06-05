package com.example.bookmall.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmall.databinding.ItemCategoryBinding;
import com.example.bookmall.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final List<Category> mCategoryList;
    private final OnClickListener<Category> listener;

    public CategoryAdapter(List<Category> categoryList, OnClickListener<Category> listener){
        this.mCategoryList = categoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Category category = mCategoryList.get(position);
        holder.binding.setCategory(category);
        holder.binding.setOnItemClick(view -> listener.onItemClick(category));
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
